package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, SecurityUtil.authUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            if (repository.containsKey(userId)){
                repository.get(userId).put(meal.getId(), meal);
            } else {
                repository.put(userId, new ConcurrentHashMap<>());
                repository.get(userId).put(meal.getId(), meal);
            }
            return meal;
        }
        if (repository.get(userId).containsKey(meal.getId())) {
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        }
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (repository.get(userId).containsKey(id)) {
            repository.get(userId).remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        if (repository.get(userId).containsKey(id)) {
            return repository.get(userId).get(id);
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.get(userId).values().stream()
                .sorted((o1, o2) -> o2.getDateTime().compareTo(o1.getDateTime()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetween(LocalDate startDate, LocalDate endDate, int userId) {
        return repository.get(userId).values().stream()
                .filter(meal -> !meal.getDate().isBefore(startDate) && !meal.getDate().isAfter(endDate))
                .sorted((meal1, meal2) -> meal2.getDateTime().compareTo(meal1.getDateTime()))
                .collect(Collectors.toList());
    }
}

