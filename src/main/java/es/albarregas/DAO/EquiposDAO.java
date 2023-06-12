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

    @Override
    public boolean createEquipos(Equipos equipos) {

        boolean error = true;
        String sql = "insert into equipos (marca, numserie, foto) values (?, ?, ?)";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);

            preparada.setString(1, equipos.getMarca());
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

    @Override
    public boolean updateEquipos(Equipos equipos, int idEquipo) {

        boolean error = true;
        String sql = "update equipos set marca = ?, numserie = ?, foto = ? where idEquipo = ?";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);

            preparada.setString(1, equipos.getMarca());
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
        } catch (Exception e) {
            e.getMessage();
        } finally {
            this.closeConnection();
        }
        return listaEquipos;
    }

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

}
