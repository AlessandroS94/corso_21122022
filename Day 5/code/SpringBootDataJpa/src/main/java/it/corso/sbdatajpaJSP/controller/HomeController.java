package it.corso.sbdatajpaJSP.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 *
 * @author Luigi Brandolini
 */
@Controller
public class HomeController {
    
    @GetMapping(path = {"/", "/index", "/home"})
    public String home() {
        return "index";
    }
}
