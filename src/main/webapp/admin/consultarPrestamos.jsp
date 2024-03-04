<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de préstamos</title>
    <jsp:include page="../components/bootstrap.jsp"/>
    <jsp:include page="../components/sweetalert.jsp"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

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
                    <input type="submit" class="btn btn-danger" data-bs-dismiss="modal" id="notificar"
                           value="Notificar"/>
                </form>
            </div>
        </div>
    </div>
</div>


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

            <div>
                <c:if test="${not empty resultado}">
                    <div class="alert alert-danger" role="alert">
                            ${resultado}
                    </div>
                </c:if>
            </div>

            <div class="col-3 mb-3">
                <input type="text" id="filtro" class="form-control" placeholder="Busca por libro o socio"
                       onkeyup="filtrar()"/>
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
            <c:import url="FiltrarPrestamos">
                <c:param name="filtro" value=""/>
            </c:import>
        </div>
        </c:otherwise>
        </c:choose>
    </section>
</main>

<jsp:include page="../components/footer.jsp"/>

<script>
    //filtro
    function filtrar() {
        const filtro = document.getElementById('filtro').value;
        $.ajax({
            method: "GET",
            url: "FiltrarPrestamos",
            data: {filtro: filtro}
        }).done(function (listado) {
            $("#resultados").html(listado);
        });
    }

    //confirmar mandar mails
    /*document.getElementById('notificar').addEventListener('click', function (e) {
        e.preventDefault();
        Swal.fire({
            title: "¿Estás seguro de que desea mandar los emails?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sí, enviar'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = e.target.href;
            }
        });
    });*/

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
