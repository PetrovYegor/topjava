package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

public class MealService {

    private MealRepository repository;
    @Autowired
    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal get(int id, int userId){
        return null;
    }
    public void delete(int id, int userId){

    }
    public List<Meal> getBetweenDates(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId){
        return null;
    }
    public List<Meal> getAll(int userId){
        return null;
    }

    public void update(Meal meal, int userId){

    }

    public Meal create(Meal meal, int userId){
        return null;
    }


}