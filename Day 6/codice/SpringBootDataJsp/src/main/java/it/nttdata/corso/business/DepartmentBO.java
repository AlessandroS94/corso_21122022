package it.nttdata.corso.business;

import it.nttdata.corso.domain.Department;
import java.util.List;
import java.util.Optional;



public interface DepartmentBO {
  public void delete(int uid);
 
  public List<Department> findAllDepartments();
 
  public Optional<Department> findByUid(int uid);
   
  public void save(Department department);
 
  public void update(Department department);  
}
