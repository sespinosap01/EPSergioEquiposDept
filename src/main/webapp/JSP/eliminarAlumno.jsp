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
        <form action="EliminarAlumno" method="POST">
            <table>
                <thead>
                    <tr>
                        <th>Id del alumno</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Nif</th>
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
                            <td><input type="checkbox" name="eliminarCheckbox" value="${alumno.idAlumno}"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <div class="center-button">
                <input type="submit" name="eliminar" value="Eliminar">
            </div>
        </form>
    </body>
</html>
