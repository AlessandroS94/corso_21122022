package it.capoweb.example.business.impl;

import it.capoweb.example.business.EmployeeBO;
import it.capoweb.example.domain.Employee;
import it.capoweb.example.repository.EmployeeRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class EmployeeBoImpl implements EmployeeBO {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Integer uid) {
        employeeRepository.deleteById(uid);
    }

    @Override
    public List<Employee> findAllUsers() {       
         return (List<Employee>) employeeRepository
                 .findAll();
    }

    @Override
    public List<Employee> findByEmail(String email) {
       return employeeRepository
                .findByEmailIgnoreCaseContaining(email);
    }

    @Override
    public List<Employee> findByDepartmentName(String name) {
         return employeeRepository.findByDepartmentNameContainingIgnoringCase(name);
    }

    @Override
    public List<Employee> findByGenderAndDepartment(char gender, String depName) {
        return employeeRepository.
               findByGenderAndDepartmentName(gender, depName);
    }
}