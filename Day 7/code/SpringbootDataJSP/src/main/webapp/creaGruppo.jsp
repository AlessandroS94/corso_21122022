<!--librerie jsp-->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html lang="en">
<jsp:include page="partial/head.jsp"></jsp:include>
<body>
<jsp:include page="partial/navbar.jsp"></jsp:include>

<div class="container">
    <br>
<c:if test="${operation==true}">
    <div class="alert alert-primary" role="alert">
       ok
    </div>
</c:if>
<c:if test="${operation==false}">
    <div class="alert alert-primary" role="alert">
        Male
    </div>
</c:if>
<h1>Crea Gruppo</h1>
<form action="${pageContext.request.contextPath}/gruppi/createGruppo" method="POST">
    <div class="row mb-3">
        <label for="inputEmail1"class="col-sm-2 col-form-label">Name</label>
        <div class="col-sm-10">
            <input type="text" name="name" class="form-control" id="inputEmail1">
        </div>
    </div>
    <button type="submit" class="btn btn-primary">Crea</button>
</form>
</div>
</body>
</html>