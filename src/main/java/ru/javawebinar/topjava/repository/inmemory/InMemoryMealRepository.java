package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);


    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        } else if (get(meal.getId(), userId) == null) {
            return null;
        }
        Map<Integer, Meal> usersMeals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        usersMeals.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int mealId, int userId) {
        Map<Integer, Meal> usersMeals = repository.get(userId);
        return usersMeals != null && usersMeals.remove(mealId) != null;
    }

    @Override
    public Meal get(int mealId, int userId) {
        Map<Integer, Meal> usersMeals = repository.get(userId);

        return usersMeals != null ? null : usersMeals.get(mealId);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.get(userId).values()
                .stream()
                .sorted((um1, um2) -> um2.getDateTime().compareTo(um1.getDateTime()))
                .collect(Collectors.toList());
    }
}

