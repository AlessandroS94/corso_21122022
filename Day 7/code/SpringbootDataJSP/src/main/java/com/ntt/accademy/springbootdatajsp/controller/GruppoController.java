package com.ntt.accademy.springbootdatajsp.controller;

import com.ntt.accademy.springbootdatajsp.domain.Gruppo;
import com.ntt.accademy.springbootdatajsp.domain.Person;
import com.ntt.accademy.springbootdatajsp.respository.GruppoRepository;
import com.ntt.accademy.springbootdatajsp.respository.PersonRepository;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/gruppi")
public class GruppoController {

    @Autowired
    private GruppoRepository gruppoRepository;
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("")
    private ModelAndView viewListGruppo(){
        return new ModelAndView("gruppo.jsp","gruppi",gruppoRepository.findAll());
    }
    @GetMapping("/create")
    private ModelAndView createGruppo(){
        return new ModelAndView("../creaGruppo.jsp");
    }
    @PostMapping("/createGruppo")
    private ModelAndView createGruppo(@RequestParam String name){
        try
        {
            Gruppo gruppo = new Gruppo();
            gruppo.setName(name);
            gruppoRepository.save(gruppo);
            return new ModelAndView("../creaGruppo.jsp", "operation", true);
        }
        catch (DataAccessException accessException){
            accessException.printStackTrace();
            return new ModelAndView("../creaGruppo.jsp", "operation", false);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ModelAndView("../creaGruppo.jsp", "operation", false);
        }
    }
    @GetMapping("/addPersonTOGruppo")
    private ModelAndView addPersonToGruppo(){
        ModelAndView modelAndView =  new ModelAndView("../addPersonTOGruppo.jsp","gruppi",gruppoRepository.findAll());
        modelAndView.addObject("persone",personRepository.findAll());
        return modelAndView;
    }
    @PostMapping("/addPersonTOGruppo")
    private ModelAndView addPersonToGruppo(@RequestParam Long idPerson,@RequestParam Long idGruppo){
        try
        {
            Gruppo gruppo = gruppoRepository.findById(idGruppo).orElseThrow(()-> new Exception());
            Person person = personRepository.findById(idPerson).orElseThrow(()-> new Exception());
            gruppo.addPerson(person);
            gruppoRepository.save(gruppo);

            return new ModelAndView("../addPersonTOGruppo.jsp", "operation", true);
        }
        catch (DataAccessException accessException){
            accessException.printStackTrace();
            return new ModelAndView("../addPersonTOGruppo.jsp", "operation", false);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ModelAndView("../addPersonTOGruppo.jsp", "operation", false);
        }
    }
}
