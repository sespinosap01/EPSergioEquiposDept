/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.AlumnosDAO;
import es.albarregas.beans.Alumnos;
import es.albarregas.utilities.EnumConverter;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "CrearAlumno", urlPatterns = {"/CrearAlumno"})
public class CrearAlumno extends HttpServlet {

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

        ServletContext contexto = getServletConfig().getServletContext();
        String url = "";
        String crear = request.getParameter("crear");
        if (crear.equals("Registrar")) {
            Alumnos alumno = new Alumnos();
            try {
                DateConverter dateConverter = new DateConverter(null);
                dateConverter.setPattern("yyyy/MM/dd");
                ConvertUtils.register(dateConverter, java.util.Date.class);

                ConvertUtils.register(new EnumConverter(), Alumnos.Genero.class);

                BeanUtils.populate(alumno, request.getParameterMap());

                AlumnosDAO alumnosDAO = new AlumnosDAO();
                boolean resultado = alumnosDAO.createAlumnos(alumno);

                if (resultado) {
                    url = "JSP/ErroresYverificaciones/correcto.jsp";
                    List<Alumnos> listaAlumnos = (List<Alumnos>) contexto.getAttribute("alumnos");
                    listaAlumnos.add(alumno);
                    contexto.setAttribute("alumnos", listaAlumnos);
                } else {
                    url = "JSP/ErroresYverificaciones/error.jsp";

                }
            } catch (Exception e) {
                e.printStackTrace();
                url = "JSP/ErroresYverificaciones/error.jsp";
            }
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
