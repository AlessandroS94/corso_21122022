package it.myproject.springboot_jwt_angular.repository;

import it.myproject.springboot_jwt_angular.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}