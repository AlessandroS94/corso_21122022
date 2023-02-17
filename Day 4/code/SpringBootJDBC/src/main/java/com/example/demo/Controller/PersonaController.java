package com.example.demo.Controller;

import com.example.demo.DAO.PersonaDaoJdbc;
import com.example.demo.Domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    PersonaDaoJdbc personaDao;

    @GetMapping
    public ResponseEntity<List<Persona>> list() {
        return new ResponseEntity<>(personaDao.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> get(@PathVariable Long id) {
        return new ResponseEntity<>(personaDao.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Persona persona) {
        personaDao.save(persona);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Persona persona) {
        persona.setId(id);
        personaDao.update(persona);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        personaDao.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
