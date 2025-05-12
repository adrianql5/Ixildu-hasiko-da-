/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class GestionSesiones {
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionSesiones(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }

    public List<Sesion> obtenerSesiones(String tituloPelicula, LocalDate fecha, LocalTime hora) {
        return fbd.obtenerSesiones(tituloPelicula, fecha, hora);
    }

    public Pelicula obtenerPelicula(String pelicula) {
        return fbd.obtenerPelicula(pelicula);
    }
}
