/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author alumnogreibd
 */
public class InfoReserva {
    private LocalDate fechaReserva;
    private LocalTime horaReserva;
    private String nombreSala;
    private int numeroButacas;
    private String pelicula;
    private LocalDate fechaSesion;
    private LocalTime horaSesion;
    private Integer idSesion;
    private double precioTotal;

    public InfoReserva(LocalDate fechaReserva, LocalTime horaReserva, String nombreSala, int numeroButacas, String pelicula,
                       LocalDate fechaSesion, LocalTime horaSesion, double precioTotal, Integer idSesion) {
        this.fechaReserva = fechaReserva;
        this.horaReserva=horaReserva;
        this.nombreSala = nombreSala;
        this.numeroButacas = numeroButacas;
        this.pelicula = pelicula;
        this.fechaSesion = fechaSesion;
        this.horaSesion = horaSesion;
        this.precioTotal = precioTotal;
        this.idSesion=idSesion;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    
    public LocalTime getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(LocalTime horaReserva) {
        this.horaReserva = horaReserva;
    }
    

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public int getNumeroButacas() {
        return numeroButacas;
    }

    public void setNumeroButacas(int numeroButacas) {
        this.numeroButacas = numeroButacas;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public LocalDate getFechaSesion() {
        return fechaSesion;
    }

    public void setFechaSesion(LocalDate fechaSesion) {
        this.fechaSesion = fechaSesion;
    }

    public LocalTime getHoraSesion() {
        return horaSesion;
    }

    public void setHoraSesion(LocalTime horaSesion) {
        this.horaSesion = horaSesion;
    }

    public Integer getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Integer idSesion) {
        this.idSesion = idSesion;
    }
    
    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
}

