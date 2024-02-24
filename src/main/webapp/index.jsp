<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Biblioteca - Inicio</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="components/header.jsp"/>

<main class="flex-grow-1">
    <h1 class="text-center my-5">Lista de Libros</h1>
    <c:choose>
    <c:when test="${empty libros}">
        <p class="text-center">No hay libros disponibles en la lista</p>
    </c:when>
    <c:otherwise>
    <section class="container">
        <table class="table table-striped text-center">
            <tr class="align-middle">
                <th>ISBN</th>
                <th>Título</th>
                <th>Autores</th>
                <th>Fecha de Edición</th>
                <th>Imagen de Portada</th>
                <th>Número de Ejemplares</th>
                <th>Generos</th>
            </tr>
            <c:forEach items="${libros}" var="libro">
                <tr class="align-middle">
                    <td>${libro.isbn}</td>
                    <td>${libro.titulo}</td>
                    <td>
                        <ul class="list-group">
                            <c:forEach items="${libro.autores}" var="autor">
                                <li class="list-group-item">${autor.nombre}</li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td>${libro.fechaEdicion}</td>
                    <td>
                        <c:choose>
                            <c:when test="${empty libro.imagenPortada}">
                                <span>Sin imagen</span>
                            </c:when>
                            <c:otherwise>
                                <img src="img/${libro.imagenPortada}" alt="${libro.titulo}" width="100" height="auto"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${libro.numeroEjemplares}</td>
                    <td>
                        <ul class="list-group">
                            <c:forEach items="${libro.generos}" var="genero" varStatus="loop">
                                <li class="list-group-item">${genero.genero}</li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </c:otherwise>
        </c:choose>
    </section>
</main>

<jsp:include page="components/footer.jsp"/>
</body>
</html>
