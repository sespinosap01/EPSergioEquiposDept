<%-- 
    Document   : crearAlumno
    Created on : 05-jun-2023, 13:51:59
    Author     : Sergio
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url var="style" value="CSS/style.css" scope="application" />

<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar alumno</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>

    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />

        <h1>Eliminar alumno</h1>
        <form action="EliminarAlumno" method="post">
            <h1 class="subTitle">¿Estás seguro de que quieres borrar los siguientes registros?</h1>
            <table>
                <thead>
                    <tr>
                        <th>Id de alumno</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Nif</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listaAlumnosSeleccionados}" var="listaAlumnosSeleccionados">
                        <tr>
                            <td>${listaAlumnosSeleccionados.idAlumno}</td>
                            <td>${listaAlumnosSeleccionados.nombre}</td>
                            <td>${listaAlumnosSeleccionados.apellidos}</td>
                            <td>${listaAlumnosSeleccionados.nif}</td>
                        </tr>
                    <input type="checkbox" name="eliminarCheckboxSeleccionado" value="${listaAlumnosSeleccionados.idAlumno}" hidden checked>
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
