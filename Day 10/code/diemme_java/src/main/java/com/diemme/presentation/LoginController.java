package com.diemme.presentation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.diemme.business.BusinessException;
import com.diemme.business.EmailService;
import com.diemme.business.IndexService;
import com.diemme.business.RoleService;
import com.diemme.business.impl.UserServiceImpl;
import com.diemme.domain.mysql.NewsShowcase;
import com.diemme.domain.mysql.Role;
import com.diemme.domain.mysql.User;

@Controller
public class LoginController {

	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private IndexService service;

	@Autowired
	EmailService emailService;

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("auth/login/login");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("auth/login/registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User userExists = new User();
		try {
			userExists = userService.findUserByUserName(user.getUserName());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		Role roleUser = roleService.findByRole("CLIENT");
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleUser);
		user.setRoles(roles);
		user.setActive(false);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		if (userExists != null) {
			bindingResult.rejectValue("userName", "error.user", "exist");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("auth/login/registration");
		} else {
			try {
				userService.saveUser(user);
			} catch (BusinessException e) {
				e.printStackTrace();
			}

			modelAndView.addObject("successMessage", "ok");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("auth/login/registration");
			emailService.sendUserActive(user.getEmail(), user.getName());

		}
		return modelAndView;
	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		List<NewsShowcase> showcases = new ArrayList<NewsShowcase>();
		try {
			showcases = service.findAllNewsShowcases();
		} catch (BusinessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		User user = new User();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		try {
			user = userService.findUserByUserName(auth.getName());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		modelAndView.addObject("showcases", showcases);
		modelAndView.setViewName("frontoffice/home/home");
		return modelAndView;
	}
}