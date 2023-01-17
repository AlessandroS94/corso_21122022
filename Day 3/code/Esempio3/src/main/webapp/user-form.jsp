<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>
<title>User Management Application</title>
<jsp:include page="style.jsp"></jsp:include>
<body>
<jsp:include page="navbar.jsp">
  <jsp:param name="inserimento" value="active" />
</jsp:include>
<br>
<div class="card">
  <h5 class="card-header">Aggiungi Utente</h5>
  <div class="card-body">
    <form method="post" action="ServletInserUser">
      <div class="form-group">
        <label  class="form-label">Nome</label>
        <input type="text" placeholder="Carlo" class="form-control" name="name" required="required">
      </div>
      <div class="form-group">
        <label class="form-label">Email</label>
        <input type="text" placeholder="carlo@mail.it" class="form-control" name="email" required="required">
      </div>
      <div class="form-group">
        <label  class="form-label">Stato</label>
        <input type="text" class="form-control" placeholder="Italia" name="country" required="required">
      </div>
      <div class="form-group">
        <label  class="form-label">Età</label>
        <input type="number" class="form-control" placeholder="1" name="age" required="required">
      </div>
      <button type="submit" class="btn btn-success">Salva</button>
    </form>
  </div>
</div>

</body>
</html>
