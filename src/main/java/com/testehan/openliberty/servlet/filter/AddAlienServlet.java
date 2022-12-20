package com.testehan.openliberty.servlet.filter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addAlien")
public class AddAlienServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Inside addAlien doGet Servlet");
        PrintWriter printWriter = resp.getWriter();
        String aName = req.getParameter("aname");

        printWriter.println("Hello " + aName);
    }
}
