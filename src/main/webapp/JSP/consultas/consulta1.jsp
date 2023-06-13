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
        <title>Consulta 1</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />
        <h1>Consulta 1</h1>
        <h4 class="subTitle">Alumnos</h4>
        <c:choose>
            <c:when test="${empty listaAlumnos}">
                <h1>No hay alumnos registrados</h1>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Apellidos</th>
                            <th>Nombre</th>
                            <th>Grupo</th>
                            <th>Equipo</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="alumno" items="${listaAlumnos}">
                            <tr>
                                <td>${alumno.apellidos}</td>
                                <td>${alumno.nombre}</td>
                                <td>${alumno.grupo.denominacion}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty alumno.equipo}">
                                            Sin equipo
                                        </c:when>
                                        <c:otherwise>
                                            ${alumno.equipo.marca}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>

