<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<title>Lista utenti</title>
<jsp:include page="style.jsp"></jsp:include>
<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="list" value="active"/>
</jsp:include>
<br>
<table class="table table-bordered">
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Email</th>
        <th>Nazione</th>
        <th>Age</th>
        <th>Action </th>
    </tr>
    </thead>
    <tbody>

    <c:forEach var="user" items="${listUser}">

        <tr>
            <td><c:out value="${user.getId()}"/></td>
            <td><c:out value="${user.getName()}"/></td>
            <td><c:out value="${user.getEmail()}"/></td>
            <td><c:out value="${user.getCountry()}"/></td>
            <td><c:out value="${user.getAge()}"/></td>
            <td>
                <a href="ServletDelUser?id=<c:out value='${user.getId()}' />"><i class="bi bi-trash"></i></a>
                <a href="ServletUpdateUser?id=<c:out value='${user.getId()}' />"><i class="bi bi-pencil-square"></i></a>
            </td>
        </tr>
    </c:forEach>
    <br>
    </tbody>
</table>
</body>
</html>
