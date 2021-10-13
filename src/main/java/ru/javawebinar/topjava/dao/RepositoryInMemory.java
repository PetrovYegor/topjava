package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RepositoryInMemory implements MealRepository {
    public static int caloriesPerDay = 2000;
    public static Map<Integer, Meal> mealsById = new ConcurrentHashMap<>();
    static AtomicInteger count = new AtomicInteger(1);

    {
        mealsById.put(count.get(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, count.getAndIncrement()));
        mealsById.put(count.get(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000, count.getAndIncrement()));
        mealsById.put(count.get(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500, count.getAndIncrement()));
        mealsById.put(count.get(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, count.getAndIncrement()));
        mealsById.put(count.get(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, count.getAndIncrement()));
        mealsById.put(count.get(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500, count.getAndIncrement()));
        mealsById.put(count.get(), new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410, count.getAndIncrement()));
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(mealsById.values());
    }

    @Override
    public Meal getMealById(int mealId) {
        return mealsById.get(mealId);
    }

    @Override
    public void addMeal(Meal meal) {
        meal.setId(count.getAndIncrement());
        mealsById.put(count.get(), meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        mealsById.put(meal.getId(), meal);
    }

    @Override
    public void deleteMeal(int mealId) {
        mealsById.remove(mealId);
    }
}
