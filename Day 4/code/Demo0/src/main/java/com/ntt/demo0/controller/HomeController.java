package com.ntt.demo0.controller;

import com.ntt.demo0.domain.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {
    @GetMapping("/ciao")
    public ResponseEntity<String> returnCiao(){
        return new ResponseEntity<>("ciao", HttpStatus.OK);
    }
    @GetMapping("/persona")
    public ResponseEntity<Persona> returnPersona(){
        Persona persona = new Persona();
        persona.setName("Ale");
        persona.setSurname("Sall");
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }

    @GetMapping("/ciao3")
    public ResponseEntity<Map<String,String>> returnMap(){
        Map<String,String> mappa = new HashMap<>();
        mappa.put("ciao","ciao");
        ResponseEntity<Map<String,String>> responseEntity = new ResponseEntity<>(mappa,HttpStatus.OK);
        return  responseEntity;
    }

    @PostMapping("/persona")
    public ResponseEntity<?> createPersona(@RequestBody Persona persona){
        Persona persona1 = persona;
        return new ResponseEntity<>(persona1,HttpStatus.CREATED);

    }
    @PutMapping("/persona/{id}")
    public ResponseEntity<?> updatePersona(@PathVariable long id,@RequestBody Persona persona){
        Persona persona2 = new Persona(1,"Ale","Sall");
        if (persona2.getId() == id)
            return new ResponseEntity<>(persona2,HttpStatus.OK);
        return new ResponseEntity<>(persona,HttpStatus.NO_CONTENT);
    }



}
