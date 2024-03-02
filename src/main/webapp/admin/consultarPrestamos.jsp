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

<!-- Modal ver usuarios sancionados -->
<div class="modal fade" id="modalUsuariosSancionados" tabindex="-1" aria-labelledby="modalUsuariosSancionadosLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalUsuariosSancionadosLabel">Usuarios sancionados</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <ul id="listaUsuariosSancionados" class="list-group">
                    <!-- Aquí se mostrarán los usuarios sancionados -->
                </ul>
            </div>
            <div class="modal-footer">
                <form method="post">
                    <input type="submit" class="btn btn-danger" data-bs-dismiss="modal" value="Notificar"/>
                </form>
            </div>
        </div>
    </div>
</div>


<c:set var="DIAS_MAXIMOS" value="15"/>
<jsp:include page="../components/header.jsp"/>

<main class="flex-grow-1">
    <h1 class="text-center my-5">Gestión de Préstamos</h1>
    <c:choose>
    <c:when test="${empty prestamos}">
        <p class="text-center">No existen préstamos</p>
    </c:when>
    <c:otherwise>
    <section class="container">
        <div class="row">

            <div class="col-3 mb-3">
                <input type="text" id="filtro" class="form-control" placeholder="Busca por libro o socio"/>
            </div>

            <div class="col-3 mb-3">
                <button class="btn btn-danger ver-sancionados" id="sancionar">
                    Ver Socios Sancionados (${sancionados.size()})
                </button>
                <ul class="list-group" style="display: none;">
                    <c:forEach items="${sancionados}" var="usuarioSancionado">
                        <li class="list-group-item">
                                ${usuarioSancionado.nombre} - ${usuarioSancionado.email}
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div id="resultados">
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
        </div>
        </c:otherwise>
        </c:choose>
    </section>
</main>

<jsp:include page="../components/footer.jsp"/>

<%--<script>
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
</script>--%>

<script>
    //filtro
    document.getElementById('filtro').addEventListener('keyup', (e) => {
        const filtro = e.target.value.trim();
        //peticion fetch a la url del controlador Inicio con el parametro filtro
        fetch('Inicio?filtro=' + encodeURIComponent(filtro), {method: 'GET'})
            .then(response => response.text())
            .then(result => {
                const resultado = document.getElementById('resultadoLibros');
                resultado.innerHTML = result;
            })
    });

    // Mostrar los usuarios sancionados en una ventana modal
    document.querySelectorAll('.ver-sancionados').forEach(usuario => {
        usuario.addEventListener('click', function (e) {
            e.preventDefault();
            const listadoUsuariosSancionados = document.getElementById('listaUsuariosSancionados');
            listadoUsuariosSancionados.innerHTML = '';
            const usuarios = document.querySelectorAll('.list-group-item');
            usuarios.forEach(usuario => {
                const li = document.createElement('li');
                li.classList.add('list-group-item');
                li.textContent = usuario.textContent;
                listadoUsuariosSancionados.appendChild(li);
            });
            // Mostrar la ventana modal
            new bootstrap.Modal(document.getElementById('modalUsuariosSancionados')).show();
        });
    });
</script>
</body>
</html>
