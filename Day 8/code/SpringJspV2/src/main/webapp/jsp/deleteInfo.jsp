<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<jsp:include page="head.jsp"></jsp:include>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<c:if test="${operation == true}">
<div class="alert alert-success" role="alert">
   ok
</div>
</c:if>
<c:if test="${id_not_found == true}">
<div class="alert alert-danger" role="alert">
   manca l'id
</div>
</c:if>

<div class="container">
    <form action="${pageContext.request.contextPath}/admin/deleteInfo" method="get">
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">id</label>
            <input type="number" name="id" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
        </div>
        <button type="submit" class="btn btn-primary">Invia</button>
    </form>
</div>
<br>
<footer class="footer mt-auto py-3 bg-light">
    <div class="container">
        <span class="text-muted">Prova</span>
    </div>
</footer>
</body>
<jsp:include page="scriptJS.jsp"></jsp:include>