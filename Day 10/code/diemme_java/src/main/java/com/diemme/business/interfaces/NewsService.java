package com.diemme.business.interfaces;

import java.util.List;
import java.util.Optional;

import com.diemme.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.diemme.domain.mysql.NewsShowcase;
import com.diemme.domain.mysql.User;

public interface NewsService {

	List<NewsShowcase> findAllNewsShowcases() throws BusinessException;

	Optional<NewsShowcase> findNewsShowcase(Long id) throws BusinessException;

	NewsShowcase getNews(Long id) throws BusinessException;

	NewsShowcase saveNews(NewsShowcase news) throws BusinessException;

	Page<NewsShowcase> getAllNewsPageable(Integer page, Integer size) throws BusinessException;

	void deleteNews(Long id) throws BusinessException;

	void createNews(NewsShowcase news, MultipartFile contentImg, User userAuth) throws BusinessException;

	void updateNews(Long id, NewsShowcase news, MultipartFile contentImg, User userAuth) throws BusinessException;

}
