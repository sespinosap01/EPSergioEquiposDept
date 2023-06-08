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
        <title>Modificar alumno</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>

    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />

        <h1>Modificar alumno</h1>
        <form action="ModificarAlumno" method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th>Id del alumno</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Nif</th>
                       <!-- <th>Genero</th> -->
                        <th>Id de grupo</th>
                        <th>Id de equipo</th>
                        <th>Accion</th>   
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${alumnos}" var="alumno">
                        <tr>
                            <td>${alumno.idAlumno}</td>
                            <td>${alumno.nombre}</td>
                            <td>${alumno.apellidos}</td>
                            <td>${alumno.nif}</td>
                          <!-- <td>${alumno.genero}</td>   -->                          
                            <td>${alumno.idGrupo}</td>
                            <td>${alumno.idEquipo}</td>
                            <td><input type="radio" name="modificar" value="${alumno.idAlumno}"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <input type="submit" name="modificar" value="Modificar">
        </form>
    </body>
</html>
