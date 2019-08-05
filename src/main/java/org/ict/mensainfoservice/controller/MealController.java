package org.ict.mensainfoservice.controller;

import org.ict.mensainfoservice.entity.Comment;
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
    public ModelAndView getMeal(@PathVariable String id, ModelAndView modelAndView){
        //TODO Meal aus MealService (aus DB)
        modelAndView.setViewName("meal");
        modelAndView.addObject("meal", mensaService.getMeals().get(id));
        modelAndView.addObject("comments", mensaService.getMeals().get(id).getComments());
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/meal/{id}")
    public ModelAndView postComment(@PathVariable String id,
                                    ModelAndView modelAndView,
                                    @ModelAttribute("postCommentText") String comment,
                                    @ModelAttribute("inlineRadioOptions") String radioValue,
                                    @ModelAttribute("postHeading") String heading,
                                    Authentication authentication){

        Object principal = authentication.getPrincipal();
        User user = (User) principal;
        String name = user.getUsername();


        Meal meal = mensaService.getMeals().get(id);

        if(!comment.isEmpty() && !heading.isEmpty()) {
            Comment newComment = new Comment(heading, comment, name);
            meal.addComment(newComment);
        }
        if(!radioValue.isEmpty()) meal.getMealRating().rate(Integer.parseInt(radioValue));

        return getMeal(id, modelAndView);
    }
}
