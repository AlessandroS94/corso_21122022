package it.course.rest.examplecourse.business.impl;

import it.course.rest.examplecourse.business.interfaces.RoleBO;
import it.course.rest.examplecourse.model.ERole;
import it.course.rest.examplecourse.model.Role;
import it.course.rest.examplecourse.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleBOImpl implements RoleBO {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role getRole(ERole eRole) {
        switch (eRole) {
            case ROLE_ADMIN:
                return roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            case ROLE_MODERATOR:
                return roleRepository.findByName(ERole.ROLE_MODERATOR)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            default:
                return roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        }
    }

}
