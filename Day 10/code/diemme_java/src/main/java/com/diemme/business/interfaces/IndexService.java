package com.diemme.business.interfaces;

import java.util.List;
import java.util.Optional;

import com.diemme.exception.BusinessException;
import com.diemme.domain.mysql.NewsShowcase;


public interface IndexService {
	
	List<NewsShowcase> findAllNewsShowcases() throws BusinessException;
	
	Optional<NewsShowcase> findNewsShowcase(Long id) throws BusinessException;
	


}
