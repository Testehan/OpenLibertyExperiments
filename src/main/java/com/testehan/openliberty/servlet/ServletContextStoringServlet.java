package com.testehan.openliberty.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ServletContextStoringServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext(); // using servletContext object to pass values to another servlet

        String sum = req.getParameter("sum");
        if (sum != null) {
            servletContext.setAttribute("sum", sum);
        }

        System.out.println( "ServletContextStoringServlet Thread ID: " + Thread.currentThread().getId());

        PrintWriter writer = resp.getWriter();
        writer.println(servletContext.getAttribute("sum"));

    }
}
