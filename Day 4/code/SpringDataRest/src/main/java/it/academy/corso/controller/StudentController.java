package it.academy.corso.controller;

import it.academy.corso.model.Student;
import it.academy.corso.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students")
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
    @PutMapping("/student/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") long id,@RequestBody Student student) {
        Student student1 = studentRepository.getById(id);
        student1.setName(student.getName());
        studentRepository.save(student1);
        return new ResponseEntity<>(student1,HttpStatus.OK);
    }

   @GetMapping("/student/{id}")
   public ResponseEntity<?> getStudent(@PathVariable long id){
        Optional<Student> student = studentRepository.findById(id);
        return new ResponseEntity<>(student,HttpStatus.OK);
   }
}
