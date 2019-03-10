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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Title</title>
</head>
<body>

<%
    Date date = (Date) request.getAttribute("currentDateFromBackend");
%>
<h1>Greetings! It's  <%=date == null ? "some unknown time" : date%> on our server</h1>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Bank</th>
                    <th>Percent </th>
                    <th>Early redemption</th>
                    <th>Increase credit line</th>
                    <th>Max sum</th>
                </tr>
                </thead>
                <% List<CreditLine> creditLines = (List<CreditLine>) request.getAttribute("listOfCreditLines");
                    for (CreditLine creditLine : creditLines) { %>
                <tr>
                    <td>
                        <%=creditLine.getBank().getName()%>
                    </td>
                    <td>
                        <%=creditLine.getPercent()%>
                    </td>
                    <td>
                        <%=creditLine.isEarlyRedemption()%>
                    </td>
                    <td>
                        <%=creditLine.isIncreaseCreditLine()%>
                    </td>
                    <td>
                        <%=creditLine.getMaxSum()%>
                    </td>

                </tr>

                <%} %>
            </table>

            <a href="add-credit-line" class="btn btn-primary">Add credit line</a>
            <a href="/Credits-1.0-SNAPSHOT/main/" class="btn btn-primary">Main</a>
        </div>
    </div>
</div>
</body>
</html>
