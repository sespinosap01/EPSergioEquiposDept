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
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
        <link rel="stylesheet" type="text/css" href="${styleForm}"/>
    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />
        <br><br>
        <fieldset>
            <legend>Registrar Equipo</legend>
            <form action="CrearEquipo" method="post">
                <br>
                <div class="form-row2">
                    <label for="marca">Marca</label>
                    <input type="text" id="marca" name="marca" placeholder="Ej: Asus">
                </div>
                <div class="form-row2">
                    <label for="numSerie">Numero de serie</label>
                    <input type="text" id="numSerie" name="numSerie" placeholder="Ej: 2365A8KU2A">
                </div>
                <div class="form-row2">
                    <label for="foto">Foto</label>
                    <input type="file" id="foto" name="foto">
                </div>
                <div class="submitLine">
                    <input type="submit" class="submitBtn" name="crear" value="Registrar">
                </div>
            </form>
        </fieldset>
    </body>
</html>
