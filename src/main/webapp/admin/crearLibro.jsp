<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nuevo Libro</title>
    <jsp:include page="../components/bootstrap.jsp"/>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="../components/header.jsp"/>

<main class="flex-grow-1">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card my-5">
                    <div class="card-header text-center">Añadir Libro</div>
                    <div class="card-body">
                        <%-- Mostrar mensaje de error si hay uno --%>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                    ${error}
                            </div>
                        </c:if>
                        <form method="post" enctype="multipart/form-data">
                            <div class="mb-3">
                                <label for="isbn" class="form-label">ISBN</label>
                                <input type="text" class="form-control" id="isbn" name="isbn" required>
                            </div>
                            <div class="mb-3">
                                <label for="titulo" class="form-label">Titulo</label>
                                <input type="text" class="form-control" id="titulo" name="titulo" required>
                            </div>
                            <div class="mb-3">
                                <label for="ejemplares" class="form-label">Numero de ejemplares</label>
                                <input type="number" class="form-control" id="ejemplares" name="ejemplares" required>
                            </div>
                            <div class="mb-3">
                                <label for="fechaEdicion" class="form-label">Fecha de edición</label>
                                <input type="date" class="form-control" id="fechaEdicion" name="fechaEdicion" required>
                            </div>
                            <div class="mb-3">
                                <label for="portada" class="form-label">Imagen de portada <span>(opcional)</span></label>
                                <input type="file" class="form-control" id="portada" name="portada">
                            </div>
                            <%--Select multiple para los autores--%>
                            <div class="mb-3">
                                <label for="autores" class="form-label">Autores</label>
                                <select class="form-select" id="autores" name="autores" required multiple>
                                    <c:forEach items="${autores}" var="autor">
                                        <option value="${autor.id}">${autor.nombre}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <%--Select multiple para los generos --%>
                            <div class="mb-3">
                                <label for="generos" class="form-label">Generos</label>
                                <select class="form-select" id="generos" name="generos" required multiple>
                                    <c:forEach items="${generos}" var="genero">
                                        <option value="${genero.id}">${genero.genero}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="d-grid gap-2">
                                <input type="submit" class="btn btn-primary" value="Guardar Libro"/>
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
