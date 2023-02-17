package com.ntt.demo0.controller;

import com.ntt.demo0.domain.Gruppo;
import com.ntt.demo0.domain.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gruppo")
public class GruppoController {
    @GetMapping("")
    public ResponseEntity<?> returnGruppo(){
        Gruppo gruppo = new Gruppo();
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona(1,"Ale","Sall"));
        personas.add(new Persona(1,"Ale1","Sall1"));
        personas.add(new Persona(1,"Ale2","Sall2"));
        personas.add(new Persona(1,"Ale3","Sall3"));
        gruppo.setPersonas(personas);
        return new ResponseEntity<>(gruppo, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createPersona(@RequestBody Gruppo gruppo){
        return new ResponseEntity<>(gruppo,HttpStatus.CREATED);
    }
}
