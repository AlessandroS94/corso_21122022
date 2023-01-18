package com.diemme.business;

import java.util.Set;

import org.springframework.data.domain.Page;

import com.diemme.domain.mysql.User;

public interface UserService {
	
    User findUserByEmail(String email) throws BusinessException;
    
    User findUserByUserName(String userName)throws BusinessException;
    
    User saveUser(User user)throws BusinessException;
    
    Set<User> getUsersByRole (String role)throws BusinessException;

	Page<User> getAllUserPageable(Integer page, Integer size) throws BusinessException;

	void deleteUser(Long id) throws BusinessException;

	User getUser(Long id)throws BusinessException;

	User createUser(User user) throws BusinessException;

	User updateUser(Long id, User userUpdate, User userExists) throws BusinessException;

    

}
