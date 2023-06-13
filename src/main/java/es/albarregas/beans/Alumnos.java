/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Sergio
 */
public class Alumnos implements Serializable {

    private int idAlumno;
    private String nombre;
    private String apellidos;
    private String nif;
    private Date fechaNacimiento;
    private Genero genero;
    private String email;
    private int idGrupo;
    private int idEquipo;
    private Equipos equipo;
    private Grupos grupo;

    public enum Genero {
        HOMBRE("H"),
        MUJER("M"),
        OTRO("O");

        private String caracteres;

        Genero(String caracteres) {
            this.caracteres = caracteres;
        }

        public String getCaracteres() {
            return this.caracteres;
        }

        public static Genero cambiarStringAChar(String text) {
            for (Genero genero : Genero.values()) {
                if (genero.getCaracteres().equalsIgnoreCase(text)) {
                    return genero;
                }
            }
            throw new IllegalArgumentException("El texto proporcionado no es un valor válido de género " + text);
        }

    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Equipos getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipos equipo) {
        this.equipo = equipo;
    }

    public Grupos getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupos grupo) {
        this.grupo = grupo;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
