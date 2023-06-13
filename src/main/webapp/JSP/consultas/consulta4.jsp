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
        <title>Consulta 4</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />
        <h1>Consulta 4</h1>
        <h4 class="subTitle">Alumnos y equipos asignados</h4>
        <table>
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Marca</th>
                    <th>Número de serie</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="alumno" items="${listaAlumnos}">
                    <tr>
                        <td>${alumno.nombre}</td>
                        <td>${alumno.apellidos}</td>          
                        <td>
                            <c:choose>
                                <c:when test="${empty alumno.equipo}">
                                    Sin asignar
                                </c:when>
                                <c:otherwise>
                                    ${alumno.equipo.marca}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${empty alumno.equipo}">
                                    Sin asignar
                                </c:when>
                                <c:otherwise>
                                    ${alumno.equipo.numSerie}
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

