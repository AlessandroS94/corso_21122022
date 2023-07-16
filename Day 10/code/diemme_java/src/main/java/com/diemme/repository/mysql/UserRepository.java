package com.diemme.repository.mysql;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.diemme.domain.mysql.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findByUserName(String userName);
	@Query("SELECT u FROM User u JOIN u.roles r WHERE r.role = :role")
	Set<User> findUserByRole(@Param("role") String role);

}
