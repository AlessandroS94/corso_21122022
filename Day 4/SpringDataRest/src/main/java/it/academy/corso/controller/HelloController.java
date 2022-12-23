package it.academy.corso.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HelloController {


   @GetMapping("/hi")
    public ResponseEntity<String> hi (){
        String message = "ciao ";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }











    @GetMapping("/hi2")
    public ResponseEntity<Map<String,String>> hi2 (){
        Map <String,String> value = new HashMap();
        value.put("Message","AUGURI");
        return new ResponseEntity<>(value, HttpStatus.OK);
    }

}
