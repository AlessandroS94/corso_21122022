package com.diemme.business.impl;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.QuotationService;
import com.diemme.domain.mysql.QuotationShowcase;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mysql.QuotationShowcaseRepository;

@Service
@Transactional
public class QuotationServiceImpl implements QuotationService {

	@Autowired
	private QuotationShowcaseRepository quotationShowcaseRepository;

	@Override
	public List<QuotationShowcase> findAllQuotationShowcases() throws BusinessException {

		return quotationShowcaseRepository.findAll();
	}

	@Override
	public Page<QuotationShowcase> getAllQuotationPageable(Integer page, Integer size) throws BusinessException {

		return quotationShowcaseRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public QuotationShowcase getQuotation(Long id) throws BusinessException {

		return quotationShowcaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("QuotationShowcase", "id", id));
	}

	@Override
	public QuotationShowcase saveQuotation(QuotationShowcase quotation) throws BusinessException {

		return quotationShowcaseRepository.save(quotation);
	}

	@Override
	public void createQuotation(QuotationShowcase quotation, User userAuth) throws BusinessException {

		quotation.setUser(userAuth);
		quotationShowcaseRepository.save(quotation);

	}

	@Override
	public void updateQuotation(Long id, QuotationShowcase quotation, User userAuth) throws BusinessException {
		QuotationShowcase quotationOld = new QuotationShowcase();

		quotationOld = quotationShowcaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("QuotationShowcase", "id", id));
		ZonedDateTime dateCreation = quotationOld.getInsertDate();
		quotation.setUser(userAuth);
		quotation.setInsertDate(dateCreation);
		quotation.setModifyDate(ZonedDateTime.now());
		quotationShowcaseRepository.save(quotation);

	}

	@Override
	public void deleteQuotation(Long id) throws BusinessException {

		quotationShowcaseRepository.deleteById(id);
	}
}
