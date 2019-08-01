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
import java.util.List;

@Service
public class MensaService {

    private static Logger logger = LoggerFactory.getLogger(MensaService.class);

    private List<Meal> meals;

    public MensaService() {
        this.meals = obtainMeals();
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
                meals.add(meal);
            }
            this.meals = meals;
            return meals;
        } catch (IOException e) {
            logger.error("", e);
            return null;
        }
    }
}
