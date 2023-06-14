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
        <title>Eliminar Equipo</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>

    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />

        <h1>Eliminar Equipo</h1>
        <c:choose>
            <c:when test="${empty equipos}">
                <p>No hay registros en el sistema</p>
            </c:when>
            <c:otherwise>
                <form action="EliminarEquipo" method="POST">
                    <table>
                        <thead>
                            <tr>
                                <th>Id de equipo</th>
                                <th>Marca</th>
                                <th>numSerie</th>   
                                <th>Accion</th>   
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${equipos}" var="equipo">
                                <tr>
                                    <td>${equipo.idEquipo}</td>
                                    <td>${equipo.marca}</td>
                                    <td>${equipo.numSerie}</td>
                                    <td><input type="checkbox" name="eliminarCheckbox" value="${equipo.idEquipo}"></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <br>
                    <div class="center-button">
                        <input type="submit" name="op" value="Elegir para eliminar">
                    </div>
                </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>
