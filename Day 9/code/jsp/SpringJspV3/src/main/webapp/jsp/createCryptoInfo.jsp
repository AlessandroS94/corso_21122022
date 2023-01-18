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
<c:if test="${not_operation == true}">
<div class="alert alert-success" role="error">
   Non salvato
</div>
</c:if>

<div class="container">
    <form action="${pageContext.request.contextPath}/admin/createInfo" mu method="post">
        <div class="mb-3">
            <label for="exampleInputText" class="form-label">Name</label>
            <input type="text" name="name" class="form-control" id="exampleInputText" aria-describedby="emailHelp">
        </div>
        <div class="mb-3">
            <label for="exampleInputText2" class="form-label">Url</label>
            <input type="text" name="url" class="form-control" id="exampleInputText2">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">Upload</span>
            </div>
            <div class="custom-file">
                <input name="image" type="file" class="custom-file-input" id="inputGroupFile01">
                <label class="custom-file-label" for="inputGroupFile01">Scagli un file</label>
            </div>
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