package it.academy.corso.controller;

import it.academy.corso.model.Student;
import it.academy.corso.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

   @GetMapping("/student")
    public ResponseEntity<List<Student>> getStudents (){
        List<Student> studentArrayList = new ArrayList<Student>();
        studentRepository.findAll().forEach(studentArrayList::add);
        if (studentArrayList.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    return new ResponseEntity<>(studentArrayList, HttpStatus.OK);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createTutorial(@RequestBody Student student) {
        Student _student = studentRepository.save(student);
        return new ResponseEntity<>(_student, HttpStatus.CREATED);
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
        studentRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
