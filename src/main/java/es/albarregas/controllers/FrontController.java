/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.AlumnosDAO;
import es.albarregas.DAO.EquiposDAO;
import es.albarregas.DAO.GruposDAO;
import es.albarregas.beans.Alumnos;
import es.albarregas.beans.Equipos;
import es.albarregas.beans.Grupos;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "FrontController", urlPatterns = {"/FrontController"})
public class FrontController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "";

        String op = request.getParameter("op");

        AlumnosDAO alumnos;
        EquiposDAO equipos;
        GruposDAO grupos;
        List<Alumnos> listaAlumnos;
        List<Equipos> listaEquipos;
        List<Grupos> listaGrupos;

        switch (op) {
            //CREAR
            case "Registrar alumno":
                //Controlamos que la fecha que se vaya a introducir en el formulario sea mayor a 18 años desde la fecha actual
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, -18);
                Date fechaMaxima = calendar.getTime();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaLimit = formatoFecha.format(fechaMaxima);
                request.setAttribute("fechaLimit", fechaLimit);
                url = "/JSP/Alumnos/crearAlumno.jsp";
                break;
            case "Registrar equipo":
                url = "/JSP/Equipos/crearEquipo.jsp";
                break;
            case "Registrar grupo":
                url = "/JSP/Grupos/crearGrupo.jsp";
                break;

            //MODIFICAR
            case "Modificar alumno":
                url = "/JSP/Alumnos/modificarAlumno.jsp";
                break;
            case "Modificar equipo":
                url = "/JSP/Equipos/modificarEquipo.jsp";
                break;
            case "Modificar grupo":
                url = "/JSP/Grupos/modificarGrupo.jsp";
                break;

            //ELIMINAR
            case "Eliminar alumno":
                url = "/JSP/Alumnos/eliminarAlumno.jsp";
                break;
            case "Eliminar equipo":
                url = "/JSP/Equipos/eliminarEquipo.jsp";
                break;
            case "Eliminar grupo":
                url = "/JSP/Grupos/eliminarGrupo.jsp";
                break;

            //Listados
            //Cargamos la lista antes de pasar a la vista en todos los casos
            case "Alumnos":

                alumnos = new AlumnosDAO();
                listaAlumnos = alumnos.listadoAlumnos();
                request.setAttribute("listaAlumnos", listaAlumnos);
                url = "/JSP/listados/listadoAlumnos.jsp";
                break;

            case "Equipos":

                equipos = new EquiposDAO();
                listaEquipos = equipos.listadoEquipos();
                request.setAttribute("listaEquipos", listaEquipos);
                url = "/JSP/listados/listadoEquipos.jsp";
                break;

            case "Grupos":

                grupos = new GruposDAO();
                listaGrupos = grupos.listadoGrupos();
                request.setAttribute("listaGrupos", listaGrupos);
                url = "/JSP/listados/listadoGrupos.jsp";
                break;

            case "Alumnos y equipos asignados":

                alumnos = new AlumnosDAO();
                listaAlumnos = alumnos.listadoAlumnosEquiposAsignados();
                request.setAttribute("listaAlumnos", listaAlumnos);
                url = "/JSP/listados/listadoAlumnosEquiposAsignados.jsp";
                break;

            case "Alumnos por equipos":

                alumnos = new AlumnosDAO();
                listaAlumnos = alumnos.listadoAlumnosPorEquipos();
                request.setAttribute("listaAlumnos", listaAlumnos);
                url = "/JSP/listados/listadoAlumnosPorEquipos.jsp";
                break;

            case "Alumnos por grupo":

                alumnos = new AlumnosDAO();
                listaAlumnos = alumnos.listadoAlumnosPorGrupos();
                request.setAttribute("listaAlumnos", listaAlumnos);
                url = "/JSP/listados/listadoAlumnosPorGrupos.jsp";
                break;

            case "Alumnos sin equipo asignado":

                alumnos = new AlumnosDAO();
                listaAlumnos = alumnos.listadoAlumnosSinEquipo();
                request.setAttribute("listaAlumnos", listaAlumnos);
                url = "/JSP/listados/listadoAlumnosSinEquipo.jsp";
                break;

            case "Equipos sin alumnos asignados":

                equipos = new EquiposDAO();
                listaEquipos = equipos.listadoEquiposSinAlumnos();
                request.setAttribute("listaEquipos", listaEquipos);
                url = "/JSP/listados/listadoEquiposSinAlumnos.jsp";

                break;
            case "Todos los datos":

                alumnos = new AlumnosDAO();
                listaAlumnos = alumnos.listadoTodosDatos();
                request.setAttribute("listaAlumnos", listaAlumnos);
                url = "/JSP/listados/listadoTodosDatos.jsp";

                break;
            case "Alumnos con Ajax":

                equipos = new EquiposDAO();
                listaEquipos = equipos.listadoEquiposSinAlumnos();
                request.setAttribute("listaEquipos", listaEquipos);

                grupos = new GruposDAO();
                listaGrupos = grupos.listadoGrupos();
                request.setAttribute("listaGrupos", listaGrupos);

                url = "/JSP/listados/listadoAjax.jsp";
                break;
            default:
                url = "index.jsp";
                break;
        }

        request.getRequestDispatcher(url).forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
