package com.diemme.presentation;

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
import org.springframework.web.servlet.ModelAndView;

import com.diemme.business.BusinessException;
import com.diemme.business.QuotationService;
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.QuotationShowcase;
import com.diemme.domain.mysql.User;

@Controller
public class QuotationController {

	@Autowired
	private QuotationService serviceQuotation;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private PageModel pageModel;

	@GetMapping("/preventivi")
	public String listQuotation(Model model) throws BusinessException {

		List<QuotationShowcase> quotation = new ArrayList<QuotationShowcase>();

		try {

			quotation = serviceQuotation.findAllQuotationShowcases();

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		model.addAttribute("quotation", quotation);
		return "/frontoffice/preventivi/preventivi";

	}

	@SuppressWarnings("static-access")
	@GetMapping("/preventiviGestione")
	public String manageQuotation(Model model) throws BusinessException {
		pageModel.setSIZE(5);
		pageModel.initPageAndSize();
		Page<QuotationShowcase> quotations = serviceQuotation.getAllQuotationPageable(pageModel.getPAGE(),
				pageModel.getSIZE());
		pageModel.resetPAGE();
		model.addAttribute("quot", quotations);
		return "/backoffice/quotationDashboard/manage.html";

	}

	@GetMapping("/preventiviCrea")
	public String createQuotation(Model model) throws BusinessException {
		QuotationShowcase quotationShowcase = new QuotationShowcase();
		model.addAttribute("quotation_showcase", quotationShowcase);
		return "/backoffice/quotationDashboard/create.html";
	}

	@PostMapping("/preventiviCrea")
	public ModelAndView createQuotation(@Valid @ModelAttribute("quotation_showcase") QuotationShowcase quotation,
			Errors errors, Authentication auth) throws BusinessException {

		User userAuth = new User();
		ModelAndView modelAndView = new ModelAndView();
		String username = auth.getName();

		try {

			userAuth = serviceUser.findUserByUserName(username);
			serviceQuotation.createQuotation(quotation, userAuth);

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
		QuotationShowcase quotationShowcase = new QuotationShowcase();

		try {

			quotationShowcase = serviceQuotation.getQuotation(id);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		model.addAttribute("quotation_showcase_update", quotationShowcase);
		return "/backoffice/quotationDashboard/update.html";
	}

	@PostMapping("/preventiviUpdate/{id}")
	public String updateQuotation(@PathVariable("id") Long id,
			@Valid @ModelAttribute("quotation_showcase_update") QuotationShowcase quotation, Errors errors,
			Authentication auth) throws BusinessException {

		User userAuth = new User();
		String username = auth.getName();

		try {

			userAuth = serviceUser.findUserByUserName(username);
			serviceQuotation.updateQuotation(id, quotation, userAuth);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		return "redirect:/preventiviGestione";
	}

	@PostMapping("/preventiviDelete/{id}")
	public String deleteQuotationShocases(@PathVariable(value = "id") Long id) throws BusinessException {

		try {

			serviceQuotation.deleteQuotation(id);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		return "redirect:/preventiviGestione";

	}
}
