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
        <title>Confirmación</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
        <link rel="stylesheet" type="text/css" href="${styleForm}"/>
    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />

        <form action="EliminarGrupo" method="post">
            <h1 class="subTitle">¿Estás seguro de que quieres borrar los siguientes registros?</h1>
            <table>
                <thead>
                    <tr>
                        <th>Id de grupo</th>
                        <th>Denominacion</th>
                        <th>Tutor</th>   
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaGruposSeleccionados}" var="listaGruposSeleccionados">
                        <tr>
                            <td>${listaGruposSeleccionados.idGrupo}</td>
                            <td>${listaGruposSeleccionados.denominacion}</td>
                            <td>${listaGruposSeleccionados.tutor}</td>
                        </tr>
                    <input type="checkbox" name="eliminarCheckboxSeleccionado" value="${listaGruposSeleccionados.idGrupo}" hidden checked>
                </c:forEach>
                </tbody>
            </table>
            <div class="center-button">
                <input type="submit" name="op" value="Eliminar">
                <input type="submit" name="op" value="Canclear">
            </div>
        </form>
    </body>
</html>
