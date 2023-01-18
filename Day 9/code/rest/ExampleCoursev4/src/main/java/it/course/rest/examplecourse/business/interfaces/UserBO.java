package it.course.rest.examplecourse.business.interfaces;

import it.course.rest.examplecourse.model.Role;
import it.course.rest.examplecourse.model.User;
import it.course.rest.examplecourse.payload.request.SignupRequest;

import java.util.Set;

public interface UserBO {
    User saveUser(User user);
    boolean existUser(SignupRequest signUpRequest);
    boolean existEmail(SignupRequest signUpRequest);
    User addUserWithRole(User user, Role role);
}
