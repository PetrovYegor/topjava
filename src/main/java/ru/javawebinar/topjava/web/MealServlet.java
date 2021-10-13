package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealRepository;
import ru.javawebinar.topjava.dao.RepositoryInMemory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private final MealRepository mealRepository = new RepositoryInMemory();
    private static String INSERT_OR_EDIT = "/edit.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String action = request.getParameter("action");
        if (action == null || action.isEmpty()) {
            request.setAttribute("allMeals", MealsUtil.filteredByStreams(mealRepository.getAllMeals(),
                    LocalTime.of(0, 0, 0), LocalTime.of(23, 59, 59), RepositoryInMemory.caloriesPerDay));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealRepository.deleteMeal(mealId);
            request.setAttribute("allMeals", MealsUtil.filteredByStreams(mealRepository.getAllMeals(),
                    LocalTime.of(0, 0, 0), LocalTime.of(23, 59, 59), RepositoryInMemory.caloriesPerDay));
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        } else if ("edit".equalsIgnoreCase(action) || "create".equalsIgnoreCase(action)) {
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mealDateAttribute = request.getParameter("mealDate");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime mealDate = LocalDateTime.parse(mealDateAttribute, formatter);

        String mealId = request.getParameter("mealId");
        if (mealId == null || mealId.isEmpty()) {
            Meal meal = new Meal(mealDate, request.getParameter("description"), Integer.parseInt(request.getParameter("calories")), 0);
            mealRepository.addMeal(meal);
        } else {
            Meal meal = new Meal(mealDate, request.getParameter("description"), Integer.parseInt(request.getParameter("calories")), Integer.parseInt(request.getParameter("mealId")));
            mealRepository.updateMeal(meal);
        }

        request.setAttribute("allMeals", MealsUtil.filteredByStreams(mealRepository.getAllMeals(),
                LocalTime.of(0, 0, 0), LocalTime.of(23, 59, 59), RepositoryInMemory.caloriesPerDay));
        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
