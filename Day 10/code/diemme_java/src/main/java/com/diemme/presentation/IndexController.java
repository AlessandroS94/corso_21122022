package com.diemme.presentation;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.diemme.exception.BusinessException;
import com.diemme.business.interfaces.IndexService;
import com.diemme.domain.mysql.NewsShowcase;

@Controller
public class IndexController {

	@Autowired
	private IndexService service;

	@GetMapping("/")
	public String listNewsShowcases(Model model) throws BusinessException {
		List<NewsShowcase> showcases = service.findAllNewsShowcases();
		model.addAttribute("showcases", showcases);
		return "/frontoffice/home/home.html";

	}

	@GetMapping("/showcase/{id}")
	@ResponseBody
	public byte[] listNewsShowcases(@PathVariable Long id, Model model) throws BusinessException, IOException {
		
		Optional<NewsShowcase> showcase = Optional.empty();
		
		try {
		showcase = service.findNewsShowcase(id);
		} catch (DataAccessException e) {
			e.printStackTrace();

		}
		byte[] byteImage = showcase.get().getContentImg();
		return byteImage;

	}
}
