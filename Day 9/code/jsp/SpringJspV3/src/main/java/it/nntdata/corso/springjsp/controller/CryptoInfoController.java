package it.nntdata.corso.springjsp.controller;


import it.nntdata.corso.springjsp.business.interfaces.CryptoInfoBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("admin/")
public class CryptoInfoController {
    @Autowired
    CryptoInfoBO cryptoInfoBO;

    @GetMapping("/insert")
    public ModelAndView cryptoInsert() {return new ModelAndView("/jsp/createCryptoInfo.jsp");}

    @PostMapping("/insert")
    public ModelAndView crytpoInsert(@RequestParam String name, String url, MultipartFile image) {
        System.out.println(image.getContentType());
        try {
            cryptoInfoBO.insertCrypto(name,url,image);
            return new ModelAndView("/jsp/error.jsp","not_operation",true);
        } catch (IOException e) {
        }
        return new ModelAndView("/jsp/createCryptoInfo.jsp","operation",true);
    }
}
