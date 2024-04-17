package it.learning.springbootjparepositoryquery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    @GetMapping("")
    ResponseEntity<?> getAllPath(){
        Map<String,String> info = new HashMap<>();
        info.put("Swagger ", "http://localhost:8080/swagger-ui/index.html");
        return new ResponseEntity<>(info, HttpStatus.OK);
    }
}
