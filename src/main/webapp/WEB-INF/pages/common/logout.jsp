<%--
  Created by IntelliJ IDEA.
  User: Deepak
  Date: 1/17/2020
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    //session.invalidate();
    //response.sendRedirect("https://172.30.3.57:9443/cas/logout");

    session.invalidate();
    response.sendRedirect("https://www.citizenservices.gov.bt/cas/logout");
%>
</body>
</html>
