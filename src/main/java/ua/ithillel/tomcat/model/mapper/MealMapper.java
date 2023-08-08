package ua.ithillel.tomcat.model.mapper;


import ua.ithillel.tomcat.model.dto.MealDto;
import ua.ithillel.tomcat.model.entity.IngredientEntity;
import ua.ithillel.tomcat.model.entity.MealEntity;
import ua.ithillel.tomcat.model.vm.IngredientVm;
import ua.ithillel.tomcat.model.vm.MealItemVm;

public interface MealMapper {
    MealItemVm mealDtoToMealItemVm(MealDto mealDto);
    MealEntity mealItemVmToMealEntity(MealItemVm mealItemVm);
    IngredientEntity ingredientVmToIngredientEntity(IngredientVm ingredientVm);
    MealItemVm mealEntityToMealItemVm(MealEntity mealEntity);
    IngredientVm ingredientEntityToIngredientVm(IngredientEntity ingredientEntity);
}
