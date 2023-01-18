package com.diemme.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.diemme.business.BusinessException;

@Controller
public class ErrorController {
	@GetMapping("/error")
	public String showError(Model model) throws BusinessException{
		return "error/error.html";
	}

}
