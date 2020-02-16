package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealService {

    private MealRepository repository;

    @Autowired
    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public List<Meal> getAll(int userId) {
        List<Meal> result = repository.getAll(userId).stream().collect(Collectors.toList());
        if (result == null || result.isEmpty()) throw new NotFoundException("Meals not found");
        return result;
    }

    public Meal get(int userId, int mealId) {
        Meal result = repository.get(mealId, userId);
        if (result == null) throw new NotFoundException("Meal not found");
        return result;
    }

    public boolean delete(int userId, int mealId) {
        boolean result = repository.delete(mealId, userId);
        if (result == false) throw new NotFoundException("Meal not found");
        return result;
    }

    public Meal update(int userId, Meal meal) {
        Meal result = repository.save(meal, userId);
        if (result == null) throw new NotFoundException("Meal not found");
        return result;
    }

    public Meal create(int userId, Meal meal) {
        Meal result = repository.save(meal, userId);
        if (result == null) throw new NotFoundException("Meal not found");
        return result;
    }

}