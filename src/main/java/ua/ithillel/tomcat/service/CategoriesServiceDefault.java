package ua.ithillel.tomcat.service;


import ua.ithillel.tomcat.client.MealClient;
import ua.ithillel.tomcat.model.dto.CategoryDto;
import ua.ithillel.tomcat.model.dto.CategoryResponseDto;
import ua.ithillel.tomcat.model.dto.CategoryShortResponseDto;
import ua.ithillel.tomcat.model.mapper.CategoriesMapper;
import ua.ithillel.tomcat.model.vm.CategoryItemVm;
import ua.ithillel.tomcat.model.vm.CategoryShortItemVm;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriesServiceDefault implements CategoryService {
    private final MealClient mealClient;
    private final CategoriesMapper categoriesMapper;

    public CategoriesServiceDefault(MealClient mealClient, CategoriesMapper categoriesMapper) {
        this.mealClient = mealClient;
        this.categoriesMapper = categoriesMapper;
    }

    @Override
    public List<CategoryShortItemVm> getShortCategories() {
        CategoryShortResponseDto categoriesList = mealClient.getCategoriesList();
        List<CategoryDto> categories = categoriesList.getMeals();
        return categories.stream()
                .map(categoriesMapper::categoryDtoToCategoryShortVm)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryItemVm> getCategories() {
        CategoryResponseDto categoriesList = mealClient.getCategories();
        List<CategoryDto> categories = categoriesList.getCategories();
        return categories.stream()
                .map(categoriesMapper::categoryDtoToCategoryVm)
                .collect(Collectors.toList());
    }
}
