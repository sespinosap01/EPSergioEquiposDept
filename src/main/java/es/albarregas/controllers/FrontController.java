/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

        String url = "index.jsp";

        String op = request.getParameter("op");

        switch (op) {
            //CREAR
            case "Registrar alumno":
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, -18);
                Date fechaMaxima = calendar.getTime();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaLimit = formatoFecha.format(fechaMaxima);                
                request.setAttribute("fechaLimit", fechaLimit);                
                url = "/JSP/crearAlumno.jsp";
                break;
            case "Registrar equipo":
                url = "/JSP/crearEquipo.jsp";
                break;
            case "Registrar grupo":
                url = "/JSP/crearGrupo.jsp";
                break;

            //MODIFICAR
            case "Modificar alumno":
                url = "/JSP/modificarAlumno.jsp";
                break;
            case "Modificar equipo":
                url = "/JSP/modificarEquipo.jsp";
                break;
            case "Modificar grupo":
                url = "/JSP/modificarGrupo.jsp";
                break;

            //ELIMINAR
            case "Eliminar alumno":
                url = "/JSP/eliminarAlumno.jsp";
                break;
            case "Eliminar equipo":
                url = "/JSP/eliminarEquipo.jsp";
                break;
            case "Eliminar grupo":
                url = "/JSP/eliminarGrupo.jsp";
                break;

            //CONSULTAS
            case "Alumnos":
                url = "Consulta1";
                break;
            case "Equipos":
                url = "Consulta2";
                break;
            case "Grupos":
                url = "Consulta3";
                break;
            case "Alumnos y equipos asignados":
                url = "Consulta4";
                break;
            case "Alumnos por equipos":
                url = "Consulta5";
                break;
            case "Alumnos por grupo":
                url = "Consulta6";
                break;
            case "Alumnos sin equipo asignado":
                url = "Consulta7";
                break;
            case "Equipos sin alumnos asignados":
                url = "Consulta8";
                break;
            case "Todos los datos":
                url = "Consulta9";
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
