<%-- 
    Document   : desplegable
    Created on : 05-jun-2023, 17:30:25
    Author     : Sergio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="titulo">Gestion de alumnos, grupos y equipos</h1>
<form action="FrontController" method="post">
    <div class="navbar">
        <div class="dropdown">
            <div class="dropbtn">Altas
                <div class="dropdown-content">
                    <input type="submit" name="op" value="Registrar alumno">
                    <input type="submit" name="op" value="Registrar equipo">
                    <input type="submit" name="op" value="Registrar grupo">
                </div>
            </div>
        </div>
        <div class="dropdown">
            <div class="dropbtn">Modificaciones
                <div class="dropdown-content">
                    <input type="submit" name="op" value="Modificar alumno">
                    <input type="submit" name="op" value="Modificar equipo">
                    <input type="submit" name="op" value="Modificar grupo">
                </div>
            </div>
        </div>
        <div class="dropdown">
            <div class="dropbtn">Eliminaciones
                <div class="dropdown-content">
                    <input type="submit" name="op" value="Eliminar alumno">
                    <input type="submit" name="op" value="Eliminar equipo">
                    <input type="submit" name="op" value="Eliminar grupo">
                </div>
            </div>
        </div>
        <div class="dropdown">
            <div class="dropbtn">Listados
                <div class="dropdown-content">
                    <!--1--><input type="submit" name="op" value="Alumnos">
                    <!--2--><input type="submit" name="op" value="Equipos">
                    <!--3--><input type="submit" name="op" value="Grupos">
                    <!--4--><input type="submit" name="op" value="Alumnos y equipos asignados">
                    <!--5--><input type="submit" name="op" value="Alumnos por equipos">
                    <!--6--><input type="submit" name="op" value="Alumnos por grupo">
                    <!--7--><input type="submit" name="op" value="Alumnos sin equipo asignado">
                    <!--8--><input type="submit" name="op" value="Equipos sin alumnos asignados">
                    <!--9--><input type="submit" name="op" value="Todos los datos">
                    <!--Ajax--><input type="submit" name="op" value="Alumnos con Ajax">
                </div>
            </div>
        </div>
    </div>
</form>
