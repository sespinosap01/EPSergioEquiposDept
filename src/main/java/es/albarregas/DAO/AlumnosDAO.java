/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Alumnos;
import es.albarregas.beans.Equipos;
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
            preparada.setDate(5, new java.sql.Date(alumnos.getFechaNacimiento().getTime()));
            preparada.setString(6, alumnos.getGenero().getCaracteres());
            preparada.setString(7, alumnos.getEmail());
            preparada.setInt(8, alumnos.getIdEquipo());

            preparada.executeUpdate();

            conexion.commit();
            sql = "SELECT LAST_INSERT_ID() AS UltimoID;";
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);

            if (resultado.next()) {
                alumnos.setIdAlumno(resultado.getInt("UltimoID"));
            }

        } catch (SQLException e) {
            System.out.println("Error de MYSQL" + e.getMessage());
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
            preparada.setDate(5, new java.sql.Date(alumnos.getFechaNacimiento().getTime()));
            preparada.setString(6, alumnos.getGenero().getCaracteres());
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
        String sql = "delete from alumnos where idAlumno = ?";
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
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

    @Override
    public List<Alumnos> getAllAlumnos() {

        ResultSet resultado;
        Alumnos alumno;
        List<Alumnos> listaAlumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";

        try {
            Connection conexion = ConnectionFactory.getConnection();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                alumno = new Alumnos();
                alumno.setIdAlumno(resultado.getInt("IdAlumno"));
                alumno.setNombre(resultado.getString("Nombre"));
                alumno.setApellidos(resultado.getString("Apellidos"));
                alumno.setIdGrupo(resultado.getInt("IdGrupo"));
                alumno.setIdEquipo(resultado.getInt("IdEquipo"));
                alumno.setNif(resultado.getString("Nif"));

                Alumnos.Genero genero = Alumnos.Genero.cambiarStringAChar(resultado.getString("genero"));
                alumno.setGenero(genero);

                alumno.setFechaNacimiento(resultado.getDate("FechaNacimiento"));
                alumno.setEmail(resultado.getString("Email"));

                ResultSet resultadoGrupo = null;
                Grupos grupo;
                sql = "select * from grupos where idGrupo= ?";
                PreparedStatement preparadaGr = conexion.prepareStatement(sql);
                preparadaGr.setInt(1, alumno.getIdGrupo());
                resultadoGrupo = preparadaGr.executeQuery();
                while (resultadoGrupo.next()) {
                    grupo = new Grupos();
                    grupo.setIdGrupo(resultadoGrupo.getInt("idGrupo"));
                    grupo.setDenominacion(resultadoGrupo.getString("denominacion"));
                    grupo.setTutor(resultadoGrupo.getString("tutor"));
                    alumno.setGrupo(grupo);
                }

                ResultSet resultadoEquipo = null;
                Equipos equipo;
                sql = "select * from equipos where idEquipo= ?";
                PreparedStatement preparadaEq = conexion.prepareStatement(sql);
                preparadaEq.setInt(1, alumno.getIdEquipo());
                resultadoEquipo = preparadaEq.executeQuery();
                while (resultadoEquipo.next()) {
                    equipo = new Equipos();
                    equipo.setIdEquipo(resultadoEquipo.getInt("idEquipo"));
                    equipo.setMarca(resultadoEquipo.getString("Marca"));
                    equipo.setNumSerie(resultadoEquipo.getString("numSerie"));
                    equipo.setFoto(resultadoEquipo.getString("Foto"));
                    alumno.setEquipo(equipo);
                }

                listaAlumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            this.closeConnection();
        }
        return listaAlumnos;
    }

    public Alumnos getAlumno(int idAlumno) {
        ResultSet resultado;
        Alumnos alumno = new Alumnos();
        String sql = "SELECT * FROM alumnos where idAlumno = ?";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            PreparedStatement preparada = conexion.prepareStatement(sql);
            preparada.setInt(1, idAlumno);
            resultado = preparada.executeQuery();

            if (resultado.next()) {
                alumno.setIdAlumno(resultado.getInt("IdAlumno"));
                alumno.setNombre(resultado.getString("nombre"));
                alumno.setApellidos(resultado.getString("apellidos"));
                alumno.setEmail(resultado.getString("email"));
                alumno.setNif(resultado.getString("nif"));
                alumno.setFechaNacimiento(resultado.getDate("fechaNacimiento"));

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
        return alumno;

    }

    @Override
    public boolean correoExiste(String email) {
        boolean existe = false;
        ResultSet resultado;

        String sql = "select email from alumnos where email = ?";
        try {
            Connection conexion = ConnectionFactory.getConnection();
            PreparedStatement preparada = conexion.prepareStatement(sql);
            preparada.setString(1, email);
            resultado = preparada.executeQuery();

            while (resultado.next()) {
                if (resultado.getString("email") != null) {
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

    @Override
    public List<Alumnos> consulta1() {

        ResultSet resultado;
        Alumnos alumno;
        Equipos equipo;
        Grupos grupo;

        List<Alumnos> listaAlumnos = new ArrayList<>();
        String sql = "SELECT a.Apellidos, a.Nombre, g.Denominacion, e.Marca FROM alumnos AS a JOIN grupos AS g ON a.IdGrupo = g.IdGrupo JOIN equipos AS e ON a.IdEquipo = e.IdEquipo ORDER BY apellidos, nombre;";

        try {
            Connection conexion = ConnectionFactory.getConnection();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                alumno = new Alumnos();
                alumno.setApellidos(resultado.getString("Apellidos"));
                alumno.setNombre(resultado.getString("Nombre"));

                grupo = new Grupos();
                grupo.setDenominacion(resultado.getString("Denominacion"));
                alumno.setGrupo(grupo);

                equipo = new Equipos();
                equipo.setMarca(resultado.getString("marca"));
                alumno.setEquipo(equipo);

                listaAlumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            this.closeConnection();
        }
        return listaAlumnos;
    }

    @Override
    public List<Alumnos> consulta4() {

        ResultSet resultado;
        Alumnos alumno;
        Equipos equipo;

        List<Alumnos> listaAlumnos = new ArrayList<>();
        String sql = "SELECT a.Nombre, a.Apellidos, e.Marca, e.NumSerie FROM alumnos AS a JOIN equipos AS e ON a.IdEquipo = e.IdEquipo;";

        try {
            Connection conexion = ConnectionFactory.getConnection();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                alumno = new Alumnos();
                alumno.setNombre(resultado.getString("Nombre"));
                alumno.setApellidos(resultado.getString("Apellidos"));

                equipo = new Equipos();
                equipo.setMarca(resultado.getString("marca"));
                equipo.setNumSerie(resultado.getString("numSerie"));
                alumno.setEquipo(equipo);

                listaAlumnos.add(alumno);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            this.closeConnection();
        }
        return listaAlumnos;
    }

    @Override
    public List<Alumnos> consulta5() {

        ResultSet resultado;
        Alumnos alumno;
        Equipos equipo;
        Grupos grupo;

        List<Alumnos> listaAlumnos = new ArrayList<>();
        String sql = "SELECT a.Nombre, a.Apellidos, g.Denominacion AS Grupo, e.Marca, e.NumSerie FROM alumnos AS a JOIN equipos AS e ON a.IdEquipo = e.IdEquipo JOIN grupos AS g ON a.IdGrupo = g.IdGrupo";

        try {
            Connection conexion = ConnectionFactory.getConnection();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                alumno = new Alumnos();
                alumno.setApellidos(resultado.getString("Apellidos"));
                alumno.setNombre(resultado.getString("Nombre"));

                // grupo = new Grupos();
                //grupo.setDenominacion(resultado.getString("Denominacion"));
                //alumno.setGrupo(grupo);
                
                equipo = new Equipos();
                equipo.setMarca(resultado.getString("marca"));
                equipo.setNumSerie(resultado.getString("numSerie"));

                alumno.setEquipo(equipo);

                listaAlumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            this.closeConnection();
        }
        return listaAlumnos;
    }

}
