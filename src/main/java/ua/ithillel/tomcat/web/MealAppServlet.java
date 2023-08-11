package ua.ithillel.tomcat.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.ithillel.tomcat.model.vm.AreaItemVm;
import ua.ithillel.tomcat.model.vm.CategoryShortItemVm;
import ua.ithillel.tomcat.model.vm.MealItemVm;
import ua.ithillel.tomcat.service.AreaService;
import ua.ithillel.tomcat.service.CategoryService;
import ua.ithillel.tomcat.service.MealSearchService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class MealAppServlet extends HttpServlet {
    private final MealSearchService mealSearchService;
    private final CategoryService categoryService;
    private final AreaService areaService;

    public MealAppServlet(MealSearchService mealSearchService, CategoryService categoryService, AreaService areaService) {
        this.mealSearchService = mealSearchService;
        this.categoryService = categoryService;
        this.areaService = areaService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CategoryShortItemVm> shortCategories = categoryService.getShortCategories();
        List<AreaItemVm> areas = areaService.getAreas();

        req.setAttribute("categories", shortCategories);
        req.setAttribute("countries", areas);

        String queryString = req.getQueryString();
        if (queryString != null) {
            // mealId=52967
            String[] params = queryString.split("=");

            if (params[0].equals("mealId")) {
                String mealId = params[1];
                MealItemVm mealById = mealSearchService.getMealById(mealId);

                req.setAttribute("meal", mealById);

                req.getRequestDispatcher("/recipe.jsp").include(req, resp);
                return;
            } else if (params[0].equals("category")) {
                String category = params[1];
                CategoryShortItemVm categoryShortItemVm = new CategoryShortItemVm(category);

                List<MealItemVm> mealsByCategory = mealSearchService.getMealsByCategory(categoryShortItemVm);
                req.setAttribute("meals", mealsByCategory);

                req.getRequestDispatcher("/meals.jsp").include(req, resp);
                return;
            } else if (params[0].equals("country")){
                String country = params[1];
                AreaItemVm areaItemVm = new AreaItemVm(country);

                List<MealItemVm> meals = mealSearchService.getMealsByArea(areaItemVm);
                req.setAttribute("meals", meals);

                req.getRequestDispatcher("/meals.jsp").include(req, resp);
                return;
            }
        }

        CategoryShortItemVm categoryShortItemVm = new CategoryShortItemVm();
        categoryShortItemVm.setName("Breakfast");
        List<MealItemVm> mealsByCategory = mealSearchService.getMealsByCategory(categoryShortItemVm);

        req.setAttribute("meals", mealsByCategory);

        req.getRequestDispatcher("/meals.jsp").include(req, resp);


    }
}
