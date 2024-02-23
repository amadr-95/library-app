<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú Principal</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css"/>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="components/header.jsp"/>

<h2>Menú Principal</h2>
<main class="flex-grow-1">

    <c:choose>
        <c:when test="${admin}">
            <!-- Si el rol es ADMINISTRADOR, muestra opciones de administrador -->
            <ul>
                <li><a href="#">Administrar usuarios</a></li>
                <li><a href="#">Administrar libros</a></li>
                <!-- Agrega aquí más opciones de menú para el rol de administrador -->
            </ul>
        </c:when>
        <c:when test="${empleado}">
            <!-- Si el rol es EMPLEADO, muestra opciones de bibliotecario -->
            <ul>
                <li><a href="#">Registrar préstamo</a></li>
                <li><a href="#">Registrar devolución</a></li>
                <!-- Agrega aquí más opciones de menú para el rol de bibliotecario -->
            </ul>
        </c:when>
        <c:when test="${socio}">
            <!-- Si el rol es USUARIO, muestra opciones de usuario -->
            <ul>
                <li><a href="#">Buscar libros</a></li>
                <li><a href="#">Ver historial de préstamos</a></li>
                <!-- Agrega aquí más opciones de menú para el rol de usuario -->
            </ul>
        </c:when>
        <c:otherwise>
            <!-- En caso de que el rol no coincida con ninguno de los anteriores -->
            <p>Rol desconocido. Por favor, contacta al administrador.</p>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="components/footer.jsp"/>

</body>
</html>

