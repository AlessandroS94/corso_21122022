<!--librerie jsp-->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html lang="en">
<jsp:include page="partial/head.jsp"></jsp:include>
<body>
<jsp:include page="partial/navbar.jsp"></jsp:include>
<div class="container">
<h1>Benvenuto</h1>
<c:forEach items="${peopleList}" var="person">
    <div>
        <h5>${person.name}</h5>
        <h5 >${person.surname}</h5
        <h5 >${person.tipo}</h5>
        <h5 >${person.gruppo.name}</h5>

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
        <label for="inputEmail2"class="col-sm-2 col-form-label">Surname</label>
        <div class="col-sm-10">
            <input type="text" name="surname" class="form-control" id="inputEmail2">
        </div>
    </div>
    <select class="form-select form-select-lg mb-3" name="tipo" aria-label="Default select example">
        <c:forEach items="${tipi}" var="tipo">
            <option value="${tipo}">${tipo}</option>
        </c:forEach>
    </select>
    <button type="submit" class="btn btn-primary">create</button>
</form>

</div>
</body>
</html>