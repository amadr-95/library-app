<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de préstamos</title>
    <jsp:include page="../components/bootstrap.jsp"/>
    <jsp:include page="../components/sweetalert.jsp"/>
</head>
<body class="d-flex flex-column min-vh-100">
<c:set var="DIAS_MAXIMOS" value="10"/>
<jsp:include page="../components/header.jsp"/>

<main class="flex-grow-1">
    <h1 class="text-center my-5">Gestión de Préstamos</h1>
    <c:choose>
    <c:when test="${empty prestamos}">
        <p class="text-center">No tienes ningún préstamo</p>
    </c:when>
    <c:otherwise>
    <section class="container">
        <table class="table table-striped text-center">
            <tr>
                <td>ID del préstamo</td>
                <td>ISBN del libro</td>
                <td>Titulo del libro</td>
                <td>Fecha de préstamo</td>
                <td>Dias transcurridos</td>
                <td>Fecha de devolución</td>
                <td>Acciones</td>
            </tr>
            <c:forEach items="${prestamos}" var="prestamo">
                <tr>
                    <td>${prestamo.id}</td>
                    <td>${prestamo.libro.isbn}</td>
                    <td>${prestamo.libro.titulo}</td>
                    <td>${prestamo.fechaPrestamo}</td>
                    <td <c:if test="${prestamo.diasPrestamo > DIAS_MAXIMOS}"> class="text-danger" </c:if> >
                            ${prestamo.diasPrestamo}
                    </td>
                    <td>${prestamo.fechaDevolucion}</td>
                    <td>
                        <c:choose>
                            <c:when test="${prestamo.fechaDevolucion == null}">
                                <a href="DevolucionPrestamo?id=${prestamo.id}" class="btn btn-primary devolver">Devolver</a>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${prestamo.diasPrestamo > DIAS_MAXIMOS}">
                                        <p class="text-danger">Devuelto (retraso)</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="text-success">Devuelto</p>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        </c:otherwise>
        </c:choose>
    </section>
</main>

<jsp:include page="../components/footer.jsp"/>

<script>
    document.querySelectorAll('.devolver').forEach(libro => {
        libro.addEventListener('click', function (e) {
            e.preventDefault();
            Swal.fire({
                title: "¿Estás seguro de devolver el libro?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí, devolver'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location.href = e.target.href;
                }
            });
        });
    });
</script>
</body>
</html>
