package com.diemme.business.impl;

import java.util.HashSet;
import java.util.Set;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.diemme.ResourceNotFoundException;
import com.diemme.business.BusinessException;
import com.diemme.business.UserService;
import com.diemme.domain.mysql.Role;
import com.diemme.domain.mysql.User;
import com.diemme.repository.mysql.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public User findUserByEmail(String email) throws BusinessException {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserByUserName(String userName) throws BusinessException {
		return userRepository.findByUserName(userName);
	}

	@Override
	public void deleteUser(Long id) throws BusinessException {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public User saveUser(User user) throws BusinessException {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User createUser(User user) throws BusinessException {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Set<Role> roles = new HashSet<Role>();
		roles.addAll(user.getRoles());
		user.setRoles(roles);
		user.setActive(true);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setAddressShipment(user.getAddressShipment());
		user.setAddressShipment(user.getAddressShipment());
		user.setFiscalCode(user.getFiscalCode());
		user.setPIva(user.getPIva());
		user.setCompanyName(user.getCompanyName());
		return user;
	}

	@Override
	@Transactional
	public User updateUser(Long id, User userUpdate, User userExists) throws BusinessException {

		if (userUpdate.getRoles() != null) {
			Set<Role> roles = new HashSet<Role>();
			roles.addAll(userUpdate.getRoles());
			userUpdate.setRoles(roles);

		}
		userUpdate.setInsertDate(userExists.getInsertDate());
		userUpdate.setActive(userUpdate.getActive());
		userUpdate.setPassword(userExists.getPassword());
		userUpdate.setAddressShipment(userUpdate.getAddressShipment());
		userUpdate.setFiscalCode(userUpdate.getFiscalCode());
		userUpdate.setPIva(userUpdate.getPIva());
		userUpdate.setCompanyName(userUpdate.getCompanyName());
		return userUpdate;
	}

	@Override
	public Set<User> getUsersByRole(String role) throws BusinessException {
		// TODO Auto-generated method stub
		return userRepository.findUserByRole(role);
	}

	@Override
	public Page<User> getAllUserPageable(Integer page, Integer size) throws BusinessException {
		return userRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public User getUser(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
	}

}
