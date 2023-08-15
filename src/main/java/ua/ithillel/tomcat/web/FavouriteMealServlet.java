package ua.ithillel.tomcat.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.ithillel.tomcat.model.vm.MealItemVm;
import ua.ithillel.tomcat.service.MealSearchService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class FavouriteMealServlet extends HttpServlet {
    private final MealSearchService mealSearchService;

    public FavouriteMealServlet(MealSearchService mealSearchService) {
        this.mealSearchService = mealSearchService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            BufferedReader reader = req.getReader();
            String body = reader.readLine();
            String[] params = body.split("=");
            if (params.length < 2) {
                return;
            }

            String mealId = params[1];
            MealItemVm mealById = mealSearchService.getMealById(mealId);
            mealSearchService.toggleFavouriteMeal(mealById);

            req.setAttribute("meal", mealById);
            req.getRequestDispatcher("/recipe.jsp").include(req, resp);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            req.getRequestDispatcher("/servererror.jsp").include(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // ?order=true
            String queryString = req.getQueryString();
            boolean order = false;
            if (queryString != null) {
                String[] params = queryString.split("=");
                if (params[0].equals("order")) {
                    order = Boolean.parseBoolean(params[1]);
                }
            }
            List<MealItemVm> favouriteMeals = mealSearchService.getFavouriteMeals(order);
            req.setAttribute("meals", favouriteMeals);

            req.getRequestDispatcher("/favourite.jsp").include(req, resp);

        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            req.getRequestDispatcher("/servererror.jsp").include(req, resp);
        }
    }
}
