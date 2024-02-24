<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion Empleados</title>
    <link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="../components/header.jsp"/>

<main class="flex-grow-1">
    <h1 class="text-center my-5">Gestión de empleados</h1>
    <c:choose>
        <c:when test="${empty listaEmpleados}">
            <p class="text-center">No hay empleados</p>
        </c:when>
        <c:otherwise>
            <section class="container">
                <table class="table table-striped text-center">
                    <thead>
                    <tr>
                        <th scope="col">Nombre</th>
                        <th scope="col">Apellido</th>
                        <th scope="col">Email</th>
                        <th scope="col">Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listaEmpleados}" var="empleado">
                        <tr>
                            <td>${empleado.nombre}</td>
                            <td>${empleado.apellido}</td>
                            <td>${empleado.email}</td>
                            <td>
                                <a href="EditarEmpleado?id=${empleado.id}" class="btn btn-primary">Editar</a>
                                <a href="EliminarEmpleado?id=${empleado.id}" class="btn btn-danger">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <a href="CrearEmpleado" class="btn btn-success">Nuevo empleado</a>
            </section>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="../components/footer.jsp"/>
</body>
</html>
