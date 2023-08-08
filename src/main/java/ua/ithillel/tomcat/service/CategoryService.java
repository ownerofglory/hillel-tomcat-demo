package ua.ithillel.tomcat.service;


import ua.ithillel.tomcat.model.vm.CategoryItemVm;
import ua.ithillel.tomcat.model.vm.CategoryShortItemVm;

import java.util.List;

public interface CategoryService {
    List<CategoryShortItemVm>  getShortCategories();
    List<CategoryItemVm> getCategories();
}
