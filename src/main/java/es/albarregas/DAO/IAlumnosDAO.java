/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Alumnos;

/**
 *
 * @author Sergio
 */
public interface IAlumnosDAO {

    public boolean createAlumnos(Alumnos alumnos);
    public boolean updateAlumnos(Alumnos alumnos, int idAlumno);
    public boolean deleteAlumnos(int idAlumno);
    public Alumnos getUsuarioByID(int idAlumno);
    public boolean existeEmail(String email);
    public void closeConnection();


}
