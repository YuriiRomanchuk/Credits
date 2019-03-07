<%@ page import="credits.model.CreditLine" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: yromanchuk
  Date: 3/6/19
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <% List<CreditLine> creditLines = (List<CreditLine>) request.getAttribute("listOfCreditLines");
        for (CreditLine creditLine : creditLines) { %>

    <tr>
        <td>
            <%=String.format("Bank %s percent %s", creditLine.getBank().getName(), creditLine.getPercent())%>
        </td>
    </tr>

    <%} %>
</table>
<a href="add-credit-line">Add credit line</a>
<a href="/Credits-1.0-SNAPSHOT/main/">Main</a>

</body>
</html>
