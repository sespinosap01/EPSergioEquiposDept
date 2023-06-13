package es.albarregas.DAOFactory;

import es.albarregas.DAO.IAlumnosDAO;
import es.albarregas.DAO.IEquiposDAO;
import es.albarregas.DAO.IGruposDAO;

/**
 *
 * Clase abstracta que contiene métodos abstractos de las interfaces de los DAO
 *
 * @author Sergio
 */
public abstract class DAOFactory {

    public abstract IAlumnosDAO getAlumnosDAO();
    public abstract IEquiposDAO getEquiposDAO();
    public abstract IGruposDAO getGruposDAO();

    public static DAOFactory getDAOFactory() {

        DAOFactory daof;
        daof = new MySQLDAOFactory();
        return daof;
    }
}
