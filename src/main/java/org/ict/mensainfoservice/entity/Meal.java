package org.ict.mensainfoservice.entity;

import java.util.UUID;

//@Entity
public class Meal {

    //@Id
    private String id;
    private String description;
    private String priceStudent;
    private String priceStaff;
    private String priceGuest;
    private MealRating mealRating;

    public Meal(String description, String priceStudent, String priceStaff, String priceGuest){
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.priceStudent = priceStudent;
        this.priceStaff = priceStaff;
        this.priceGuest = priceGuest;
        this.mealRating = new MealRating();
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", priceStudent='" + priceStudent + '\'' +
                ", priceStaff='" + priceStaff + '\'' +
                ", priceGuest='" + priceGuest + '\'' +
                ", mealRating=" + mealRating +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriceStudent() {
        return priceStudent;
    }

    public void setPriceStudent(String priceStudent) {
        this.priceStudent = priceStudent;
    }

    public String getPriceStaff() {
        return priceStaff;
    }

    public void setPriceStaff(String priceStaff) {
        this.priceStaff = priceStaff;
    }

    public String getPriceGuest() {
        return priceGuest;
    }

    public void setPriceGuest(String priceGuest) {
        this.priceGuest = priceGuest;
    }

    public MealRating getMealRating() { return mealRating; }
}
