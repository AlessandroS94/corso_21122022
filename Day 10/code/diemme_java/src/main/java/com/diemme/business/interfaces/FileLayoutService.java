package com.diemme.business.interfaces;

import java.util.List;

import com.diemme.exception.BusinessException;
import org.springframework.data.domain.Page;

import com.diemme.domain.mysql.FileLayout;


public interface FileLayoutService {
	
	FileLayout getFileLayout (Long id) throws BusinessException;
	
	FileLayout saveFileLayout(FileLayout fileLayout) throws BusinessException;

	Page<FileLayout> getAllFileslayout(Integer page, Integer size, List<Long> id) throws BusinessException;

}
