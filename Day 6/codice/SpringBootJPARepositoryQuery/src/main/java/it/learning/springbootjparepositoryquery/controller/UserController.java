package it.learning.springbootjparepositoryquery.controller;

import it.learning.springbootjparepositoryquery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getUsers(){
        return new ResponseEntity<>(userRepository.findAll(),HttpStatus.OK);
    }
/*
    @GetMapping("/name_not_null/{name}")
    public ResponseEntity<?> getUsersNotNull(@NonNull String name){
        return new ResponseEntity<>(userRepository.findUserByIdAndNameContaining(name),HttpStatus.OK);
    }*/
}
