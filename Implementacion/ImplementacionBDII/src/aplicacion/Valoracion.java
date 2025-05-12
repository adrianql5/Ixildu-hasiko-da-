/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import java.time.LocalDate;

/**
 *
 * @author alumnogreibd
 */
public class Valoracion {
    private String opinion;
    private Integer puntuacion;
    private String usuario;
    private String pelicula;
    private LocalDate fecha;
    
    public Valoracion(String opinion, Integer puntuacion, String usuario, String pelicula, LocalDate fecha) {
        this.opinion = opinion;
        this.puntuacion = puntuacion;
        this.usuario = usuario;
        this.pelicula = pelicula;
        this.fecha = fecha;
    }
    
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
    
    public void setPuntuacion(Integer p) {
        this.puntuacion = p;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getOpinion() {
        return this.opinion;
    }
    
    public Integer getPuntuacion() {
        return this.puntuacion;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public String getPelicula() {
        return this.pelicula;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }
}
