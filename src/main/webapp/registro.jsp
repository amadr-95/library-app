<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="components/header.jsp"/>

<main class="flex-grow-1">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card my-5">
                    <div class="card-header text-center">Regístrate</div>
                    <div class="card-body">
                        <%-- Mostrar mensaje de error si hay uno --%>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                    ${error}
                            </div>
                        </c:if>
                        <form method="post">
                            <div class="mb-3">
                                <label for="nombre" class="form-label">Nombre</label>
                                <input type="text" class="form-control" id="nombre" name="nombre" required>
                            </div>
                            <div class="mb-3">
                                <label for="apellidos" class="form-label">Apellidos <span>(Opcional)</span></label>
                                <input type="text" class="form-control" id="apellidos" name="apellidos">
                            </div>
                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" id="email" name="email" required>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Contraseña</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <div class="mb-3">
                                <label for="password2" class="form-label text-danger">Repetir contraseña</label>
                                <input type="password" class="form-control" id="password2" name="password2" required>
                            </div>
                            <div class="d-grid gap-2">
                                <input type="submit" class="btn btn-primary" value="Regístrate" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="components/footer.jsp"/>
</body>
</html>
