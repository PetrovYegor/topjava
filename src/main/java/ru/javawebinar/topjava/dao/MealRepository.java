package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {

    List<Meal> getAllMeals();

    Meal getMealById(int mealId);

    void addMeal(Meal meal);

    void updateMeal(Meal meal);

    void deleteMeal(int mealId);
}
