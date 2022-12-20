package com.testehan.openliberty.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

// since servlet specification 3 one can use annotations instead of web.xml file for servlet configurations
// not sure yet if you can do everything that you can do with web.xml
@WebServlet(urlPatterns = "servlet")
public class HelloServlet extends HttpServlet {

    private int requests;

    @Override
    public void init() throws ServletException {
        super.init();
        requests = 0;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        requests ++;
        response.getWriter().append("Hello! How are you today? Nr of requests " + requests);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String content = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        response.getWriter().append("Hello! How are you today? This is post method :" + content);
    }
}
