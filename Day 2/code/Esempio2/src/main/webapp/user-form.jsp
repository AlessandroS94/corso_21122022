<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
                   class="nav-link">home</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="container">
    <form method="post" action="ServletInserUser">
        <div class="mb-3">
            <label class="form-label">Email address</label>
            <input type="text" class="form-control" name="name" required="required">
        </div>
        <div class="mb-3">
            <label class="form-label">email</label>
            <input type="text" class="form-control" name="email" required="required">
        </div>
        <div class="mb-3">
            <label class="form-label">Nazione</label>
            <input type="text" class="form-control" name="country" required="required">
        </div>
        <button type="submit" class="btn btn-success">Save</button>
    </form>
</div>
</body>
</html>
