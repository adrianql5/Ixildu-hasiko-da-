/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class GestionValoraciones {
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionValoraciones(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }
    
    public void guardarValoracion(Usuario u, Pelicula p, String opinion, int puntuacion) {
        Valoracion v = new Valoracion(opinion,puntuacion,u.getIdUsuario(),p.getTitulo(),LocalDate.now());
        fbd.guardarValoracion(u,p,v);
    }

    public List<Valoracion> obtenerValoraciones(String idUsuario) {
        return fbd.obtenerValoraciones(idUsuario);
    }

    void actualizarValoracion(Usuario u, String titulo, String opinion, int puntuacion) {
        fbd.actualizarValoracion(u.getIdUsuario(),titulo,opinion,puntuacion);
    }

    void eliminarValoracion(Usuario u, String pelicula) {
        fbd.eliminarValoracion(u.getIdUsuario(),pelicula);
    }

    public List<Opinion> obtenerOpiniones(Pelicula p) {
        return fbd.obtenerOpiniones(p.getTitulo());
    }
}
