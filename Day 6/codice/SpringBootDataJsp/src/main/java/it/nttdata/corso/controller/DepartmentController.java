package it.nttdata.corso.controller;

import it.nttdata.corso.business.DepartmentBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import it.nttdata.corso.domain.Department;
import it.nttdata.corso.util.ClientResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentBO departmentBO;

    Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String departmentHome(Model model) {
        logger.debug("department home() invoked");

        List<Department> list = departmentBO.findAllDepartments();
        model.addAttribute("list", list);
        return "departments";
    }

   @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String createDepartment(@RequestParam(value = "name", required = true) String name, Model model) {
        logger.debug("department registration() invoked");

        Department department = new Department(name);
        List<Department> list;
        try {
            departmentBO.save(department);
            list = departmentBO.findAllDepartments();
            model.addAttribute("response", new ClientResponse(true, "Operation success"));
            model.addAttribute("list", list);
        } catch (DataAccessException e) {
            logger.error("A DataAccessException has occurred: {}", e);
            model.addAttribute("response", new ClientResponse(false, e.getMessage()));
        }

        return "departments";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteDepartment(@RequestParam(value = "id", required = true) int uid,
           Model model) {
        logger.debug("delete registration() invoked");

        List<Department> list;
        try {
            departmentBO.delete(uid);
            list = departmentBO.findAllDepartments();
            model.addAttribute("response",
                    new ClientResponse(true, "Operation success"));
            model.addAttribute("list", list);
        } catch (DataAccessException e) {
            logger.error("A DataAccessException has occurred: {}", e);
            model.addAttribute("response", new ClientResponse(false, e.getMessage()));
            //return "redirect:/department/home";
        }

        return "departments";
    }
}
