<%--
  Created by IntelliJ IDEA.
  User: zasobav
  Date: 11.08.23
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String[] params = {};
        String queryString = request.getQueryString();
        // name=Vlad
        if (queryString != null)
            params = queryString.split("=");

        String name = "";
        if (params.length > 1) {
            name = params[1];
        }
    %>

    <h1>Hello <%= name %></h1>
</body>
</html>
