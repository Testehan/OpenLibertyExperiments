package com.testehan.openliberty.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("requestdispatcher")
public class RequestDispatcherExampleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sum = 10;
        // we want to call SquareSevlet from this servlet...how do we do that?
        // we have 2 options : RequestDispatcher and Redirect

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("square");

        // how do we send data from this servlet to square servlet ? The first choice would be
        // sessionManagement.. but here we just set a value in the req object
        req.setAttribute("sum",sum);    // this is not relevant because in square servlet, we obtain the "sum"
        requestDispatcher.forward(req,resp); // from multiple places, and this value 10 from here will be overwritten there
        // i left the setAttribute to show an example of how can we pass data from one servlet to another
    }
}
