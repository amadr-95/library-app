<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión Autores</title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css"/>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="../components/header.jsp"/>

<main class="flex-grow-1">
    <h1 class="text-center my-5">Gestión de Autores</h1>
    <c:choose>
    <c:when test="${empty listaAutores}">
        <p class="text-center">No hay autores disponibles en la lista</p>
    </c:when>
    <c:otherwise>
    <section class="container">
        <a href="CrearAutor" class="btn btn-success mb-4">Nuevo Autor</a>
        <table class="table table-striped text-center">
            <tr>
                <th>ID</th>
                <th>Nombre del autor</th>
                <th>Libros</th>
                <th>Acciones</th>
            </tr>
            <c:forEach items="${listaAutores}" var="autor">
                <tr>
                    <td>${autor.id}</td>
                    <td>${autor.nombre}</td>
                    <td>
                        <c:forEach items="${autor.libros}" var="libro" varStatus="loop">
                            ${libro.titulo}<c:if test="${not loop.last}">,</c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <a href="EditarAutor?id=${autor.id}" class="btn btn-primary">Editar</a>
                        <a href="EliminarAutor?id=${autor.id}"
                           class="btn btn-danger eliminar-autor">Eliminar</a>
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
    document.querySelectorAll('.eliminar-autor').forEach(autor => {
        autor.addEventListener('click', function (e) {
            // let nombre = e.target.parentElement.parentElement.children[0].textContent;
            e.preventDefault();
            Swal.fire({
                title: "¿Está seguro?",
                text: "Esta acción no se puede deshacer",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sí, eliminar'
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
