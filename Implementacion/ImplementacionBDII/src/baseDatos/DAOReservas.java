/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import aplicacion.Butaca;
import aplicacion.Equipo;
import aplicacion.InfoReserva;
import aplicacion.Pelicula;
import aplicacion.Reserva;
import aplicacion.Sesion;
import aplicacion.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class DAOReservas extends AbstractDAO{
    public DAOReservas (Connection conexion, aplicacion.FachadaAplicacion fa){
        super.setConexion(conexion);
        super.setFachadaAplicacion(fa);
    }

    public List<Reserva> obtenerReservas(Integer idSesion) {
        List<Reserva> resultado = new ArrayList<>();
        Reserva reservaActual;
        Butaca butacaActual;
        Connection con;
        PreparedStatement stmReservas = null;
        PreparedStatement stmButacas = null;
        ResultSet rsReservas;
        ResultSet rsButacas;

        con = super.getConexion();

        String consultaReservas = "SELECT idreserva, idusuario, idsesion, fechareserva, horareserva " +
                                  "FROM reserva WHERE idsesion = ?";

        try {
            stmReservas = con.prepareStatement(consultaReservas);
            stmReservas.setInt(1, idSesion);
            rsReservas = stmReservas.executeQuery();

            while (rsReservas.next()) {
                reservaActual = new Reserva(
                    rsReservas.getInt("idreserva"),
                    rsReservas.getString("idusuario"),
                    rsReservas.getInt("idsesion"),
                    rsReservas.getDate("fechareserva").toLocalDate(),
                    rsReservas.getTime("horareserva").toLocalTime()
                );
                // Cargar las butacas de esta reserva
                String consultaButacas = "SELECT idbutaca, idsala, tipo FROM butaca WHERE idreserva = ?";
                stmButacas = con.prepareStatement(consultaButacas);
                stmButacas.setInt(1, rsReservas.getInt("idreserva"));
                rsButacas = stmButacas.executeQuery();

                List<Butaca> butacas = new ArrayList<>();
                while (rsButacas.next()) {
                    butacaActual = new Butaca(
                        rsButacas.getInt("idbutaca"),
                        rsButacas.getInt("idsala"),
                        rsReservas.getInt("idsesion"),  
                        rsButacas.getString("tipo")
                    );
                    butacas.add(butacaActual);
                }
                reservaActual.setButacas(butacas);
                resultado.add(reservaActual);

                if (stmButacas != null) stmButacas.close();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmReservas != null) stmReservas.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    public List<Equipo> obtenerEquipo(Sesion s) {
        List<Equipo> resultado = new ArrayList<>();
        Equipo equipoActual;
        Connection con;
        PreparedStatement stmEquipos = null;
        ResultSet rsEquipos;

        con = super.getConexion(); 

        String consulta = "SELECT sa.nombre AS nombreSala, eq.nombre AS nombreEquipo, eq.tipo, eq.marca, eq.modelo, eq.fechaadquisicion, eq.precio " +
                          "FROM equipo eq JOIN sala sa ON eq.idsala = sa.idsala " +
                          "WHERE eq.idsala = ?";

        try {
            stmEquipos = con.prepareStatement(consulta);
            stmEquipos.setInt(1, s.getSala()); 
            rsEquipos = stmEquipos.executeQuery();

            while (rsEquipos.next()) {
                equipoActual = new Equipo(
                    rsEquipos.getString("nombreSala"),
                    rsEquipos.getString("nombreEquipo"),
                    rsEquipos.getString("tipo"),
                    rsEquipos.getString("marca"),
                    rsEquipos.getString("modelo"),
                    rsEquipos.getDate("fechaadquisicion").toLocalDate(),
                    rsEquipos.getFloat("precio")
                );
                resultado.add(equipoActual);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmEquipos != null) stmEquipos.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    public void reservarButaca(Integer idButaca, Integer idSesion, String idUsuario) {
        Connection con = null;
        PreparedStatement bloquearButaca = null;
        PreparedStatement insertarReserva = null;
        PreparedStatement obtenerIdReserva = null;
        PreparedStatement actualizarButaca = null;
        ResultSet rs = null;

        try {
            con = super.getConexion();
            if (con == null) throw new SQLException("La conexión es nula.");

            con.setAutoCommit(false); // Inicia la transacción (fase de crecimiento del 2PL)

            // 1. Bloquear la butaca seleccionada
            String lockSQL = "SELECT idreserva FROM butaca WHERE idbutaca = ? FOR UPDATE";
            bloquearButaca = con.prepareStatement(lockSQL);
            bloquearButaca.setInt(1, idButaca);
            rs = bloquearButaca.executeQuery();
            if (!rs.next()) {
                throw new SQLException("Butaca no encontrada.");
            }
            if (rs.getInt("idreserva") != 0) {
                throw new SQLException("La butaca ya está reservada.");
            }

            // Obtener hora actual cambiada a segundos
            long currentMillis = System.currentTimeMillis();
            long truncatedMillis = (currentMillis / 1000) * 1000;
            java.sql.Time horaActual = new java.sql.Time(truncatedMillis);
            java.sql.Date fechaActual = new java.sql.Date(currentMillis);

            // 2. Insertar la nueva reserva
            String insertSQL = "INSERT INTO reserva (idusuario, idsesion, fechareserva, horareserva) VALUES (?, ?, ?, ?)";
            insertarReserva = con.prepareStatement(insertSQL);
            insertarReserva.setString(1, idUsuario);
            insertarReserva.setInt(2, idSesion);
            insertarReserva.setDate(3, fechaActual);
            insertarReserva.setTime(4, horaActual);
            insertarReserva.executeUpdate();

            // 3. Obtener el ID de la reserva recién creada
            String selectSQL = "SELECT idreserva FROM reserva WHERE idusuario = ? ORDER BY idreserva DESC LIMIT 1";
            obtenerIdReserva = con.prepareStatement(selectSQL);
            obtenerIdReserva.setString(1, idUsuario);
            rs = obtenerIdReserva.executeQuery();

            Integer idReserva = null;
            if (rs.next()) {
                idReserva = rs.getInt("idreserva");
            } else {
                throw new SQLException("No se encontró la reserva recién insertada.");
            }

            // 4. Asignar la reserva a la butaca bloqueada
            String updateSQL = "UPDATE butaca SET idreserva = ? WHERE idbutaca = ?";
            actualizarButaca = con.prepareStatement(updateSQL);
            actualizarButaca.setInt(1, idReserva);
            actualizarButaca.setInt(2, idButaca);
            int filasActualizadas = actualizarButaca.executeUpdate();

            if (filasActualizadas == 0) {
                throw new SQLException("No se actualizó ninguna butaca. ¿La idbutaca existe?");
            }

            con.commit(); // Fase de decrecimiento del 2PL: liberar bloqueos

        } catch (SQLException e) {
            try {
                if (con != null) con.rollback(); 
            } catch (SQLException ex) {
                System.out.println("Error en rollback: " + ex.getMessage());
            }
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());

        } finally {
            try {
                if (rs != null) rs.close();
                if (bloquearButaca != null) bloquearButaca.close();
                if (insertarReserva != null) insertarReserva.close();
                if (obtenerIdReserva != null) obtenerIdReserva.close();
                if (actualizarButaca != null) actualizarButaca.close();
                if (con != null) con.setAutoCommit(true); 
            } catch (SQLException e) {
                System.out.println("Imposible cerrar recursos: " + e.getMessage());
            }
        }
    }



    public List<InfoReserva> obtenerInfoReservas(Usuario u) {
        List<InfoReserva> resultado = new ArrayList<>();
        Connection con;
        PreparedStatement stmReservas = null;
        ResultSet rsReservas;

        con = super.getConexion();

        String consulta = """
            SELECT 
                r.fechareserva,
                r.horareserva,
                sa.nombre AS nombresala,
                se.titulo AS pelicula,
                se.idsesion,
                se.fechasesion,
                se.horainicio,
                COUNT(*) AS num_butacas,
                COUNT(*) * se.precio AS precio_total
            FROM reserva r
            JOIN sesion se ON r.idsesion = se.idsesion
            JOIN sala sa ON se.idsala = sa.idsala
            WHERE r.idusuario = ?
            GROUP BY se.idsesion, sa.nombre, se.titulo, se.fechasesion, se.horainicio, se.precio, r.fechareserva, r.horareserva
            ORDER BY fechareserva DESC;
            """;

        try {
            stmReservas = con.prepareStatement(consulta);
            stmReservas.setString(1, u.getIdUsuario()); 
            rsReservas = stmReservas.executeQuery();

            while (rsReservas.next()) {
                InfoReserva info = new InfoReserva(
                    rsReservas.getDate("fechareserva").toLocalDate(),
                    rsReservas.getTime("horareserva").toLocalTime(),
                    rsReservas.getString("nombresala"),
                    rsReservas.getInt("num_butacas"),
                    rsReservas.getString("pelicula"),
                    rsReservas.getDate("fechasesion").toLocalDate(),
                    rsReservas.getTime("horainicio").toLocalTime(),
                    rsReservas.getDouble("precio_total"),
                    rsReservas.getInt("idsesion")
                        
                );
                
                resultado.add(info);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (stmReservas != null) stmReservas.close();
            } catch (SQLException e) {
                System.out.println("Imposible cerrar cursores");
            }
        }

        return resultado;
    }

    public void eliminarReserva(String idUsuario, InfoReserva reserva) {
        Connection con = null;
        PreparedStatement eliminarReservas = null;

        try {
            con = super.getConexion();
            if (con == null) throw new SQLException("Conexión nula.");

            String sqlEliminar = "DELETE FROM reserva WHERE idusuario = ? AND idsesion = ? AND fechareserva = ? AND horareserva = ?";
            eliminarReservas = con.prepareStatement(sqlEliminar);
            eliminarReservas.setString(1, idUsuario);
            eliminarReservas.setInt(2, reserva.getIdSesion());
            eliminarReservas.setDate(3, java.sql.Date.valueOf(reserva.getFechaReserva()));
            eliminarReservas.setTime(4, java.sql.Time.valueOf(reserva.getHoraReserva()));

            int filasEliminadas = eliminarReservas.executeUpdate();

            if (filasEliminadas == 0) {
                throw new SQLException("No se eliminó ninguna reserva. ¿Los datos coinciden con una reserva activa?");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            this.getFachadaAplicacion().muestraExcepcion(e.getMessage());
        } finally {
            try {
                if (eliminarReservas != null) eliminarReservas.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }










}
