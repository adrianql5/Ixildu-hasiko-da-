/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.Pelicula;
import aplicacion.Sesion;
import aplicacion.Usuario;

/**
 *
 * @author alumnogreibd
 */
public class FachadaGui {
    aplicacion.FachadaAplicacion fa;
    VPrincipal vp;
    VConfiguracion vc;
    
    public FachadaGui(aplicacion.FachadaAplicacion fa){
        this.fa=fa;
    } 
    
    
    
    public void iniciaVista(){
      VAutentificacion va;
      va = new VAutentificacion(vp, true, fa);
      va.setVisible(true);
    }
    
    public void muestraExcepcion(String txtExcepcion){
       VAviso va; 
       va = new VAviso(vp, true, txtExcepcion);
       va.setVisible(true);
    }
    
    public void nuevaCuenta(){
        VCrearCuenta vcc;
        vcc = new VCrearCuenta(vp,true,fa);
        vcc.setVisible(true);
    }
    
    public void iniciarAp(Usuario u){
        this.vp = new VPrincipal(fa, u);
        this.vp.setVisible(true);
    }


    public void configuracionUsuario(Usuario u) {
       VConfiguracion vc = new VConfiguracion(vp,true,fa,u);
       this.vc=vc;
       vc.setVisible(true);
    }

    public void eliminarCuenta() {
       vc.dispose();
       vp.dispose();
       iniciaVista();
    }

    public void salirVP() {
        iniciaVista();
    }

    public void valorar(Usuario u, Pelicula pelicula) {
        VValorar vv=new VValorar(vp,true,fa,u,pelicula);
        vv.setVisible(true);
    }

    public void verInformacion(Pelicula pelicula) {
        VInformacion vi=new VInformacion(vp,true,fa,pelicula);
        vi.setVisible(true);
    }

    public void valoracionesUsuario(Usuario u) {
        VValoraciones vv = new VValoraciones(vp,true,fa,u);
        vv.setVisible(true);
    }

    public void verOpiniones(Pelicula pelicula) {
        VOpiniones vo = new VOpiniones(vp,true,fa,pelicula);
        vo.setVisible(true);
    }

    public void reservar(Usuario u, Sesion sesionSeleccionada) {
        VReservar vr = new VReservar(vp,true,fa,u,sesionSeleccionada);
        vr.setVisible(true);
    }

    public void reservasUsuario(Usuario u) {
        VReservas vr = new VReservas(vp,true,fa,u);
        vr.setVisible(true);
    }

    public void comida(Usuario u) {
        VPedido vpe = new VPedido(vp,true,fa,u);
        vpe.setVisible(true);
    }
}
