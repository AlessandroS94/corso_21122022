${Error}
<!doctype html>
<html lang="en">
<jsp:include page="head.jsp"></jsp:include>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<div class="container">
    <br>
    <div class="card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">${Error}</h5>
        </div>
        <p>
            ${Error_info}
        </p>
    </div>
</div>
</body>
<jsp:include page="scriptJS.jsp"></jsp:include>