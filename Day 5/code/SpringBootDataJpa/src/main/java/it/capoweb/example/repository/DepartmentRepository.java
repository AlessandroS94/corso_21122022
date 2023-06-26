package it.capoweb.example.repository;

import it.capoweb.example.domain.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
}
