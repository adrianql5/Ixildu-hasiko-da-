/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


/**
 *
 * @author alumnogreibd
 */
public class FachadaAplicacion {
    gui.FachadaGui fgui;
    baseDatos.FachadaBaseDatos fbd;
    GestionComidas cc;
    GestionReservas cr;
    GestionSesiones cs;
    GestionUsuarios cu;
    GestionValoraciones cv;
    
    
    public FachadaAplicacion(){
      fgui=new gui.FachadaGui(this);
      fbd= new baseDatos.FachadaBaseDatos(this);
      cc=new GestionComidas(fgui, fbd);
      cr= new GestionReservas(fgui, fbd);
      cs=new GestionSesiones(fgui, fbd);
      cu= new GestionUsuarios(fgui, fbd);
      cv= new GestionValoraciones(fgui,fbd);
    }

    public static void main(String args[]) {
        FachadaAplicacion fa;

        fa= new FachadaAplicacion();
        fa.iniciaInterfazUsuario();
    }
    
    public void iniciaInterfazUsuario(){
        fgui.iniciaVista();
    }

    public void muestraExcepcion(String e){
        fgui.muestraExcepcion(e);
    }

    //Seccion usuarios
    public Usuario comprobarAutentificacion(String idUsuario, String clave){
        return cu.comprobarAutentificacion(idUsuario, clave);
    }

    public void nuevaCuenta() {
        cu.nuevaCuenta();
    }

    public Boolean crearCuenta(String id, String nombre, String ap1, String ap2, String email, String contraseña, LocalDate fechaNacimiento, TipoUsuario tipo) {
        return cu.crearCuenta(id,  nombre,  ap1,  ap2,  email,  contraseña,  fechaNacimiento,  tipo) ;
    }

    public void inciarAp(Usuario u) {
        cu.iniciarAp(u);
    }
    
    public void configuracionUsuario(Usuario u) {
        cu.configuracionUsuario(u);
    }

    public void editarUsuario(Usuario u, String id, String nombre, String ap1, String ap2, String email, String contraseña, LocalDate fechaNacimiento, TipoUsuario tipo) {
       cu.editarUsuario(u,id,  nombre,  ap1,  ap2,  email,  contraseña,  fechaNacimiento,  tipo);
    }

    public void eliminarCuenta(Usuario u) {
        cu.eliminarCuenta(u);
        fgui.eliminarCuenta();
    }

    public void salirVP() {
        fgui.salirVP();
    }

    //Seccion sesiones
    public List<Sesion> obtenerSesiones(String titulo, LocalDate fecha, LocalTime hora) {
        return cs.obtenerSesiones(titulo, fecha, hora);
    }

    public void valorar(Usuario u, Pelicula pelicula) {
        fgui.valorar(u,pelicula);
    }

    public Pelicula obtenerPelicula(String pelicula) {
        return cs.obtenerPelicula(pelicula);
    }

    public void verInformacion(Pelicula pelicula) {
        fgui.verInformacion(pelicula);
    }
    
    //Seccion Valoraciones
    public void guardarValoracion(Usuario u, Pelicula p, String opinion, int puntuacion) {
        cv.guardarValoracion(u,p,opinion,puntuacion);
    }

    public void valoracionesUsuario(Usuario u) {
        fgui.valoracionesUsuario(u);
    }

    public List<Valoracion> obtenerValoraciones(String idUsuario) {
        return cv.obtenerValoraciones(idUsuario);
    }

    public void actualizarValoracion(Usuario u, String p, String opinion, int puntuacion) {
        cv.actualizarValoracion(u,p,opinion,puntuacion);
    }

    public void eliminarValoracion(Usuario u, String pelicula) {
        cv.eliminarValoracion(u,pelicula);
    }

    //Seccion Opiniones
    public void verOpiniones(Pelicula pelicula) {
        fgui.verOpiniones(pelicula);
    }

    public List<Opinion> obtenerOpiniones(Pelicula p) {
        return cv.obtenerOpiniones(p);
    }

    //Seccion Reservas
    public void reservar(Usuario u, Sesion sesionSeleccionada) {
        fgui.reservar(u,sesionSeleccionada);
    }

    public List<Reserva> obtenerReservas(Sesion s) {
        return cr.obtenerReservas(s);
    }
    
    public List<Equipo> obtenerEquipo(Sesion s) {
        return cr.obtenerEquipo(s);
    }

    public void reservarButacas(List<Integer> idsButacasSeleccionadas, Sesion s, Usuario u) {
        cr.reservarButacas(idsButacasSeleccionadas,s,u);
    }

    public void reservasUsuario(Usuario u) {
        fgui.reservasUsuario(u);
    }

    public List<InfoReserva> obtenerInfoReservas(Usuario u) {
        return cr.obtenerInfoReservas(u);
    }

    public void eliminarReserva(Usuario u, InfoReserva reservaSeleccionada) {
        cr.eliminarReserva(u,reservaSeleccionada);
    }

    //seccion comida
    public void comida(Usuario u) {
        fgui.comida(u);
    }

    public List<Comida> obtenerMenu() {
        return cc.obtenerMenu();
    }

    public boolean pedir(Usuario u, Comida comidaSeleccionada, Integer cantidad) {
        return cc.pedir(u, comidaSeleccionada, cantidad);
    }

    public List<Pedido> obtenerPedidosActivos(Usuario u) {
        return cc.obtenerPedidosActivos(u);
    }

    public void eliminarPedido(Pedido pedidoSeleccionado, Usuario u) {
        cc.eliminarPedido(pedidoSeleccionado,u);
    }

  
}
