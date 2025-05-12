/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baseDatos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import aplicacion.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author alumnogreibd
 */
public class FachadaBaseDatos {
    private aplicacion.FachadaAplicacion fa;
    private java.sql.Connection conexion;
    private DAOComidas daoComidas;
    private DAOReservas daoReservas;
    private DAOSesiones daoSesiones;
    private DAOUsuarios daoUsuarios;
    private DAOValoraciones daoValoraciones;
    
    public FachadaBaseDatos (aplicacion.FachadaAplicacion fa){
        
        Properties configuracion = new Properties();
        this.fa=fa;
        FileInputStream arqConfiguracion;

        try {
            arqConfiguracion = new FileInputStream("baseDatos.properties");
            configuracion.load(arqConfiguracion);
            arqConfiguracion.close();

            Properties usuario = new  Properties();
     

            String gestor = configuracion.getProperty("gestor");

            usuario.setProperty("user", configuracion.getProperty("usuario"));
            usuario.setProperty("password", configuracion.getProperty("clave"));
            
            System.out.println("jdbc:"+gestor+"://"+
                    configuracion.getProperty("servidor")+":"+
                    configuracion.getProperty("puerto")+"/"+
                    configuracion.getProperty("baseDatos"));
            
            System.out.println(usuario);
            
            this.conexion=java.sql.DriverManager.getConnection("jdbc:"+gestor+"://"+
                    configuracion.getProperty("servidor")+":"+
                    configuracion.getProperty("puerto")+"/"+
                    configuracion.getProperty("baseDatos"),
                    usuario);
            
            

            daoComidas = new DAOComidas(conexion, fa);
            daoReservas = new DAOReservas(conexion, fa);
            daoSesiones = new DAOSesiones(conexion,fa);
            daoUsuarios = new DAOUsuarios(conexion, fa);
            daoValoraciones = new DAOValoraciones(conexion, fa);
          


        } catch (FileNotFoundException f){
            System.out.println(f.getMessage());
            fa.muestraExcepcion(f.getMessage());
        } catch (IOException i){
            System.out.println(i.getMessage());
            fa.muestraExcepcion(i.getMessage());
        } 
        catch (java.sql.SQLException e){
            System.out.println(e.getMessage());
            fa.muestraExcepcion(e.getMessage());
            System.out.println("ERROR");
            System.out.flush();
        }

    }
    
    //Sección usuarios
    public Usuario obtenerUsuarioPorID(String idUsuario){
        return daoUsuarios.obtenerUsuarioPorID(idUsuario);
    }

    public void insertarUsuario(Usuario u) {
        daoUsuarios.insertarUsuario(u);
    }

    public void editarUsuario(String id, String nombre, String ap1, String ap2, String email, String contraseña, LocalDate fechaNacimiento, TipoUsuario tipo) {
        daoUsuarios.editarUsuario(id,  nombre,  ap1,  ap2,  email,  contraseña,  fechaNacimiento,  tipo);
    }

    public void eliminarCuenta(Usuario u) {
        daoUsuarios.eliminarCuenta(u.getIdUsuario());
    }

    //Seccion sesiones
    public List<Sesion> obtenerSesiones(String titulo, LocalDate fecha, LocalTime hora) {
        return daoSesiones.obtenerSesiones(titulo, fecha, hora);
    }

    public Pelicula obtenerPelicula(String pelicula) {
        return daoSesiones.obtenerPelicula(pelicula);
    }

    //Seccion valoraciones
    public void guardarValoracion(Usuario u, Pelicula p, Valoracion v) {
        daoValoraciones.guardarValoracion(u,p,v);
    }

    public List<Valoracion> obtenerValoraciones(String idUsuario) {
        return daoValoraciones.obtenerValoraciones(idUsuario);
    }

    public void actualizarValoracion(String idUsuario, String titulo, String opinion, int puntuacion) {
        daoValoraciones.actualizarValoraciones(idUsuario,titulo,opinion,puntuacion);
    }

    public void eliminarValoracion(String u, String pelicula) {
        daoValoraciones.eliminarValoracion(u,pelicula);
    }

    public List<Opinion> obtenerOpiniones(String titulo) {
        return daoValoraciones.obtenerOpiniones(titulo);
    }

    public List<Reserva> obtenerReservas(Sesion s) {
       return daoReservas.obtenerReservas(s.getIdSesion());
    }

    public List<Equipo> obtenerEquipo(Sesion s) {
        return daoReservas.obtenerEquipo(s);
    }

    public void reservarButaca(Integer id, Integer idSesion, String idUsuario) {
        daoReservas.reservarButaca(id,idSesion,idUsuario);
    }

    public List<InfoReserva> obtenerInfoReservas(Usuario u) {
        return daoReservas.obtenerInfoReservas(u);
    }

    public void eliminarReserva(Usuario u, InfoReserva reservaSeleccionada) {
        daoReservas.eliminarReserva(u.getIdUsuario(), reservaSeleccionada);
    }

    //Seccion comida
    public List<Comida> obtenerMenu() {
        return daoComidas.obtenerMenu();
    }

    public void pedir(String idUsuario, int idComida, Integer cantidad) {
        daoComidas.pedir(idUsuario, idComida, cantidad);
    }

    public List<Pedido> obtenerPedidosActivos(String idUsuario) {
        return daoComidas.obtenerPedidosActivos(idUsuario);
    }

    public void eliminarPedido(Pedido pedidoSeleccionado, String idUsuario) {
        daoComidas.eliminarPedido(pedidoSeleccionado, idUsuario);
    }
}