/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.GruposDAO;
import es.albarregas.beans.Grupos;
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
@WebServlet(name = "CrearGrupo", urlPatterns = {"/CrearGrupo"})
public class CrearGrupo extends HttpServlet {

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
            Grupos grupo = new Grupos();
            try {
                BeanUtils.populate(grupo, request.getParameterMap());
                GruposDAO gruposDAO = new GruposDAO();
                boolean resultado = gruposDAO.createGrupos(grupo);
                if (resultado) {
                    url = "JSP/ErroresYverificaciones/correcto.jsp";
                    //Actualizamos la variable de contexto
                    List<Grupos> listaGrupos = (List<Grupos>) contexto.getAttribute("grupos");
                    listaGrupos.add(grupo);
                    contexto.setAttribute("grupos", listaGrupos);

                    //Pasamos por Attribute el mensaje mostrando los datos introducidos a la base de datos
                    request.setAttribute("mensajeVerificacion", "Datos introducidos: <br><br>"
                            + "Id del grupo: " + grupo.getIdGrupo() + "<br>"
                            + "Denominacion: " + grupo.getDenominacion() + "<br>"
                            + "Tutor: " + grupo.getTutor() + "<br>"
                    );
                } else {
                    url = "JSP/ErroresYverificaciones/error.jsp";
                    request.setAttribute("mensajeError", "Error al registrar el grupo");

                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.getMessage();
                url = "JSP/ErroresYverificaciones/error.jsp";
                request.setAttribute("mensajeError", "Error al registrar el grupo");
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
