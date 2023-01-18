package com.diemme.business.impl;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.ProductService;
import com.diemme.domain.mysql.ProductShowcase;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mysql.ProductsShowcaseRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductsShowcaseRepository producsShowcaseRepository;
	
	private com.diemme.util.CompressionUtils CompressionUtils;

	
	@Override
	public List<ProductShowcase> findAllProductShowcases() throws BusinessException{
		
		return producsShowcaseRepository.findAll();
	}
	
	@Override
	public Optional<ProductShowcase> findProductShowcase(Long id) throws BusinessException{
		
		return producsShowcaseRepository.findById(id);
	}
	
	@Override
	public ProductShowcase getProduct (Long id) throws BusinessException{
		
		return producsShowcaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("producsShowcase", "id", id));
	}
	

	@Override
	public ProductShowcase saveProduct(ProductShowcase product) throws BusinessException {
		return producsShowcaseRepository.save(product);
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void createProduct(ProductShowcase product, MultipartFile contentImg, User userAuth) throws BusinessException {
		
		byte[] bytes = new byte[(int) contentImg.getSize()];
		byte[] byteCompress = new byte[0];
		try {
			byteCompress = CompressionUtils.compress(bytes);
			bytes = contentImg.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		product.setContentImg(bytes);
		product.setCompressImg(byteCompress);
		product.setUser(userAuth);
		
		producsShowcaseRepository.save(product);
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void updateProduct(Long id, ProductShowcase product, MultipartFile contentImg, User userAuth) throws BusinessException {
		
		byte[] bytes = new byte[(int) contentImg.getSize()];
		byte[] byteCompress = new byte[0];
		ProductShowcase productOld = new ProductShowcase();
		ProductShowcase productSave = new ProductShowcase();
		
		productOld = producsShowcaseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("producsShowcase", "id", id));

		ZonedDateTime dateCreation = productOld.getInsertDate();

		if (contentImg.isEmpty()) {

			product.setContentImg(productOld.getContentImg());
			product.setCompressImg(productOld.getCompressImg());
			product.setModifyDate(ZonedDateTime.now());

		} else {

			try {
				byteCompress = CompressionUtils.compress(bytes);
				bytes = contentImg.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
			product.setContentImg(bytes);
			product.setCompressImg(byteCompress);
		}
		
		product.setUser(userAuth);
		productSave = producsShowcaseRepository.save(product);
		productSave.setInsertDate(dateCreation);
		producsShowcaseRepository.save(productSave);
	}

	@Override
	public Page<ProductShowcase> getAllProductPageable(Integer page, Integer size) throws BusinessException {
		return producsShowcaseRepository.findAll(PageRequest.of(page,size));
	}

	@Override
	public void deleteProduct(Long id) throws BusinessException {
		producsShowcaseRepository.deleteById(id);
	}
}
