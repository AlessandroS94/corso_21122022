<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <title>Registration employee</title>
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

        <h2> Employee Creation: </h2>
        
        <form:form action="${pageContext.request.contextPath}/employee/registration" 
                   method="POST" modelAttribute="newEmployee">
            <form:label path="fullname">Fullname</form:label>
            <form:input type="text" path="fullname" /> 
            <br>

            <form:label path="email">E-mail</form:label>
            <form:input type="text" path="email" /> 
            <br>

            Gender
            M <form:radiobutton path="gender" value="M"  />
            F <form:radiobutton path="gender" value="F" />
            <br>

            <form:label path="department">Department</form:label>
            <form:select path="department.id">
                <form:options items="${departments}" itemLabel="name" itemValue="id"/>
            </form:select>
            <br>

            <input type="submit" value="Save" /> 
        </form:form>

        <br>

        <form action="${pageContext.request.contextPath}/employee/searchByEmail" method="POST">

            <label for="email">Cerca per e-mail:</label>
            <input id="email" type="text" name="email" />
            <input type="submit" value="Cerca!"/>
        </form>

        <br>
        
        <form action="${pageContext.request.contextPath}/employee/search/genderAndDepartment" method="POST">

            <label for="depName">Cerca per sesso e nome dipartimento</label>
            <input id="depName" type="text" name="depName" />
            
            M <input type="radio" name="gender" value="M" checked />
            F <input type="radio" name="gender" value="F" />
            <br>
            
            <input type="submit" value="Cerca!"/>
        </form>

        <br>
        
        <form action="${pageContext.request.contextPath}/employee/search/department" method="POST">

            <label for="depName">Cerca per dipartimento(name):</label>
            <input id="depName" type="text" name="depName" />
            <input type="submit" value="Cerca!"/>
        </form>

        <br>

        <div style="display:table;width:25%;text-align:center;border:1px solid black">
            <div style="display:table-row;background-color: lightsteelblue">
                <div style="display:table-cell">Fullname</div>
                <div style="display:table-cell">Email</div>
                <div style="display:table-cell">Gender</div>
                <div style="display:table-cell">Dipartimento</div>
                <div style="display:table-cell">Delete</div>
            </div>

            <c:forEach items="${employees}" var="emp">
                <div style="display:table-row">
                    <div style="display:table-cell">${emp.fullname}</div>
                    <div style="display:table-cell">${emp.email}</div>
                    <div style="display:table-cell">${emp.gender}</div>
                    <div style="display:table-cell">${emp.department.name}</div>
                    <div style="display:table-cell">
                        <a href="${pageContext.request.contextPath}/employee/delete?id=${emp.id}">Delete</a>
                    </div>
                </div>
            </c:forEach>
        </div>
        
        <br>

        <form action="${pageContext.request.contextPath}/employee/">
            <input type="submit" value="Load all"/>
        </form>

        <div>
            &nbsp;
        </div>
        <a href="${pageContext.request.contextPath}/">Home</a>          
</div>
</body>
</html>