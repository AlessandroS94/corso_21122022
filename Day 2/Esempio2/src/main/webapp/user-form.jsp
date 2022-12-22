<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>
<head>
  <title>User Management Application</title>
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>

<header>
  <nav class="navbar navbar-expand-md navbar-dark"
       style="background-color: #50788b">
    <div>
      <a href="https://www.javaguides.net" class="navbar-brand"> User Management App </a>
    </div>

    <ul class="navbar-nav">
      <li><a href="<%=request.getContextPath()%>/"
             class="nav-link">home</a></li>
    </ul>
  </nav>
</header>
<br>

<div>
  <form method="post" action="ServletInserUser">
    <div class="mb-3">
    <label  class="form-label">Email address</label>
    <input type="text" class="form-control" name="name" required="required">
    </div>
    <div class="mb-3">
    <label class="form-label">email</label>
    <input type="text" class="form-control" name="email" required="required">
    </div>
      <div class="mb-3">
    <label  class="form-label">Stato</label>
    <input type="text" class="form-control" name="country" required="required">
      </div>
    <button type="submit" class="btn btn-success">Save</button>
  </form>

</div>
</body>
</html>
