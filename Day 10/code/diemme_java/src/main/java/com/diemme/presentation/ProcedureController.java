package com.diemme.presentation;

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
		List<ProcedureShowcase> procedure;
		try {
			procedure = procedureService.findAllProcedureShowcases();
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";
		}
		model.addAttribute("procedures", procedure);
		return "/frontoffice/preventivi/preventivi";

	}

	@SuppressWarnings("static-access")
	@GetMapping("/preventiviGestione")
	public String manageProcedure(Model model) throws BusinessException {
		pageModel.setSIZE(5);
		pageModel.initPageAndSize();
		Page<ProcedureShowcase> procedureServiceAllProcedurePageable = procedureService.getAllProcedurePageable(pageModel.getPAGE(),
				pageModel.getSIZE());
		pageModel.resetPAGE();
		model.addAttribute("proce", procedureServiceAllProcedurePageable);
		return "/backoffice/procedureDashboard/manage.html";

	}

	@GetMapping("/preventiviCrea")
	public String createProcedure(Model model) throws BusinessException {
		ProcedureShowcase procedureShowcase = new ProcedureShowcase();
		model.addAttribute("procedure_showcase", procedureShowcase);
		return "/backoffice/procedureDashboard/create.html";
	}

	@PostMapping("/preventiviCrea")
	public ModelAndView createProcedure(@Valid @ModelAttribute("procedure_showcase") ProcedureShowcase procedureShowcase,
			Errors errors, Authentication auth) throws BusinessException {

		User userAuth;
		ModelAndView modelAndView = new ModelAndView();
		String username = auth.getName();
		try {
			userAuth = serviceUser.findUserByUserName(username);
			procedureService.createProcedure(procedureShowcase, userAuth);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return new ModelAndView("/error/error.html");
		}
		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
		modelAndView.setViewName("/backoffice/procedureDashboard/create.html");
		return modelAndView;
	}

	@GetMapping("/preventiviUpdate")
	public String updateProcedure(Long id, Model model) throws BusinessException {
		ProcedureShowcase procedureShowcase;
		try {
			procedureShowcase = procedureService.getProcedure(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";
		}
		model.addAttribute("procedure_showcase_update", procedureShowcase);
		return "/backoffice/procedureDashboard/update.html";
	}

	@PostMapping("/preventiviUpdate/{id}")
	public String updateProcedure(@PathVariable("id") Long id,
								  @Valid @ModelAttribute("procedure_showcase_update") ProcedureShowcase quotation, Errors errors,
								  Authentication auth) throws BusinessException {

		String username = auth.getName();
		try {

			User userAuth = serviceUser.findUserByUserName(username);
			procedureService.updateProcedure(id, quotation, userAuth);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		return "redirect:/preventiviGestione";
	}

	@PostMapping("/preventiviDelete/{id}")
	public String deleteProcedureShocases(@PathVariable(value = "id") Long id) throws BusinessException {
		try {
			procedureService.deleteProcedure(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";
		}
		return "redirect:/preventiviGestione";

	}
}
