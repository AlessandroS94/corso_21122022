<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: #50788b">
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/"
                   class="nav-link">Home</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="container">
<h1><%= "Hello World!" %>
</h1>
<br/>
 <a href="hello-servlet">Hello Servlet</a>
    <hr>
 <a href="ServletInserUser">ServletInserUser</a>
</div>
</body>
</html>