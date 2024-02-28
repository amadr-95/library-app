<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú Principal</title>
    <jsp:include page="components/bootstrap.jsp"/>
    <style>
        .card {
            transition: transform 0.3s ease-in-out;
        }

        .card:hover {
            transform: scale(1.05);
        }
    </style>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="components/header.jsp"/>

<main class="flex-grow-1">
    <h1 class="text-center my-5">Menú Principal</h1>
    <section class="container">
        <div class="row">

            <c:choose>
                <c:when test="${admin}">
                    <!-- Si el rol es ADMINISTRADOR, muestra opciones de administrador -->
                    <div class="col-md-4 text-center">
                        <div class="card card-body">
                            <h5 class="card-title">Administrar empleados</h5>
                            <p class="card-text">Crea, edita y elimina empleados del sistema</p>
                                <%--Este enlace es al servlet--%>
                            <a href="admin/GestionEmpleados" class="btn btn-primary">Acceder</a>
                        </div>
                    </div>
                    <div class="col-md-4 text-center">
                        <div class="card card-body">
                            <h5 class="card-title">Administrar libros</h5>
                            <p class="card-text">Crea, edita y elimina libros del sistema</p>
                                <%--Este enlace es al servlet--%>
                            <a href="admin/GestionLibros" class="btn btn-primary">Acceder</a>
                        </div>
                    </div>
                    <div class="col-md-4 text-center">
                        <div class="card card-body">
                            <h5 class="card-title">Administrar Autores y Generos</h5>
                            <p class="card-text">Crea, edita y elimina autores y géneros del sistema</p>
                                <%--Este enlace es al servlet--%>
                            <div class="d-flex gap-2">
                                <a href="admin/GestionAutores" class="w-50 btn btn-primary">Autores</a>
                                <a href="admin/GestionGeneros" class="w-50 btn btn-primary">Géneros</a>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:when test="${empleado}">
                    <div class="col-md-4 text-center">
                        <div class="card card-body">
                            <h5 class="card-title">Tarjeta 1</h5>
                            <p class="card-text">Texto 1</p>
                                <%--Este enlace es al servlet--%>
                            <a href="empleado/" class="btn btn-primary">Acceder</a>
                        </div>
                    </div>
                    <div class="col-md-4 text-center">
                        <div class="card card-body">
                            <h5 class="card-title">Tarjeta 2</h5>
                            <p class="card-text">Texto 2</p>
                                <%--Este enlace es al servlet--%>
                            <a href="empleado/" class="btn btn-primary">Acceder</a>
                        </div>
                    </div>
                    <%--<ul>
                        <li><a href="#">Registrar préstamo</a></li>
                        <li><a href="#">Registrar devolución</a></li>
                    </ul>--%>
                </c:when>
                <c:when test="${socio}">
                    <!-- redirigir al servlet GestionPrestamos -->
                    <%--<c:redirect url="socio/GestionPrestamos"/>--%>
                    <div class="container justify-content-center col-4 text-center">
                        <div class="card card-body">
                            <h5 class="card-title text-center">Gestionar préstamos</h5>
                            <p class="card-text">Gestiona tus préstamos y devoluciones</p>
                            <a href="socio/GestionPrestamos" class="btn btn-primary">Acceder</a>
                        </div>
                    </div>

                </c:when>
                <c:otherwise>
                    <!-- En caso de que el rol no coincida con ninguno de los anteriores -->
                    <p>Rol desconocido. Por favor, contacta al administrador.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </section>
</main>

<jsp:include page="components/footer.jsp"/>

</body>
</html>

