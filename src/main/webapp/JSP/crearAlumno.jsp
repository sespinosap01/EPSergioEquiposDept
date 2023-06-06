<%-- 
    Document   : crearAlumno
    Created on : 05-jun-2023, 13:51:59
    Author     : Sergio
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:url var="style" value="CSS/style.css" scope="application" />
<c:url var="styleForm" value="CSS/styleForm.css" scope="application" />


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="${style}"/>
        <link rel="stylesheet" type="text/css" href="${styleForm}"/>

    </head>
    <body>
        <jsp:include page="/JSP/desplegable.jsp" />
        <br><br>
        <fieldset>
            <legend>Registrar Alumno</legend>
            <form action="CrearAlumno" method="post">
                <table>
                    <tr>
                        <td>
                            <div class="form-row">
                                <label for="nombre">Nombre</label>
                                <input type="text" id="nombre" name="nombre" placeholder="Ej: Sergio">
                            </div>
                            <div class="form-row">
                                <label for="apellidos">Apellidos</label>
                                <input type="text" id="apellidos" name="apellidos" placeholder="Ej: Espinosa Pascua">
                            </div>
                            <div class="form-row">
                                <label for="idGrupo">Id del Grupo</label>
                                <input type="text" id="idGrupo" name="idGrupo" placeholder="Ej: 4">
                            </div>
                            <div class="form-row">
                                <label for="nif">Nif</label>
                                <input type="text" id="nif" name="nif" placeholder="Ej: 12345678A">
                            </div>
                        </td>
                        <td>
                            <div class="form-row">
                                <label for="fechaNacimiento">Fecha de nacimiento</label>
                                <input type="date" id="fechaNacimiento" name="fechaNacimiento">
                            </div>
                            <div class="form-row">
                                <label for="genero">Genero</label>
                                <input type="text" id="genero" name="genero" placeholder="Ej: M">
                            </div>
                            <div class="form-row">
                                <label for="email">Email</label>
                                <input type="text" id="email" name="email" placeholder="Ej: sergioesp@albarregas.com">
                            </div>
                            <div class="form-row">
                                <label for="idEquipo">Id de equipo</label>
                                <input type="text" id="idEquipo" name="idEquipo" placeholder="Ej: 2">
                            </div>
                        </td>
                    </tr>
                </table>
                <div class="submitLine">
                    <input type="submit" class="submitBtn" name="crear" value="Registrar">
                </div>
            </form>
        </fieldset>
    </body>
</html>
