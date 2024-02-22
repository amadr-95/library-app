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
<body>
<h1>Lista de Libros</h1>
<c:choose>
    <c:when test="${empty libros}">
        <p>No hay libros disponibles en la lista</p>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>ISBN</th>
                <th>Título</th>
                <th>Fecha de Edición</th>
                <th>Imagen de Portada</th>
                <th>Número de Ejemplares</th>
            </tr>
            <c:forEach items="${libros}" var="libro">
                <tr>
                    <td>${libro.isbn}</td>
                    <td>${libro.titulo}</td>
                    <td>${libro.fechaEdicion}</td>
                    <td><img src="${libro.imagenPortada}" alt="Portada" width="100" height="150"></td>
                    <td>${libro.numeroEjemplares}</td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>