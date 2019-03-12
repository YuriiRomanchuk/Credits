<%@ page import="credits.data.model.Bank" %>
<%@ page import="credits.data.model.ClientCredit" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: yromanchuk
  Date: 3/10/19
  Time: 3:30 PM
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

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <form method="post" action="client-cabinet" class="form" role="form">
                <div class="form-group">
                    <%
                        String client_id = String.valueOf(request.getAttribute("client_id"));
                    %>
                    <label for="client_id">Client id:</label>
                    <input type="client_id" class="form-control" id="client_id" name="client_id"
                           value=<%=client_id == null? "": client_id %> readonly>
                </div>

               <%-- <select class="custom-select mr-sm-2" id="bank" name="bank">
                    <option selected>Choose...</option>

                    <% List<Bank> banks = (List<Bank>) request.getAttribute("listOfBank");
                        for (Bank bank : banks) { %>
                    <option value="<%=  bank.getId()%>"><%=bank.getName() %>
                    </option>
                    <%
                        }
                    %>
                </select>--%>

                <div class="form-group">
                    <label for="percent">Percent(before):</label>
                    <input type="number" class="form-control" id="percent" name="percent"
                           placeholder="Enter percent">
                    <label for="earlyRedemption">Early redemption:</label>
                    <input type="checkbox" class="form-control" id="earlyRedemption" name="earlyRedemption">
                    <label for="increaseCreditLine">Increase credit line:</label>
                    <input type="checkbox" class="form-control" id="increaseCreditLine" name="increaseCreditLine">
                    <label for="maxSum">Max sum(before):</label>
                    <input type="number" class="form-control" id="maxSum" name="maxSum" placeholder="Enter max sum">
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
                    <% List<ClientCredit> clientCredits = (List<ClientCredit>) request.getAttribute("listClientCredit");
                        for (ClientCredit clientCredit : clientCredits) { %>
                    <tr>
                        <td>
                            <%=clientCredit.getCreditLine().getBank().getName()%>
                        </td>
                        <td>
                            <%=clientCredit.getCreditLine().getPercent()%>
                        </td>
                        <td>
                            <%=clientCredit.getCreditLine().isEarlyRedemption()%>
                        </td>
                        <td>
                            <%=clientCredit.getCreditLine().isIncreaseCreditLine()%>
                        </td>
                        <td>
                            <%=clientCredit.getCreditLine().getMaxSum()%>
                        </td>

                    </tr>

                    <%} %>
                </table>
                <button type="submit" class="btn btn-primary">Search</button>
                <%--<a href="choose-credit-line" class="btn btn-primary">choose credit</a>--%>
                <a href="/Credits-1.0-SNAPSHOT/main/" class="btn btn-primary">Main</a>

            </form>
        </div>
    </div>
</div>

</body>
</html>
