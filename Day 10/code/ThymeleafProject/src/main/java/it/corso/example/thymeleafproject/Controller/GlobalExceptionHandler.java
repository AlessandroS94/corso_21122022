package it.corso.example.thymeleafproject.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception ex) {
        model.addAttribute("errorMessage", "Si è verificato un errore durante l'elaborazione della richiesta.");
        model.addAttribute("exceptionMessage", ex.getMessage());
        return "error";
    }
}
