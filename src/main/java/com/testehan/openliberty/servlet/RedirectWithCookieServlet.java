package com.testehan.openliberty.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class RedirectWithCookieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("sum","9"); // using cookie object to pass values to another servlet
        resp.addCookie(cookie);
        resp.sendRedirect("square");
    }
}
