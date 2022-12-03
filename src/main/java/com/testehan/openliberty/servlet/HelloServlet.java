package com.testehan.openliberty.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/servlet")
public class HelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().append("Hello! How are you today?");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String content = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        response.getWriter().append("Hello! How are you today? This is post method :" + content);
    }
}
