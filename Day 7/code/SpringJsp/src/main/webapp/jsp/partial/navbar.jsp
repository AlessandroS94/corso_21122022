<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="href="${pageContext.request.contextPath}">Springboot JSP</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link ${param.index}" aria-current="page" href="${pageContext.request.contextPath}/">Home</a>
                    </li>

                <li class="nav-item">
                    <a class="nav-link ${param.create}"  aria-current="page" href="${pageContext.request.contextPath}/createInfo">Crea Info</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link ${param.delete}"  aria-current="page" href="${pageContext.request.contextPath}/deleteViewInfo">Cancella Info</a>
                </li>
            </ul>
        </div>
    </div>
</nav>