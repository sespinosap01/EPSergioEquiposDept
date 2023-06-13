/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.EquiposDAO;
import es.albarregas.beans.Equipos;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "CrearEquipo", urlPatterns = {"/CrearEquipo"})
public class CrearEquipo extends HttpServlet {

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
            Equipos equipo = new Equipos();

            try {
                BeanUtils.populate(equipo, request.getParameterMap());
                EquiposDAO equiposDAO = new EquiposDAO();
                boolean resultado = equiposDAO.createEquipos(equipo);

                if (resultado) {
                    url = "JSP/ErroresYverificaciones/correcto.jsp";
                    List<Equipos> listaEquipos = (List<Equipos>) contexto.getAttribute("equipos");
                    listaEquipos.add(equipo);
                    contexto.setAttribute("equipos", listaEquipos);

                    request.setAttribute("mensajeVerificacion", "Datos introducidos: <br><br>"
                            + "Id del equipo: " + equipo.getIdEquipo() + "<br>"
                            + "Marca: " + equipo.getMarca() + "<br>"
                            + "Número de serie: " + equipo.getNumSerie() + "<br>"
                            + "Foto: " + equipo.getFoto() + "<br>"
                    );

                } else {
                    url = "JSP/ErroresYverificaciones/error.jsp";
                    request.setAttribute("mensajeError", "Error al registrar el equipo");
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.getMessage();
                url = "JSP/ErroresYverificaciones/error.jsp";
                request.setAttribute("mensajeError", "Error al registrar el equipo");

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
