package ua.ithillel.tomcat.service;


import ua.ithillel.tomcat.exception.MealAppException;
import ua.ithillel.tomcat.model.vm.AreaItemVm;
import ua.ithillel.tomcat.model.vm.CategoryShortItemVm;
import ua.ithillel.tomcat.model.vm.MealItemVm;

import java.util.List;

public interface MealSearchService {
    List<MealItemVm> searchMeals(String name);
    List<MealItemVm> getMealsByCategory(CategoryShortItemVm category);
    MealItemVm getMealById(String id);
    List<MealItemVm> getMealsByArea(AreaItemVm category);
    void toggleFavouriteMeal(MealItemVm mealItemVm) throws MealAppException;
    List<MealItemVm> getFavouriteMeals(boolean order) throws MealAppException;
    MealItemVm getFavouriteMeal(Integer id) throws MealAppException;
    MealItemVm getFavouriteMealByMealId(String mealId) throws MealAppException;
}
