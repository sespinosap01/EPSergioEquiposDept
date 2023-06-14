/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.models;

/**
 *
 * @author Sergio
 */
public class StringUtils {

    public static String capitalizar(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        }
    }

    public static String capitalizarTexto(String apellidos) {
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
}
