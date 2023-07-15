package com.diemme.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.diemme.exception.BusinessException;

@Controller
public class DashboardController {
	
	@GetMapping("/dashboard")
	public String showDashboard(Model model) throws BusinessException{
		return "backoffice/dashboard/dashboard.html";
	}

}
