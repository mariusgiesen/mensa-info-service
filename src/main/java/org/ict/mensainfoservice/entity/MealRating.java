package org.ict.mensainfoservice.entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "meal_rating")
public class MealRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double averageRating;

    @ElementCollection
    @MapKeyColumn(name="rating")
    @Column(name="count")
    private Map<Integer, Integer> numOfRatings;

    public MealRating(){
        this.averageRating = 0.0;
        this.numOfRatings = new HashMap<>();
        this.numOfRatings.put(1,0);
        this.numOfRatings.put(2,0);
        this.numOfRatings.put(3,0);
        this.numOfRatings.put(4,0);
        this.numOfRatings.put(5,0);
    }

    public void rate(int rating){
        if(rating >= 1 && rating <= 5){
            int number = this.numOfRatings.get(rating);
            number++;
            this.numOfRatings.put(rating, number);
            this.averageRating = calculateAvgRating();
        } else
            System.out.println("Error! Rating " + rating + " not allowed!");
    }

    private double calculateAvgRating(){
        double voteCount = 0;
        double rating = 0;
        for(int i = 1; i <= 5; i++){
            voteCount += this.numOfRatings.get(i);
            rating += this.numOfRatings.get(i)*i;
        }
        double avgRating = (rating / voteCount);
       return avgRating;
    }

    public double getAverageRating(){
        return this.averageRating;
    }

    public Map<Integer, Integer> getNumOfRatings() {
        return numOfRatings;
    }
}
