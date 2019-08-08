package org.ict.mensainfoservice.entity;

import javax.persistence.*;

@Entity
@Table(name = "meal_rating")
public class MealRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long mealId;
    private Long customUserId;

    private int value;

    public MealRating(){}

    public MealRating(int value, Long mealId, Long customUserId){
        this.value = value;
        this.mealId = mealId;
        this.customUserId = customUserId;
    }

    public Long getMealId() {
        return mealId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MealRating{" +
                "id=" + id +
                ", mealId=" + mealId +
                ", customUserId=" + customUserId +
                ", value=" + value +
                '}';
    }
}
