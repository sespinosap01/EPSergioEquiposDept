/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Equipos;
import java.util.List;

/**
 *
 * @author Sergio
 */
public interface IEquiposDAO {

    public boolean createEquipos(Equipos equipos);
    public boolean updateEquipos(Equipos equipos, int idEquipo);
    public boolean deleteEquipos(int idEquipo);
    public void closeConnection();
    public List<Equipos> getAllEquipos();
    public Equipos getEquipo(int idEquipo);
    public boolean numSerieExiste(String numSerie);
    public List<Equipos> consulta2();

}
