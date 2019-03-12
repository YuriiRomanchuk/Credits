<%@ page import="credits.data.model.CreditLine" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
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
            <form method="post" action="choose-credit-line" class="form" role="form">

                <div class="form-group">
                    <%
                        String client_id = String.valueOf(request.getAttribute("client_id"));
                    %>
                    <label for="client_id">Client id:</label>
                    <input type="client_id" class="form-control" id="client_id" name="client_id" value = <%=client_id == null? "": client_id %> readonly>
                </div>

                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Bank</th>
                        <th>Percent</th>
                        <th>Early redemption</th>
                        <th>Increase credit line</th>
                        <th>Max sum</th>
                    </tr>
                    </thead>
                    <%List<CreditLine> creditLines = (List<CreditLine>) request.getAttribute("listOfCreditLines");
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
                        <td>
                            <button type="submit" name="choose-credit-line" value="<%=creditLine.getId()%>"class="btn btn-primary">choose</button>
                        </td>

                    </tr>

                    <%} %>
                </table>
                <button type="submit" class="btn btn-primary">Return</button>
                <a href="/Credits-1.0-SNAPSHOT/main/" class="btn btn-primary">Main</a>
            </form>
        </div>
    </div>
</div>
</body>
</html>
