package com.diemme.business.interfaces;

import java.util.List;

import com.diemme.exception.BusinessException;
import org.springframework.data.domain.Page;

import com.diemme.domain.mysql.ContactShowcase;
import com.diemme.domain.mysql.User;

public interface ContactService {
	
	List<ContactShowcase> findAllContactShowcases() throws BusinessException;

	void deleteContactShowcase(Long id) throws BusinessException;

	Page<ContactShowcase> getAllContactPageable(Integer page, Integer size) throws BusinessException;

	void createContact(ContactShowcase contact, User userAuth) throws BusinessException;

	void updateContact(Long id, ContactShowcase contactShowcase, User userAuth) throws BusinessException;

	ContactShowcase findContactShowcase(Long id) throws BusinessException;

	ContactShowcase findActiveContac() throws BusinessException;





}
