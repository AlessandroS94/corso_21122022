package com.diemme.business;

import java.util.List;

import com.diemme.domain.mysql.Role;

public interface RoleService {
	
	Role  findByRole (String name);
	List<Role> getAllRoles();


}
