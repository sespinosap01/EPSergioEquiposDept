package es.albarregas.DAOFactory;

import es.albarregas.DAO.AlumnosDAO;
import es.albarregas.DAO.EquiposDAO;
import es.albarregas.DAO.GruposDAO;
import es.albarregas.DAO.IAlumnosDAO;
import es.albarregas.DAO.IEquiposDAO;
import es.albarregas.DAO.IGruposDAO;


/**
 *
 * Clase que extiende de DAOFactory que contiene métodos que devuelven DAO
 * correspondiente
 *
 * @author Gonzalo
 */
public class MySQLDAOFactory extends DAOFactory {

    @Override
    public IAlumnosDAO getAlumnosDAO() {
        return new AlumnosDAO();
    }

    @Override
    public IEquiposDAO getEquiposDAO() {
        return new EquiposDAO();
    }

    @Override
    public IGruposDAO getGruposDAO() {
        return new GruposDAO();
    }
}
