package org.ict.mensainfoservice;

import org.ict.mensainfoservice.entity.MealRating;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MensaInfoServiceApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void calculateAvgRatings(){
        MealRating mealRating = new MealRating();
        for (int i = 1; i <= 100; i++){
            mealRating.rate(i%6);
        }
        System.out.println(mealRating.getNumOfRatings());
        System.out.println(mealRating.getAverageRating());
    }

}
