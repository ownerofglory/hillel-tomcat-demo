package ua.ithillel.tomcat.web.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

public class LoggingFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.printf("[%s][%s][%s][%s]%n",
                LocalDateTime.now(),
                "INFO",
                Thread.currentThread().getName(),
                "Request: " + req.getMethod() + " " + req.getPathInfo());

        // before servlet
        chain.doFilter(req, res);
        // after servlet
        System.out.printf("[%s][%s][%s][%s]%n",
                LocalDateTime.now(),
                "INFO",
                Thread.currentThread().getName(),
                "Response: " + res.getStatus());

    }
}
