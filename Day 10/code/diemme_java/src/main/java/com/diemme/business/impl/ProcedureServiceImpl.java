package com.diemme.business.impl;

import java.time.ZonedDateTime;
import java.util.List;

import com.diemme.domain.mysql.ProcedureShowcase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diemme.exception.ResourceNotFoundException;
import com.diemme.exception.BusinessException;
import com.diemme.business.interfaces.ProcedureService;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mysql.ProcedureShowcaseRepository;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

	@Autowired
	private ProcedureShowcaseRepository procedureShowcaseRepository;

	@Override
	public List<ProcedureShowcase> findAllQuotationShowcases() throws BusinessException {

		return procedureShowcaseRepository.findAll();
	}

	@Override
	public Page<ProcedureShowcase> getAllQuotationPageable(Integer page, Integer size) throws BusinessException {

		return procedureShowcaseRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public ProcedureShowcase getQuotation(Long id) throws BusinessException {

		return procedureShowcaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ProcedureShowcase", "id", id));
	}

	@Override
	public ProcedureShowcase saveQuotation(ProcedureShowcase quotation) throws BusinessException {

		return procedureShowcaseRepository.save(quotation);
	}

	@Override
	public void createQuotation(ProcedureShowcase quotation, User userAuth) throws BusinessException {

		quotation.setUser(userAuth);
		procedureShowcaseRepository.save(quotation);

	}

	@Override
	public void updateQuotation(Long id, ProcedureShowcase quotation, User userAuth) throws BusinessException {
		ProcedureShowcase quotationOld = new ProcedureShowcase();

		quotationOld = procedureShowcaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ProcedureShowcase", "id", id));
		ZonedDateTime dateCreation = quotationOld.getInsertDate();
		quotation.setUser(userAuth);
		quotation.setInsertDate(dateCreation);
		quotation.setModifyDate(ZonedDateTime.now());
		procedureShowcaseRepository.save(quotation);

	}

	@Override
	public void deleteQuotation(Long id) throws BusinessException {

		procedureShowcaseRepository.deleteById(id);
	}
}
