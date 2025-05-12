/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class Reserva {
    private Integer idReserva;
    private String idUsuario;
    private Integer idSesion;
    private LocalDate fechaReserva;
    private LocalTime horaReserva;
    private List<Butaca> butacas;

    // Constructor completo
    public Reserva(Integer idReserva, String idUsuario, Integer idSesion, LocalDate fechaReserva, LocalTime horaReserva) {
        this.idReserva = idReserva;
        this.idUsuario = idUsuario;
        this.idSesion = idSesion;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.butacas=new ArrayList<>();
    }

    // Getters
    public List<Butaca> getButacas(){
        return butacas;
    }
    
    public Integer getIdReserva() {
        return idReserva;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public Integer getIdSesion() {
        return idSesion;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public LocalTime getHoraReserva() {
        return horaReserva;
    }

    // Setters
    public void setButacas (List<Butaca> butacas){
        this.butacas=butacas;
    }
    
    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdSesion(Integer idSesion) {
        this.idSesion = idSesion;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setHoraReserva(LocalTime horaReserva) {
        this.horaReserva = horaReserva;
    }
}

