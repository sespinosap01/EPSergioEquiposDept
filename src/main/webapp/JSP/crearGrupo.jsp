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
        <script src="JS/validarFormularioGrupos.js" defer></script>
    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />
        <br><br>
        <fieldset>
            <legend>Registrar Grupo</legend>
            <form action="CrearGrupo" method="post">
                <br>
                <div class="form-row2">
                    <label for="denominacion">Denominación</label>
                    <input type="text" id="denominacion" name="denominacion" placeholder="Ej: DAW2" onblur="validarDenominacion()" required>
                </div>
                <div class="form-row2">
                    <label for="tutor">Tutor</label>
                    <input type="text" id="tutor" name="tutor" placeholder="Ej: Jesús García" onblur="validarTutor()" required>
                </div>      
                <div class="submitLine">
                    <input type="submit" class="submitBtn" name="crear" value="Registrar" id="crear" disabled>
                </div>
            </form>
        </fieldset>
    </body>
</html>
