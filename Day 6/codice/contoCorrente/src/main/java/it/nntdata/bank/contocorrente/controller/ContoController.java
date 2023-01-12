package it.nntdata.bank.contocorrente.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class ContoController {
    @RequestMapping(value = "/conto", method = RequestMethod.GET)
    public String viewConto(Model model) {

        model.addAttribute("nameConto", "Alessandro");
        return "conto";
    }

}
