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
        <script src="JS/validarFormularioAlumnos.js" defer></script>
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
                                <input type="text" id="nombre" name="nombre" placeholder="Ej: Sergio" onblur="validarNombre()" required>
                            </div>
                            <div class="form-row">
                                <label for="apellidos">Apellidos</label>
                                <input type="text" id="apellidos" name="apellidos" placeholder="Ej: Espinosa Pascua" onblur="validarApellidos()" required>
                            </div>
                            <div class="form-row">
                                <label for="idGrupo">Grupo</label>
                                <select id="idGrupo" name="idGrupo" required>
                                    <option value="selec">Elige uno ...</option>                                            
                                    <c:forEach items="${grupos}" var="grupo">
                                        <option value="${grupo.idGrupo}">${grupo.denominacion} | ${grupo.tutor}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-row">
                                <label for="nif">Nif</label>
                                <input type="text" id="nif" name="nif" placeholder="Ej: 12345678A" onblur="validarNIF()" required>
                            </div>
                        </td>
                        <td>
                            <div class="form-row">
                                <label for="fechaNacimiento">Fecha de nacimiento</label>
                                <input type="text" id="fechaNacimiento" name="fechaNacimiento" placeholder="Ej: 2001/06/25" required>
                            </div>
                            <div class="form-row">
                                <label for="genero">Genero</label>
                                <select id="genero" name="genero">
                                    <option value="">Selecciona una opcion</option>        
                                    <option value="H">Hombre</option>
                                    <option value="M">Mujer</option>
                                    <option value="O">Otro</option>
                                </select>
                            </div>
                            <div class="form-row">
                                <label for="email">Email</label>
                                <input type="text" id="email" name="email" placeholder="Ej: sergioesp@albarregas.com" onblur="validarEmail()" required>
                            </div>
                            <div class="form-row">
                                <label for="idEquipo">Equipo</label>
                                <select id="idEquipo" name="idEquipo" required>
                                    <option value="selec">Selecciona una opcion</option>        
                                    <c:forEach items="${equipos}" var="equipo">
                                        <option value="${equipo.idEquipo}">${equipo.marca} | ${equipo.numSerie} </option>
                                    </c:forEach>
                                </select>                               
                            </div>
                        </td>
                    </tr>
                </table>
                <div class="submitLine">
                    <input type="submit" class="submitBtn" name="crear" value="Registrar" id="crear" disabled>
                </div>
            </form>
        </fieldset>
    </body>
</html>
