<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>New bank</title>
</head>
<body>


<%
    String error = (String) request.getAttribute("Error");
    if (error != null) {
%> <h1><%=error%></h1> <%
    }
%>
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <h1><%=session.getAttribute("h1.addBank")%></h1>
                <form method="post" action="add-bank" class="form" role="form">
                    <div class="form-group">
                        <label for="name"><%=session.getAttribute("label.Name")%>:</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="<%=session.getAttribute("placeholder.Name")%>">
                    </div>
                    <div class="form-group">
                        <label for="registrationNumber"><%=session.getAttribute("label.Number")%>:</label>
                        <input type="number" class="form-control" id="registrationNumber" name="registrationNumber" placeholder="<%=session.getAttribute("placeholder.Number")%>">
                    </div>
                    <button type="submit" class="btn btn-primary"><%=session.getAttribute("button.Save")%></button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
