<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Videos</title>
    </head>
<body>

        <%
            // back button logins the user back in the app...which is bad..this is an attempt to fix this
            // see more info in the video from below
            response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); // Http 1.1
            response.setHeader("Pragma", "no-cache"); // Http 1.0
            response.setHeader("Expires","0"); // proxies

            // all sorts of objects like session, response are available to use directly in jsp file
            // because remember that since this file is compiled into a java servlet, and these
            // objects are also present/accesible in servlets, then they are also available to use in
            // jsp files

            if (session.getAttribute("username")==null){
                response.sendRedirect("loginForm.jsp");
            }
        %>

        <iframe width="560" height="315" src="https://www.youtube.com/embed/gQLQ0t9B5yk" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
</body>
</html