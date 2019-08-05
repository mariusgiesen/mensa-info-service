package org.ict.mensainfoservice.service;

import org.ict.mensainfoservice.entity.Meal;
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

    private static Logger logger = LoggerFactory.getLogger(MensaService.class);

    private HashMap<String, Meal> meals;

    public MensaService() {
        this.meals = obtainMeals();
    }

    public HashMap<String, Meal> getMeals(){
        return this.meals;
    }

    public HashMap<String, Meal> obtainMeals(){
        try {
            HashMap<String, Meal> meals = new HashMap<>();
            Document document = Jsoup.connect("https://www.stwdo.de/mensa-co/fh-dortmund/sonnenstrasse/").get();
            Elements select = document.select("div.meal-item");
            for (Element element:select) {
                Meal meal = new Meal(
                        element.getElementsByClass("item description").text(),
                        element.getElementsByClass("item price student").text(),
                        element.getElementsByClass("item price staff").text(),
                        element.getElementsByClass("item price guest").text());
                meals.put(meal.getId(), meal);
            }
            this.meals = meals;
            System.out.println(meals);
            return meals;
        } catch (IOException e) {
            logger.error("", e);
            return null;
        }
    }
}
