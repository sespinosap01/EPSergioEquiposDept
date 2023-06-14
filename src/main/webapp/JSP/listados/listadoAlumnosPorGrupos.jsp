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
        <title>Alumnos por grupos</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />

        <h1>Alumnos por grupos</h1>
        <c:choose>
            <c:when test="${empty listaAlumnos}">
                <p>No hay registros en el sistema</p>
            </c:when>
            <c:otherwise>
                <c:set var="prevDenominacion" value="${listaAlumnos[0].grupo.denominacion}" />
                <c:forEach var="alumno" items="${listaAlumnos}">
                    <p>${alumno.grupo.denominacion}, ${alumno.grupo.tutor}</p>
                    <table>
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Apellidos</th>
                                <th>Email</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <td>${alumno.nombre}</td>
                                <td>${alumno.apellidos}</td>
                                <td>${alumno.email}</td>
                            </tr>
                        </tbody>
                    </table>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </body>

</html>