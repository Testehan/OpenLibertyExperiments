
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import = "java.time.Instant" %>

   <h1>This is the footer</h1>
   <%
        Instant instant = Instant.now();
        out.println("Time = " + instant.toString());
        out.println(" ");
        out.println(" ");
        out.println("Created by " +  pageContext.getAttribute("name",PageContext.SESSION_SCOPE));

   %>
