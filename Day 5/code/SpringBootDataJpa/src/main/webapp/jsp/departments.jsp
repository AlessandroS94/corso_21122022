<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!doctype html>
<html lang="en">
<jsp:include page="partial/head.jsp"></jsp:include>
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
<body>
<jsp:include page="partial/navbar.jsp"></jsp:include>
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