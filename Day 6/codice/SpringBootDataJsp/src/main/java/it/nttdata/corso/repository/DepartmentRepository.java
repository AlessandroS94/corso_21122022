package it.nttdata.corso.repository;

import it.nttdata.corso.domain.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface DepartmentRepository extends CrudRepository<Department, Integer> {
}
