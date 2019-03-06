<%@ page import="credits.model.Bank" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="credits.model.Bank" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Date date = (Date) request.getAttribute("currentDateFromBackend");
%>
<h1>Greetings! It's  <%=date == null ? "some unknown time" : date%> on our server</h1>
<table>
    <% List<Bank> banks = (List<Bank>) request.getAttribute("listOfData");
        for (Bank bank : banks) { %>

    <tr>
        <td>
            <%=String.format("%s of age %s", bank.getName(), bank.getRegistrationNumber())%>
        </td>
    </tr>

    <%} %>
</table>
<a href="add-bank">Add bank</a>
<a href="/Credits-1.0-SNAPSHOT/main/">Main</a>
</body>
</html>