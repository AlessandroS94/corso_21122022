<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<title>JSP - Hello World</title>
<jsp:include page="style.jsp"></jsp:include>
<jsp:include page="navbar.jsp">
    <jsp:param name="home" value="active"/>
</jsp:include>
<body>
<h1><%= "Benvenuto!" %>
</h1>
<br/>
</body>
</html>