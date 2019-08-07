package org.ict.mensainfoservice.controller;

import org.ict.mensainfoservice.entity.CustomUser;
import org.ict.mensainfoservice.service.MensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfigurationController {

    @Autowired
    private MensaService mensaService;

    @RequestMapping(method = RequestMethod.GET, path = "/configuration")
    public ModelAndView home(ModelAndView modelAndView, Authentication authentication){
        modelAndView.setViewName("configuration");

        Object principal = authentication.getPrincipal();
        CustomUser user = (CustomUser) principal;

        modelAndView.addObject("comments", mensaService.getCommentByUsername(user.getUsername()));

        return modelAndView;
    }
}
