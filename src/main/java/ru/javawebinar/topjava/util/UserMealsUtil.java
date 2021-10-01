package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> groupedMapByDateAndCalories = new HashMap<>();

        for (UserMeal userMeal : meals) {
            LocalDate key = userMeal.getDateTime().toLocalDate();
            groupedMapByDateAndCalories.merge(key, userMeal.getCalories(), Integer::sum);
        }

        List<UserMealWithExcess> resultList = new ArrayList<>();

        for (UserMeal userMeal : meals) {
            LocalTime targetTime = userMeal.getDateTime().toLocalTime();
            if (targetTime.compareTo(startTime) >= 0 && targetTime.compareTo(endTime) < 0) {
                LocalDate mealDate = userMeal.getDateTime().toLocalDate();
                if (groupedMapByDateAndCalories.get(mealDate) <= caloriesPerDay) {
                    resultList.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), false));
                } else {
                    resultList.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true));
                }
            }
        }
        return resultList;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> groupedMapByDateAndCalories = new HashMap<>();
        List<UserMealWithExcess> resultList;

        meals = meals.stream().peek(a -> {
            LocalDate mapKey = a.getDateTime().toLocalDate();
            groupedMapByDateAndCalories.merge(mapKey, a.getCalories(), Integer::sum);
        }).collect(Collectors.toList());

        resultList = meals.stream().map(a -> {
            LocalTime targetTime = a.getDateTime().toLocalTime();
            UserMealWithExcess userForResultList = null;

            if (targetTime.compareTo(startTime) >= 0 && targetTime.compareTo(endTime) < 0) {
                LocalDate mealDate = a.getDateTime().toLocalDate();
                if (groupedMapByDateAndCalories.get(mealDate) <= caloriesPerDay) {
                    userForResultList = new UserMealWithExcess(a.getDateTime(), a.getDescription(), a.getCalories(), false);
                } else {
                    userForResultList = new UserMealWithExcess(a.getDateTime(), a.getDescription(), a.getCalories(), true);
                }
            }
            return userForResultList;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return resultList;
    }
}
