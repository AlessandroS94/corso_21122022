package com.diemme.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.diemme.domain.mysql.ProcedureShowcase;
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
import org.springframework.web.servlet.ModelAndView;

import com.diemme.exception.BusinessException;
import com.diemme.business.interfaces.ProcedureService;
import com.diemme.business.interfaces.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.User;

@Controller
public class ProcedureController {

	@Autowired
	private ProcedureService procedureService;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private PageModel pageModel;

	@GetMapping("/preventivi")
	public String listProcedure(Model model) throws BusinessException {

		List<ProcedureShowcase> procedure = new ArrayList<ProcedureShowcase>();

		try {

			procedure = procedureService.findAllProcedureShowcases();

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		model.addAttribute("quotation", procedure);
		return "/frontoffice/preventivi/preventivi";

	}

	@SuppressWarnings("static-access")
	@GetMapping("/preventiviGestione")
	public String manageQuotation(Model model) throws BusinessException {
		pageModel.setSIZE(5);
		pageModel.initPageAndSize();
		Page<ProcedureShowcase> quotations = procedureService.getAllProcedurePageable(pageModel.getPAGE(),
				pageModel.getSIZE());
		pageModel.resetPAGE();
		model.addAttribute("quot", quotations);
		return "/backoffice/quotationDashboard/manage.html";

	}

	@GetMapping("/preventiviCrea")
	public String createQuotation(Model model) throws BusinessException {
		ProcedureShowcase procedureShowcase = new ProcedureShowcase();
		model.addAttribute("quotation_showcase", procedureShowcase);
		return "/backoffice/quotationDashboard/create.html";
	}

	@PostMapping("/preventiviCrea")
	public ModelAndView createQuotation(@Valid @ModelAttribute("quotation_showcase") ProcedureShowcase quotation,
			Errors errors, Authentication auth) throws BusinessException {

		User userAuth = new User();
		ModelAndView modelAndView = new ModelAndView();
		String username = auth.getName();

		try {

			userAuth = serviceUser.findUserByUserName(username);
			procedureService.createProcedure(quotation, userAuth);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return new ModelAndView("/error/error.html");

		}

		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
		modelAndView.setViewName("/backoffice/quotationDashboard/create.html");
		return modelAndView;
	}

	@GetMapping("/preventiviUpdate")
	public String updateQuotation(Long id, Model model) throws BusinessException {
		ProcedureShowcase procedureShowcase = new ProcedureShowcase();

		try {

			procedureShowcase = procedureService.getProcedure(id);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		model.addAttribute("quotation_showcase_update", procedureShowcase);
		return "/backoffice/quotationDashboard/update.html";
	}

	@PostMapping("/preventiviUpdate/{id}")
	public String updateQuotation(@PathVariable("id") Long id,
								  @Valid @ModelAttribute("quotation_showcase_update") ProcedureShowcase quotation, Errors errors,
								  Authentication auth) throws BusinessException {

		User userAuth = new User();
		String username = auth.getName();

		try {

			userAuth = serviceUser.findUserByUserName(username);
			procedureService.updateProcedure(id, quotation, userAuth);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		return "redirect:/preventiviGestione";
	}

	@PostMapping("/preventiviDelete/{id}")
	public String deleteQuotationShocases(@PathVariable(value = "id") Long id) throws BusinessException {

		try {

			procedureService.deleteProcedure(id);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		return "redirect:/preventiviGestione";

	}
}
