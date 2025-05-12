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
public class GestionComidas {
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionComidas(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }

    public List<Comida> obtenerMenu() {
        return fbd.obtenerMenu();
    }

    boolean pedir(Usuario u, Comida comidaSeleccionada, Integer cantidad) {
        if (cantidad == null || cantidad <= 0) {
            return false;
        }

        if (comidaSeleccionada.getStockDisponible() < cantidad) {
            return false;
        }

        fbd.pedir(u.getIdUsuario(), comidaSeleccionada.getIdComida(), cantidad);
        return true;
    }

    public List<Pedido> obtenerPedidosActivos(Usuario u) {
        return fbd.obtenerPedidosActivos(u.getIdUsuario());
    }

    void eliminarPedido(Pedido pedidoSeleccionado, Usuario u) {
        fbd.eliminarPedido(pedidoSeleccionado, u.getIdUsuario());
    }

        
}
