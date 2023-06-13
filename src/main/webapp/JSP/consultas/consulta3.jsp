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
        <title>Consulta 3</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
    </head>
    <body>      
        <jsp:include page="/JSP/desplegable.jsp" />
        <h1>Consulta 3</h1>
        <h4 class="subTitle">Grupos</h4>
        <c:choose>
            <c:when test="${empty listaGrupos}">
                <p>No hay registros en el sistema</p>
            </c:when>
            <c:otherwise>
                <table>
                    <thead>
                        <tr>
                            <th>Denominacion</th>
                            <th>Tutor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="grupo" items="${listaGrupos}">
                            <tr>
                                <td>${grupo.denominacion}</td>
                                <td>${grupo.tutor}</td>                                    
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </body>
</html>

