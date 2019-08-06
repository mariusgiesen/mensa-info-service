package org.ict.mensainfoservice.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "meal")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String priceStudent;
    private String priceStaff;
    private String priceGuest;

    @OneToOne(cascade = CascadeType.ALL)
    private MealRating mealRating;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Meal(){
    }

    public Meal(String description, String priceStudent, String priceStaff, String priceGuest){
        //this.id = new Random().nextLong();
        this.description = description;
        this.priceStudent = priceStudent;
        this.priceStaff = priceStaff;
        this.priceGuest = priceGuest;
        this.mealRating = new MealRating();
        this.comments = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Meal{" +
                "description='" + description + '\'' +
                ", priceStudent='" + priceStudent + '\'' +
                ", priceStaff='" + priceStaff + '\'' +
                ", priceGuest='" + priceGuest + '\'' +
                ", mealRating=" + mealRating +
                ", comments=" + comments +
                '}';
    }

    public Long getId() {
        return id;
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

    public void setMealRating(MealRating mealRating) {
        this.mealRating = mealRating;
    }

    public void addComment(Comment comment){
        this.comments.add(comment);
    }

    public List<Comment> getComments() {
        return comments;
    }
}
