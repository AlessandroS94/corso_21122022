<!--librerie jsp-->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>SpringbootDataJSP</title>
</head>
<body>
<h1>Benvenuto</h1>
<c:forEach items="${peopleList}" var="person">
    <div>
        <h5>${person.name}</h5>
        <h5 >${person.surname}</h5>
    </div>
    <hr>
</c:forEach>

<form action="${pageContext.request.contextPath}/person/create" method="POST">
    <div class="row mb-3">
        <label for="inputEmail1"class="col-sm-2 col-form-label">Name</label>
        <div class="col-sm-10">
            <input type="text" name="name" class="form-control" id="inputEmail1">
        </div>
    </div>
    <div class="row mb-3">
        <label for="inputEmail2"class="col-sm-2 col-form-label">Name</label>
        <div class="col-sm-10">
            <input type="text" name="surname" class="form-control" id="inputEmail2">
        </div>
    </div>
    <button type="submit" class="btn btn-primary">create</button>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>