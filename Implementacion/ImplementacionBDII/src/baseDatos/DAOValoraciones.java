/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Opinion;
import aplicacion.Pelicula;
import aplicacion.Usuario;
import aplicacion.Valoracion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author alumnogreibd
 */
public class DAOValoraciones extends AbstractDAO {
    public DAOValoraciones (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    void guardarValoracion(Usuario u, Pelicula p, Valoracion v) {
        Connection con = this.getConexion();  
        PreparedStatement stmValoracion = null;

        String consulta = "INSERT INTO Valorar (fecha, titulo, idUsuario, opinion, puntuacion) " +
                          "VALUES (?, ?, ?, ?, ?)";

        try {
            stmValoracion = con.prepareStatement(consulta);

            stmValoracion.setDate(1, java.sql.Date.valueOf(LocalDate.now()));  
            stmValoracion.setString(2, p.getTitulo());
            stmValoracion.setString(3, u.getIdUsuario());
            stmValoracion.setString(4, v.getOpinion());      
            stmValoracion.setInt(5, v.getPuntuacion());      

            stmValoracion.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al guardar la valoración: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al guardar la valoración.");
        } finally {
            try {
                if (stmValoracion != null) stmValoracion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
            }
        }
    }

    public List<Valoracion> obtenerValoraciones(String idUsuario) {
        List<Valoracion> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmValoraciones = null;
        ResultSet rsValoraciones;

        con = super.getConexion();

        String consulta = "SELECT opinion, puntuacion, idUsuario, titulo, fecha FROM Valorar WHERE idUsuario = ?";

        try {
            stmValoraciones = con.prepareStatement(consulta);
            stmValoraciones.setString(1, idUsuario);
            rsValoraciones = stmValoraciones.executeQuery();

            while (rsValoraciones.next()) {
                String opinion = rsValoraciones.getString("opinion");
                int puntuacion = rsValoraciones.getInt("puntuacion");
                String usuario = rsValoraciones.getString("idUsuario"); 
                String pelicula = rsValoraciones.getString("titulo");
                LocalDate fecha = rsValoraciones.getDate("fecha").toLocalDate();

                Valoracion val = new Valoracion(opinion, puntuacion, usuario, pelicula, fecha);
                resultado.add(val);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmValoraciones != null) stmValoraciones.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    void actualizarValoraciones(String idUsuario, String titulo, String opinion, int puntuacion) {
        Connection con = this.getConexion();  
        PreparedStatement stmActualizar = null;

        String consulta = "UPDATE Valorar SET opinion = ?, puntuacion = ?, fecha = ? " +
                          "WHERE idUsuario = ? AND titulo = ?";

        try {
            stmActualizar = con.prepareStatement(consulta);

            stmActualizar.setString(1, opinion);
            stmActualizar.setInt(2, puntuacion);
            stmActualizar.setDate(3, java.sql.Date.valueOf(LocalDate.now())); 
            stmActualizar.setString(4, idUsuario);
            stmActualizar.setString(5, titulo);

            int filasActualizadas = stmActualizar.executeUpdate();

            if (filasActualizadas == 0) {
                System.out.println("No se encontró una valoración para actualizar.");
                this.getFachadaAplicacion().muestraExcepcion("No se encontró una valoración para actualizar.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar la valoración: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al actualizar la valoración.");
        } finally {
            try {
                if (stmActualizar != null) stmActualizar.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
            }
        }
    }

    void eliminarValoracion(String u, String pelicula) {
        Connection con = this.getConexion();  
        PreparedStatement stmEliminar = null;

        String consulta = "DELETE FROM Valorar WHERE idUsuario = ? AND titulo = ?";

        try {
            stmEliminar = con.prepareStatement(consulta);

            stmEliminar.setString(1, u);
            stmEliminar.setString(2, pelicula);

            int filasEliminadas = stmEliminar.executeUpdate();

            if (filasEliminadas == 0) {
                System.out.println("No se encontró la valoración a eliminar.");
                this.getFachadaAplicacion().muestraExcepcion("No se encontró la valoración a eliminar.");
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar la valoración: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al eliminar la valoración.");
        } finally {
            try {
                if (stmEliminar != null) stmEliminar.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
            }
        }
    }

    public List<Opinion> obtenerOpiniones(String titulo) {
        List<Opinion> opiniones = new ArrayList<>();
        Connection con = this.getConexion(); 
        PreparedStatement stmOpiniones = null;
        ResultSet rs = null;

        String consulta = 
            "SELECT v.idusuario, v.opinion, v.puntuacion, " +
            "(SELECT AVG(puntuacion)::int FROM valorar WHERE titulo = ?) AS puntuacionmedia " +
            "FROM valorar v WHERE v.titulo = ?";

        try {
            stmOpiniones = con.prepareStatement(consulta);
            stmOpiniones.setString(1, titulo);  
            stmOpiniones.setString(2, titulo); 

            rs = stmOpiniones.executeQuery();

            while (rs.next()) {
                String idUsuario = rs.getString("idusuario");
                String opinion = rs.getString("opinion");
                int puntuacion = rs.getInt("puntuacion");
                int puntuacionMedia = rs.getInt("puntuacionmedia");

                opiniones.add(new Opinion(idUsuario, opinion, puntuacion, puntuacionMedia));
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener opiniones: " + e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion("Error al obtener las opiniones.");
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmOpiniones != null) stmOpiniones.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }

        return opiniones;
    }





}

