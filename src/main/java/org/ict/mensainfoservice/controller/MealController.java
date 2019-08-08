package org.ict.mensainfoservice.controller;

import org.ict.mensainfoservice.entity.Comment;
import org.ict.mensainfoservice.entity.CustomUser;
import org.ict.mensainfoservice.entity.MealRating;
import org.ict.mensainfoservice.service.MensaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MealController {

    @Autowired
    private MensaService mensaService;

    @RequestMapping(method = RequestMethod.GET, path = "/meal/{id}")
    public ModelAndView getMeal(@PathVariable Long id, ModelAndView modelAndView){
        modelAndView.setViewName("meal");
        modelAndView.addObject("meal", mensaService.getMealById(id));
        List<Comment> comments = mensaService.getCommentsByMealId(id);
        modelAndView.addObject("comments", comments);
        modelAndView.addObject("usernames", mensaService.getUserNamesForComments(comments));
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

        if(!comment.isEmpty() && !heading.isEmpty()) {
            Comment newComment = new Comment(heading, comment, id, user.getId());
            mensaService.saveComment(newComment);
        }
        if(!radioValue.isEmpty()) {
            MealRating newRating = new MealRating(Integer.parseInt(radioValue), id, user.getId());
            mensaService.saveMealRating(newRating);
        }

        return getMeal(id, modelAndView);
    }
}
