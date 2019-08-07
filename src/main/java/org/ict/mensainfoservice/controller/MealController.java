package org.ict.mensainfoservice.controller;

import org.ict.mensainfoservice.entity.Comment;
import org.ict.mensainfoservice.entity.CustomUser;
import org.ict.mensainfoservice.entity.Meal;
import org.ict.mensainfoservice.service.MensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MealController {

    @Autowired
    private MensaService mensaService;

    @RequestMapping(method = RequestMethod.GET, path = "/meal/{id}")
    public ModelAndView getMeal(@PathVariable Long id, ModelAndView modelAndView){
        //TODO Meal aus MealService (aus DB)
        modelAndView.setViewName("meal");
        modelAndView.addObject("meal", mensaService.getMealById(id));
        modelAndView.addObject("comments", mensaService.getMealById(id).getComments());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/meal/{id}")
    public ModelAndView postComment(@PathVariable Long id,
                                    ModelAndView modelAndView,
                                    @ModelAttribute("postCommentText") String comment,
                                    @ModelAttribute("inlineRadioOptions") String radioValue,
                                    @ModelAttribute("postHeading") String heading,
                                    Authentication authentication){

        Object principal = authentication.getPrincipal();
        CustomUser user = (CustomUser) principal;
        String name = user.getUsername();

        Meal meal = mensaService.getMealById(id);

        if(!comment.isEmpty() && !heading.isEmpty()) {
            Comment newComment = new Comment(heading, comment, name);
            meal.addComment(newComment);
            mensaService.saveMeal(meal);
        }
        if(!radioValue.isEmpty()) {
            meal.getMealRating().rate(Integer.parseInt(radioValue), name);
            mensaService.saveMeal(meal);
        }

        return getMeal(id, modelAndView);
    }
}
