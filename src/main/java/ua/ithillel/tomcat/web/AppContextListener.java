package ua.ithillel.tomcat.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import ua.ithillel.tomcat.client.MealClient;
import ua.ithillel.tomcat.client.TheMealDbClient;
import ua.ithillel.tomcat.dao.FavouriteMealDao;
import ua.ithillel.tomcat.dao.FavouriteMealJdbcDao;
import ua.ithillel.tomcat.db.DbSchemaInitializer;
import ua.ithillel.tomcat.exception.DbInitException;
import ua.ithillel.tomcat.model.mapper.AreaMapperDefault;
import ua.ithillel.tomcat.model.mapper.CategoriesMapperDefault;
import ua.ithillel.tomcat.model.mapper.MealMapperDefault;
import ua.ithillel.tomcat.service.*;

import java.net.http.HttpClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;

public class AppContextListener implements ServletContextListener {
    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Class.forName("org.sqlite.JDBC");

            String dbFilePath = sce.getServletContext().getInitParameter("db-file");
            String dbPath = Objects.requireNonNull(getClass().getResource(dbFilePath)).getPath();

            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            // init DB schema
            DbSchemaInitializer.init(connection, "sqlite-schema.sql");

            // create DAO
            FavouriteMealDao favouriteMealDao = new FavouriteMealJdbcDao(connection);
            // create client
            MealClient mealClient = new TheMealDbClient(HttpClient.newHttpClient(), new ObjectMapper());
            // create services
            MealSearchService mealSearchService = new MealSearchServiceDefault(mealClient, new MealMapperDefault(), favouriteMealDao);
            CategoryService categoryService = new CategoriesServiceDefault(mealClient, new CategoriesMapperDefault());
            AreaService areaService = new AreaServiceDefault(mealClient, new AreaMapperDefault());

            MealAppServlet mealAppServlet = new MealAppServlet(mealSearchService, categoryService, areaService);
            sce.getServletContext().addServlet("MealAppServlet", mealAppServlet).addMapping("/meal");

            FavouriteMealServlet favouriteMealServlet = new FavouriteMealServlet(mealSearchService);
            sce.getServletContext().addServlet("FavouriteMealServlet", favouriteMealServlet).addMapping("/favourite");

            System.out.println();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DbInitException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
