/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Alumnos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Sergio
 */
public class AlumnosDAO implements IAlumnosDAO {

    @Override
    public boolean createAlumnos(Alumnos alumnos) {
        
        boolean error = true;
        String sql = "insert into alumnos (nombre, apellidos, idgrupo, nif, fechanacimiento, genero, email, idequipo) values (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);

            preparada.setString(1, alumnos.getNombre());
            preparada.setString(2, alumnos.getApellidos());
            preparada.setInt(3, alumnos.getIdGrupo());
            preparada.setString(4, alumnos.getNif());
            java.util.Date utilDate = alumnos.getFechaNacimiento();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparada.setDate(5, sqlDate);
            preparada.setString(6, String.valueOf(alumnos.getGenero()));
            preparada.setString(7, alumnos.getEmail());
            preparada.setInt(8, alumnos.getIdEquipo());

            preparada.executeUpdate();
            conexion.commit();
        } catch (SQLException e) {
            e.getMessage();
            error = false;
            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException exe) {
                exe.getMessage();
            }
        } finally {
            this.closeConnection();
        }

        return error;

    }

    @Override
    public boolean updateAlumnos(Alumnos alumnos, int idAlumno) {

        boolean error = true;
        String sql = "update alumnos set nombre = ?, apellidos = ?, idgrupo = ?, nif = ?, fechanacimiento = ?, genero = ?,"
                + "email = ?, idequipo = ? where idAlumno = ?";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);

            preparada.setString(1, alumnos.getNombre());
            preparada.setString(2, alumnos.getApellidos());
            preparada.setInt(3, alumnos.getIdGrupo());
            preparada.setString(4, alumnos.getNif());
            java.util.Date utilDate = alumnos.getFechaNacimiento();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            preparada.setDate(5, sqlDate);
            preparada.setString(6, String.valueOf(alumnos.getGenero()));
            preparada.setString(7, alumnos.getEmail());
            preparada.setInt(8, alumnos.getIdEquipo());
            preparada.setInt(9, idAlumno);

            preparada.executeUpdate();
            conexion.commit();

        } catch (SQLException e) {
            e.getMessage();
            error = false;
            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException exe) {
                exe.getMessage();
            }

        } finally {
            this.closeConnection();
        }
        return error;
    }

    @Override
    public boolean deleteAlumnos(int idAlumno) {

        boolean error = true;
        String sql = "delete alumnos where idUsuario = ?";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);

            preparada.setInt(1, idAlumno);

            preparada.executeUpdate();
            conexion.commit();

        } catch (SQLException e) {
            e.getMessage();
            error = false;
            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException exe) {
                exe.getMessage();
            }
        } finally {
            this.closeConnection();
        }

        return error;
    }

    @Override
    public Alumnos getUsuarioByID(int idAlumno) {

        return null;
    }

    @Override
    public boolean existeEmail(String email) {

        return true;

    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
}
