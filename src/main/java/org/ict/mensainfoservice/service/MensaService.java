package org.ict.mensainfoservice.service;

import org.ict.mensainfoservice.entity.Comment;
import org.ict.mensainfoservice.entity.CustomUser;
import org.ict.mensainfoservice.entity.Meal;
import org.ict.mensainfoservice.entity.MealRating;
import org.ict.mensainfoservice.repository.CommentRepository;
import org.ict.mensainfoservice.repository.CustomUserRepository;
import org.ict.mensainfoservice.repository.MealRatingRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MensaService {

    private MealRepository mealRepository;
    private CommentRepository commentRepository;
    private CustomUserRepository customUserRepository;
    private MealRatingRepository mealRatingRepository;

    private static Logger logger = LoggerFactory.getLogger(MensaService.class);

    public MensaService(CustomUserRepository customUserRepository,
                        MealRepository mealRepository,
                        CommentRepository commentRepository,
                        MealRatingRepository mealRatingRepository) {
        this.mealRepository = mealRepository;
        this.commentRepository = commentRepository;
        this.customUserRepository = customUserRepository;
        this.mealRatingRepository = mealRatingRepository;
        obtainCurrentMeals();
    }

    public List<Comment> getCommentsByCustomUserId(Long id){
        return this.commentRepository.getCommentsByCustomUserId(id);
    }

    public MealRating saveMealRating(MealRating mealRating){
        Meal meal = this.mealRepository.findById(mealRating.getMealId()).get();
        System.out.println("New Rating for Meal: " + meal.getDescription() + " - Avg Rating: " + meal.getAvgRating());
        System.out.println("Rating: " + mealRating.getValue());
        List<MealRating> ratings = this.mealRatingRepository.getAllByMealId(mealRating.getMealId());
        double newAvgRating = 0;
        for (MealRating rating:ratings) {
            newAvgRating += rating.getValue();
        }
        newAvgRating += mealRating.getValue();
        newAvgRating = newAvgRating / (ratings.size()+1);
        meal.setAvgRating(newAvgRating);
        this.mealRepository.save(meal);
        System.out.println("New Average Rating: " + newAvgRating);

        return this.mealRatingRepository.save(mealRating);
    }

    public HashMap<Long, String> getUserNamesForComments(List<Comment> comments){
        HashMap<Long, String> usernames = new HashMap<>();
        for (Comment comment:comments) {
            usernames.put(comment.getId(), this.customUserRepository.findById(comment.getCustomUserId()).get().getUsername());
        }
        return usernames;
    }

    public Comment saveComment(Comment comment){
        return this.commentRepository.save(comment);
    }

    public CustomUser saveUser(CustomUser user){
        return this.customUserRepository.save(user);
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

    public List<Comment> getCommentsByMealId(Long mealId){
        return commentRepository.getCommentsByMealId(mealId);
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
                else mealRepository.findMealByDescription(meal.getDescription()).setDate(LocalDate.now());
            }
        } catch (IOException e) {
            logger.error("", e);
        }
    }
}
