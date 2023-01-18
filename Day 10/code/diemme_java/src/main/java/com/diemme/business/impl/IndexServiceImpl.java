package com.diemme.business.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diemme.business.BusinessException;
import com.diemme.business.IndexService;
import com.diemme.domain.mysql.NewsShowcase;
import com.diemme.repository.mysql.NewsShowcaseRepository;

@Service
@Transactional
public class IndexServiceImpl implements IndexService {

	@Autowired
	private NewsShowcaseRepository newsShowcaseRepository;

	@Override
	public List<NewsShowcase> findAllNewsShowcases() throws BusinessException {

		return newsShowcaseRepository.findAll();
	}

	@Override

	public Optional<NewsShowcase> findNewsShowcase(Long id) throws BusinessException {

		return newsShowcaseRepository.findById(id);
	}

}
