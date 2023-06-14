/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Equipos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sergio
 */
public class EquiposDAO implements IEquiposDAO {

    /**
     * Capitaliza la marca del equipo
     *
     * @param str La marca de equipo a capitalizar
     * @return La marca de equipo capitalizada
     */
    private String capitalizarMarca(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
    }

    /**
     * Inserta un nuevo registro de equipo en la base de datos
     *
     * @param equipos el objeto Equipos que contiene la información del equipo a
     * insertar
     * @return true si el registro se insertó correctamente
     */
    @Override
    public boolean createEquipos(Equipos equipos) {
        boolean error = true;
        String sql = "insert into equipos (marca, numserie, foto) values (?, ?, ?)";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);

            preparada.setString(1, capitalizarMarca(equipos.getMarca()));
            preparada.setString(2, equipos.getNumSerie());
            preparada.setString(3, equipos.getFoto());

            preparada.executeUpdate();
            conexion.commit();

            sql = "SELECT LAST_INSERT_ID() AS UltimoID;";
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);

            if (resultado.next()) {
                equipos.setIdEquipo(resultado.getInt("UltimoID"));
            }
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

    /**
     * Actualiza un registro de equipo existente en la base de datos
     *
     * @param equipos el objeto Equipos que contiene la información actualizada
     * del equipo
     * @param idEquipo el ID del equipo que se desea actualizar
     * @return true si la actualización se realizó correctamente
     */
    @Override
    public boolean updateEquipos(Equipos equipos, int idEquipo) {

        boolean error = true;
        String sql = "update equipos set marca = ?, numserie = ?, foto = ? where idEquipo = ?";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);

            preparada.setString(1, capitalizarMarca(equipos.getMarca()));
            preparada.setString(2, equipos.getNumSerie());
            preparada.setString(3, equipos.getFoto());
            preparada.setInt(4, idEquipo);

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

    /**
     * Elimina un registro de equipo de la base de datos
     *
     * @param idEquipo el ID del equipo que se desea eliminar
     * @return true si la eliminación se realizó correctamente
     */
    @Override
    public boolean deleteEquipos(int idEquipo) {

        boolean error = true;
        String sql = "delete from equipos where idEquipo = ?";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);

            preparada.setInt(1, idEquipo);

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
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

    /**
     * Obtiene todos los registros de la tabla de equipos 
     */
    @Override
    public List<Equipos> getAllEquipos() {

        ResultSet resultado;
        Equipos equipo;
        List<Equipos> listaEquipos = new ArrayList<>();
        String sql = "SELECT * FROM equipos";

        try {
            Connection conexion = ConnectionFactory.getConnection();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                equipo = new Equipos();
                equipo.setIdEquipo(resultado.getInt("IdEquipo"));
                equipo.setMarca(resultado.getString("marca"));
                equipo.setNumSerie(resultado.getString("numSerie"));
                equipo.setFoto(resultado.getString("foto"));

                listaEquipos.add(equipo);
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            this.closeConnection();
        }
        return listaEquipos;
    }

    /**
     * Elimina un registro de equipo de la base de datos
     *
     * @param idEquipo el ID del equipo que se desea eliminar
     * @return true si la eliminación se realizó correctamente
     */
    @Override
    public Equipos getEquipo(int idEquipo) {

        ResultSet resultado;
        Equipos equipo = new Equipos();
        String sql = "SELECT * FROM equipos where idEquipo = ?";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement preparada = conexion.prepareStatement(sql);
            preparada.setInt(1, idEquipo);
            resultado = preparada.executeQuery();

            if (resultado.next()) {
                equipo.setIdEquipo(resultado.getInt("IdEquipo"));
                equipo.setMarca(resultado.getString("marca"));
                equipo.setNumSerie(resultado.getString("numSerie"));
                equipo.setFoto(resultado.getString("foto"));
            }
        } catch (SQLException e) {
            e.getMessage();
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
        return equipo;
    }

    /**
     * Verifica si un número de serie existe en la base de datos
     *
     * @param numSerie el número de serie a verificar
     * @return true si el número de serie existe en la base de datos
     */
    @Override
    public boolean numSerieExiste(String numSerie) {
        boolean existe = false;
        ResultSet resultado;

        String sql = "select numSerie from equipos where numSerie = ?";
        try {
            Connection conexion = ConnectionFactory.getConnection();
            PreparedStatement preparada = conexion.prepareStatement(sql);
            preparada.setString(1, numSerie);
            resultado = preparada.executeQuery();

            while (resultado.next()) {
                if (resultado.getString("numSerie") != null) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            e.getMessage();

        } finally {
            this.closeConnection();
        }

        return existe;
    }

    /**
     * Realiza la consulta para obtener todos los equipos de la base de datos
     *
     * @return una lista de equipos que contiene todos los equipos en la base de
     * datos
     */
    @Override
    public List<Equipos> listadoEquipos() {
        ResultSet resultado;
        Equipos equipo;

        List<Equipos> listaEquipos = new ArrayList<>();
        String sql = "select * from equipos";
        try {
            Connection conexion = ConnectionFactory.getConnection();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                equipo = new Equipos();
                equipo.setMarca(resultado.getString("marca"));
                equipo.setNumSerie(resultado.getString("numSerie"));
                equipo.setFoto(resultado.getString("foto"));

                listaEquipos.add(equipo);
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            this.closeConnection();
        }
        return listaEquipos;
    }

    /**
     * Realiza la consulta para obtener los equipos que no están asignados a ningún alumno
     *
     * @return una lista de equipos que no están asignados a ningún alumno
     */
    @Override
    public List<Equipos> listadoEquiposSinAlumnos() {
        ResultSet resultado;
        Equipos equipo;

        List<Equipos> listaEquipos = new ArrayList<>();
        String sql = "SELECT e.Marca, e.NumSerie FROM equipos AS e LEFT JOIN alumnos AS a ON e.IdEquipo = a.IdEquipo WHERE a.IdEquipo IS NULL";
        try {
            Connection conexion = ConnectionFactory.getConnection();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                equipo = new Equipos();
                equipo.setMarca(resultado.getString("marca"));
                equipo.setNumSerie(resultado.getString("numSerie"));

                listaEquipos.add(equipo);
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            this.closeConnection();
        }
        return listaEquipos;
    }

}
