<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Libro</title>
    <jsp:include page="../components/bootstrap.jsp"/>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="../components/header.jsp"/>

<main class="flex-grow-1">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card my-5">
                    <div class="card-header text-center">Editar Libro</div>
                    <div class="card-body">
                        <%-- Mostrar mensaje de error si hay uno --%>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                    ${error}
                            </div>
                        </c:if>
                        <form method="post" enctype="multipart/form-data">
                            <input type="hidden" name="id" value="${libroEditar.id}">
                            <div class="mb-3">
                                <label for="isbn" class="form-label">ISBN</label>
                                <input type="text" class="form-control" id="isbn" name="isbn"
                                       value="${libroEditar.isbn}" required>
                            </div>
                            <div class="mb-3">
                                <label for="titulo" class="form-label">Titulo</label>
                                <input type="text" class="form-control" id="titulo" name="titulo"
                                       value="${libroEditar.titulo}" required>
                            </div>
                            <div class="mb-3">
                                <label for="ejemplares" class="form-label">Numero de ejemplares</label>
                                <input type="number" class="form-control" id="ejemplares" name="ejemplares"
                                       value="${libroEditar.numeroEjemplares}" required>
                            </div>
                            <div class="mb-3">
                                <label for="fechaEdicion" class="form-label">Fecha de edición</label>
                                <input type="date" class="form-control" id="fechaEdicion" name="fechaEdicion"
                                       value="${libroEditar.fechaEdicion}" required>
                            </div>
                            <input type="hidden" name="portadaActual" value="${libroEditar.imagenPortada}">
                            <div class="mb-3">
                                <label for="portada" class="form-label">Imagen de portada
                                    <span>(opcional)</span></label>
                                <input type="file" class="form-control" id="portada" name="portada">
                                <span class="form-text">Solo se permiten archivos de tipo <i>.png</i>.</span>
                                <span class="form-text">Si no cargas ninguna foto se mantendrá la que estaba</span>
                            </div>
                            <%--Select multiple para los autores--%>
                            <div class="mb-3">
                                <label for="autores" class="form-label">Autores</label>
                                <select class="form-select" id="autores" name="autores" required multiple>
                                    <c:forEach items="${autores}" var="autor">
                                        <c:set var="seleccionado" value="false"/>
                                        <c:forEach items="${libroEditar.autores}" var="autorLibro">
                                            <c:if test="${autor.id == autorLibro.id}">
                                                <c:set var="seleccionado" value="true"/>
                                            </c:if>
                                        </c:forEach>
                                        <option value="${autor.id}"
                                                <c:if test="${seleccionado}">selected</c:if>>${autor.nombre}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <%--Select multiple para los generos --%>
                            <div class="mb-3">
                                <label for="generos" class="form-label">Generos</label>
                                <select class="form-select" id="generos" name="generos" required multiple>
                                    <c:forEach items="${generos}" var="genero">
                                        <c:set var="seleccionado" value="false"/>
                                        <c:forEach items="${libroEditar.generos}" var="generoLibro">
                                            <c:if test="${genero.id == generoLibro.id}">
                                                <c:set var="seleccionado" value="true"/>
                                            </c:if>
                                        </c:forEach>
                                        <option value="${genero.id}"
                                                <c:if test="${seleccionado}">selected</c:if>>${genero.genero}
                                        </option>
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
