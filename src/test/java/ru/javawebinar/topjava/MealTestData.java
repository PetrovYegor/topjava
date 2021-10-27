package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL_ID = START_SEQ + 2;

    public static final Meal userMeal_1 = new Meal(USER_MEAL_ID, LocalDateTime.of(2021, Month.JANUARY, 30, 10, 00), "Пользовательский Завтрак", 500);
    public static final Meal userMeal_2 = new Meal(USER_MEAL_ID + 1, LocalDateTime.of(2021, Month.JANUARY, 31, 20, 00), "Пользовательский Ужин", 410);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2021, Month.JANUARY, 30, 15, 00), "New meal", 555);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(userMeal_1);
        updated.setDateTime(LocalDateTime.of(2021, Month.JANUARY, 30, 15, 00));
        updated.setDescription("New Description");
        updated.setCalories(111);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("dateTime").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields("dateTime").isEqualTo(expected);
    }
}
