<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <title>Registration department</title>
    <style type="text/css">
        label {
            display:inline-block;
            width:125px;
        }
        div.notification {
            color : white;
            font-weight: bold;
            width:25%;
            padding: 5px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}">Menu</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a  class="nav-link" href="${pageContext.request.contextPath}/employee/home"> Employees mangement </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/department/home"> Departments management </a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
        <c:if test="${response != null && response.success == false}">
            <div class="notification" style="background-color: red">
                ${response.message}
            </div>
        </c:if>
        
        <c:if test="${response != null && response.success == true}">
            <div class="notification" style="background-color: green">
                ${response.message}
            </div>
        </c:if>
        
        <h2> Department Creation: </h2>
        <form action="${pageContext.request.contextPath}/department/registration" method="POST">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" />
            <input type="submit" value="Save" /> 
        </form>
            
        <br>
        
        <div style="display:table;width:25%;text-align:center;border:1px solid black">
            <div style="display:table-row;background-color: lightsteelblue">
                <div style="display:table-cell">Name</div>
                <div style="display:table-cell">Delete</div>
            </div>
            
            <c:forEach items="${list}" var="department">
                <div style="display:table-row">
                    <div style="display:table-cell">${department.name}</div>
                    <div style="display:table-cell">
                        <a href="${pageContext.request.contextPath}/department/delete?id=${department.id}">Delete</a>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div>
            &nbsp;
        </div>
        <a href="${pageContext.request.contextPath}/">Home</a>          
</div>
</body>
</html>