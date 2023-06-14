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
        <title>Consulta 9</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />

        <h1>Consulta 9</h1>
        <c:choose>
            <c:when test="${empty listaAlumnos}">
                <p>No hay registros en el sistema</p>
            </c:when>
            <c:otherwise>
                <table class="tablaTodosLosDatos">
                    <thead>
                        <tr>
                            <th>Id del Alumno</th>
                            <th>Nombre</th>
                            <th>Apellidos</th>
                            <th>Nif</th>
                            <th>Fecha de nacimiento</th>
                            <th>Genero</th>
                            <th>Email</th>
                            <th>Denominacion del grupo</th>
                            <th>Tutor</th>
                            <th>Id del Equipo</th>
                            <th>Numero de serie del equipo</th>
                            <th>Foto</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="alumno" items="${listaAlumnos}">
                            <tr>
                                <td>${alumno.idAlumno}</td>
                                <td>${alumno.nombre}</td>
                                <td>${alumno.apellidos}</td>
                                <td>${alumno.nif}</td>
                                <td>${alumno.fechaNacimiento}</td>
                                <td>${alumno.genero}</td>
                                <td>${alumno.email}</td>
                                <td>${alumno.grupo.denominacion}</td>
                                <td>${alumno.grupo.tutor}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty alumno.equipo.numSerie}">
                                            Sin equipo
                                        </c:when>
                                        <c:otherwise>
                                            ${alumno.equipo.idEquipo}                       
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty alumno.equipo.numSerie}">
                                            Sin número de serie
                                        </c:when>
                                        <c:otherwise>
                                            ${alumno.equipo.numSerie}                       
                                        </c:otherwise>
                                    </c:choose>
                                </td>             
                                <td>
                                    <c:choose>
                                        <c:when test="${empty alumno.equipo.foto}">
                                            Sin foto
                                        </c:when>
                                        <c:otherwise>
                                            ${alumno.equipo.foto}                       
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