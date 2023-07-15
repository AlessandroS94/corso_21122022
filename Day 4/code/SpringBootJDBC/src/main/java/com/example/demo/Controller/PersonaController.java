package com.example.demo.Controller;

import com.example.demo.Domain.Persona;
import com.example.demo.business.interfaces.PersonaBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaBO personaBO;

    @GetMapping
    public ResponseEntity<List<Persona>> list() {
        return new ResponseEntity<>(personaBO.findAllPersona(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> get(@PathVariable Long id) {
        return new ResponseEntity<>(personaBO.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Persona persona) {
        personaBO.create(persona);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Persona persona) {
        persona.setId(id);
        personaBO.update(persona);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personaBO.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
