package it.course.rest.examplecourse.business;

import it.course.rest.examplecourse.model.User;
import it.course.rest.examplecourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class register {
    @Autowired
    UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);
    }
}
