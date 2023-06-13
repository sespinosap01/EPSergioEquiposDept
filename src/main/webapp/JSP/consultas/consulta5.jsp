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
        <title>Consulta 5</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />
        <h1>Consulta 5</h1>
        <h4 class="subTitle">Alumnos por equipos</h4>
        <c:choose>
            <c:when test="${empty listaAlumnos}">
                <p>No hay registros en el sistema</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th colspan="2"></th>
                            <th colspan="3">Alumnos</th> 
                        </tr>
                    <th>Marca</th>
                    <th>Número de Serie</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>Grupo</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                </tr>
                <c:forEach var="alumno" items="${listaAlumnos}">                          
                <td>${alumno.equipo.marca}</td>
                <td>${alumno.equipo.numSerie}</td>
                <td>${alumno.nombre}</td>
                <td>${alumno.apellidos}</td>
                <td>${alumno.grupo.denominacion}</td>
            </tr>                   
        </c:forEach>
    </tbody>
</table>
</c:otherwise>
</c:choose>
</body>

</html>