<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="en_US"/>
<fmt:bundle basename="myProp">
    <fmt:formatDate value=""
    <!DOCTYPE html>
    <html>
    <head>
        <title><fmt:message key="titulo.pagina.inicio"/></title>
        <jsp:include page="components/bootstrap.jsp"/>
    </head>
    <body class="d-flex flex-column min-vh-100">

    <jsp:include page="components/header.jsp"/>

    <main class="flex-grow-1">
        <h1 class="text-center my-5"><fmt:message key="titulo.lista.libros"/></h1>
        <c:choose>
        <c:when test="${empty libros}">
            <p class="text-center"><fmt:message key="mensaje.sin.libros"/></p>
        </c:when>
        <c:otherwise>
        <section class="container">

            <table class="table table-striped text-center">
                <tr class="align-middle">
                    <th><fmt:message key="isbn"/></th>
                    <th><fmt:message key="titulo"/></th>
                    <th><fmt:message key="autores"/></th>
                    <th><fmt:message key="fecha.edicion"/></th>
                    <th><fmt:message key="imagen.portada"/></th>
                    <th><fmt:message key="numero.ejemplares"/></th>
                    <th><fmt:message key="generos"/></th>
                </tr>
                <c:forEach items="${libros}" var="libro">
                    <tr class="align-middle">
                        <td>${libro.isbn}</td>
                        <td>${libro.titulo}</td>
                        <td>
                            <ul class="list-group">
                                <c:forEach items="${libro.autores}" var="autor">
                                    <li class="list-group-item">${autor.nombre}</li>
                                </c:forEach>
                            </ul>
                        </td>
                        <td>${libro.fechaEdicion}</td>
                        <td>
                            <c:choose>
                                <c:when test="${empty libro.imagenPortada}">
                                    <p class="text-muted fw-light"><fmt:message key="sin.portada"/></p>
                                </c:when>
                                <c:otherwise>
                                    <img src="img/${libro.imagenPortada}" alt="${libro.titulo}" width="100"
                                         height="auto"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${libro.numeroEjemplares}</td>
                        <td>
                            <ul class="list-group">
                                <c:forEach items="${libro.generos}" var="genero">
                                    <li class="list-group-item">${genero.genero}</li>
                                </c:forEach>
                            </ul>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            </c:otherwise>
            </c:choose>
        </section>
    </main>

    <jsp:include page="components/footer.jsp"/>
    </body>
    </html>
</fmt:bundle>
