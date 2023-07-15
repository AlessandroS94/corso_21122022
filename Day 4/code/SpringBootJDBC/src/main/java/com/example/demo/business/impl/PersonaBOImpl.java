package com.example.demo.business.impl;

import com.example.demo.DAO.PersonaDaoJdbc;
import com.example.demo.Domain.Persona;
import com.example.demo.business.interfaces.PersonaBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaBOImpl implements PersonaBO {
    @Autowired
    PersonaDaoJdbc personaDao;

    @Override
    public List<Persona> findAllPersona(){
        return personaDao.findAll();
    }

    @Override
    public Persona findById(Long id){
        return personaDao.findById(id);
    }
    @Override
    public void create(Persona persona){
        personaDao.save(persona);
    }
    @Override
    public void update(Persona persona){
        personaDao.update(persona);
    }
    @Override
    public void delete(Long id){
        personaDao.delete(id);
    }

}
