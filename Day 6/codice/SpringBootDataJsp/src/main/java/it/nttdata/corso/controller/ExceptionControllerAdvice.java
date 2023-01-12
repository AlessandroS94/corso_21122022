package it.nttdata.corso.controller;


import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception e) {
        ModelAndView mav = new ModelAndView("exception");
        mav.addObject("name", e.getClass().getSimpleName());
        mav.addObject("message", e.getMessage());

        return mav;
    }
    
    @ExceptionHandler(DataAccessException.class)
    public ModelAndView exception(DataAccessException e) {
        ModelAndView mav = new ModelAndView("exception");
        mav.addObject("name", e.getClass().getSimpleName());
        mav.addObject("message", e.getMessage());

        return mav;
    }
}
