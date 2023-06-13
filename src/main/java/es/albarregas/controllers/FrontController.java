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

            //CONSULTAS
            case "Alumnos":

                alumnos = new AlumnosDAO();
                listaAlumnos = alumnos.consulta1();
                request.setAttribute("listaAlumnos", listaAlumnos);
                url = "/JSP/consultas/consulta1.jsp";
                break;

            case "Equipos":

                equipos = new EquiposDAO();
                listaEquipos = equipos.consulta2();
                request.setAttribute("listaEquipos", listaEquipos);
                url = "/JSP/consultas/consulta2.jsp";
                break;

            case "Grupos":

                grupos = new GruposDAO();
                listaGrupos = grupos.consulta3();
                request.setAttribute("listaGrupos", listaGrupos);
                url = "/JSP/consultas/consulta3.jsp";
                break;

            case "Alumnos y equipos asignados":

                alumnos = new AlumnosDAO();
                listaAlumnos = alumnos.consulta4();
                request.setAttribute("listaAlumnos", listaAlumnos);
                url = "/JSP/consultas/consulta4.jsp";
                break;

            case "Alumnos por equipos":

                alumnos = new AlumnosDAO();
                listaAlumnos = alumnos.consulta5();
                request.setAttribute("listaAlumnos", listaAlumnos);
                url = "/JSP/consultas/consulta5.jsp";
                break;

            case "Alumnos por grupo":

                alumnos = new AlumnosDAO();
                listaAlumnos = alumnos.consulta6();
                request.setAttribute("listaAlumnos", listaAlumnos);
                url = "/JSP/consultas/consulta6.jsp";
                break;

            case "Alumnos sin equipo asignado":

                alumnos = new AlumnosDAO();
                listaAlumnos = alumnos.consulta7();
                request.setAttribute("listaAlumnos", listaAlumnos);
                url = "/JSP/consultas/consulta7.jsp";
                break;

            case "Equipos sin alumnos asignados":

                equipos = new EquiposDAO();
                listaEquipos = equipos.consulta8();
                request.setAttribute("listaEquipos", listaEquipos);
                url = "/JSP/consultas/consulta8.jsp";

                break;
            case "Todos los datos":

                alumnos = new AlumnosDAO();
                listaAlumnos = alumnos.consulta9();
                request.setAttribute("listaAlumnos", listaAlumnos);
                url = "/JSP/consultas/consulta9.jsp";

                break;
            case "Alumnos con Ajax":
                url = "ConsultaAjax";
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
