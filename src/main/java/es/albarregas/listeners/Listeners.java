/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.listeners;

import es.albarregas.DAO.IAlumnosDAO;
import es.albarregas.DAO.IEquiposDAO;
import es.albarregas.DAO.IGruposDAO;
import es.albarregas.DAOFactory.DAOFactory;
import es.albarregas.beans.Alumnos;
import es.albarregas.beans.Equipos;
import es.albarregas.beans.Grupos;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Sergio
 */ 
public class Listeners implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext contexto = sce.getServletContext();

        DAOFactory daof = DAOFactory.getDAOFactory();
        IEquiposDAO edao = daof.getEquiposDAO();
        IGruposDAO gdao = daof.getGruposDAO();
        IAlumnosDAO adao = daof.getAlumnosDAO();

        List<Grupos> listaGrupos = gdao.getAllGrupos();
        List<Equipos> listaEquipos = edao.getAllEquipos();
        List<Alumnos> listaAlumnos = adao.getAllAlumnos();

        synchronized (contexto) {
            contexto.setAttribute("grupos", listaGrupos);
            contexto.setAttribute("equipos", listaEquipos);
            contexto.setAttribute("alumnos", listaAlumnos);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
