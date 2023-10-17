<!--librerie jsp-->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html lang="en">
<jsp:include page="partial/head.jsp"></jsp:include>
<body>
<jsp:include page="partial/navbar.jsp"></jsp:include>
<h1>Crea Gruppo</h1>
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
<div class="container">
<form action="${pageContext.request.contextPath}/gruppi/addPersonTOGruppo" method="POST">
    <select class="form-select" name="idPerson" aria-label="Default select example">
        <c:forEach items="${persone}" var="persona">
            <option value="${persona.id}">${persona.name}</option>
        </c:forEach>
    </select>
    <select class="form-select" name="idGruppo" aria-label="Default select example">
       <c:forEach items="${gruppi}" var="gruppo">
           <option value="${gruppo.id}">${gruppo.name}</option>
       </c:forEach>
    </select>
    <button type="submit" class="btn btn-primary">Aggiungi</button>
</form>
</div>
</body>
</html>