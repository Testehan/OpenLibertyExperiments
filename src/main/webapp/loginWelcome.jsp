<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Welcome</title>
    </head>
<body>

        <%
            // back button logins the user back in the app...which is bad..this is an attempt to fix this
            // see more info in the video from videos page (loginVideos.jsp)
            response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
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
        <h1>Welcome to my app ${username}</h1>
        <p>(the username value is obtained liked this because it is set in the session object in the java code)</p>

        <a href="loginVideos.jsp">Click here to see the videos ;) </a>

        <br>
        <br>
        <br>
        <br>
        <form action ="logout">
            <input type="submit" value="logout">
        </form>
</body>
</html