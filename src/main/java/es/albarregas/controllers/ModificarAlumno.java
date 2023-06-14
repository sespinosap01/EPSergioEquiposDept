/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.AlumnosDAO;
import es.albarregas.DAO.IAlumnosDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Alumnos;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "ModificarAlumno", urlPatterns = {"/ModificarAlumno"})
public class ModificarAlumno extends HttpServlet {

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
        Los dos .jsp de modificar alumno tienen como destino este controlador, con el switch
        controlaremos desde donde viene la informacion introducida, cargaremos en variable 
        de contexto la lista de alumnos seleccionada para poder modificar despues la informacion
         */
        switch (op) {
            case "Elegir para modificar":

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.YEAR, -18);
                Date fechaMaxima = calendar.getTime();
                SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                String fechaLimit = formatoFecha.format(fechaMaxima);
                request.setAttribute("fechaLimit", fechaLimit);

                HttpSession session = request.getSession();
                session.setAttribute("modificarRadioSessionAlumno", modificarRadio);
                String radioSesion = (String) session.getAttribute("modificarRadioSessionAlumno");

                AlumnosDAO alumnosDAO1 = new AlumnosDAO();
                Alumnos alumno1 = alumnosDAO1.getAlumno(Integer.parseInt(radioSesion));

                request.setAttribute("alumno1", alumno1);

                url = "JSP/Alumnos/modificarAlumnoFormulario.jsp";

                break;
            case "Modificar":
                ServletContext contexto = getServletConfig().getServletContext();
                session = request.getSession();
                String modificarRadioValue = (String) session.getAttribute("modificarRadioSessionAlumno");
                Alumnos alumno = new Alumnos();

                try {
                    String generoStr = request.getParameter("genero");
                    Alumnos.Genero genero = Alumnos.Genero.cambiarStringAChar(generoStr);
                    alumno.setGenero(genero);

                    String fechaNacimientoStr = request.getParameter("fechaNacimiento");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaNacimiento = sdf.parse(fechaNacimientoStr);
                    alumno.setFechaNacimiento(fechaNacimiento);

                    Map<String, String[]> copiaParam = new HashMap<>(request.getParameterMap());
                    copiaParam.remove("genero");
                    copiaParam.remove("fechaNacimiento");

                    BeanUtils.populate(alumno, copiaParam);

                    AlumnosDAO alumnosDAO = new AlumnosDAO();

                    boolean resultado = alumnosDAO.updateAlumnos(alumno, Integer.parseInt(modificarRadioValue));

                    if (resultado) {
                        url = "JSP/ErroresYverificaciones/correcto.jsp";
                        DAOFactory daof = DAOFactory.getDAOFactory();
                        IAlumnosDAO adao = daof.getAlumnosDAO();
                        List<Alumnos> listaAlumnos = adao.getAllAlumnos();
                        contexto.setAttribute("alumnos", listaAlumnos);

                        request.setAttribute("mensajeVerificacion", "Se ha modificado el alumno con ID: " + modificarRadioValue);
                    } else {
                        url = "JSP/ErroresYverificaciones/error.jsp";
                        request.setAttribute("mensajeError", "Se produjo un error durante la modificación del alumno");

                    }
                } catch (IllegalAccessException | NumberFormatException | InvocationTargetException | ParseException e) {
                    e.getMessage();
                    url = "JSP/ErroresYverificaciones/error.jsp";
                    request.setAttribute("mensajeError", "Error al modificar alumno");
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
