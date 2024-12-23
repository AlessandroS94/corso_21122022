package com.diemme.business.interfaces;

import java.util.List;

import com.diemme.domain.mysql.ProcedureShowcase;
import com.diemme.exception.BusinessException;
import org.springframework.data.domain.Page;

import com.diemme.domain.mysql.User;

public interface ProcedureService {

	List<ProcedureShowcase> findAllProcedureShowcases() throws BusinessException;

	Page<ProcedureShowcase> getAllProcedurePageable(Integer page, Integer size) throws BusinessException;

	ProcedureShowcase getProcedure(Long id) throws BusinessException;

	ProcedureShowcase saveProcedure(ProcedureShowcase procedureShowcase) throws BusinessException;

	void deleteProcedure(Long id) throws BusinessException;

	void createProcedure(ProcedureShowcase procedureShowcase, User userAuth) throws BusinessException;

	void updateProcedure(Long id, ProcedureShowcase procedureShowcase, User userAuth) throws BusinessException;
}
