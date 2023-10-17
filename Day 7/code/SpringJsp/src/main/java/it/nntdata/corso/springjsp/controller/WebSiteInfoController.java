package it.nntdata.corso.springjsp.controller;

import it.nntdata.corso.springjsp.business.interfaces.WebSiteInfoBO;
import it.nntdata.corso.springjsp.model.WebSiteInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.Blob;

@Controller
public class WebSiteInfoController {
    @Autowired
    WebSiteInfoBO webSiteInfoBO;

    @GetMapping(path = {"/","/index"})
    public ModelAndView home(){
        WebSiteInfo webSiteInfo = webSiteInfoBO.getWebSiteInfo();
        ModelAndView modelAndView = new ModelAndView("/jsp/index.jsp","Info",webSiteInfo);
        if (webSiteInfo.getContentType() == null) {
            modelAndView.addObject("image", false);
        } else {
            modelAndView.addObject("image", true);
        }
        return modelAndView;
    }
    @GetMapping("/createInfo")
    public ModelAndView createInfo(){
        return new ModelAndView("jsp/createInfo.jsp");
    }
    @PostMapping("/createInfo")
    public ModelAndView createInfo(@RequestParam String description, @RequestParam String name, @RequestParam("file") MultipartFile file) throws Exception {
        WebSiteInfo webSiteInfo = new WebSiteInfo();
        webSiteInfo.setName(name);
        webSiteInfo.setDescription(description);
        if(!file.isEmpty()) {
            if (file.getContentType().equals("image/jpg") || file.getContentType().equals("image/png") || file.getContentType().equals("image/jpeg")) {
                webSiteInfo.setContentType(file.getContentType());
                webSiteInfo.setLogo(file.getBytes());
            } else {
                throw new Exception("File non valido");
            }
        }
        webSiteInfoBO.insertWebSiteInfo(webSiteInfo);


        ModelAndView modelAndView = new ModelAndView("/jsp/createInfo.jsp");
        return new ModelAndView("/jsp/createInfo.jsp","operation",true);
    }

    @GetMapping("deleteViewInfo")
    public ModelAndView deleteInfo(){
        return new ModelAndView("/jsp/deleteInfo.jsp");
    }

    @GetMapping("deleteInfo")
    public ModelAndView deleteInfo(@RequestParam String id){
        if(!id.isEmpty()) {
            webSiteInfoBO.deleteWebSiteInfo(Long.parseLong(id));
            //webSiteInfoRepository.deleteById(id);
            return new ModelAndView("/jsp/deleteInfo.jsp","operation",true);
        }
        return new ModelAndView("/jsp/deleteInfo.jsp","id_not_found",true);
    }
}
