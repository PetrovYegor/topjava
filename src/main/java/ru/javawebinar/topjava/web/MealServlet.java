package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealRepository;
import ru.javawebinar.topjava.dao.RepositoryInMemory;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final MealRepository mealRepository = new RepositoryInMemory();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        List<MealTo> listOfMealsWE = MealsUtil.filteredByStreams(RepositoryInMemory.meals, LocalTime.of(0,0,0), LocalTime.of(23,59,59), RepositoryInMemory.caloriesPerDay);
        request.setAttribute("allMealsWithExcess", listOfMealsWE);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

}
