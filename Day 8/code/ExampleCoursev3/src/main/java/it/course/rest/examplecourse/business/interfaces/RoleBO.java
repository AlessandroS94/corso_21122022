package it.course.rest.examplecourse.business.interfaces;

import it.course.rest.examplecourse.model.ERole;
import it.course.rest.examplecourse.model.Role;

public interface RoleBO {
    Role getRole(ERole eRole);
}
