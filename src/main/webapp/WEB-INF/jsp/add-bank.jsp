<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>New User</title>
</head>
<body>
<%
    String error = (String) request.getAttribute("Error");
    if (error != null) {
%> <h1><%=error%></h1> <%
    }
%>
<h1>Add bank</h1>
<form method="post" action="add-bank">
    Name: <input name="name" type="text"/> </br>
    Reg number:  <input name="registrationNumber" type="number"/> </br>
    <button type="submit" >Save</button>
</form>
</body>
</html>
