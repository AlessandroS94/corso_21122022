package it.nttdata.corso.business;

import it.nttdata.corso.domain.Employee;
import java.util.List;



public interface EmployeeBO {
  public void delete(Integer uid);
 
  public List<Employee> findAllUsers();
 
  public List<Employee> findByEmail(String email);
  
  public List<Employee> findByDepartmentName(String name);
  
  public List<Employee> findByGenderAndDepartment(char gender, String depName);
   
  public void save(Employee user);
 
  public void update(Employee user);  
}
