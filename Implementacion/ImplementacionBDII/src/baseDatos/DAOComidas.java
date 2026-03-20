/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Comida;
import aplicacion.Pedido;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author alumnogreibd
 */
public class DAOComidas extends AbstractDAO{
    public DAOComidas (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public List<Comida> obtenerMenu() {
        List<Comida> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmMenu = null;
        ResultSet rsMenu;

        con = super.getConexion();

        // Consulta sin el GROUP BY ya que no es necesario para esta operación
        String consulta = "SELECT idComida, nombre, tamaño, descripcion, precio, stockDisponible " +
                          "FROM Comida " +
                          "ORDER BY nombre, tamaño";

        try {
            stmMenu = con.prepareStatement(consulta);
            rsMenu = stmMenu.executeQuery();

            while (rsMenu.next()) {
                int idComida = rsMenu.getInt("idComida"); // Obtener el idComida desde la consulta
                String nombre = rsMenu.getString("nombre");
                String tamaño = rsMenu.getString("tamaño");
                String descripcion = rsMenu.getString("descripcion");
                double precio = rsMenu.getDouble("precio");
                int stockDisponible = rsMenu.getInt("stockDisponible"); // Obtener el stockDisponible desde la consulta
                // Crear el objeto Comida con los datos obtenidos
                Comida c = new Comida(idComida, nombre, descripcion, precio, tamaño, stockDisponible);
                resultado.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmMenu != null) stmMenu.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }



    public void pedir(String idUsuario, int idComida, Integer cantidad) {
        Connection con = null;
        PreparedStatement stmSelectStock = null;
        PreparedStatement stmPedir = null;
        PreparedStatement stmActualizarStock = null;
        ResultSet rs = null;

        try {
            con = this.getConexion();
            con.setAutoCommit(false); // Iniciar transacción manual

            // 1. Bloquear la fila de stock para esta comida
            String consultaStock = "SELECT stockDisponible FROM Comida WHERE idComida = ? FOR UPDATE";
            stmSelectStock = con.prepareStatement(consultaStock);
            stmSelectStock.setInt(1, idComida);
            rs = stmSelectStock.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Comida no encontrada.");
            }

            int stockActual = rs.getInt("stockDisponible");
            if (stockActual < cantidad) {
                throw new SQLException("No hay suficiente stock disponible.");
            }

            // 2. Insertar el pedido
            long currentMillis = System.currentTimeMillis();
            long truncatedMillis = (currentMillis / 1000) * 1000;
            java.sql.Time horaActual = new java.sql.Time(truncatedMillis);
            java.sql.Date fechaActual = new java.sql.Date(currentMillis);

            String consultaInsertar = "INSERT INTO Pedir (fecha, hora, idComida, idUsuario, cantidad, entregado) " +
                                      "VALUES (?, ?, ?, ?, ?, FALSE)";
            stmPedir = con.prepareStatement(consultaInsertar);
            stmPedir.setDate(1, fechaActual);
            stmPedir.setTime(2, horaActual);
            stmPedir.setInt(3, idComida);
            stmPedir.setString(4, idUsuario);
            stmPedir.setInt(5, cantidad);
            stmPedir.executeUpdate();

            // 3. Actualizar el stock
            String consultaActualizarStock = "UPDATE Comida SET stockDisponible = stockDisponible - ? WHERE idComida = ?";
            stmActualizarStock = con.prepareStatement(consultaActualizarStock);
            stmActualizarStock.setInt(1, cantidad);
            stmActualizarStock.setInt(2, idComida);
            stmActualizarStock.executeUpdate();

            con.commit(); // Fase de liberación de bloqueos: aquí se liberan

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback(); // Revertir si hay error
                } catch (SQLException rollbackEx) {
                    System.out.println("Error haciendo rollback: " + rollbackEx.getMessage());
                }
            }
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmSelectStock != null) stmSelectStock.close();
                if (stmPedir != null) stmPedir.close();
                if (stmActualizarStock != null) stmActualizarStock.close();
                if (con != null) con.setAutoCommit(true); // Restaurar estado por defecto
            } catch (SQLException e) {
                System.out.println("Imposible cerrar recursos: " + e.getMessage());
            }
        }
    }


    public List<Pedido> obtenerPedidosActivos(String idUsuario) {
        List<Pedido> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmPedidos = null;
        ResultSet rsPedidos;

        con = this.getConexion();

        String consulta = "SELECT p.fecha, p.hora, c.nombre AS nombreComida,p.idComida, p.idUsuario, p.cantidad, p.entregado " +
                          "FROM Pedir p " +
                          "JOIN Comida c ON p.idComida = c.idComida " +
                          "WHERE p.idUsuario = ? AND p.entregado = FALSE " +
                          "ORDER BY p.fecha DESC, p.hora DESC";

        try {
            stmPedidos = con.prepareStatement(consulta);
            stmPedidos.setString(1, idUsuario);
            rsPedidos = stmPedidos.executeQuery();

            while (rsPedidos.next()) {
                LocalDate fecha = rsPedidos.getDate("fecha").toLocalDate();
                LocalTime hora = rsPedidos.getTime("hora").toLocalTime();
                String nombreComida = rsPedidos.getString("nombreComida");
                String usuario = rsPedidos.getString("idUsuario");
                int cantidad = rsPedidos.getInt("cantidad");
                boolean entregado = rsPedidos.getBoolean("entregado");
                int idComida = rsPedidos.getInt("idComida");
                Pedido pedido = new Pedido(fecha, hora, nombreComida,idComida, usuario, cantidad, entregado);
                resultado.add(pedido);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmPedidos != null) stmPedidos.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    public void eliminarPedido(Pedido pedidoSeleccionado, String idUsuario) {
        Connection con = null;
        PreparedStatement eliminarPedidoStmt = null;
        PreparedStatement actualizarStockStmt = null;

        try {
            con = super.getConexion();  
            if (con == null) throw new SQLException("Conexión nula.");

            // Paso 1: Eliminar el pedido
            String sqlEliminar = "DELETE FROM Pedir WHERE idUsuario = ? AND fecha = ? AND hora = ? AND idComida = ?";
            eliminarPedidoStmt = con.prepareStatement(sqlEliminar);

           
            eliminarPedidoStmt.setString(1, idUsuario);
            eliminarPedidoStmt.setDate(2, java.sql.Date.valueOf(pedidoSeleccionado.getFecha()));  // Convertir LocalDate a java.sql.Date
            eliminarPedidoStmt.setTime(3, java.sql.Time.valueOf(pedidoSeleccionado.getHora()));   // Convertir LocalTime a java.sql.Time
            eliminarPedidoStmt.setInt(4, pedidoSeleccionado.getIdComida());  // Obtener el idComida del pedido

          
            int filasEliminadas = eliminarPedidoStmt.executeUpdate();

            if (filasEliminadas == 0) {
                throw new SQLException("No se eliminó ningún pedido. ¿Los datos coinciden con un pedido activo?");
            }

            // Actualizar el stock de comida
            
            int cantidadPedido = pedidoSeleccionado.getCantidad();

            String sqlActualizarStock = "UPDATE Comida SET stockDisponible = stockDisponible + ? WHERE idComida = ?";
            actualizarStockStmt = con.prepareStatement(sqlActualizarStock);

            
            actualizarStockStmt.setInt(1, cantidadPedido);
            actualizarStockStmt.setInt(2, pedidoSeleccionado.getIdComida());

            
            int filasActualizadas = actualizarStockStmt.executeUpdate();

            if (filasActualizadas == 0) {
                throw new SQLException("No se actualizó el stock. ¿El idComida existe?");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            
            try {
                if (eliminarPedidoStmt != null) eliminarPedidoStmt.close();
                if (actualizarStockStmt != null) actualizarStockStmt.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }








}
