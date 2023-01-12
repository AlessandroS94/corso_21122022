package it.nttdata.corso.business.impl;

import it.nttdata.corso.business.DepartmentBO;
import it.nttdata.corso.repository.DepartmentRepository;
import it.nttdata.corso.domain.Department;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class DepartmentBoImpl implements DepartmentBO {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void update(Department department) {
        departmentRepository.save(department);
    }
 
    @Override
    public void delete(int uid) {
        Optional<Department> departmentDb = departmentRepository.findById(uid);
        
        if ( departmentDb.isPresent() ) {
            Department department = departmentDb.get();
            department.getEmployees().forEach( e -> e.setDepartment(null) );
            department.getEmployees().clear();
            departmentRepository.delete(department);
        }
    }

    @Override
    public List<Department> findAllDepartments() {
        return (List<Department>) departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findByUid(int uid) throws DataAccessException {
        return departmentRepository.findById(uid);
    }
}
