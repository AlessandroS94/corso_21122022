package it.course.rest.examplecourse;

import it.course.rest.examplecourse.model.ERole;
import it.course.rest.examplecourse.model.Role;
import it.course.rest.examplecourse.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CreareRole implements CommandLineRunner {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Set<Role> roles = new HashSet<>();
        Role role1 = new Role(ERole.ROLE_USER);
        Role role2 = new Role(ERole.ROLE_ADMIN);
        Role role3 = new Role(ERole.ROLE_MODERATOR);
        role1.setId(1);
        role2.setId(2);
        role3.setId(3);
        roles.add(role1);
        roles.add(role2);
        roles.add(role3);
        roleRepository.save(role1);roleRepository.save(role2);roleRepository.save(role3);
        roleRepository.saveAll(roles);
    }
}
