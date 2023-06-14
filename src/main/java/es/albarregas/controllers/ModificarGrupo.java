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
@WebServlet(name = "ModificarGrupo", urlPatterns = {"/ModificarGrupo"})
public class ModificarGrupo extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "";
        String op = request.getParameter("op");
        String modificarRadio = request.getParameter("modificarRadio");

        /*
        Los dos .jsp de modificar grupo tienen como destino este controlador, con el switch
        controlaremos desde donde viene la informacion introducida, cargaremos en variable 
        de contexto la lista de grupos seleccionada para poder modificar despues la informacion
         */
        switch (op) {
            case "Elegir para modificar":
                HttpSession session = request.getSession();
                session.setAttribute("modificarRadioSessionGrupo", modificarRadio);
                String radioSesion = (String) session.getAttribute("modificarRadioSessionGrupo");

                GruposDAO gruposDAO1 = new GruposDAO();
                Grupos grupo1 = gruposDAO1.getGrupo(Integer.parseInt(radioSesion));

                request.setAttribute("grupo1", grupo1);

                url = "JSP/Grupos/modificarGrupoFormulario.jsp";
                break;
            case "Modificar":
                session = request.getSession();
                String modificarRadioValue = (String) session.getAttribute("modificarRadioSessionGrupo");

                ServletContext contexto = getServletConfig().getServletContext();
                Grupos grupo = new Grupos();

                try {
                    BeanUtils.populate(grupo, request.getParameterMap());
                    GruposDAO gruposDAO = new GruposDAO();
                    boolean resultado = gruposDAO.updateGrupos(grupo, Integer.parseInt(modificarRadioValue));

                    if (resultado) {
                        url = "JSP/ErroresYverificaciones/correcto.jsp";
                        DAOFactory daof = DAOFactory.getDAOFactory();
                        IGruposDAO gdao = daof.getGruposDAO();
                        List<Grupos> listaGrupos = gdao.getAllGrupos();
                        contexto.setAttribute("grupos", listaGrupos);

                        request.setAttribute("mensajeVerificacion", "Se ha modificado el grupo con ID: " + modificarRadioValue);

                    } else {
                        url = "JSP/ErroresYverificaciones/error.jsp";
                    }

                } catch (NumberFormatException e) {
                    e.getMessage();
                    url = "JSP/ErroresYverificaciones/error.jsp";
                    request.setAttribute("mensajeError", "El valor de modificarRadio no es un número válido");
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.getMessage();
                    url = "JSP/ErroresYverificaciones/error.jsp";
                    request.setAttribute("mensajeError", "Se produjo un error durante la modificación del grupo");
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
