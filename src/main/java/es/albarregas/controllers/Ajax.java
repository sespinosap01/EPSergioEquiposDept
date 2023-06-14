/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import es.albarregas.DAO.AlumnosDAO;
import es.albarregas.DAO.IAlumnosDAO;
import es.albarregas.DAO.IEquiposDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Alumnos;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author Sergio
 */
@WebServlet(name = "Ajax", urlPatterns = {"/Ajax"})
public class Ajax extends HttpServlet {

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

        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        Gson gson = new Gson();
        JsonElement jsonElement = gson.fromJson(json, JsonElement.class);
        String op = jsonElement.getAsJsonObject().get("ajax").getAsString();

        ArrayList<String> respuesta = new ArrayList<>();
        DAOFactory daof = DAOFactory.getDAOFactory();
        IAlumnosDAO adao = daof.getAlumnosDAO();
        IEquiposDAO edao = daof.getEquiposDAO();

        switch (op) {
            case "1":
                String correo = jsonElement.getAsJsonObject().get("email").getAsString();
                boolean existe = adao.correoExiste(correo);
                if (existe) {
                    respuesta.add("true");
                } else {
                    respuesta.add("false");
                }
                json = gson.toJson(respuesta);

                break;

            case "2":
                String numSerie = jsonElement.getAsJsonObject().get("numSerie").getAsString();
                boolean existeNS = edao.numSerieExiste(numSerie);
                if (existeNS) {
                    respuesta.add("true");
                } else {
                    respuesta.add("false");
                }
                json = gson.toJson(respuesta);

                break;
            case "3":
                String idGrupo = request.getParameter("idGrupo");
                String idEquipo = request.getParameter("idEquipo");
                System.out.println(idGrupo);
                System.out.println(idEquipo);

                JSONArray arrayJSON = null;
                List<Alumnos> listaAlumnos = new ArrayList<>();
                AlumnosDAO alumnosDAO = new AlumnosDAO();
                listaAlumnos = alumnosDAO.listadoAjax(Integer.parseInt(idGrupo), Integer.parseInt(idEquipo));
                arrayJSON = new JSONArray(listaAlumnos);
                response.setContentType("application/json");
                response.getWriter().print(arrayJSON);
                break;
        }

        response.getWriter().write(json);
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
