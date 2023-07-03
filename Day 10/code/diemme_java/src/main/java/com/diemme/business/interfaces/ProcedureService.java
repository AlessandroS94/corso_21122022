package com.diemme.business.interfaces;

import java.util.List;

import com.diemme.domain.mysql.ProcedureShowcase;
import com.diemme.exception.BusinessException;
import org.springframework.data.domain.Page;

import com.diemme.domain.mysql.User;

public interface ProcedureService {

	List<ProcedureShowcase> findAllQuotationShowcases() throws BusinessException;

	Page<ProcedureShowcase> getAllQuotationPageable(Integer page, Integer size) throws BusinessException;

	ProcedureShowcase getQuotation(Long id) throws BusinessException;

	ProcedureShowcase saveQuotation(ProcedureShowcase quotation) throws BusinessException;

	void deleteQuotation(Long id) throws BusinessException;

	void createQuotation(ProcedureShowcase quotation, User userAuth) throws BusinessException;

	void updateQuotation(Long id, ProcedureShowcase quotation, User userAuth) throws BusinessException;
}
