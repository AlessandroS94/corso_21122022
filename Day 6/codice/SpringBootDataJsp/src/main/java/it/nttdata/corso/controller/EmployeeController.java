package it.nttdata.corso.controller;

import it.nttdata.corso.business.DepartmentBO;
import it.nttdata.corso.business.EmployeeBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import it.nttdata.corso.domain.Department;
import it.nttdata.corso.domain.Employee;
import it.nttdata.corso.util.ClientResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeBO employeeBO;

    @Autowired
    private DepartmentBO departmentBO;

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping({"/home", "/"})
    public ModelAndView employeeHome() {
        logger.debug("employee home() invoked");
        ModelAndView model = new ModelAndView("employees", "newEmployee", new Employee());
        
        List<Employee> employees;
        List<Department> departments;
      
        employees = employeeBO.findAllUsers();
        departments = departmentBO.findAllDepartments();
        model.addObject("employees", employees);
        model.addObject("departments", departments);
        model.setViewName("employees");

        return model;
    }

    @PostMapping("/registration")
    public ModelAndView employeeRegistration(Employee employee) {
        logger.debug("employee registration() invoked");

        ModelAndView model = new ModelAndView("employees", 
                "newEmployee", new Employee());
      
        List<Employee> employees;
        List<Department> departments;

        try {
            employeeBO.save(employee);
            employees = employeeBO.findAllUsers();
            departments = departmentBO.findAllDepartments();

            model.addObject("response", 
                    new ClientResponse(true, "Operation success"));
            model.addObject("employees", employees);
            model.addObject("departments", departments);
        } catch (DataAccessException e) {
            logger.error("A DataAccessException has occurred: {}", e);
            model.addObject("response", new ClientResponse(false, e.getMessage()));
        }

        return model;
    }

    @PostMapping(value = "/searchByEmail")
    public ModelAndView searchByEmail(@RequestParam String email) {
        logger.debug("employee searchByEmail() invoked, email=" + email);

        ModelAndView model = new ModelAndView("employees", "newEmployee", new Employee());
     
        List<Employee> employees;
        List<Department> departments;

        try {
            employees = employeeBO.findByEmail(email);
            departments = departmentBO.findAllDepartments();

            model.addObject("employees", employees);
            model.addObject("departments", departments);
        } catch (DataAccessException e) {
            logger.error("A DataAccessException has occurred: {}", e);
            model.addObject("response", new ClientResponse(false, e.getMessage()));
        }

        return model;
    }
    
    @PostMapping(value = "/search/genderAndDepartment")
    public ModelAndView searchByGenderAndDepartmentName(@RequestParam char gender, 
            @RequestParam String depName) {
        logger.debug("employee searchByGenderAndDepartmentName() invoked, "
                + "depName=" + depName);

        ModelAndView model = new ModelAndView("employees", "newEmployee",
                new Employee());
     
        List<Employee> employees;
        List<Department> departments;

        try {
            employees = employeeBO.findByGenderAndDepartment(gender, depName);
            departments = departmentBO.findAllDepartments();

            model.addObject("employees", employees);
            model.addObject("departments", departments);
        } catch (DataAccessException e) {
            logger.error("A DataAccessException has occurred: {}", e);
            model.addObject("response", new ClientResponse(false, e.getMessage()));
        }

        return model;
    }
    
    @PostMapping("/search/department")
    public ModelAndView searchByDepartment(@RequestParam String depName) {
        logger.debug("employee searchByDepartment() invoked, depName=" + depName);

        ModelAndView model = new ModelAndView("employees", "newEmployee",
                new Employee());
     
        List<Employee> employees;
        List<Department> departments;

        try {
            employees = employeeBO.findByDepartmentName(depName);
            departments = departmentBO.findAllDepartments();

            model.addObject("employees", employees);
            model.addObject("departments", departments);
        } catch (DataAccessException e) {
            logger.error("A DataAccessException has occurred: {}", e);
            model.addObject("response", new ClientResponse(false, e.getMessage()));
        }

        return model;
    }
    
    @GetMapping("/delete")
    public ModelAndView deleteEmployee(@RequestParam(value = "id", required = true) Integer uid) {
        logger.debug("delete() employee invoked");

        ModelAndView model = new ModelAndView("employees", "newEmployee", new Employee());
        model.setViewName("employees");

        List<Employee> employees;
        List<Department> departments;
        try {
            employeeBO.delete(uid);
            employees = employeeBO.findAllUsers();
            departments = departmentBO.findAllDepartments();
            model.addObject("response",
                    new ClientResponse(true, "Operation success"));
            model.addObject("departments", departments);
            model.addObject("employees", employees);
        } catch (DataAccessException e) {
            logger.error("A DataAccessException has occurred: {}", e);
            model.addObject("response", new ClientResponse(false, e.getMessage()));
        }

        return model;
    }
}
