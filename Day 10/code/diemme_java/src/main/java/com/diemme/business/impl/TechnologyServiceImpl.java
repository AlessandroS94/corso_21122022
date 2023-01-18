package com.diemme.business.impl;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.TechnologyService;
import com.diemme.domain.mysql.TechnologyShowcase;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mysql.TechnologyShowcaseRepository;

@Service
@Transactional
public class TechnologyServiceImpl implements TechnologyService {

	@Autowired
	private TechnologyShowcaseRepository technologyShowcaseRepository;

	private com.diemme.util.CompressionUtils CompressionUtils;

	@Override
	public List<TechnologyShowcase> getAllTecnology() throws BusinessException {

		return technologyShowcaseRepository.findAll();
	}

	@Override
	public Page<TechnologyShowcase> getAllTecnologyPageable(Integer page, Integer size) throws BusinessException {

		return technologyShowcaseRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public TechnologyShowcase getTecnology(Long id) throws BusinessException {

		return technologyShowcaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TechnologyShowcase", "id", id));
	}

	@Override
	public TechnologyShowcase saveTechnology(TechnologyShowcase technology) throws BusinessException {

		return technologyShowcaseRepository.save(technology);
	}

	@SuppressWarnings("static-access")
	@Override
	public void createTechnology(TechnologyShowcase technology, MultipartFile contentImg, User userAuth)
			throws BusinessException {

		byte[] bytes = new byte[(int) contentImg.getSize()];
		byte[] byteCompress = new byte[0];

		try {
			byteCompress = CompressionUtils.compress(bytes);
			bytes = contentImg.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}

		technology.setContentImg(bytes);
		technology.setCompressImg(byteCompress);
		technology.setUser(userAuth);
		technologyShowcaseRepository.save(technology);

	}

	@SuppressWarnings("static-access")
	@Override
	public void updateTechnology(Long id, TechnologyShowcase technology, MultipartFile contentImg, User userAuth)
			throws BusinessException {

		byte[] bytes = new byte[(int) contentImg.getSize()];
		byte[] byteCompress = new byte[0];
		TechnologyShowcase technologyOld = new TechnologyShowcase();
		TechnologyShowcase technologySave = new TechnologyShowcase();
		technologyOld = technologyShowcaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("TechnologyShowcase", "id", id));
		ZonedDateTime dateCreation = technologyOld.getInsertDate();

		if (contentImg.isEmpty()) {
			technology.setContentImg(technologyOld.getContentImg());
			technology.setCompressImg(technologyOld.getCompressImg());
			technology.setModifyDate(ZonedDateTime.now());

		} else {
			try {
				byteCompress = CompressionUtils.compress(bytes);
				bytes = contentImg.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
			technology.setContentImg(bytes);
			technology.setCompressImg(byteCompress);
		}

		technology.setUser(userAuth);
		technologySave = technologyShowcaseRepository.save(technology);
		technologySave.setInsertDate(dateCreation);
		technologyShowcaseRepository.save(technologySave);

	}

	@Override
	public void deleteTechnology(Long id) throws BusinessException {

		technologyShowcaseRepository.deleteById(id);
	}

}
