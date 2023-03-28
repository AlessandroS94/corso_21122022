package com.diemme.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.diemme.business.BusinessException;
import com.diemme.business.TechnologyService;
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.TechnologyShowcase;
import com.diemme.domain.mysql.User;

@Controller
public class TechnologyController {

	@Autowired
	private TechnologyService serviceTecnology;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private PageModel pageModel;

	@GetMapping("/tecnologie")
	public String listTechnology(Model model) throws BusinessException {

		List<TechnologyShowcase> technologies = new ArrayList<TechnologyShowcase>();

		try {

			technologies = serviceTecnology.getAllTecnology();

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		model.addAttribute("techno", technologies);
		return "/frontoffice/tecnologie/tecnologie.html";

	}

	@SuppressWarnings("static-access")
	@GetMapping("/tecnologieGestione")
	public String manageTechnology(Model model) throws BusinessException {

		pageModel.setSIZE(5);
		pageModel.initPageAndSize();
		Page<TechnologyShowcase> technologies = serviceTecnology.getAllTecnologyPageable(pageModel.getPAGE(),
				pageModel.getSIZE());
		pageModel.resetPAGE();
		model.addAttribute("techno", technologies);
		return "/backoffice/technologyDashboard/manage.html";

	}

	@GetMapping("/tecnologie/image/{id}")
	@ResponseBody
	public byte[] getImage(@PathVariable Long id) throws BusinessException, IOException {

		TechnologyShowcase product = new TechnologyShowcase();

		try {

			product = serviceTecnology.getTecnology(id);

		} catch (DataAccessException e) {
			e.printStackTrace();

		}
		byte[] imageProduct = product.getContentImg();
		return imageProduct;
	}

	@GetMapping("/tecnologieCrea")
	public String createTechnology(Model model) throws BusinessException {

		TechnologyShowcase technologyShowcase = new TechnologyShowcase();
		model.addAttribute("tecnology_showcase", technologyShowcase);
		return "/backoffice/technologyDashboard/create.html";
	}

	@PostMapping("/tecnologieCrea")
	public ModelAndView createTechnology(@Valid @ModelAttribute("tecnology_showcase") TechnologyShowcase technology,
			Errors errors, @RequestParam("contentImg") MultipartFile contentImg, Authentication auth)
			throws BusinessException {

		User userAuth = new User();
		ModelAndView modelAndView = new ModelAndView();
		String username = auth.getName();

		try {

			userAuth = serviceUser.findUserByUserName(username);
			serviceTecnology.createTechnology(technology, contentImg, userAuth);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return new ModelAndView("/error/error.html");

		}
		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
		modelAndView.setViewName("/backoffice/technologyDashboard/create.html");
		return modelAndView;
	}

	@PostMapping("/tecnologieDelete/{id}")
	public String createTechnology(@PathVariable(value = "id") Long id) throws BusinessException {

		try {

			serviceTecnology.deleteTechnology(id);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		return "redirect:/tecnologieGestione";

	}

	@GetMapping("/tecnologieUpdate")
	public String updateTechnology(Long id, Model model) throws BusinessException {
		TechnologyShowcase technologyShowcase = new TechnologyShowcase();

		try {

			technologyShowcase = serviceTecnology.getTecnology(id);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		model.addAttribute("tecnology_showcase_update", technologyShowcase);
		return "/backoffice/technologyDashboard/update.html";
	}

	@PostMapping("/tecnologieUpdate/{id}")
	public String updateTechnology(@PathVariable("id") Long id,
			@Valid @ModelAttribute("tecnology_showcase_update") TechnologyShowcase technology, Errors errors,
			@RequestParam("contentImg") MultipartFile contentImg, Authentication auth) throws BusinessException {

		User userAuth = new User();
		String username = auth.getName();

		try {

			userAuth = serviceUser.findUserByUserName(username);
			serviceTecnology.updateTechnology(id, technology, contentImg, userAuth);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		return "redirect:/tecnologieGestione";
	}

}
