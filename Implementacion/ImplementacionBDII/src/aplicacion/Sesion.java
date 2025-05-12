/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
/**
 *
 * @author alumnogreibd
 */

/**
 *
 * @author alumnogreibd
 */
public class Sesion {
    private Integer idSesion;
    private String pelicula;
    private Integer sala;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private int duracion;
    private float precio;

    public Sesion(Integer idSesion,String pelicula, Integer sala, LocalDate fecha, LocalTime horaInicio,int duracion, float precio) {
        this.idSesion=idSesion;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.duracion=duracion;
        this.precio = precio;
    }

    public Integer getIdSesion(){
        return idSesion;
    }
    
    public void setIdSesion(Integer i){
        this.idSesion=i;
    }
    
    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public Integer getSala() {
        return sala;
    }

    public void setSala(Integer sala) {
        this.sala = sala;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    

    public float getPrecio() {
        return precio;
    }

    private void setPrecio(float precio) {
       this.precio=precio;
    }
    
    public int getDuracion() {
        return duracion;
    }

    private void setDuracion(int duracion) {
       this.duracion=duracion;
    }
}

