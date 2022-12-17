package it.corso.sbdatajpaJSP.business;

import it.corso.sbdatajpaJSP.domain.Employee;

import java.util.List;

/**
 *
 * @author Luigi Brandolini
 */

public interface EmployeeBO {
  public void delete(Integer uid);
 
  public List<Employee> findAllUsers();
 
  public List<Employee> findByEmail(String email);
  
  public List<Employee> findByDepartmentName(String name);
  
  public List<Employee> findByGenderAndDepartment(char gender, String depName);
   
  public void save(Employee user);
 
  public void update(Employee user);  
}
