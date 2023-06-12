<%-- 
    Document   : crearAlumno
    Created on : 07-jun-2023, 18:11:33
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
        <title>Modificar Equipo</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
        <link rel="stylesheet" type="text/css" href="${styleForm}"/>
        <script src="JS/validarFormularioEquiposModificar.js" defer></script>
    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />
        <br><br>
        <fieldset>
            <legend>Modificar Equipo</legend>
            <form action="ModificarEquipo" method="post">
                <br>
                <div class="form-row2">
                    <label for="marca">Marca</label>
                    <input type="text" id="marca" name="marca" placeholder="Ej: Asus" value="<c:out value="${marca}"/>" onblur="validarMarca()" required>
                </div>
                <div class="form-row2">
                    <label for="numSerie">Numero de serie</label>
                    <input type="text" id="numSerie" name="numSerie" placeholder="Ej: AAA-11111"  value="<c:out value="${numSerie}"/>" onblur="validarNumSerie()" required>
                </div>
                <div class="form-row2">
                    <label for="foto">Foto</label>
                    <input type="file" id="foto" name="foto" >
                </div>
                <div class="submitLine">
                    <input type="submit" class="submitBtn" name="op" value="Modificar" id="modificar" disabled>
                </div>
            </form>
        </fieldset>
    </body>
</html>
