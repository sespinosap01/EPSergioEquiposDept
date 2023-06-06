/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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
                url = "/JSP/consultas/consulta1.jsp";
                break;
            case "Equipos":
                url = "/JSP/consultas/consulta2.jsp";
                break;
            case "Grupos":
                url = "/JSP/consultas/consulta3.jsp";
                break;
            case "Alumnos y equipos asignados":
                url = "/JSP/consultas/consulta4.jsp";
                break;
            case "Alumnos por equipos":
                url = "/JSP/consultas/consulta5.jsp";
                break;
            case "Alumnos por grupo":
                url = "/JSP/consultas/consulta6.jsp";
                break;
            case "Alumnos sin equipo asignado":
                url = "/JSP/consultas/consulta7.jsp";
                break;
            case "Equipos sin alumnos asignados":
                url = "/JSP/consultas/consulta8.jsp";
                break;
            case "Todos los datos":
                url = "/JSP/consultas/consulta9.jsp";
                break;
            case "Alumnos con Ajax":
                url = "/JSP/consultas/consultaAjax.jsp";
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
