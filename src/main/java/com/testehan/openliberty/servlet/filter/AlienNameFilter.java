package com.testehan.openliberty.servlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(value = "/addAlien",filterName="AlienNameFilter")
public class AlienNameFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Inside AlienNameFilter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String alienName = request.getParameter("aname");

        if (alienName.length()>3){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            PrintWriter out = servletResponse.getWriter();
            out.println("Alien name is too short");
        }
    }
}
