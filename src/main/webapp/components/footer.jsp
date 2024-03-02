<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="en_US"/>
<fmt:bundle basename="myProp">
    <footer class="bg-dark p-3 mt-5">
        <div class="container">
            <p class="mb-0 text-white text-center p-2">&copy; 2024 <fmt:message key="footer"/></p>
        </div>
    </footer>
</fmt:bundle>
