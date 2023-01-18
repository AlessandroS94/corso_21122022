package it.course.rest.examplecourse.business.impl;

import it.course.rest.examplecourse.business.interfaces.UserBO;
import it.course.rest.examplecourse.model.Role;
import it.course.rest.examplecourse.model.User;
import it.course.rest.examplecourse.payload.request.SignupRequest;
import it.course.rest.examplecourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserBOImpl implements UserBO {
    @Autowired
    UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User addUserWithRole(User user, Role role){
        Set <Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        return  userRepository.save(user);
    }

    @Override
    public boolean existUser(SignupRequest signUpRequest){
        return userRepository.existsByUsername(signUpRequest.getUsername());
    }
    @Override
    public boolean existEmail(SignupRequest signUpRequest){
        return userRepository.existsByUsername(signUpRequest.getUsername());
    }
}
