<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
    </body>
</html>
