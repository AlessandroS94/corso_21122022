<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>JEE 10 - JAKARTA</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Jakarta</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" href="#">JEE 10</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container my-4">
    <form method="post" action="ServletInserUser">
        <div class="mb-3">
            <label class="form-label">Nome</label>
            <input type="text" class="form-control" name="name" required placeholder="Enter your name">
        </div>
        <div class="mb-3">
            <label class="form-label">Email</label>
            <input type="email" class="form-control" name="email" required placeholder="Enter your email">
        </div>
        <div class="mb-3">
            <label for="countrySelect" class="form-label">Nazione</label>
            <select class="form-control" id="countrySelect" name="country" required>
                <option value="">Select your country</option>
                <option value="Austria">Austria</option>
                <option value="Belgium">Belgium</option>
                <option value="Bulgaria">Bulgaria</option>
                <option value="Croatia">Croatia</option>
                <option value="Cyprus">Cyprus</option>
                <option value="Czech Republic">Czech Republic</option>
                <option value="Denmark">Denmark</option>
                <option value="Estonia">Estonia</option>
                <option value="Finland">Finland</option>
                <option value="France">France</option>
                <option value="Germany">Germany</option>
                <option value="Greece">Greece</option>
                <option value="Hungary">Hungary</option>
                <option value="Ireland">Ireland</option>
                <option value="Italy">Italy</option>
                <option value="Latvia">Latvia</option>
                <option value="Lithuania">Lithuania</option>
                <option value="Luxembourg">Luxembourg</option>
                <option value="Malta">Malta</option>
                <option value="Netherlands">Netherlands</option>
                <option value="Poland">Poland</option>
                <option value="Portugal">Portugal</option>
                <option value="Romania">Romania</option>
                <option value="Slovakia">Slovakia</option>
                <option value="Slovenia">Slovenia</option>
                <option value="Spain">Spain</option>
                <option value="Sweden">Sweden</option>
            </select>
        </div>

        <button type="submit" class="btn btn-success">Salva</button>
        <!-- Button to trigger modal -->
        <button type="button" class="btn btn-info" data-bs-toggle="modal" data-bs-target="#usersModal">
            Mostra tutti gli utenti
        </button>
    </form>
</div>
<!-- Modal Structure -->
<div class="modal fade" id="usersModal" tabindex="-1" aria-labelledby="usersModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="usersModalLabel">Utenti</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- User List Here -->
                <ul>
                    <c:forEach var="user" items="${users}">
                        <b>Nome:</b> ${user.name} <br>
                        <b>Cognome:</b> ${user.email}<br>
                        <b>Nazione:</b> ${user.country}<br>
                        <a href="deleteUser?userId=${user.id}" class="btn btn-danger btn-sm">Delete</a>
                        <hr>
                    </c:forEach>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<footer class="text-center text-lg-start bg-light text-muted mt-4">
    <div class="text-center p-4">
        © 2023 JEE 10 - Jakarta EE
    </div>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
