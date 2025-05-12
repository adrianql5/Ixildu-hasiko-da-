/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.TipoUsuario;
import aplicacion.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;


/**
 *
 * @author alumnogreibd
 */
public class DAOUsuarios extends AbstractDAO {
    public DAOUsuarios(Connection conexion, aplicacion.FachadaAplicacion fa) {
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public Usuario obtenerUsuarioPorID(String idUsuario) {
        Usuario resultado = null;
        Connection con;
        PreparedStatement stmUsuario = null;
        ResultSet rsUsuario = null;

        con = this.getConexion();

        try {
            stmUsuario = con.prepareStatement(
                "SELECT idUsuario, nombre, ap1, ap2, email, contraseña, tipoUsuario, fechaNacimiento " +
                "FROM usuario " +
                "WHERE idUsuario = ?"
            );
            stmUsuario.setString(1, idUsuario);
            rsUsuario = stmUsuario.executeQuery();

            if (rsUsuario.next()) {
                resultado = new Usuario(
                    rsUsuario.getString("idUsuario"),
                    rsUsuario.getString("nombre"),
                    rsUsuario.getString("ap1"),
                    rsUsuario.getString("ap2"),
                    rsUsuario.getString("email"),
                    rsUsuario.getString("contraseña"),
                    rsUsuario.getDate("fechaNacimiento").toLocalDate(),
                    TipoUsuario.valueOf(rsUsuario.getString("tipoUsuario"))
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try { if (rsUsuario != null) rsUsuario.close(); } catch (SQLException e) { System.out.println("Imposible cerrar ResultSet"); }
            try { if (stmUsuario != null) stmUsuario.close(); } catch (SQLException e) { System.out.println("Imposible cerrar PreparedStatement"); }
        }

        return resultado;
    }

    public void insertarUsuario(Usuario usuario) {
        Connection con;
        PreparedStatement stmUsuario = null;
        con = super.getConexion();

        try {
            stmUsuario = con.prepareStatement(
                "INSERT INTO Usuario(idUsuario, nombre, ap1, ap2, email, contraseña, tipoUsuario, fechaNacimiento) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );

            stmUsuario.setString(1, usuario.getIdUsuario());
            stmUsuario.setString(2, usuario.getNombre());
            stmUsuario.setString(3, usuario.getAp1());
            stmUsuario.setString(4, usuario.getAp2());
            stmUsuario.setString(5, usuario.getEmail());
            stmUsuario.setString(6, usuario.getContraseña());
            stmUsuario.setString(7, usuario.getTipoUsuario().name());
            stmUsuario.setDate(8, java.sql.Date.valueOf(usuario.getFechaNacimiento())); // LocalDate → SQL Date

            stmUsuario.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmUsuario != null) stmUsuario.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    void editarUsuario(String id, String nombre, String ap1, String ap2, String email, String contraseña, LocalDate fechaNacimiento, TipoUsuario tipo) {
        Connection con;
        PreparedStatement stmUsuario = null;

        con = super.getConexion();

        try {
            stmUsuario = con.prepareStatement(
                "UPDATE Usuario SET " +
                "nombre = ?, " +
                "ap1 = ?, " +
                "ap2 = ?, " +
                "email = ?, " +
                "contraseña = ?, " +
                "fechaNacimiento = ?, " +
                "tipoUsuario = ? " +
                "WHERE idUsuario = ?"
            );

            stmUsuario.setString(1, nombre);
            stmUsuario.setString(2, ap1);
            stmUsuario.setString(3, ap2);
            stmUsuario.setString(4, email);
            stmUsuario.setString(5, contraseña);
            stmUsuario.setDate(6, java.sql.Date.valueOf(fechaNacimiento));
            stmUsuario.setString(7, tipo.name());
            stmUsuario.setString(8, id);

            stmUsuario.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmUsuario != null) stmUsuario.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }
    }

    void eliminarCuenta(String idUsuario) {
        Connection con;
        PreparedStatement stmUsuario = null;
        con = super.getConexion();
        
        try{
            stmUsuario=con.prepareStatement("delete from Usuario where idUsuario = ?");
            stmUsuario.setString(1,idUsuario);
            stmUsuario.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        }
        finally{
            try{
                if(stmUsuario!=null) stmUsuario.close();
            }
            catch(SQLException e){
                System.out.println("Imposible cerrar cursores");
            }
        }
    }


}