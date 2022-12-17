package it.ntt.academy.repository;

import it.ntt.academy.domain.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
}
