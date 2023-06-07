<%-- 
    Document   : index
    Created on : 14-may-2023, 16:37:10
    Author     : Sergio
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application"/>
<c:url var="style" value="CSS/style.css" scope="application" />

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
    </head>
    <body>
       <jsp:include page="JSP/desplegable.jsp" />
       <br>
       <h1 class="tituloIndex">Pincha en alguna opcion para comenzar</h1>
    </body>
</html>

