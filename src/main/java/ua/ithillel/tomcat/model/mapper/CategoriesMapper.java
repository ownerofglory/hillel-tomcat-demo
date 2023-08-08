package ua.ithillel.tomcat.model.mapper;


import ua.ithillel.tomcat.model.dto.CategoryDto;
import ua.ithillel.tomcat.model.vm.CategoryItemVm;
import ua.ithillel.tomcat.model.vm.CategoryShortItemVm;

public interface CategoriesMapper {
    CategoryShortItemVm categoryDtoToCategoryShortVm(CategoryDto categoryDto);

    CategoryItemVm categoryDtoToCategoryVm(CategoryDto categoryDto);
}
