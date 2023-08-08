package ua.ithillel.tomcat.dao;

import ua.ithillel.tomcat.exception.MealAppException;
import ua.ithillel.tomcat.model.entity.MealEntity;

import java.util.List;

public interface FavouriteMealDao {
    List<MealEntity> findAll(boolean asc) throws MealAppException;
    MealEntity findById(Integer id) throws MealAppException;
    MealEntity findByMealId(String mealId) throws MealAppException;
    void save(MealEntity mealEntity) throws MealAppException;
    void remove(Integer id) throws MealAppException;
}
