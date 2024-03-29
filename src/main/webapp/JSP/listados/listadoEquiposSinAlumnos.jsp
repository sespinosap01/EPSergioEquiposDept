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
        <title>Equipos sin alumnos asignados</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
    </head>
  <body>      
        <jsp:include page="/JSP/desplegable.jsp" />
        <h1>Equipos sin alumnos asignados</h1>
        <c:choose>
            <c:when test="${empty listaEquipos}">
                <p>No hay registros en el sistema</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Marca</th>
                            <th>Número de serie</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="equipo" items="${listaEquipos}">
                            <tr>
                                <td>${equipo.marca}</td>
                                <td>${equipo.numSerie}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>