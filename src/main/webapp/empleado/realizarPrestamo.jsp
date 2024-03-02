<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <title>Realizar préstamo</title>
    <jsp:include page="../components/bootstrap.jsp"/>
</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="../components/header.jsp"/>

<main class="flex-grow-1">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card my-5">
                    <div class="card-header text-center">Realizar Préstamo</div>
                    <div class="card-body">
                        <%-- Mostrar mensaje de error si hay uno --%>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger" role="alert">
                                    ${error}
                            </div>
                        </c:if>
                        <form method="post">
                            <div class="mb-3">
                                <label for="socio" class="form-label">Elige un socio</label>
                                <select class="form-select" id="socio" name="socio" required>
                                    <option value="">Selecciona un socio</option>
                                    <c:forEach items="${socios}" var="socio">
                                    <option value="${socio.id}">
                                            ${socio.email} - ${socio.nombre} ${socio.apellido}
                                    </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="mb-3">
                                <label for="libro" class="form-label">Elige un libro</label>
                                <select class="form-select" id="libro" name="libro" required>
                                    <option value="">Selecciona un libro</option>
                                    <c:forEach items="${libros}" var="libro">
                                    <option value="${libro.id}">
                                            ${libro.isbn} - ${libro.titulo} | Uds: ${libro.numeroEjemplares}
                                    </option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="d-grid gap-2">
                                <input type="submit" class="btn btn-primary" value="Realizar Préstamo"/>
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