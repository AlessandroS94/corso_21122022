package com.diemme.presentation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.diemme.exception.BusinessException;
import com.diemme.business.interfaces.EmailService;
import com.diemme.business.interfaces.LayoutService;
import com.diemme.business.interfaces.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.Layout;
import com.diemme.domain.mysql.Role;
import com.diemme.domain.mysql.StatusType;
import com.diemme.domain.mysql.User;
import com.diemme.wrapperForm.FormWrapperLayout;

@Controller
public class LayoutController {

	@Autowired
	private LayoutService serviceLayout;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private EmailService emailService;
	@Autowired
	private PageModel pageModel;

	@SuppressWarnings("static-access")
	@GetMapping("/layoutGestione")
	public String manageMyLayouts(Model model, Authentication auth) throws BusinessException {

		User userAuth = new User();
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		pageModel.initPageAndSize();
		pageModel.setSIZE(5);
		Page<Layout> layouts = serviceLayout.getLayoutsByUserId(userAuth.getId(), pageModel.getPAGE(),
				pageModel.getSIZE());
		model.addAttribute("layouts", layouts);
		pageModel.resetPAGE();
		return "/backoffice/layoutDashboard/manage.html";

	}

	@SuppressWarnings("static-access")
	@GetMapping("/layoutProduzioneGestione")
	public String manageLayoutsByStatus(Model model) throws BusinessException {

		pageModel.initPageAndSize();
		pageModel.setSIZE(5);
		Page<Layout> layouts = serviceLayout.getLayoutsByStatus(StatusType.TRANSFERRED_TO_PRODUCTION,
				pageModel.getPAGE(), pageModel.getSIZE());
		model.addAttribute("layouts", layouts);

		pageModel.resetPAGE();
		return "/backoffice/factoryDashboard/manage.html";

	}

	@SuppressWarnings("static-access")
	@GetMapping("/layoutClientGestione")
	public String manageMyLayoutsByStatus(Model model, Authentication auth) throws BusinessException {

		User userAuth = new User();
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		pageModel.initPageAndSize();
		pageModel.setSIZE(5);
		Page<Layout> layouts = serviceLayout.getMyLayoutsByStatus(userAuth.getId(), StatusType.SEND,
				pageModel.getPAGE(), pageModel.getSIZE());
		model.addAttribute("layouts", layouts);
		pageModel.resetPAGE();
		return "/backoffice/layoutDashboard/manageOrdini.html";

	}

	@GetMapping("/layoutCrea")
	public String createLayout(Model model) throws BusinessException {

		FormWrapperLayout layoutWrapper = new FormWrapperLayout();
		Set<User> userClients = new HashSet<User>();

		try {
			userClients = serviceUser.getUsersByRole("CLIENT");
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		model.addAttribute("userClients", userClients);
		model.addAttribute("layoutWrapper", layoutWrapper);
		return "/backoffice/layoutDashboard/create.html";
	}

	@PostMapping("/layoutCrea")
	public ModelAndView createLayout(@Valid @ModelAttribute("layoutWrapper") FormWrapperLayout layoutWrapper,
			Errors errors, @RequestParam(value = "contentImg") List<MultipartFile> contentImg, Authentication auth)
			throws BusinessException {

		User userAuth = new User();
		User userClient = new User();
		ModelAndView modelAndView = new ModelAndView();
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);
			serviceLayout.createLayout(layoutWrapper, contentImg, userAuth);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return new ModelAndView("/error/error.html");
		}

		modelAndView.addObject("userClient", userClient);
		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
		modelAndView.setViewName("redirect:/layoutGestione");
		return modelAndView;
	}

	@PostMapping("/layoutDelete/{id}")
	public String deletelayout(@PathVariable(value = "id") Long id) throws BusinessException {

		try {
			serviceLayout.deleteLayout(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		return "redirect:/layoutGestione";

	}

	@GetMapping("/layoutUpdate")
	public String updateLayout(Long id, Model model) throws BusinessException {

		Layout layout = new Layout();
		try {
			layout = serviceLayout.getLayout(id);
		} catch (BusinessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		model.addAttribute("StatusType", StatusType.values());
		model.addAttribute("layout", layout);
		return "/backoffice/layoutDashboard/update.html";
	}

	@PostMapping("/layoutUpdate/{id}")
	public String updateLayout(@PathVariable("id") Long id, @Valid @ModelAttribute("layout") Layout layout,
			Errors errors, @RequestParam("contentImg") List<MultipartFile> contentImg, Authentication auth)
			throws BusinessException {

		User userAuth = new User();
		Layout layoutSave = new Layout();
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);
			layoutSave = serviceLayout.updateLayout(id, layout, contentImg, userAuth);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		if (layoutSave.getStatus().equals(StatusType.TRANSFERRED_TO_PRODUCTION)) {
			emailService.sendNotifyNewOrder(userAuth.getEmail(), userAuth.getName(), layoutSave.getName());
		}

		return "redirect:/layoutGestione";
	}

	@GetMapping("/layoutUpdateProductor")
	public String updateProductorLayout(Long id, Model model) throws BusinessException {

		Layout layout = new Layout();
		try {
			layout = serviceLayout.getLayout(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		model.addAttribute("StatusType", StatusType.values());
		model.addAttribute("layout", layout);
		return "/backoffice/factoryDashboard/update.html";
	}

	@SuppressWarnings("unlikely-arg-type")
	@PostMapping("/layoutUpdateProductor/{id}")
	public String updateProductorLayout(@PathVariable("id") Long id, @Valid @ModelAttribute("layout") Layout layout,
			Errors errors, Authentication auth) throws BusinessException {

		User userAuth = new User();
		User client = new User();
		Layout layoutSave = new Layout();
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);
			layoutSave = serviceLayout.updateProductorLayout(id, layout, userAuth);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		for (User user : layoutSave.getUsers()) {
			for (Role role : user.getRoles()) {
				if (role.equals("CLIENT")) {
					client = user;
				}
			}
		}

		if (layoutSave.getStatus().equals(StatusType.SEND)) {
			emailService.sendNotifyOrderShip(userAuth.getEmail(), userAuth.getName(), layoutSave.getName(),
					client.getAddressShipment());
		}

		return "redirect:/layoutProduzioneGestione";
	}

	@GetMapping("/layoutUpdateClient")
	public String updateClientLayout(Long id, Model model) throws BusinessException {

		Layout layout = new Layout();
		try {
			layout = serviceLayout.getLayout(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		model.addAttribute("layout", layout);
		return "/backoffice/layoutDashboard/updateOrdini.html";
	}

	@PostMapping("/layoutUpdateClient/{id}")
	public String updateClientLayout(@PathVariable("id") Long id, @Valid @ModelAttribute("layout") Layout layout,
			Errors errors, Authentication auth) throws BusinessException {

		User userAuth = new User();
		Layout layoutSave = new Layout();
		String username = auth.getName();
		try {
			userAuth = serviceUser.findUserByUserName(username);
			layoutSave = serviceLayout.updateClientLayout(id, layout, userAuth);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		if (layoutSave.getCompleted() == true) {
			emailService.sendChangeStatusOrder(userAuth.getEmail(), userAuth.getName(), layoutSave.getName(),
					layoutSave.getCompleted());
		}

		return "redirect:/layoutClientGestione";
	}

}
