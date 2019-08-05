package org.ict.mensainfoservice.controller;

import org.ict.mensainfoservice.service.MensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MealController {

    @Autowired
    private MensaService mensaService;

    @RequestMapping(method = RequestMethod.GET, path = "/meal/{id}")
    public ModelAndView getMeal(@PathVariable String id, ModelAndView modelAndView){
        //TODO Meal aus MealService (aus DB)
        modelAndView.setViewName("meal");
        modelAndView.addObject("meal", mensaService.getMeals().get(id));
        return modelAndView;
    }
}
