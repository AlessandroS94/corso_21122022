package it.course.rest.examplecourse.repository;

import it.course.rest.examplecourse.model.ERole;
import it.course.rest.examplecourse.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}