/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class GestionReservas {
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionReservas(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }

    public List<Reserva> obtenerReservas(Sesion s) {
        return fbd.obtenerReservas(s);
    }

    public List<Equipo> obtenerEquipo(Sesion s) {
        return fbd.obtenerEquipo(s);
    }

    public void reservarButacas(List<Integer> idsButacasSeleccionadas, Sesion s, Usuario u) {
        for(Integer id: idsButacasSeleccionadas){
            fbd.reservarButaca(id,s.getIdSesion(),u.getIdUsuario());
        }
    }

    public List<InfoReserva> obtenerInfoReservas(Usuario u) {
        return fbd.obtenerInfoReservas(u);
    }

    public void eliminarReserva(Usuario u, InfoReserva reservaSeleccionada) {
        fbd.eliminarReserva(u, reservaSeleccionada);
    }

    
}
