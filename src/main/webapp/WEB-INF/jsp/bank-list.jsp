<%@ page import="credits.model.Bank" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="credits.model.Bank" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                    <th>Registration number </th>
                </tr>
                </thead>
                <% List<Bank> banks = (List<Bank>) request.getAttribute("listOfData");
                    for (Bank bank : banks) { %>

                <tr>
                    <td>
                        <%=bank.getName()%>
                    </td>
                    <td>
                        <%=bank.getRegistrationNumber()%>
                    </td>
                </tr>

                <%} %>
            </table>

            <a href="add-bank" class="btn btn-primary">Add bank</a>
            <a href="/Credits-1.0-SNAPSHOT/main/" class="btn btn-primary">Main</a>
        </div>
    </div>
</div>
</body>
</html>