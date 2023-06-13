/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.DAO.AlumnosDAO;
import es.albarregas.beans.Alumnos;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.apache.commons.beanutils.BeanUtils;

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
                boolean resultado = alumnosDAO.createAlumnos(alumno);

                if (resultado) {
                    url = "JSP/ErroresYverificaciones/correcto.jsp";
                    List<Alumnos> listaAlumnos = (List<Alumnos>) contexto.getAttribute("alumnos");
                    listaAlumnos.add(alumno);
                    contexto.setAttribute("alumnos", listaAlumnos);

                    //formatear la fecha para pasarla por attribute formateada
                    SimpleDateFormat sdfOut = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaNacimientoFormateada = sdfOut.format(alumno.getFechaNacimiento());

                    request.setAttribute("mensajeVerificacion", "Datos introducidos: <br><br>"
                            + "Nombre: " + alumno.getNombre() + "<br>"
                            + "Apellidos: " + alumno.getApellidos() + "<br>"
                            + "Id del grupo: " + alumno.getIdGrupo() + "<br>"
                            + "Nif: " + alumno.getNif() + "<br>"
                            + "Fecha de nacimiento: " + fechaNacimientoFormateada + "<br>"
                            + "Género: " + genero + "<br>"
                            + "Email: " + alumno.getEmail() + "<br>"
                            + "Id del equipo: " + alumno.getIdEquipo() + "<br>"
                    );

                } else {
                    url = "JSP/ErroresYverificaciones/error.jsp";
                    request.setAttribute("mensajeError", "Error al registrar el alumno");

                }
            } catch (IllegalAccessException | InvocationTargetException | ParseException e) {
                e.getMessage();
                url = "JSP/ErroresYverificaciones/error.jsp";
                request.setAttribute("mensajeError", "Error al registrar el alumno");
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
