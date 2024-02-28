<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Editar Empleado</title>
    <jsp:include page="../components/bootstrap.jsp"/>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="../components/header.jsp"/>

<main class="flex-grow-1">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card my-5">
                    <div class="card-header text-center">Editar Empleado</div>
                    <div class="card-body">
                        <%-- Mostrar mensaje de error si hay uno --%>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                    ${error}
                            </div>
                        </c:if>
                        <form method="post">
                            <input type="hidden" name="id" value="${usuarioEditar.id}">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre</label>
                                <input type="text" class="form-control" id="nombre" name="nombre"
                                       value="${usuarioEditar.nombre}" required>
                            </div>
                            <div class="mb-3">
                                <label for="apellidos" class="form-label">Apellidos <span>(Opcional)</span></label>
                                <input type="text" class="form-control" id="apellidos" name="apellidos"
                                       value="${usuarioEditar.apellido}">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email"
                                       value="${usuarioEditar.email}" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Contraseña</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       value="${usuarioEditar.password}" required>
                            </div>
                            <div class="mb-3">
                                <label for="rol" class="form-label">Rol</label>
                                <select class="form-select" id="rol" name="rol" required>
                                    <option value="">Selecciona un rol</option>
                                    <c:forEach items="${roles}" var="rol">
                                        <c:if test="${rol != 'SOCIO'}">
                                            <option value="${rol}"
                                                    <c:if test="${rol == usuarioEditar.rol}">selected</c:if>>${rol}
                                            </option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="d-grid gap-2">
                                <input type="submit" class="btn btn-primary" value="Editar Empleado"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="../components/footer.jsp"/>
</body>
</html>
