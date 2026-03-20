package aplicacion;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author alumnogreibd
 */
public class Pedido {
    private LocalDate fecha;
    private LocalTime hora;
    private String nombreComida;
    private Integer idComida;
    private String idUsuario;
    private int cantidad;
    private boolean entregado;

    public Pedido(LocalDate fecha, LocalTime hora, String nombreComida,Integer idComida, String idUsuario, int cantidad, boolean entregado) {
        this.fecha = fecha;
        this.hora = hora;
        this.nombreComida = nombreComida;
        this.idComida=idComida;
        this.idUsuario = idUsuario;
        this.cantidad = cantidad;
        this.entregado = entregado;
    }

    // Getters
    public LocalDate getFecha() {
        return fecha;
    }

    public LocalTime getHora() {
        return hora;
    }
    public Integer getIdComida() {
        return idComida;
    }

    public void setIdComida(Integer idComida) {
        this.idComida = idComida;
    }

    public String getNombreComida() {
        return nombreComida;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public boolean isEntregado() {
        return entregado;
    }

    // Setters
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setNombreComida(String nombreComida) {
        this.nombreComida = nombreComida;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "fecha=" + fecha +
                ", hora=" + hora +
                ", nombreComida='" + nombreComida + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", cantidad=" + cantidad +
                ", entregado=" + entregado +
                '}';
    }
}
