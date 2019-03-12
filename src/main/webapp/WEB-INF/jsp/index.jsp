<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <meta charset="UTF-8"/>
        <title>Credits</title>
    </head>
<body>

<%
    Locale locale = (Locale) request.getAttribute("local");
    ResourceBundle bundle = ResourceBundle.getBundle("Locale", locale);
    for (Enumeration e = bundle.getKeys(); e.hasMoreElements(); ) {
        String key = (String) e.nextElement();
        String s = bundle.getString(key);
        session.setAttribute(key, new String(s.getBytes("ISO-8859-1"), "UTF-8"));
    }
%>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <form class="form" role="form">
                <h3>Hello!</h3>

                <div style="overflow: hidden;">
                    <div style="width: 1000%;">
                        <div style="float: left; width: 200px; height: 200px;">
                            <li>Administration
                                <ul>
                                    <li><a href="add-bank">add bank</a></li>
                                    <li><a href="add-credit-line">add credit line</a></li>
                                    <li><a href="bank-list">bank list</a></li>
                                    <li><a href="credit-line-list">credit line list</a></li>
                                </ul>
                            </li>
                        </div>
                        <div style="float: left; width: 200px; height: 100px;">
                            <li>Clients
                                <ul>
                                    <li><a href="add-client">authorization</a></li>
                                </ul>
                            </li>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>

</body>
</html>