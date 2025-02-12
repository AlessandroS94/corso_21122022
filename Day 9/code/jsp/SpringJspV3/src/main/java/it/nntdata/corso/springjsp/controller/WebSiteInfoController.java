package it.nntdata.corso.springjsp.controller;

import it.nntdata.corso.springjsp.business.interfaces.WebSiteInfoBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebSiteInfoController {
    @Autowired
    WebSiteInfoBO webSiteInfoBO;
    @GetMapping("/admin/createInfo")
    public ModelAndView createInfo(){
        return new ModelAndView("/jsp/createInfo.jsp");
    }
    @PostMapping("/admin/createInfo")
    public ModelAndView createInfo(@RequestParam String description, @RequestParam String name){
        it.nntdata.corso.springjsp.model.WebSiteInfo webSiteInfo = new it.nntdata.corso.springjsp.model.WebSiteInfo();
        webSiteInfo.setName(name);
        webSiteInfo.setDescription(description);
        webSiteInfoBO.insertWebSiteInfo(webSiteInfo);
        return new ModelAndView("/jsp/createInfo.jsp","operation",true);
    }

    @GetMapping("/admin/deleteViewInfo")
    public ModelAndView deleteInfo(){
        return new ModelAndView("/jsp/deleteInfo.jsp");
    }

    @GetMapping("/admin/deleteInfo")
    public ModelAndView deleteInfo(@RequestParam String id){
        if(!id.isEmpty()) {
            webSiteInfoBO.deleteWebSiteInfo(Long.parseLong(id));
            return new ModelAndView("/jsp/deleteInfo.jsp","operation",true);
        }
        return new ModelAndView("/jsp/deleteInfo.jsp","id_not_found",true);
    }

}
