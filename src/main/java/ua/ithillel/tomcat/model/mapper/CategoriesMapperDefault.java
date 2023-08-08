package ua.ithillel.tomcat.model.mapper;

import ua.ithillel.tomcat.model.dto.CategoryDto;
import ua.ithillel.tomcat.model.vm.CategoryItemVm;
import ua.ithillel.tomcat.model.vm.CategoryShortItemVm;

public class CategoriesMapperDefault implements CategoriesMapper {
    @Override
    public CategoryShortItemVm categoryDtoToCategoryShortVm(CategoryDto categoryDto) {
        CategoryShortItemVm categoryShortItemVm = new CategoryShortItemVm();
        categoryShortItemVm.setName(categoryDto.getStrCategory());
        return categoryShortItemVm;
    }

    @Override
    public CategoryItemVm categoryDtoToCategoryVm(CategoryDto categoryDto) {
        CategoryItemVm categoryItemVm = new CategoryItemVm();
        categoryItemVm.setName(categoryDto.getStrCategory());
        categoryItemVm.setDescription(categoryDto.getStrCategoryDescription());
        categoryItemVm.setImageUrl(categoryDto.getStrCategoryThumb());
        return categoryItemVm;
    }
}
