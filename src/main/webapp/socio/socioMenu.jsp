<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- Tabla con los prestamos asociados al usuario
    añadir un boton en la tabla para realizar la devolución del
    libro; incrementar el numero de ejemplares disponibles -->
<table class="table table-striped">
    <thead>
        <tr>
            <th>Id</th>
            <th>Titulo del libro</th>
            <th>Fecha de préstamo</th>
            <th>Fecha de devolución</th>
            <th>Acciones</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${prestamos}" var="prestamo">
            <tr>
                <td>${prestamo.libro.titulo}</td>
                <td>${prestamo.fechaPrestamo}</td>
                <td>${prestamo.fechaDevolucion}</td>
                <td>
                    <a href="Devolucion?id=${prestamo.id}" class="btn btn-primary">Devolver</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>