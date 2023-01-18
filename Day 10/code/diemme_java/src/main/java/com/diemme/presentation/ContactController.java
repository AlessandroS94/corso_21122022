package com.diemme.presentation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.diemme.business.BusinessException;
import com.diemme.business.ContactService;
import com.diemme.business.EmailService;
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.Contact;
import com.diemme.domain.mysql.ContactShowcase;
import com.diemme.domain.mysql.User;

@Controller
public class ContactController {

	@Autowired
	EmailService serviceEmail;
	@Autowired
	ContactService serviceContact;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private PageModel pageModel;

	@GetMapping("/contatti")
	public String activeContact(Model model) throws BusinessException {

		ContactShowcase contactActive = new ContactShowcase();
		try {
			contactActive = serviceContact.findActiveContac();
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		model.addAttribute("contact", new Contact());
		model.addAttribute("contacts", contactActive);
		return "/frontoffice/contatti/contatti.html";

	}

	@SuppressWarnings("static-access")
	@GetMapping("/contattiGestione")
	public String manageContacts(Model model) throws BusinessException {

		pageModel.setSIZE(5);
		pageModel.initPageAndSize();
		Page<ContactShowcase> contacts = serviceContact.getAllContactPageable(pageModel.getPAGE(), pageModel.getSIZE());
		model.addAttribute("cont", contacts);
		return "/backoffice/contactDashboard/manage.html";

	}

	@GetMapping("/contattiCrea")
	public String createContact(Model model) throws BusinessException {

		ContactShowcase contactShowcase = new ContactShowcase();
		model.addAttribute("contact_showcase", contactShowcase);
		return "/backoffice/contactDashboard/create.html";
	}

	@PostMapping("/contattiCrea")
	public ModelAndView createContact(@Valid @ModelAttribute("contact_showcase") ContactShowcase contact, Errors errors,
			Authentication auth) throws BusinessException {

		User userAuth = new User();
		ModelAndView modelAndView = new ModelAndView();
		String username = auth.getName();
		try {

			userAuth = serviceUser.findUserByUserName(username);
			serviceContact.createContact(contact, userAuth);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return new ModelAndView("/error/error.html");

		}

		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
		modelAndView.setViewName("/backoffice/contactDashboard/create.html");
		return modelAndView;
	}

	@GetMapping("/contattiUpdate")
	public String updateContactShocases(Long id, Model model) throws BusinessException {

		ContactShowcase contactShowcase = new ContactShowcase();
		try {
			contactShowcase = serviceContact.findContactShowcase(id);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		model.addAttribute("contact_showcase_update", contactShowcase);
		return "/backoffice/contactDashboard/update.html";
	}

	@PostMapping("/contattiUpdate/{id}")
	public String update(@PathVariable("id") Long id,
			@Valid @ModelAttribute("contact_showcase_update") ContactShowcase contactShowcase, Errors errors,
			Authentication auth) throws BusinessException {

		User userAuth = new User();
		String username = auth.getName();

		try {

			userAuth = serviceUser.findUserByUserName(username);
			serviceContact.updateContact(id, contactShowcase, userAuth);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		return "redirect:/contattiGestione";
	}

	@PostMapping("/contattiDelete/{id}")
	public String deleteProductShocases(@PathVariable(value = "id") Long id) throws BusinessException {

		try {

			serviceContact.deleteContactShowcase(id);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		return "redirect:/contattiGestione";

	}

	@PostMapping("/sendEmail")
	public String sendEmail(@Valid @ModelAttribute("contact") Contact contact, BindingResult result,
			RedirectAttributes redirectAttributes) throws BusinessException {

		String from = contact.getEmail();
		String object = contact.getObject();
		String body = contact.getText();
		String nameSender = contact.getName();
		redirectAttributes.addFlashAttribute("message", "Qualcosa è andato storto...Riprova!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-danger");

		if (result.hasErrors()) {
			return "redirect:/contatti";
		}

		serviceEmail.sendContact(from, object, body, nameSender);
		redirectAttributes.addFlashAttribute("message", "Il messaggio è stato inviato correttamente!");
		redirectAttributes.addFlashAttribute("alertClass", "alert-success");

		return "redirect:/contatti";

	}

}
