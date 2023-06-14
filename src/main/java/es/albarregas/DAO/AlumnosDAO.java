/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.DAO;

import es.albarregas.beans.Alumnos;
import es.albarregas.beans.Equipos;
import es.albarregas.beans.Grupos;
import es.albarregas.models.StringUtils;
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

    /**
     * Crea un registro de alumnos en la base de datos
     *
     * @param alumnos El objeto alumnos que se va a crear
     * @return true si se crea correctamente, false en caso contrario
     */
    @Override
    public boolean createAlumnos(Alumnos alumnos) {

        boolean error = true;
        String sql = "insert into alumnos (nombre, apellidos, idgrupo, nif, fechanacimiento, genero, email, idequipo) values (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conexion = null;
        try {
            conexion = ConnectionFactory.getConnection();
            conexion.setAutoCommit(false);
            PreparedStatement preparada = conexion.prepareStatement(sql);

            preparada.setString(1, StringUtils.capitalizarTexto(alumnos.getNombre()));
            preparada.setString(2, StringUtils.capitalizarTexto(alumnos.getApellidos()));
            preparada.setInt(3, alumnos.getIdGrupo());
            preparada.setString(4, alumnos.getNif());
            preparada.setDate(5, new java.sql.Date(alumnos.getFechaNacimiento().getTime()));
            preparada.setString(6, alumnos.getGenero().getCaracteres());
            preparada.setString(7, alumnos.getEmail());
            if (alumnos.getIdEquipo() != 0) {
                preparada.setInt(8, alumnos.getIdEquipo());
            } else {
                preparada.setNull(8, 0);
            }

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

    /**
     * Actualiza un registro de alumnos en la base de datos
     *
     * @param alumnos El objeto alumnos con los nuevos datos
     * @param idAlumno El ID del alumno a actualizar
     * @return true si se actualiza correctamente, false en caso contrario
     */
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

            preparada.setString(1, StringUtils.capitalizarTexto(alumnos.getNombre()));
            preparada.setString(2, StringUtils.capitalizarTexto(alumnos.getApellidos()));
            preparada.setInt(3, alumnos.getIdGrupo());
            preparada.setString(4, alumnos.getNif());
            preparada.setDate(5, new java.sql.Date(alumnos.getFechaNacimiento().getTime()));
            preparada.setString(6, alumnos.getGenero().getCaracteres());
            preparada.setString(7, alumnos.getEmail());
            if (alumnos.getIdEquipo() != 0) {
                preparada.setInt(8, alumnos.getIdEquipo());
            } else {
                preparada.setNull(8, 0);
            }
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

    /**
     * Elimina un registro de alumnos de la base de datos
     *
     * @param idAlumno El ID del alumno a eliminar
     * @return true si se elimina correctamente, false en caso contrario
     */
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

    /**
     * Cierra la conexión activa con la base de datos. Este método debe ser
     * llamado al finalizar las operaciones de la base de datos
     */
    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

    /**
     * Obtiene una lista de todos los registros de alumnos de la base de datos
     *
     * @return Una lista de objetos alumnos que representa todos los registros
     * de Alumnos
     */
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

    /**
     * Obtiene un registro de alumnos de la base de datos segun su ID
     *
     * @param idAlumno El ID del alumno a buscar
     * @return Un objeto Alumnos que representa el registro de Alumnos
     * encontrado
     */
    @Override
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

    /**
     * Verifica si existe un registro de alumnos en la base de datos con el
     * correo electrónico especificado
     *
     * @param email El correo electrónico a verificar
     * @return true si existe un registro con el correo electrónico
     * especificado, false en caso contrario
     */
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

    /**
     * Realiza una consulta en la base de datos para obtener una lista de
     * alumnos
     *
     * @return Una lista de objetos alumnos que contienen los apellidos, nombre,
     * denominación de grupo y marca de equipo
     */
    @Override
    public List<Alumnos> listadoAlumnos() {

        ResultSet resultado;
        Alumnos alumno;
        Equipos equipo;
        Grupos grupo;

        List<Alumnos> listaAlumnos = new ArrayList<>();
        String sql = "SELECT a.Apellidos, a.Nombre, g.Denominacion, e.Marca FROM alumnos AS a LEFT JOIN "
                + "grupos AS g ON a.IdGrupo = g.IdGrupo LEFT JOIN equipos AS e ON a.IdEquipo = e.IdEquipo";
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

    /**
     * Realiza una consulta en la base de datos para obtener una lista de
     * alumnos con información adicional sobre los equipos
     *
     * @return Una lista de objetos alumnos que contienen el nombre, apellidos,
     * marca y número de serie del equipo
     */
    @Override
    public List<Alumnos> listadoAlumnosEquiposAsignados() {

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
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            this.closeConnection();
        }
        return listaAlumnos;
    }

    /**
     * Realiza una consulta en la base de datos para obtener una lista de
     * alumnos con información adicional sobre los equipos y grupos, ordenados
     * por marca y número de serie del equipo
     *
     * @return Una lista de objetos alumnos que contienen el nombre, apellidos,
     * grupo, marca y número de serie del equipo
     */
    @Override
    public List<Alumnos> listadoAlumnosPorEquipos() {

        ResultSet resultado;
        Alumnos alumno;
        Equipos equipo;
        Grupos grupo;

        List<Alumnos> listaAlumnos = new ArrayList<>();
        String sql = "SELECT a.Nombre, a.Apellidos, g.Denominacion AS Grupo, e.Marca, e.NumSerie FROM alumnos AS a JOIN equipos AS e ON a.IdEquipo = e.IdEquipo JOIN grupos AS g ON a.IdGrupo = g.IdGrupo ORDER BY e.marca, e.NumSerie";

        try {
            Connection conexion = ConnectionFactory.getConnection();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                alumno = new Alumnos();
                alumno.setApellidos(resultado.getString("Apellidos"));
                alumno.setNombre(resultado.getString("Nombre"));

                grupo = new Grupos();
                grupo.setDenominacion(resultado.getString("Grupo"));
                alumno.setGrupo(grupo);

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

    @Override
    public List<Alumnos> listadoAlumnosPorGrupos() {

        ResultSet resultado;
        Alumnos alumno;
        Grupos grupo;

        List<Alumnos> listaAlumnos = new ArrayList<>();
        String sql = "SELECT g.Denominacion, g.Tutor, a.Nombre, a.Apellidos, a.Email FROM grupos AS g "
                + "JOIN alumnos AS a ON g.IdGrupo = a.IdGrupo ORDER BY g.Denominacion, g.Tutor, a.Apellidos, a.Nombre ";

        try {
            Connection conexion = ConnectionFactory.getConnection();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                alumno = new Alumnos();
                alumno.setApellidos(resultado.getString("Apellidos"));
                alumno.setNombre(resultado.getString("Nombre"));
                alumno.setEmail(resultado.getString("Email"));

                grupo = new Grupos();
                grupo.setDenominacion(resultado.getString("denominacion"));
                grupo.setTutor(resultado.getString("tutor"));
                alumno.setGrupo(grupo);

                listaAlumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            this.closeConnection();
        }
        return listaAlumnos;
    }

    /**
     * Realiza una consulta en la base de datos para obtener una lista de
     * alumnos con información adicional sobre los grupos, ordenados por
     * denominación del grupo, tutor, apellidos y nombre del alumno
     *
     * @return Una lista de objetos alumnos que contienen el nombre, apellidos,
     * email y grupo (denominación y tutor)
     */
    @Override
    public List<Alumnos> listadoAlumnosSinEquipo() {

        ResultSet resultado;
        Alumnos alumno;
        Grupos grupo;

        List<Alumnos> listaAlumnos = new ArrayList<>();
        String sql = "SELECT a.Nombre, a.Apellidos, g.Denominacion FROM alumnos AS a JOIN grupos AS g ON a.IdGrupo = g.IdGrupo where idEquipo is null;";

        try {
            Connection conexion = ConnectionFactory.getConnection();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);

            while (resultado.next()) {
                alumno = new Alumnos();
                alumno.setApellidos(resultado.getString("Apellidos"));
                alumno.setNombre(resultado.getString("Nombre"));

                grupo = new Grupos();
                grupo.setDenominacion(resultado.getString("denominacion"));
                alumno.setGrupo(grupo);

                listaAlumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.getMessage();
        } finally {
            this.closeConnection();
        }
        return listaAlumnos;
    }

    /**
     * Realiza una consulta en la base de datos para obtener una lista de
     * alumnos con información adicional sobre los grupos y los equipos a los
     * que pertenecen.
     *
     * @return Una lista de objetos alumnos que contienen información completa
     * sobre los alumnos, incluyendo los datos de los grupos y los equipos.
     */
    public List<Alumnos> listadoTodosDatos() {

        ResultSet resultado;
        Alumnos alumno;
        Grupos grupo;
        Equipos equipo;

        List<Alumnos> listaAlumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumnos LEFT JOIN equipos ON alumnos.IdEquipo = equipos.IdEquipo "
                + "JOIN grupos ON alumnos.IdGrupo = grupos.IdGrupo;";

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
                alumno.setNif(resultado.getString("nif"));
                alumno.setFechaNacimiento(resultado.getDate("FechaNacimiento"));
                Alumnos.Genero genero = Alumnos.Genero.cambiarStringAChar(resultado.getString("genero"));
                alumno.setGenero(genero);
                alumno.setEmail(resultado.getString("Email"));

                grupo = new Grupos();
                grupo.setIdGrupo(resultado.getInt("IdGrupo"));
                grupo.setDenominacion(resultado.getString("denominacion"));
                grupo.setTutor(resultado.getString("tutor"));
                alumno.setGrupo(grupo);

                equipo = new Equipos();
                equipo.setIdEquipo(resultado.getInt("IdEquipo"));
                equipo.setMarca(resultado.getString("marca"));
                equipo.setNumSerie(resultado.getString("numSerie"));
                equipo.setFoto(resultado.getString("foto"));
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

    /**
     *
     * @param idEquipo
     * @param idGrupo
     * @return
     */
    @Override
    public List<Alumnos> listadoAjax(int idEquipo, int idGrupo) {
        ResultSet resultado;
        Alumnos alumno;

        List<Alumnos> listaAlumnos = new ArrayList<>();
        String sql = "SELECT nombre, apellidos FROM alumnos WHERE where idEquipo = ? AND idGrupo = ?";

        try {

            Connection conexion = ConnectionFactory.getConnection();
            PreparedStatement preparada = conexion.prepareStatement(sql);
            preparada.setInt(1, idEquipo);
            preparada.setInt(2, idGrupo);
            resultado = preparada.executeQuery();

            while (resultado.next()) {
                alumno = new Alumnos();
                alumno.setNombre(resultado.getString("nombre"));
                alumno.setApellidos(resultado.getString("apellidos"));

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
