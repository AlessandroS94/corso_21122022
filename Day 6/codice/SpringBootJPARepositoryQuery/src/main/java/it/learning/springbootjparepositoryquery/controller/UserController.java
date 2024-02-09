package it.learning.springbootjparepositoryquery.controller;

import it.learning.springbootjparepositoryquery.model.User;
import it.learning.springbootjparepositoryquery.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("")
    public ResponseEntity<?> getUsers(){
        return new ResponseEntity<>(userRepository.findAll(),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return new ResponseEntity<>(userRepository.save(user),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@NonNull @RequestBody User user, @NonNull @PathVariable("id")Long id){
        user.setId(id);
        ResponseEntity<?> responseEntity = new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchUser(@PathVariable String name){
        return new ResponseEntity<>(userRepository.findByNameStartsWith(name),HttpStatus.OK);
    }

    @GetMapping("/notContain/{name}")
    public ResponseEntity<?> searchAgeUser(@PathVariable String name){
        return new ResponseEntity<>(userRepository.findByNameNotContainsOrderByNameAsc(name),HttpStatus.OK);
    }

    @GetMapping("/searchByBirthdayGreaterThanEqual/{data}")
    public ResponseEntity<?> searchByAgeUser(@PathVariable Date data){
        return new ResponseEntity<>(userRepository.findByBirthdayGreaterThanEqual(data),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
