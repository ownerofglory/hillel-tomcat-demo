package ua.ithillel.tomcat.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletRegistration;
import ua.ithillel.tomcat.client.MealClient;
import ua.ithillel.tomcat.client.TheMealDbClient;
import ua.ithillel.tomcat.dao.FavouriteMealDao;
import ua.ithillel.tomcat.dao.FavouriteMealJdbcDao;
import ua.ithillel.tomcat.db.DbSchemaInitializer;
import ua.ithillel.tomcat.exception.DbInitException;
import ua.ithillel.tomcat.model.mapper.MealMapperDefault;
import ua.ithillel.tomcat.service.MealSearchService;
import ua.ithillel.tomcat.service.MealSearchServiceDefault;

import java.net.http.HttpClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class AppContextListener implements ServletContextListener {
    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            String sqliteConnStringEnv = System.getenv("SQLITE_CONN_STRING");
            String sqliteConnString = sqliteConnStringEnv != null
                    ? sqliteConnStringEnv : System.getProperty("sqliteConnString");

            if (sqliteConnString == null) {
                sqliteConnString = "jdbc:sqlite:meal-app.db";
            }

            System.out.println("Connection string: " + sqliteConnString);

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(sqliteConnString);

            // init DB schema
            DbSchemaInitializer.init(connection, "sqlite-schema.sql");

            // create DAO
            FavouriteMealDao favouriteMealDao = new FavouriteMealJdbcDao(connection);
            // create client
            MealClient mealClient = new TheMealDbClient(HttpClient.newHttpClient(), new ObjectMapper());
            // create services
            MealSearchService mealSearchService = new MealSearchServiceDefault(mealClient, new MealMapperDefault(), favouriteMealDao);

            MealAppServlet mealAppServlet = new MealAppServlet(mealSearchService);



            sce.getServletContext().addServlet("MealAppServlet", mealAppServlet).addMapping("/meal");

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
        ServletContextListener.super.contextDestroyed(sce);
    }
}
