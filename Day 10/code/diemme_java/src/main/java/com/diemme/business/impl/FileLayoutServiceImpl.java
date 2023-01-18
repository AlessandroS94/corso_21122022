package com.diemme.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.FileLayoutService;
import com.diemme.domain.mysql.FileLayout;
import com.diemme.repository.mysql.FileLayoutRepository;

@Service
@Transactional
public class FileLayoutServiceImpl implements FileLayoutService {

	@Autowired
	FileLayoutRepository fileLayoutRepository;

	@Override
	public FileLayout getFileLayout(Long id) throws BusinessException {
		return fileLayoutRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("FileLayout", "id", id));
	}

	@Override
	public FileLayout saveFileLayout(FileLayout fileLayout) throws BusinessException {
		return fileLayoutRepository.save(fileLayout);
	}

	@Override
	public Page<FileLayout> getAllFileslayout(Integer page, Integer size, List<Long> id) throws BusinessException {
		return fileLayoutRepository.getFileLayoutsByLayoutId(PageRequest.of(page, size), id);

	}

}
