package com.diemme.business.impl;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.diemme.exception.ResourceNotFoundException;
import com.diemme.exception.BusinessException;
import com.diemme.business.interfaces.NewsService;
import com.diemme.domain.mysql.NewsShowcase;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mysql.NewsShowcaseRepository;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsShowcaseRepository newsShowcaseRepository;

	private com.diemme.util.CompressionUtils CompressionUtils;

	@Override
	public List<NewsShowcase> findAllNewsShowcases() throws BusinessException {

		return newsShowcaseRepository.findAll();
	}

	@Override
	public Optional<NewsShowcase> findNewsShowcase(Long id) throws BusinessException {

		return newsShowcaseRepository.findById(id);
	}

	@Override
	public NewsShowcase getNews(Long id) throws BusinessException {

		return newsShowcaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("NewsShowcase", "id", id));
	}

	@Override
	public NewsShowcase saveNews(NewsShowcase news) throws BusinessException {
		return newsShowcaseRepository.save(news);
	}

	@SuppressWarnings("static-access")
	@Override
	public void createNews(NewsShowcase news, MultipartFile contentImg, User userAuth) throws BusinessException {

		byte[] bytes = new byte[(int) contentImg.getSize()];
		byte[] byteCompress = new byte[0];

		try {
			byteCompress = CompressionUtils.compress(bytes);
			bytes = contentImg.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
		}

		news.setContentImg(bytes);
		news.setCompressImg(byteCompress);
		news.setUser(userAuth);
		newsShowcaseRepository.save(news);
	}

	@SuppressWarnings("static-access")
	@Override
	public void updateNews(Long id, NewsShowcase news, MultipartFile contentImg, User userAuth)
			throws BusinessException {

		NewsShowcase newsOld = new NewsShowcase();
		NewsShowcase newsSave = new NewsShowcase();
		byte[] bytes = new byte[(int) contentImg.getSize()];
		byte[] byteCompress = new byte[0];

		newsOld = newsShowcaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("NewsShowcase", "id", id));

		ZonedDateTime dateCreation = newsOld.getInsertDate();

		if (contentImg.isEmpty()) {

			news.setContentImg(newsOld.getContentImg());
			news.setCompressImg(newsOld.getCompressImg());
			news.setModifyDate(ZonedDateTime.now());

		} else {

			try {
				byteCompress = CompressionUtils.compress(bytes);
				bytes = contentImg.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
			news.setContentImg(bytes);
			news.setCompressImg(byteCompress);
		}

		news.setUser(userAuth);
		newsSave = newsShowcaseRepository.save(news);
		newsSave.setInsertDate(dateCreation);
		newsShowcaseRepository.save(newsSave);

	}

	@Override
	public Page<NewsShowcase> getAllNewsPageable(Integer page, Integer size) throws BusinessException {
		return newsShowcaseRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public void deleteNews(Long id) throws BusinessException {
		newsShowcaseRepository.deleteById(id);
	}

}
