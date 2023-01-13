<!doctype html>
<html lang="en">
<jsp:include page="head.jsp"></jsp:include>
<body>
<jsp:include page="navbar.jsp"></jsp:include>

<div class="container">
    <code class="small">${Info.getName()}</code>
    <p class="lead">${Info.getDescription()}</p>
</div>
<footer class="footer mt-auto py-3 bg-light">
    <div class="container">
        <span class="text-muted">Brutto web site</span>
    </div>
</footer>
</body>
<jsp:include page="scriptJS.jsp"></jsp:include>