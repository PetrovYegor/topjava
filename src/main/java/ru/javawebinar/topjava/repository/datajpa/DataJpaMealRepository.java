package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository, CrudUserRepository crudUserRepository) {
        this.crudRepository = crudRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setUser(crudUserRepository.getById(userId));
            return crudRepository.save(meal);
        }
        Meal currentMeal = get(meal.getId(), userId);
        if (currentMeal == null) {
            return null;
        }
        currentMeal.setDateTime(meal.getDateTime());
        currentMeal.setCalories(meal.getCalories());
        currentMeal.setDescription(meal.getDescription());
        crudRepository.save(currentMeal);
        return currentMeal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.findById(id)
                .filter(m -> m.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAll(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudRepository.findAll(userId, startDateTime, endDateTime);
    }
}
