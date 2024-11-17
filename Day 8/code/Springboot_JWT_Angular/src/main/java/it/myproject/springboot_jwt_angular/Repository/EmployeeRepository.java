package it.myproject.springboot_jwt_angular.Repository;

import it.myproject.springboot_jwt_angular.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}