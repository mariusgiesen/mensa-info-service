package org.ict.mensainfoservice.entity;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate date;
    private double avgRating;

    public Meal(){
    }

    public Meal(String description, String priceStudent, String priceStaff, String priceGuest){
        this.description = description;
        this.priceStudent = priceStudent;
        this.priceStaff = priceStaff;
        this.priceGuest = priceGuest;
        this.date = LocalDate.now();
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getPriceStudent() {
        return priceStudent;
    }

    public String getPriceStaff() {
        return priceStaff;
    }

    public String getPriceGuest() {
        return priceGuest;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", priceStudent='" + priceStudent + '\'' +
                ", priceStaff='" + priceStaff + '\'' +
                ", priceGuest='" + priceGuest + '\'' +
                ", date=" + date +
                '}';
    }
}
