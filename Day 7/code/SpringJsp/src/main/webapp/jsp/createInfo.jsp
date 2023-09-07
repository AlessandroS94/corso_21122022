<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<jsp:include page="partial/head.jsp">
<jsp:param name="operation" value="operation"/>
</jsp:include>
<body>
<jsp:include page="partial/navbar.jsp">
    <jsp:param name="create" value="active"/>
</jsp:include>
<c:if test="${operation == true}">
<div class="alert alert-success" role="alert">
   ok
</div>
</c:if>

<div class="container">
    <form action="${pageContext.request.contextPath}/createInfo" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Name</label>
            <input type="text" name="name" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Descrizione</label>
            <input type="text" name="description" class="form-control" id="exampleInputPassword1">
        </div>
        <div class="input-group mb-3">
            <label class="input-group-text" for="inputGroupFile01">Upload</label>
            <input type="file" name="file" class="form-control" id="inputGroupFile01">
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
<jsp:include page="partial/scriptJS.jsp"></jsp:include>