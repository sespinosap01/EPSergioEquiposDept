/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Grupos;
import java.util.List;

/**
 *
 * @author Sergio
 */
public interface IGruposDAO {

    public boolean createGrupos(Grupos grupos);
    public boolean updateGrupos(Grupos grupos, int idGrupo);
    public boolean deleteGrupos(int idGrupo);
    public void closeConnection();
    public List<Grupos> getAllGrupos();
    public Grupos getGrupo(int idGrupo);
    public List<Grupos> consulta3();

}
