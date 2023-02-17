package com.ntt.springbootdemo.controller;

import com.ntt.springbootdemo.domain.Persona;
import com.ntt.springbootdemo.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonaController {
    @Autowired
    PersonaRepository personaRepository;

    @PostMapping("/persona")
    public ResponseEntity<Persona> create(@RequestBody Persona persona){
        Persona persona1 = personaRepository.save(persona);
        return new ResponseEntity<>(persona1, HttpStatus.CREATED);
    }

    @GetMapping("/persone")
    public ResponseEntity<List<Persona>> findAll(){
        List<Persona> personaList = personaRepository.findAll();
        return new ResponseEntity<List<Persona>>(personaList, HttpStatus.OK);
    }

}
