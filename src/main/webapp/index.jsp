<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Biblioteca - Inicio</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
    <%-- No funciona
    <script defer type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>--%>
</head>
<body class="d-flex flex-column min-vh-100">
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-dark">
        <div class="container">
            <a class="navbar-brand text-white" href="#">Biblioteca - Inicio</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link text-white" href="Index">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white" href="CerrarSesion">Cerrar sesión</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white" href="Index">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white" href="Registro">Registrarse</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-white" href="InicioSesion">Iniciar Sesión</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<main class="flex-grow-1">

    <h1 class="text-center my-5">Lista de Libros</h1>
    <c:choose>
    <c:when test="${empty libros}">
        <p>No hay libros disponibles en la lista</p>
    </c:when>
    <c:otherwise>
    <section class="container">

        <table class="table table-striped p-4 text-center">
            <tr>
                <th>ISBN</th>
                <th>Título</th>
                <th>Autores</th>
                <th>Fecha de Edición</th>
                <th>Imagen de Portada</th>
                <th>Número de Ejemplares</th>
                <th>Generos</th>
            </tr>
            <c:forEach items="${libros}" var="libro">
                <tr>
                    <td>${libro.isbn}</td>
                    <td>${libro.titulo}</td>
                    <td>
                        <c:forEach items="${libro.autores}" var="autor" varStatus="loop">
                            ${autor.nombre}<c:if test="${not loop.last}">,</c:if>
                        </c:forEach>
                    </td>
                    <td>${libro.fechaEdicion}</td>
                    <td>
                        <c:choose>
                            <c:when test="${empty libro.imagenPortada}">
                                <span>Sin imagen</span>
                            </c:when>
                            <c:otherwise>
                                <img src="img/${libro.imagenPortada}" alt="${libro.titulo}" width="100" height="100"/>
                            </c:otherwise>
                        </c:choose>

                    </td>
                    <td>${libro.numeroEjemplares}</td>
                    <td>
                        <c:forEach items="${libro.generos}" var="genero" varStatus="loop">
                            ${genero.genero}<c:if test="${not loop.last}">,</c:if>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </c:otherwise>
        </c:choose>
    </section>
</main>
<footer class="bg-dark">
    <div class="container">
        <p class="mb-0 text-white text-center p-2">&copy; 2024 Biblioteca</p>
    </div>
</footer>
</body>
</html>
