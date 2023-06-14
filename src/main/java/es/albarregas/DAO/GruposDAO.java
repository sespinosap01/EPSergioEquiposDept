/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Grupos;
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
public class GruposDAO implements IGruposDAO {

  private String capitalizar(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
    }

    private String capitalizarTutor(String apellidos) {
        if (apellidos == null || apellidos.isEmpty()) {
            return apellidos;
        } else {
            String[] partes = apellidos.split(" ");
            StringBuilder resultado = new StringBuilder();
            for (String parte : partes) {
                resultado.append(capitalizar(parte)).append(" ");
            }
            return resultado.toString().trim();
        }
    }

    @Override
    public boolean createGrupos(Grupos grupos) {
        boolean error = true;
        String sql = "insert into grupos (denominacion, tutor) values (?, ?)";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);

            preparada.setString(1, grupos.getDenominacion());
            preparada.setString(2, capitalizarTutor(grupos.getTutor()));

            preparada.executeUpdate();
            conexion.commit();
            sql = "SELECT LAST_INSERT_ID() AS UltimoID;";
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);

            if (resultado.next()) {
                grupos.setIdGrupo(resultado.getInt("UltimoID"));
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
    public boolean updateGrupos(Grupos grupos, int idGrupo) {

        boolean error = true;
        String sql = "update grupos set denominacion = ?, tutor = ? where idGrupo = ?";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);

            preparada.setString(1, grupos.getDenominacion());
            preparada.setString(2, capitalizarTutor(grupos.getTutor()));
            preparada.setInt(3, idGrupo);

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
    public boolean deleteGrupos(int idGrupo) {

        boolean error = true;
        String sql = "delete from grupos where idGrupo = ?";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);

            preparada.setInt(1, idGrupo);

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
    public List<Grupos> getAllGrupos() {

        ResultSet resultado;
        Grupos grupo;
        List<Grupos> listaGrupos = new ArrayList<>();
        String sql = "SELECT * FROM grupos";

        try {
            Connection conexion = ConnectionFactory.getConnection();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                grupo = new Grupos();
                grupo.setIdGrupo(resultado.getInt("IdGrupo"));
                grupo.setDenominacion(resultado.getString("denominacion"));
                grupo.setTutor(resultado.getString("tutor"));

                listaGrupos.add(grupo);
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            this.closeConnection();
        }
        return listaGrupos;
    }

    public Grupos getGrupo(int idGrupo) {

        ResultSet resultado;
        Grupos grupo = new Grupos();
        String sql = "SELECT * FROM grupos where idGrupo = ?";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement preparada = conexion.prepareStatement(sql);
            preparada.setInt(1, idGrupo);
            resultado = preparada.executeQuery();

            if (resultado.next()) {
                grupo.setIdGrupo(resultado.getInt("IdGrupo"));
                grupo.setDenominacion(resultado.getString("denominacion"));
                grupo.setTutor(resultado.getString("tutor"));
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
        return grupo;
    }

    @Override
    public List<Grupos> consulta3() {

        ResultSet resultado;
        Grupos grupo;

        List<Grupos> listaGrupos = new ArrayList<>();
        String sql = "select * from grupos";

        try {
            Connection conexion = ConnectionFactory.getConnection();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {

                grupo = new Grupos();
                grupo.setDenominacion(resultado.getString("denominacion"));
                grupo.setTutor(resultado.getString("tutor"));
                listaGrupos.add(grupo);
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            this.closeConnection();
        }
        return listaGrupos;
    }

}
