package ua.ithillel.tomcat.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.ithillel.tomcat.model.vm.CategoryShortItemVm;
import ua.ithillel.tomcat.model.vm.MealItemVm;
import ua.ithillel.tomcat.service.MealSearchService;

import java.io.IOException;
import java.util.List;

public class MealAppServlet extends HttpServlet {
    private final MealSearchService mealSearchService;

    public MealAppServlet(MealSearchService mealSearchService) {
        this.mealSearchService = mealSearchService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryShortItemVm categoryShortItemVm = new CategoryShortItemVm();
        categoryShortItemVm.setName("Breakfast");
        List<MealItemVm> mealsByCategory = mealSearchService.getMealsByCategory(categoryShortItemVm);

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(mealsByCategory);
        resp.getWriter().println(body);
        resp.getWriter().flush();
    }
}
