package com.diemme.exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ControllerAdvice
public class ExceptionHandlerController
{
    private static final Logger logger = LogManager.getLogger(ExceptionHandlerController.class);
    @ExceptionHandler(Exception.class)
    public ModelAndView exception(Exception ex) {
        logger.error(ex.getMessage());
        // Logica per gestire l'eccezione
        // Puoi registrare i dettagli dell'eccezione nel file di log
        ModelAndView modelAndView = new ModelAndView("/error/error.html");
        return modelAndView;
    }


}
