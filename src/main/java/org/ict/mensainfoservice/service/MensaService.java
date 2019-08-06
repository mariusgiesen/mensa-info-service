package org.ict.mensainfoservice.service;

import org.ict.mensainfoservice.entity.Comment;
import org.ict.mensainfoservice.entity.Meal;
import org.ict.mensainfoservice.repository.CommentRepository;
import org.ict.mensainfoservice.repository.MealRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MensaService {

    private MealRepository mealRepository;
    private CommentRepository commentRepository;

    private static Logger logger = LoggerFactory.getLogger(MensaService.class);

    private List<Meal> meals;

    public MensaService(MealRepository mealRepository, CommentRepository commentRepository) {
        this.mealRepository = mealRepository;
        this.commentRepository = commentRepository;
        this.meals = obtainMeals();
    }

    public List<Comment> getCommentByUsername(String username){
        return this.commentRepository.getCommentByUsername(username);
    }

    public Meal getMealById(Long id){
        return mealRepository.getOne(id);
    }

    public Meal saveMeal(Meal meal){
        return mealRepository.save(meal);
    }

    public List<Meal> getMeals(){
        return this.meals;
    }

    public List<Meal> obtainMeals(){
        try {
            List<Meal> meals = new ArrayList<>();
            Document document = Jsoup.connect("https://www.stwdo.de/mensa-co/fh-dortmund/sonnenstrasse/").get();
            Elements select = document.select("div.meal-item");
            for (Element element:select) {
                Meal meal = new Meal(
                        element.getElementsByClass("item description").text(),
                        element.getElementsByClass("item price student").text(),
                        element.getElementsByClass("item price staff").text(),
                        element.getElementsByClass("item price guest").text());
                //meals.put(meal.getId(), meal);
                meals.add(meal);
            }
            this.meals = meals;
            mealRepository.saveAll(meals);
            System.out.println(meals);
            return meals;
        } catch (IOException e) {
            logger.error("", e);
            return null;
        }
    }
}
