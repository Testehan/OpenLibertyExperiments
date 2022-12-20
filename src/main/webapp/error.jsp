<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>

<%-- If you put somewhere in the add.jsp file...an expression line int x = 10/0; then in the page rendered by this jsp
 you will see the msg from below plus an exception message : 'java.lang.ArithmeticException: / by zero'

 As you can see below we use the 'exception' object to print info about the exception...but it is important to know that
 this object is only available when  isErrorPage="true" is set above
 --%>

   <div style="background-color:red">
    <h1>Error message  !!!</h1>
    <%= exception %>
   </div>