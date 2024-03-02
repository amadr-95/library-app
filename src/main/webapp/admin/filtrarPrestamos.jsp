<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<table class="table table-striped text-center">
    <tr>
        <td>ID del préstamo</td>
        <td>Titulo del libro</td>
        <td>Fecha de préstamo</td>
        <td>Dias transcurridos</td>
        <td>Fecha de devolución</td>
        <td>Nombre del usuario</td>
        <td>Email del usuario</td>
    </tr>
    <c:forEach items="${prestamos}" var="prestamo">
        <tr>
            <td>${prestamo.id}</td>
            <td>${prestamo.libro.titulo}</td>
            <td>${prestamo.fechaPrestamo}</td>
            <td <c:if test="${prestamo.diasPrestamo > DIAS_MAXIMOS}"> class="text-danger" </c:if> >
                    ${prestamo.diasPrestamo}
            </td>
            <td>${prestamo.fechaDevolucion}</td>
            <td>${prestamo.usuario.nombre}</td>
            <td>${prestamo.usuario.email}</td>
        </tr>
    </c:forEach>
</table>