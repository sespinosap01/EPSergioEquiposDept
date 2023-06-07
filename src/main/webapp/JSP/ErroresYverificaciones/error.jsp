<%-- 
    Document   : crearAlumno
    Created on : 05-jun-2023, 13:51:59
    Author     : Sergio
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url var="style" value="CSS/style.css" scope="application" />
<c:url var="styleForm" value="CSS/styleForm.css" scope="application" />


<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
        <link rel="stylesheet" type="text/css" href="${styleForm}"/>
    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />
       
            <form action="VolverPrincipio" method="post">
                <h1 class="textoError">Ha ocurrido un <b>ERROR</b> al crear el registro</h1>
                <input type="submit" value="Volver">
            </form>
    </body>
</html>
