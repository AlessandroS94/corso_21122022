package it.capoweb.example.repository;

import it.capoweb.example.domain.Employee;

import java.util.List;
import org.springframework.data.jpa.repository.Query;


public interface EmployeeRepositoryCustom {
    @Query(value = "FROM Employee e WHERE e.department.id = ?1")
    public List<Employee> findByDepartment(int depId);
}
