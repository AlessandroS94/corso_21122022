package com.diemme.business;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.diemme.domain.mysql.ProductShowcase;
import com.diemme.domain.mysql.User;


public interface ProductService {
	
	List<ProductShowcase> findAllProductShowcases() throws BusinessException;
	
	Optional<ProductShowcase> findProductShowcase(Long id) throws BusinessException;
	
	ProductShowcase saveProduct (ProductShowcase product) throws BusinessException;

	Page<ProductShowcase> getAllProductPageable(Integer page, Integer size) throws BusinessException;

	void deleteProduct(Long id) throws BusinessException;

	ProductShowcase getProduct(Long id) throws BusinessException;

	void createProduct(ProductShowcase product, MultipartFile contentImg, User userAuth) throws BusinessException;

	void updateProduct(Long id, ProductShowcase product, MultipartFile contentImg, User userAuth)
			throws BusinessException;


}
