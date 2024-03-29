/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Alumnos;
import java.util.List;

/**
 *
 * @author Sergio
 */
public interface IAlumnosDAO {

    public boolean createAlumnos(Alumnos alumnos);
    public boolean updateAlumnos(Alumnos alumnos, int idAlumno);
    public boolean deleteAlumnos(int idAlumno);
    public void closeConnection();
    public List<Alumnos> getAllAlumnos();
    public Alumnos getAlumno(int idAlumno);
    public boolean correoExiste(String email);
    public List<Alumnos> listadoAlumnos();
    public List<Alumnos> listadoAlumnosEquiposAsignados();
    public List<Alumnos> listadoAlumnosPorEquipos();
    public List<Alumnos> listadoAlumnosPorGrupos();
    public List<Alumnos> listadoAlumnosSinEquipo();
    public List<Alumnos> listadoTodosDatos();
    public List<Alumnos> listadoAjax(int idEquipo, int idGrupo);

}
