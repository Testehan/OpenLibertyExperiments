package com.testehan.openliberty.servlet;

import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

public class SquareServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter printWriter = resp.getWriter();
        int sum = 0;

        // we get this from AddServlet
        if (req.getAttribute("sum")!=null){
            sum = (int) req.getAttribute("sum");
        }
        // we get this from RedirectServlet
        if (req.getParameter("sum")!=null){
            sum = Integer.parseInt(req.getParameter("sum"));
        }
        // we get this from RedirectWithSessionServlet
        HttpSession session = req.getSession();
        if (session != null && session.getAttribute("sum")!= null) {
            sum = (int) session.getAttribute("sum");
        }
        // we get this from RedirectWithCookieServlet
        Cookie[] cookies = req.getCookies();
        for (Cookie c : cookies){
            if (c.getName().equalsIgnoreCase("sum")){
                sum = Integer.parseInt(c.getValue());
            }
        }

        int square = sum*sum;
        printWriter.println("Hello to Square : " + square);
    }
}
