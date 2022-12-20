<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Example of JSP file</title>
    </head>
<body bgcolor="green">

    <%@ include file="header.jsp" %>

    <%-- next section is to import various packages that will be then added to the Servlet generated behind the scenes --%>
    <%-- this is knows as the DIRECTIVE SECTION as you can see the first line from this file is also a DIRECTIVE section --%>
    <%@ page
        import = "java.util.Date"
    %>

    <%-- this type of scriplet is called "declaration". Notice the "!" at the end of the first/start <% tag
         here you can declare field instances, or methods etc that will be added to the Servlet corresponding
         to this jsp file
    --%>
    <%-- this is knows as the DECLARATION SECTION --%>
    <%!
        int i = 100;
    %>

    <%-- this is knows as the SCRIPLET SECTION --%>
    <%
        // this code will be placed in the "service" method from
        // the Servlet generated behind the scenes..SEARCH for file _add.java to see the java code that is generated

        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));

        int sum = num1+num2;
        out.println("sum=");
        //int i = 10/0;
    %>


    <%-- Whatever you put there will be printed in the outuput...so it is a sort of out.println --%>
    <%-- this is knows as the EXPRESSION SECTION --%>
    <%= sum*300 %>

     <%-- example of how one could include other jsp files --%>
    <%@ include file="footer.jsp" %>
</body>
</html