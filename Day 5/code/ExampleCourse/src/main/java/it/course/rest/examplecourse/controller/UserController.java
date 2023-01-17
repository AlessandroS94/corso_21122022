package it.course.rest.examplecourse.controller;

import it.course.rest.examplecourse.exception.ResourceNotFoundException;
import it.course.rest.examplecourse.model.User;
import it.course.rest.examplecourse.repository.CourseRepository;
import it.course.rest.examplecourse.repository.RoleRepository;
import it.course.rest.examplecourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        User _user = userRepository.getReferenceById(id);
        return new ResponseEntity<User>(_user,HttpStatus.OK);
    }
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        //return new ResponseEntity<>(user,HttpStatus.OK);
        User _user = userRepository.save(user);
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }

    /*
    @PostMapping("/course/{courseId}/user")
    public ResponseEntity<User> addTag(@PathVariable(value = "courseId") Long courseId, @RequestBody User userRequest) {
        User user = courseRepository.findById(courseId).map(course -> {
            long userId = userRequest.getId();
            // user is existed
            if (userId != 0L) {
                User _user = userRepository.findById(userId)
                        .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));
                course.addUser(_user);
                courseRepository.save(course);
                return _user;
            }
            // add and create new User
            course.addUser(userRequest);
            return userRepository.save(userRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + courseId));

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
*/
}
