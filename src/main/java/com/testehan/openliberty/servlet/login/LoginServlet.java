package com.testehan.openliberty.servlet.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");

        // sure the password would need to be hashed and then compared with the value from the DB
        // accessing a DB should be done via a DAO ...
        if (username.equals("dan") && password.equals("1234")){
            resp.sendRedirect("loginWelcome.jsp");
            HttpSession session = req.getSession();
            session.setAttribute("username",username);
        } else {
            resp.sendRedirect("loginForm.jsp");
        }
    }
}
