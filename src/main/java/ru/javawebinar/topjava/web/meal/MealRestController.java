package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
public class MealRestController {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MealService service;

    public List<MealTo> getAll() {
        int authUserId = SecurityUtil.authUserId();
        logger.debug("getAll() by user id: " + authUserId);
        return MealsUtil.getTos(service.getAll(authUserId), SecurityUtil.authUserCaloriesPerDay());
    }

    public Meal get(int mealId) {
        int authUserId = SecurityUtil.authUserId();
        logger.debug("get meal Id: " + mealId + "from user Id: " + authUserId);
        return service.get(authUserId, mealId);
    }

    public boolean delete(int mealId) {
        int authUserId = SecurityUtil.authUserId();
        logger.debug("delete meal Id: " + mealId + "from user Id: " + authUserId);
        return service.delete(authUserId, mealId);
    }

    public Meal update(Meal meal) {
        int authUserId = SecurityUtil.authUserId();
        logger.debug("update meal: " + meal + " form User id: " + authUserId);
        return service.update(authUserId, meal);
    }
   public Meal create(Meal meal) {
        int authUserId = SecurityUtil.authUserId();
        logger.debug("create meal: " + meal + " form User id: " + authUserId);
        return service.create(authUserId, meal);
    }

}