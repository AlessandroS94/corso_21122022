package com.example.demo.business.interfaces;

import com.example.demo.Domain.Persona;

import java.util.List;

public interface PersonaBO {
    List<Persona> findAllPersona();

    public Persona findById(Long id);

    public void create(Persona persona);

    public void update(Persona persona);

    public void delete(Long id);
}
