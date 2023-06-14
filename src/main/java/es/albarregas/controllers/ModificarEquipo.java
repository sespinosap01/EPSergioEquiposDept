/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.EquiposDAO;
import es.albarregas.DAO.IEquiposDAO;
import es.albarregas.DAOFactory.DAOFactory;
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
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "ModificarEquipo", urlPatterns = {"/ModificarEquipo"})
public class ModificarEquipo extends HttpServlet {

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
        String modificarRadio = request.getParameter("modificarRadio");
        
        /*
        Los dos .jsp de modificar tienen como destino este controlador, con el switch
        controlaremos desde donde viene la informacion introducida, cargaremos en variable 
        de contexto la lista de equipos seleccionada para poder modificar despues la informacion
         */
        switch (op) {
            case "Elegir para modificar":
                HttpSession session = request.getSession();
                session.setAttribute("modificarRadioSessionEquipo", modificarRadio);
                String radioSesion = (String) session.getAttribute("modificarRadioSessionEquipo");

                EquiposDAO equiposDAO1 = new EquiposDAO();
                Equipos equipo1 = equiposDAO1.getEquipo(Integer.parseInt(radioSesion));

                request.setAttribute("equipo1", equipo1);

                url = "JSP/Equipos/modificarEquipoFormulario.jsp";
                break;

            case "Modificar":
                session = request.getSession();
                String modificarRadioValue = (String) session.getAttribute("modificarRadioSessionEquipo");
                ServletContext contexto = getServletConfig().getServletContext();
                Equipos equipo = new Equipos();
                try {
                    BeanUtils.populate(equipo, request.getParameterMap());
                    es.albarregas.DAO.EquiposDAO equiposDAO = new es.albarregas.DAO.EquiposDAO();
                    boolean resultado = equiposDAO.updateEquipos(equipo, Integer.parseInt(modificarRadioValue));

                    if (resultado) {
                        url = "JSP/ErroresYverificaciones/correcto.jsp";
                        DAOFactory daof = DAOFactory.getDAOFactory();
                        IEquiposDAO edao = daof.getEquiposDAO();
                        List<Equipos> listaEquipos = edao.getAllEquipos();
                        contexto.setAttribute("equipos", listaEquipos);

                        request.setAttribute("mensajeVerificacion", "Se ha modificado el equipo con ID: " + modificarRadioValue);

                    } else {
                        url = "JSP/ErroresYverificaciones/error.jsp";
                        request.setAttribute("mensajeError", "Se produjo un error durante la modificación del equipo");
                    }
                } catch (IllegalAccessException | NumberFormatException | InvocationTargetException e) {
                    e.getMessage();
                    url = "JSP/ErroresYverificaciones/error.jsp";
                    request.setAttribute("mensajeError", "Se produjo un error durante la modificación del equipo");
                }
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
