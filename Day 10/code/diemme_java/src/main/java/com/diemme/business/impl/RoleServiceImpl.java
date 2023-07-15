package com.diemme.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diemme.business.interfaces.RoleService;
import com.diemme.domain.mysql.Role;
import com.diemme.repository.mysql.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	@Transactional
	public Role findByRole(String name) {

		return roleRepository.findByRole(name);
	}

	@Override
	@Transactional
	public List<Role> getAllRoles() {
		return roleRepository.findAll();
	}

}
