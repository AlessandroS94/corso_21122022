package com.diemme.presentation;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.diemme.exception.BusinessException;
import com.diemme.business.interfaces.ChatUserService;
import com.diemme.business.interfaces.EmailService;
import com.diemme.business.interfaces.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mongo.Chat;
import com.diemme.domain.mysql.ChatUser;
import com.diemme.domain.mysql.Role;
import com.diemme.domain.mysql.User;
import com.diemme.wrapperForm.FormWrapperChat;

@Controller
public class ChatController {

	@Autowired
	private ChatUserService chatUserService;

	@Autowired
	private UserService serviceUser;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PageModel pageModel;

	@SuppressWarnings("static-access")
	@GetMapping("/chatGestione")
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
		Page<ChatUser> chatsUser = chatUserService.getAllUserChat(pageModel.getPAGE(), pageModel.getSIZE(),
				userAuth.getId());
		model.addAttribute("chatsUser", chatsUser);
		pageModel.resetPAGE();
		return "/backoffice/chatDashboard/manage.html";

	}

	@GetMapping("/chat/{id}")
	@ResponseBody
	public Chat getChat(@PathVariable("id") String id) throws BusinessException {

		Chat chat = new Chat();
		try {
			chat = chatUserService.getChat(id);
		} catch (DataAccessException e) {
			e.printStackTrace();

		}
		return chat;
	}

	@PostMapping("/chatDelete/{id}/{idChatMongo}")
	public String deleteChat(@PathVariable(value = "id") Long id,
			@PathVariable(value = "idChatMongo") String idChatMongo) throws BusinessException {

		try {
			chatUserService.deleteChat(id, idChatMongo);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		return "redirect:/chatGestione";

	}

	@GetMapping("/chatVisione")
	public String getChatView(String id, Model model, Authentication auth) throws BusinessException {
		User userAuth = new User();
		String username = auth.getName();
		try {
			userAuth = serviceUser.findUserByUserName(username);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		Long idUser = userAuth.getId();
		model.addAttribute("auth", idUser);
		model.addAttribute("idChat", id);
		return "/backoffice/chatDashboard/chat.html";

	}

	@GetMapping("/fileVisione/{idChatMongo}/{index}")
	public String getFile(String id, Model model, @PathVariable("idChatMongo") String idChatMongo,
			@PathVariable("index") int index) throws BusinessException {

		model.addAttribute("idChatMongo", idChatMongo);
		model.addAttribute("index", index);
		return "/backoffice/chatDashboard/viewImage.html";

	}

	@GetMapping("/chatFile/{idChatMongo}/{index}")
	@ResponseBody
	public byte[] getImageChat(@PathVariable("idChatMongo") String idChatMongo, @PathVariable("index") int index)
			throws BusinessException, IOException {

		byte b[] = null;

		try {
			b = chatUserService.getImageChat(idChatMongo, index);

		} catch (DataAccessException e) {
			e.printStackTrace();

		}

		return b;
	}

	@PostMapping("/chat/{idChatMongo}")
	@ResponseBody
	public byte[] postMEssage(@PathVariable("idChatMongo") String idChatMongo) throws BusinessException, IOException {

		byte b[] = null;

		try {
			b = chatUserService.postMEssage(idChatMongo);

		} catch (DataAccessException e) {
			e.printStackTrace();

		}
		return b;
	}

	@GetMapping("/chatCrea")
	public String createChat(Model model, Authentication auth) throws BusinessException {
		FormWrapperChat formWrapperChat = new FormWrapperChat();
		User userAuth = new User();
		String typeChat = new String();
		Set<User> userRole = new HashSet<User>();
		String userName = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(userName);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		for (Role role : userAuth.getRoles()) {

			if (role.getRole().equals("CLIENT")) {

				userRole = serviceUser.getUsersByRole("DESIGNER");
				typeChat = "un Designer!";

			} else if (role.getRole().equals("DESIGNER")) {

				userRole = serviceUser.getUsersByRole("PRODUCTOR");
				typeChat = "un Produttore!";

			}
			else if (role.getRole().equals("PRODUCTOR")) {

				userRole = serviceUser.getUsersByRole("DESIGNER");
				typeChat = "un Designer!";

			}

		}
		model.addAttribute("typeChat", typeChat);
		model.addAttribute("userRole", userRole);
		model.addAttribute("formWrapperChat", formWrapperChat);
		return "/backoffice/chatDashboard/create.html";
	}

	@PostMapping("/chatCrea")
	public ModelAndView createChat(Authentication auth,
			@Valid @ModelAttribute("formWrapperChat") FormWrapperChat formWrapperChat, Errors errors,
			@RequestParam(value = "contentImg") MultipartFile contentImg) throws BusinessException {

		User userAuth = new User();
		ModelAndView modelAndView = new ModelAndView("redirect:/chatGestione");
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);
			chatUserService.saveNewChat(formWrapperChat, contentImg, userAuth);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return new ModelAndView("/error/error.html");
		}

		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
		//modelAndView.setViewName("/backoffice/chatDashboard/create.html");

		for (Role role : userAuth.getRoles()) {

			if (role.getRole().equals("CLIENT")) {
				emailService.sendNotifyClientMessage(userAuth.getEmail(), userAuth.getName());
			} else if (role.getRole().equals("DESIGNER")) {
				emailService.sendNotifyDesignerMessage(userAuth.getEmail(), userAuth.getName());
			} else if (role.getRole().equals("PRODUCTOR")) {
				emailService.sendNotifyDesignerMessage(userAuth.getEmail(), userAuth.getName());
			}

		}
		return modelAndView;

	}

	@PostMapping("/chatUpdate/{id}")
	public ModelAndView updateChat(ModelMap model, Authentication auth, String message,
			@RequestParam(value = "attachment", required = false) MultipartFile attachment,
			@PathVariable("id") String id) throws BusinessException {

		User userAuth = new User();
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);
			chatUserService.updateChat(userAuth, message, attachment, id);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return new ModelAndView("/error/error.html");

		}

		for (Role role : userAuth.getRoles()) {

			if (role.getRole().equals("CLIENT")) {
				emailService.sendNotifyClientMessage(userAuth.getEmail(), userAuth.getName());
			} else if (role.getRole().equals("DESIGNER")) {
				emailService.sendNotifyDesignerMessage(userAuth.getEmail(), userAuth.getName());
			}
			else if (role.getRole().equals("PRODUCTOR")) {
				emailService.sendNotifyProductorMessage(userAuth.getEmail(), userAuth.getName());
			}
		}

		model.addAttribute("id", id);
		return new ModelAndView("redirect:/chatVisione", model);

	}

}
