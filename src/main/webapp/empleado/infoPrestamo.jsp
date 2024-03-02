<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Información del préstamo</title>
    <jsp:include page="../components/bootstrap.jsp"/>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="../components/header.jsp"/>

<main class="flex-grow-1">
    <h1 class="text-center my-5">Información del préstamo</h1>
    <div class="container">
        <div class="row justify-content-center">
            <c:if test="${not empty libroPrestado.imagenPortada}">
                <div class="col-md-3">
                    <div class="card">
                        <div class="card-body w-auto">
                            <img src="../img/${libroPrestado.imagenPortada}" alt="${libroPrestado.titulo}" width="100">
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header text-center">Detalles del préstamo</div>
                    <div class="card-body">
                        <h5>Detalles del socio</h5>
                        <ul class="list-group">
                            <li class="list-group-item">Nombre: ${socio.nombre}</li>
                            <c:if test="${ not empty socio.apellido}">
                                <li class="list-group-item">Apellidos: ${socio.apellido}</li>
                            </c:if>
                            <li class="list-group-item">Email: ${socio.email}</li>
                            <li class="list-group-item">Préstamos totales: ${prestamosSocio.size()}</li>
                        </ul>
                        <h5 class="mt-4">Detalles del libro</h5>
                        <ul class="list-group">
                            <li class="list-group-item">ISBN: ${libroPrestado.isbn}</li>
                            <li class="list-group-item">Titulo: ${libroPrestado.titulo}</li>
                            <li class="list-group-item">Fecha Edición: ${libroPrestado.fechaEdicion}</li>
                            <li class="list-group-item">Autores:</li>
                            <ul>
                                <c:forEach items="${libroPrestado.autores}" var="autor">
                                    <li>${autor.nombre}</li>
                                </c:forEach>
                            </ul>
                            <li class="list-group-item">Géneros:</li>
                            <ul>
                                <c:forEach items="${libroPrestado.generos}" var="genero">
                                    <li>${genero.genero}</li>
                                </c:forEach>
                            </ul>
                            <li class="list-group-item">Número de veces prestado: ${prestamosLibro.size()}</li>
                        </ul>
                        <h4 class="mt-4">Fecha de devolución máxima: ${prestamo.fechaDevolucionMaxima}</h4>
                    </div>
                </div>
            </div>
        </div>
        <a href="RealizarPrestamo" class="btn btn-primary mt-3">Volver</a>
    </div>
</main>

<jsp:include page="../components/footer.jsp"/>
</body>
</html>