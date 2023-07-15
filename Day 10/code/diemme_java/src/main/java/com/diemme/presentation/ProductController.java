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

import com.diemme.exception.BusinessException;
import com.diemme.business.interfaces.ProductService;
import com.diemme.business.interfaces.UserService;
import com.diemme.component.PageModel;
import com.diemme.domain.mysql.ProductShowcase;
import com.diemme.domain.mysql.User;

@Controller
public class ProductController {

	@Autowired
	private ProductService serviceProduct;
	@Autowired
	private UserService serviceUser;
	@Autowired
	private PageModel pageModel;

	@GetMapping("/prodotti")
	public String listProduct(Model model) throws BusinessException {

		List<ProductShowcase> products = new ArrayList<ProductShowcase>();
		try {
			products = serviceProduct.findAllProductShowcases();
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		model.addAttribute("prods", products);
		return "/frontoffice/prodotti/prodotti.html";

	}

	@SuppressWarnings("static-access")
	@GetMapping("/prodottiGestione")
	public String manageProduct(Model model) throws BusinessException {

		pageModel.setSIZE(5);
		pageModel.initPageAndSize();
		Page<ProductShowcase> products = serviceProduct.getAllProductPageable(pageModel.getPAGE(), pageModel.getSIZE());
		pageModel.resetPAGE();
		model.addAttribute("prod", products);
		return "/backoffice/productDashboard/manage.html";

	}

	@GetMapping("/prodotti/image/{id}")
	@ResponseBody
	public byte[] getImage(@PathVariable Long id) throws BusinessException, IOException {

		Optional<ProductShowcase> product = Optional.empty();
		try {

			product = serviceProduct.findProductShowcase(id);

		} catch (DataAccessException e) {
			e.printStackTrace();

		}
		byte[] imageProduct = product.get().getContentImg();
		return imageProduct;
	}

	@GetMapping("/prodottiCrea")
	public String createProduct(Model model) throws BusinessException {
		ProductShowcase productShowcase = new ProductShowcase();
		model.addAttribute("product_showcase", productShowcase);
		return "/backoffice/productDashboard/create.html";
	}

	@PostMapping("/prodottiCrea")
	public ModelAndView createProduct(@Valid @ModelAttribute("product_showcase") ProductShowcase product, Errors errors,
			@RequestParam("contentImg") MultipartFile contentImg, Authentication auth) throws BusinessException {

		User userAuth = new User();
		ModelAndView modelAndView = new ModelAndView();
		String username = auth.getName();

		try {

			userAuth = serviceUser.findUserByUserName(username);
			serviceProduct.createProduct(product, contentImg, userAuth);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return new ModelAndView("/error/error.html");

		}

		modelAndView.addObject("successMessage", "l'oggetto è stato creato!");
		modelAndView.setViewName("/backoffice/productDashboard/create.html");
		return modelAndView;
	}

	@PostMapping("/prodottiDelete/{id}")
	public String deleteProduct(@PathVariable(value = "id") Long id) throws BusinessException {

		try {

			serviceProduct.deleteProduct(id);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		return "redirect:/prodottiGestione";

	}

	@GetMapping("/prodottiUpdate")
	public String updateProduct(Long id, Model model) throws BusinessException {

		ProductShowcase productShowcase = new ProductShowcase();

		try {

			productShowcase = serviceProduct.getProduct(id);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";

		}
		model.addAttribute("product_showcase_update", productShowcase);
		return "/backoffice/productDashboard/update.html";
	}

	@PostMapping("/prodottiUpdate/{id}")
	public String updateProduct(@PathVariable("id") Long id,
			@Valid @ModelAttribute("product_showcase_update") ProductShowcase product, Errors errors,
			@RequestParam("contentImg") MultipartFile contentImg, Authentication auth) throws BusinessException {

		User userAuth = new User();
		String username = auth.getName();

		try {
			userAuth = serviceUser.findUserByUserName(username);
			serviceProduct.updateProduct(id, product, contentImg, userAuth);

		} catch (DataAccessException e) {
			e.printStackTrace();
			return "/error/error.html";
		}

		return "redirect:/prodottiGestione";
	}
}
