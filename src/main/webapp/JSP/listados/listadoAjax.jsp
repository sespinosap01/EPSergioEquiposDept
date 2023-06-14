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
        <title>Consulta Ajax</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />
        <h1>Listado por Ajax</h1>
        <form action="" method="POST">
            <div class="center-button">
                <select id="idGrupo" name="idGrupo" required>
                    <option value="selec">Elige uno ...</option>                                            
                    <c:forEach items="${listaGrupos}" var="listaGrupos">
                        <option value="${listaGrupos.idGrupo}">${listaGrupos.denominacion}</option>
                    </c:forEach>
                </select>

                <select id="idEquipo" name="idEquipo" required>
                    <option value="selec">Elige uno ...</option>                                            
                    <c:forEach items="${listaEquipos}" var="listaEquipos">
                        <option value="${listaEquipos.idEquipo}">${listaEquipos.marca} | ${listaEquipos.numSerie} </option>
                    </c:forEach>
                </select>
            </div>
        </form>
    </body>
</html>