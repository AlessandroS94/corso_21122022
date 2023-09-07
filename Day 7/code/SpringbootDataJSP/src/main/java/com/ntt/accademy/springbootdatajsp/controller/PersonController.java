package com.ntt.accademy.springbootdatajsp.controller;

import com.ntt.accademy.springbootdatajsp.domain.Person;
import com.ntt.accademy.springbootdatajsp.respository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = {"/","/people"})
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("/home.jsp");
        List<Person> people = personRepository.findAll();
        modelAndView.addObject("peopleList",people);
        return modelAndView;
    }

    @PostMapping("/person/create")
    public  ModelAndView create(@RequestParam String name, @RequestParam String surname){
        Person person = new Person();
        person.setName(name);
        person.setSurname(surname);
        personRepository.save(person);
        return new ModelAndView("redirect:/");
    }
}
