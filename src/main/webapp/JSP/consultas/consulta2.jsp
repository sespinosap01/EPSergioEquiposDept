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
        <title>Consulta 2</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />       
        <h1>Consulta 2</h1>
        <h4 class="subTitle">Equipos</h4>
        <table>
            <thead>
                <tr>
                    <th>Marca</th>
                    <th>Numero de serie</th>
                    <th>Foto</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="equipo" items="${listaEquipos}">
                    <tr>
                        <td>${equipo.marca}</td>
                        <td>${equipo.numSerie}</td>
                        <td>${equipo.foto}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>

