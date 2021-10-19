package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();

    private final AtomicInteger counter = new AtomicInteger(0);
//а как ты в delete обошел? у меня private final Map<Integer, Map<Integer, Meal>> repository
// = new ConcurrentHashMap<>(); и после получения еды юзера  (внутренняя мапа)
// я проверяю что мапа не null и дальше уже получаю значение, удаляю и т.д.
    {
        MealsUtil.meals.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        //3.2: если по запрошенному id еда отсутствует или чужая, возвращать null/false (см. комментарии в MealRepository)
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (repository.get(userId).containsKey(id)){
                repository.get(userId).remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        if (repository.get(userId).containsKey(id)){
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
        //3.2: если по запрошенному id еда отсутствует или чужая, возвращать null/false (см. комментарии в MealRepository)
        // ORDERED dateTime desc
        MealsUtil.filterByPredicate(repository.get(userId).values(),
                MealsUtil.DEFAULT_CALORIES_PER_DAY,
                meal -> meal.getDateTime();
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
        return null;
    }
}

