package com.diemme.business.impl;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diemme.exception.ResourceNotFoundException;
import com.diemme.exception.BusinessException;
import com.diemme.business.interfaces.ContactService;
import com.diemme.domain.mysql.ContactShowcase;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mysql.ContactShowcaseRepository;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactShowcaseRepository contactShowcaseRepository;

	@Override
	public List<ContactShowcase> findAllContactShowcases() throws BusinessException {

		return contactShowcaseRepository.findAll();
	}

	@Override
	public ContactShowcase findContactShowcase(Long id) throws BusinessException {

		return contactShowcaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contact", "id", id));
	}

	@Override
	public ContactShowcase findActiveContac() throws BusinessException {

		return contactShowcaseRepository.findActiveContact();
	}

	@Override
	public void createContact(ContactShowcase contact, User userAuth) throws BusinessException {

		ContactShowcase contactShowcaseActive = contactShowcaseRepository.findActiveContact();

		if (contact.getActive()) {

			if (contactShowcaseRepository.findActiveContact() != null) {

				contactShowcaseActive = contactShowcaseRepository.findActiveContact();
				contactShowcaseActive.setActive(false);
				contactShowcaseRepository.save(contactShowcaseActive);
				contact.setActive(true);
				contact.setUser(userAuth);
				contactShowcaseRepository.save(contact);

			} else {

				contact.setUser(userAuth);
				contact.setActive(true);
				contactShowcaseRepository.save(contact);

			}

		} else if (!contact.getActive()) {

			contact.setUser(userAuth);
			contactShowcaseRepository.save(contact);

		}

	}

	@Override
	public void updateContact(Long id, ContactShowcase contactShowcase, User userAuth) throws BusinessException {

		ContactShowcase contactShowcaseActive = new ContactShowcase();
		ContactShowcase contactOld = new ContactShowcase();

		contactOld = contactShowcaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Contact", "id", id));

		ZonedDateTime dateCreation = contactOld.getInsertDate();

		if (contactShowcase.getActive()) {

			if (contactShowcaseRepository.findActiveContact() != null) {

				contactShowcaseActive = contactShowcaseRepository.findActiveContact();
				contactShowcaseActive.setActive(false);
				contactShowcaseRepository.save(contactShowcaseActive);
				contactShowcase.setActive(true);
				contactShowcase.setUser(userAuth);
				contactShowcase.setInsertDate(dateCreation);
				contactShowcaseRepository.save(contactShowcase);

			} else {

				contactShowcase.setUser(userAuth);
				contactShowcase.setActive(true);
				contactShowcase.setInsertDate(dateCreation);
				contactShowcaseRepository.save(contactShowcase);

			}

		} else if (!contactShowcase.getActive()) {

			contactShowcase.setUser(userAuth);
			contactShowcase.setInsertDate(dateCreation);
			contactShowcaseRepository.save(contactShowcase);

		}

	}

	@Override
	public Page<ContactShowcase> getAllContactPageable(Integer page, Integer size) throws BusinessException {
		return contactShowcaseRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public void deleteContactShowcase(Long id) throws BusinessException {

		contactShowcaseRepository.deleteById(id);
	}

}
