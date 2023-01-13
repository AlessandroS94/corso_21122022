package it.nntdata.corso.springjsp.controller;

import it.nntdata.corso.springjsp.business.interfaces.WebSiteInfoBO;
import it.nntdata.corso.springjsp.model.WebSiteInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebSiteInfoController {
    @Autowired
    WebSiteInfoBO webSiteInfoBO;

    @GetMapping(path = {"/","/index"})
    public ModelAndView home(){
        WebSiteInfo webSiteInfo = webSiteInfoBO.getWebSiteInfo();

        return new ModelAndView("/jsp/index.jsp","Info",webSiteInfo);
    }
}
