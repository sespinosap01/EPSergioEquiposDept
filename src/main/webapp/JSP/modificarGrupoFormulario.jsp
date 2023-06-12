<%-- 
    Document   : crearAlumno
    Created on : 09-jun-2023, 18:39:54
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
        <title>Modificar Grupo</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
        <link rel="stylesheet" type="text/css" href="${styleForm}"/>
        <script src="JS/validarFormularioGruposModificar.js" defer></script>
    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />
        <br><br>
        <fieldset>
            <legend>Modificar Grupo</legend>
            <form action="ModificarGrupo" method="post">
                <br>
                <div class="form-row2">
                    <label for="denominacion">Denominación</label>
                    <input type="text" id="denominacion" name="denominacion" placeholder="Ej: DAW2" value="<c:out value="${denominacion}"/>"  onblur="validarDenominacion()" required>
                </div>
                <div class="form-row2">
                    <label for="tutor">Tutor</label>
                    <input type="text" id="tutor" name="tutor" placeholder="Ej: Jesús García" value="<c:out value="${tutor}"/>"  onblur="validarTutor()" required>
                </div>      
                <div class="submitLine">
                    <input type="submit" class="submitBtn" name="op" value="Modificar" id="modificar" disabled>
                </div>
            </form>
        </fieldset>
    </body>
</html>
