<!--librerie jsp-->
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html lang="en">

<jsp:include page="partial/head.jsp"></jsp:include>
<body>
<jsp:include page="partial/navbar.jsp"></jsp:include>
<div class="container">
<h1>Gruppi</h1>
<table class="table">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Nome</th>
    </tr>
    </thead>
    <tbody>

<c:forEach items="${gruppi}" var="gruppo">
    <tr>
        <th scope="row">${gruppo.id}</th>
        <td>${gruppo.name}</td>
    </tr>
</c:forEach>
    </tbody>
    </table>
</div>
</body>
</html>