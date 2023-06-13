/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.GruposDAO;
import es.albarregas.DAO.IGruposDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Grupos;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "EliminarGrupo", urlPatterns = {"/EliminarGrupo"})
public class EliminarGrupo extends HttpServlet {

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
        GruposDAO gruposDAO = new GruposDAO();
        boolean resultado = true;
        try {

            String[] eliminarCheckbox = request.getParameterValues("eliminarCheckbox");

            if (eliminarCheckbox != null && eliminarCheckbox.length > 0) {
                for (String checkbox : eliminarCheckbox) {

                    resultado = gruposDAO.deleteGrupos(Integer.parseInt(checkbox));
                }
            } else {
                url = "JSP/ErroresYverificaciones/error.jsp";
            }

            if (resultado) {
                url = "JSP/ErroresYverificaciones/correcto.jsp";

                DAOFactory daof = DAOFactory.getDAOFactory();
                IGruposDAO gdao = daof.getGruposDAO();
                List<Grupos> listaGrupos = gdao.getAllGrupos();
                contexto.setAttribute("grupos", listaGrupos);

            } else {
                url = "JSP/ErroresYverificaciones/error.jsp";

            }
        } catch (NumberFormatException e) {
            e.getMessage();
            url = "JSP/ErroresYverificaciones/error.jsp";
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
