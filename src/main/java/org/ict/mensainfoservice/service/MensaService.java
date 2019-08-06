package org.ict.mensainfoservice.service;

import org.ict.mensainfoservice.entity.Comment;
import org.ict.mensainfoservice.entity.CustomUser;
import org.ict.mensainfoservice.entity.Meal;
import org.ict.mensainfoservice.repository.CommentRepository;
import org.ict.mensainfoservice.repository.CustomUserRepository;
import org.ict.mensainfoservice.repository.MealRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MensaService {

    private MealRepository mealRepository;
    private CommentRepository commentRepository;
    private CustomUserRepository customUserRepository;

    private static Logger logger = LoggerFactory.getLogger(MensaService.class);

    public MensaService(CustomUserRepository customUserRepository, MealRepository mealRepository, CommentRepository commentRepository) {
        this.mealRepository = mealRepository;
        this.commentRepository = commentRepository;
        this.customUserRepository = customUserRepository;
        obtainCurrentMeals();
    }

    public CustomUser saveUser(CustomUser user){
        return this.customUserRepository.save(user);
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

    public List<Meal> getAllMeals(){
        return mealRepository.findAll();
    }

    public  List<Meal> getCurrentMeals(){
        return mealRepository.findAllByDate(LocalDate.now());
    }

    public void obtainCurrentMeals(){
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
                meals.add(meal);
            }
            for (Meal meal:meals) {
                if(!mealRepository.existsMealByDescription(meal.getDescription())) saveMeal(meal);
            }
        } catch (IOException e) {
            logger.error("", e);
        }
    }
}
