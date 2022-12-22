<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>
<title>User Management Application</title>
<jsp:include page="style.jsp"></jsp:include>
<body>
<jsp:include page="navbar.jsp"></jsp:include>
<br>
<div class="card">
  <h5 class="card-header">Featured</h5>
  <div class="card-body">
    <form method="post" action="ServletInserUser">
      <div class="form-group">
        <label  class="form-label">Nome</label>
        <input type="text" placeholder="mai@mail.it" class="form-control" name="name" required="required">
      </div>
      <div class="form-group">
        <label class="form-label">Email</label>
        <input type="text" class="form-control" name="email" required="required">
      </div>
      <div class="form-group">
        <label  class="form-label">Stato</label>
        <input type="text" class="form-control" name="country" required="required">
      </div>
      <div class="form-group">
        <label  class="form-label">Stato</label>
        <input type="number" class="form-control" name="age" required="required">
      </div>
      <button type="submit" class="btn btn-success">Salva</button>
    </form>
  </div>
</div>

</body>
</html>
