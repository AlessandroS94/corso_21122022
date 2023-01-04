package it.course.rest.examplecourse.controller;

import it.course.rest.examplecourse.exception.ResourceNotFoundException;
import it.course.rest.examplecourse.model.Course;
import it.course.rest.examplecourse.model.User;
import it.course.rest.examplecourse.repository.CourseRepository;
import it.course.rest.examplecourse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;


    @PostMapping("/course")
    public ResponseEntity<Course> CreateCourse(@RequestBody Course course) {
        Course _course = courseRepository.save(course);
        return new ResponseEntity<Course>(_course, HttpStatus.CREATED);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Optional> getCourse(@PathVariable("id") Long id) {
        Optional<Course> _course = courseRepository.findById(id);
        if (_course.isPresent()) {
            return new ResponseEntity<Optional>(_course, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/course")
    public ResponseEntity<ArrayList<Course>> getAllCourse() {
        ArrayList<Course> _course = (ArrayList<Course>) courseRepository.findAll();
        return new ResponseEntity<>(_course, HttpStatus.OK);
    }
    @DeleteMapping("/course/{id}")
    public  ResponseEntity<HttpStatus> deleteCourse(@PathVariable long id){
        courseRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<Course> updateRoles(@PathVariable("id") long id, @RequestBody Course course) {
        Course _course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Course with id = " + id));
        _course.setName(course.getName());
        return new ResponseEntity<>(courseRepository.save(_course), HttpStatus.OK);
    }
    @PostMapping("/user/{id}/course")
    public ResponseEntity<Course> CreateCourseUser(@PathVariable Long id ,@RequestBody Course course) {
        User user = userRepository.getReferenceById(id);
        Set<User> userSet = new HashSet<>();
        userSet.add(user);
        course.setUsers(userSet);
        Course _course = courseRepository.save(course);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
