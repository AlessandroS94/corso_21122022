package com.diemme.business;

import java.util.List;
import java.util.Optional;

import com.diemme.domain.mysql.NewsShowcase;


public interface IndexService {
	
	List<NewsShowcase> findAllNewsShowcases() throws BusinessException;
	
	Optional<NewsShowcase> findNewsShowcase(Long id) throws BusinessException;
	


}
