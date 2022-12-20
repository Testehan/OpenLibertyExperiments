package com.testehan.openliberty.servlet.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(value = "/addAlien",filterName="AlienIdFilter")
public class AlienIdFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Inside AlienIdFilter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        int alienId = Integer.parseInt(request.getParameter("aid"));

        if (alienId>0){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            PrintWriter out = servletResponse.getWriter();
            out.println("Alien ID is invalid");
        }
    }
}
