package org.ict.mensainfoservice.repository;

import org.ict.mensainfoservice.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    Boolean existsMealByDescription(String description);
    List<Meal> findAllByDate(LocalDate date);
}
