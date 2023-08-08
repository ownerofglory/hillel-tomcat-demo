package ua.ithillel.tomcat.web;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NameServlet extends HttpServlet {
    private List<String> names;

    @Override
    public void init(ServletConfig config) throws ServletException {
        names = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       resp.getWriter().println(names);
       resp.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        String name = reader.readLine();

        names.add(name);

        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String name = pathInfo.replace("/", "");

        BufferedReader reader = req.getReader();
        String newName = reader.readLine();

        if (!names.contains(name)) {
            resp.setStatus(HttpServletResponse.SC_FOUND);
        } else {
            names.remove(name);
            names.add(newName);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        String name = pathInfo.replace("/", "");

        names.remove(name);
    }
}
