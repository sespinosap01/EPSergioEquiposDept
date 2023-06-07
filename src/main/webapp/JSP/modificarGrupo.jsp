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
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>

    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />

        <h1>Modificar Grupo</h1>
        <form action="ModificarGrupo" method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th>Id de grupo</th>
                        <th>Denominacion</th>
                        <th>Tutor</th>   
                        <th>Accion</th>   
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${grupos}" var="grupo">
                        <tr>
                            <td>${grupo.idGrupo}</td>
                            <td>${grupo.denominacion}</td>
                            <td>${grupo.tutor}</td>
                            <td><input type="submit" name="" value="Modificar"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </form>
    </body>
</html>
