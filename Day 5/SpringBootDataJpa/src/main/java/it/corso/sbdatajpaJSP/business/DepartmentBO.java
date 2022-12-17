package it.corso.sbdatajpaJSP.business;

import it.corso.sbdatajpaJSP.domain.Department;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Luigi Brandolini
 */

public interface DepartmentBO {
  public void delete(int uid);
 
  public List<Department> findAllDepartments();
 
  public Optional<Department> findByUid(int uid);
   
  public void save(Department department);
 
  public void update(Department department);  
}
