package org.ict.mensainfoservice.controller;

import org.ict.mensainfoservice.service.MensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private MensaService mensaService;

    @RequestMapping(method = RequestMethod.GET, path = "/home")
    public ModelAndView home(ModelAndView modelAndView){
        modelAndView.setViewName("home");
        modelAndView.addObject("meals", mensaService.getMeals());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/update")
    public ModelAndView updateMenu(ModelAndView modelAndView){
        modelAndView.setViewName("home");
        modelAndView.addObject("meals", mensaService.obtainMeals());
        return modelAndView;
    }
}
