<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-dark p-3">
        <div class="container">
            <a class="navbar-brand text-white" href="Inicio">Biblioteca</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <c:choose>
                        <%-- Cuando existe un usuario en la sesion --%>
                        <c:when test="${not empty sessionScope.usuario}">
                            <li class="nav-item">
                                <a class="nav-link text-white" href="MenuPrincipal"><c:out
                                        value="Hola, ${sessionScope.usuario.nombre}"></c:out></a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white" href="CerrarSesion">Cerrar sesión</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link text-white" href="Registro">Registrarse</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-white" href="Login">Iniciar Sesión</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>
</header>