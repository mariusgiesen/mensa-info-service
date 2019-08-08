package org.ict.mensainfoservice.repository;

import org.ict.mensainfoservice.entity.MealRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRatingRepository extends JpaRepository<MealRating, Long> {
    List<MealRating> getAllByMealId(Long mealId);
}
