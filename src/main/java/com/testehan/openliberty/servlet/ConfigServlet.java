package com.testehan.openliberty.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ConfigServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter print = resp.getWriter();
        print.print("Hi ");

        // this object is shared by all servlets; if you check web.xml and look at name and phone, those parameters are
        // available for all servlets
        ServletContext servletContext = getServletContext();
        String name = servletContext.getInitParameter("name");
        print.println(name);
        String phone = servletContext.getInitParameter("phone");
        print.println("You have an "+phone + " phone");

        // this object is specific just for this servlet
        ServletConfig servletConfig = getServletConfig();
        String familyName = servletConfig.getInitParameter("name");
        print.println("Family name is " + familyName);
    }
}
