package com.diemme.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.diemme.business.NewsService;
import com.diemme.business.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.NewsShowcase;
import com.diemme.domain.mysql.User;

@Controller
public class NewsController {

	@Autowired
	private NewsService serviceNews;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private PageModel pageModel;

	@GetMapping("/news")
	public String listNews(Model model) throws BusinessException {

		List<NewsShowcase> news = new ArrayList<NewsShowcase>();
		try {
			news = serviceNews.findAllNewsShowcases();
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		model.addAttribute("news_showcase", news);
		return "/frontoffice/news/news.html";

	}

	@SuppressWarnings("static-access")
	@GetMapping("/newsGestione")
	public String manageNews(Model model) throws BusinessException {

		pageModel.setSIZE(5);
		pageModel.initPageAndSize();
		Page<NewsShowcase> news = serviceNews.getAllNewsPageable(pageModel.getPAGE(), pageModel.getSIZE());
		model.addAttribute("news_showcase", news);
		pageModel.resetPAGE();
		return "/backoffice/newsDashboard/manage.html";

	}

	@GetMapping("/news/image/{id}")
	@ResponseBody
	public byte[] getImage(@PathVariable Long id) throws BusinessException, IOException {

		Optional<NewsShowcase> news = Optional.empty();
		try {
			news = serviceNews.findNewsShowcase(id);
		} catch (DataAccessException e) {
			e.printStackTrace();

		}
		byte[] imageProduct = news.get().getContentImg();
		return imageProduct;
	}

	@GetMapping("/newsCrea")
	public String createNews(Model model) throws BusinessException {

		NewsShowcase newsShowcase = new NewsShowcase();
		model.addAttribute("news_showcase", newsShowcase);
		return "/backoffice/newsDashboard/create.html";
	}

	@PostMapping("/newsCrea")
	public ModelAndView createNews(@Valid @ModelAttribute("news_showcase") NewsShowcase news, Errors errors,
			@RequestParam("contentImg") MultipartFile contentImg, Authentication auth) throws BusinessException {

		User userAuth = new User();
		ModelAndView modelAndView = new ModelAndView();
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);
			serviceNews.createNews(news, contentImg, userAuth);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return new ModelAndView("/error/error.html");

		}

		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
		modelAndView.setViewName("/backoffice/newsDashboard/create.html");
		return modelAndView;
	}

	@PostMapping("/newsDelete/{id}")
	public String deleteNews(@PathVariable(value = "id") Long id) throws BusinessException {

		try {
			serviceNews.deleteNews(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		return "redirect:/newsGestione";

	}

	@GetMapping("/newsUpdate")
	public String updateNews(Long id, Model model) throws BusinessException {

		NewsShowcase newsShowcase = new NewsShowcase();
		try {
			newsShowcase = serviceNews.getNews(id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		model.addAttribute("news_showcase_update", newsShowcase);
		return "/backoffice/newsDashboard/update.html";
	}

	@PostMapping("/newsUpdate/{id}")
	public String updateNews(@PathVariable("id") Long id,
			@Valid @ModelAttribute("news_showcase_update") NewsShowcase news, Errors errors,
			@RequestParam("contentImg") MultipartFile contentImg, Authentication auth) throws BusinessException {

		User userAuth = new User();
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);
			serviceNews.updateNews(id, news, contentImg, userAuth);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}

		return "redirect:/newsGestione";
	}

}
