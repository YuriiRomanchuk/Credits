<%@ page import="credits.model.Bank" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: yromanchuk
  Date: 3/6/19
  Time: 12:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>New credit line</title>
</head>
<body>
<%
    String error = (String) request.getAttribute("Error");
    if (error != null) {
%> <h1><%=error%></h1> <%
    }
%>

<%--<h1>Add credit line</h1>
<form method="post" action="add-credit-line">
    <p><strong>Bank</strong>
    <select bankName="bank">
        <% List<Bank> banks = (List<Bank>) request.getAttribute("listOfBank");
        for (Bank bank : banks) { %>
        <option value="<%=  bank.getRegistrationNumber()%>"><%=bank.getName() %></option>
        <%} %>
    </select></p>

    Percent: <input name="percent" type="text"/> </br>
    <button type="submit" >Save</button>
</form>--%>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <h1>Add credit line</h1>
            <form method="post" action="add-credit-line" class="form" role="form">

                <div class="col-auto my-1">
                    <label class="mr-sm-2" for="bank">Bank</label>
                    <select class="custom-select mr-sm-2" id="bank" name="bank">
                        <option selected>Choose...</option>

                        <% List<Bank> banks = (List<Bank>) request.getAttribute("listOfBank");
                            for (Bank bank : banks) { %>
                        <option value="<%=  bank.getId()%>"><%=bank.getName() %></option>
                        <%
                            }
                        %>
                    </select>

                    <div class="form-group">
                        <label for="percent">Percent:</label>
                        <input type="number" class="form-control" id="percent" name="percent" placeholder="Enter percent">
                        <label for="earlyRedemption">Early redemption:</label>
                        <input type="checkbox" class="form-control" id="earlyRedemption" name="earlyRedemption">
                        <label for="increaseCreditLine">Increase creditlLine:</label>
                        <input type="checkbox" class="form-control" id="increaseCreditLine" name="increaseCreditLine">
                        <label for="maxSum">Max sum:</label>
                        <input type="number" class="form-control" id="maxSum" name="maxSum" placeholder="Enter max sum">
                    </div>

                </div>

                <button type="submit" class="btn btn-primary">Save</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
