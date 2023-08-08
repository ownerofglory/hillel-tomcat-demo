package ua.ithillel.tomcat.client;

import ua.ithillel.tomcat.model.dto.AreaResponseDto;
import ua.ithillel.tomcat.model.dto.CategoryResponseDto;
import ua.ithillel.tomcat.model.dto.CategoryShortResponseDto;
import ua.ithillel.tomcat.model.dto.MealResponseDto;

public interface MealClient {
    MealResponseDto getMealById(String id);
    MealResponseDto searchMealByName(String name);
    MealResponseDto searchMealByCategory(String categoryName);
    MealResponseDto searchMealByArea(String areaName);

    CategoryResponseDto getCategories();
    CategoryShortResponseDto getCategoriesList();
    AreaResponseDto getAreas();
}
