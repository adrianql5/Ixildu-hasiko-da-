/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Pelicula;
import aplicacion.Sesion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class DAOSesiones extends AbstractDAO {
    public DAOSesiones (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public List<Sesion> obtenerSesiones(String titulo, LocalDate fecha, LocalTime hora) {
    List<Sesion> resultado = new ArrayList<>();
    Connection con;
    PreparedStatement stmSesiones = null;
    ResultSet rsSesiones;

    con = this.getConexion();

    String consulta =
        "SELECT s.idSesion, s.titulo, s.idsala, s.fechasesion, s.horainicio, " +
        "(p.duracion + SUM(a.duracion)) AS duracion_total, s.precio " +
        "FROM sesion s " +
        "LEFT JOIN pelicula p ON s.titulo = p.titulo " +
        "LEFT JOIN anunciar an ON s.idsesion = an.idsesion " +
        "LEFT JOIN anuncio a ON an.idanuncio = a.idanuncio ";

    // Construcción dinámica de condiciones
    List<String> condiciones = new ArrayList<>();
    if (titulo != null && !titulo.isEmpty()) {
        condiciones.add("s.titulo LIKE ?");
    }
    if (fecha != null) {
        condiciones.add("s.fechasesion = ?");
    }
    if (hora != null) {
        condiciones.add("s.horainicio = ?");
    }
    // Esta condición es fija
    condiciones.add("s.fechasesion >= CURRENT_DATE");

    if (!condiciones.isEmpty()) {
        consulta += " WHERE " + String.join(" AND ", condiciones);
    }

    consulta += " GROUP BY s.idSesion, s.titulo, s.idsala, s.fechasesion, s.horainicio, p.duracion " +
                "ORDER BY s.fechasesion ASC ";

    try {
        stmSesiones = con.prepareStatement(consulta);

        int paramIndex = 1;
        if (titulo != null && !titulo.isEmpty()) {
            stmSesiones.setString(paramIndex++, "%" + titulo + "%");
        }
        if (fecha != null) {
            stmSesiones.setDate(paramIndex++, java.sql.Date.valueOf(fecha));
        }
        if (hora != null) {
            stmSesiones.setTime(paramIndex++, java.sql.Time.valueOf(hora));
        }

        rsSesiones = stmSesiones.executeQuery();

        while (rsSesiones.next()) {
            Integer idSesion = rsSesiones.getInt("idSesion");
            String titulop = rsSesiones.getString("titulo");
            int sala = rsSesiones.getInt("idsala");
            LocalDate fechaSesion = rsSesiones.getDate("fechasesion").toLocalDate();
            LocalTime horaInicio = rsSesiones.getTime("horainicio").toLocalTime();

            // Asumiendo que duracion_total es TIME
            LocalTime duracion = rsSesiones.getTime("duracion_total").toLocalTime();
            int duracionTotal = duracion.getHour() * 60 + duracion.getMinute();

            float precio = rsSesiones.getFloat("precio");

            Sesion sesion = new Sesion(idSesion, titulop, sala, fechaSesion, horaInicio, duracionTotal, precio);
            resultado.add(sesion);
        }

    } catch (SQLException e) {
        System.out.println(e.getMessage());
        this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
    } finally {
        try {
            if (stmSesiones != null) stmSesiones.close();
        } catch (SQLException e) {
            System.out.println("Imposible cerrar cursores");
        }
    }

    return resultado;
}


    public Pelicula obtenerPelicula(String titulo) {
        Pelicula resultado = null;
        Connection con = this.getConexion();
        PreparedStatement stmPelicula = null;
        ResultSet rsPelicula;

        String consulta = "SELECT titulo, duracion, genero, sinopsis, clasificacion, idioma, fechaEstreno, duracionTrailer " +
                          "FROM Pelicula WHERE titulo = ? ";

        try {
            stmPelicula = con.prepareStatement(consulta);
            stmPelicula.setString(1, titulo);
            rsPelicula = stmPelicula.executeQuery();

            if (rsPelicula.next()) {
                String tituloP = rsPelicula.getString("titulo");
                LocalTime duracion = rsPelicula.getTime("duracion").toLocalTime();
                String genero = rsPelicula.getString("genero");
                String sinopsis = rsPelicula.getString("sinopsis");
                String clasificacion = rsPelicula.getString("clasificacion");
                String idioma = rsPelicula.getString("idioma");
                LocalDate fechaEstreno = rsPelicula.getDate("fechaEstreno").toLocalDate();
                LocalTime duracionTrailer = rsPelicula.getTime("duracionTrailer").toLocalTime();

                resultado = new Pelicula(tituloP, duracion, genero, sinopsis, clasificacion, idioma, fechaEstreno, duracionTrailer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmPelicula != null) stmPelicula.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

}
