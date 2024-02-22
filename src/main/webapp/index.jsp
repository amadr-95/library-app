<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
    <%-- No funciona
    <script defer type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>--%>
</head>
<body>
<nav
        class="navbar navbar-expand-md navbar-light bg-light fixed-top py-3 box-shadow"
>
    <a href="index.html" class="navbar-brand">
        <h3>Explorers</h3>
    </a>

    <button
            class="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Abrir Navegacion"
    >
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
            <li class="nav-item dropdown">
                <a
                        class="nav-link dropdown-toggle"
                        href="#"
                        id="navbarDropdown"
                        role="button"
                        data-toggle="dropdown"
                        aria-haspopup="true"
                        aria-expanded="false"
                >Destinos</a
                >
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="ejemploDestino.html"
                    >California</a
                    >
                    <a class="dropdown-item" href="ejemploDestino.html"
                    >Paris</a
                    >
                    <a class="dropdown-item" href="ejemploDestino.html"
                    >Dublin</a
                    >
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="planes.html">Planes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="contacto.html">Contacto</a>
            </li>
            <li class="nav-item">
                <a
                        class="nav-link"
                        href="#"
                        data-toggle="modal"
                        data-target="#modalLogin"
                >Login</a
                >
            </li>
            <li class="nav-item">
                <a
                        class="btn btn-outline-primary ml-md-2"
                        href="register.html"
                >Regístrate</a
                >
            </li>
        </ul>
    </div>
</nav>
<!-- Fin Navbar -->


</body>
</html>