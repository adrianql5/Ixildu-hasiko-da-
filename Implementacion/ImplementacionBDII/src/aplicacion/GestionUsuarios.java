/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

import baseDatos.FachadaBaseDatos;
import gui.FachadaGui;
import java.time.LocalDate;

/**
 *
 * @author alumnogreibd
 */
public class GestionUsuarios {
    FachadaGui fgui;
    FachadaBaseDatos fbd;
    
    public GestionUsuarios(FachadaGui fgui, FachadaBaseDatos fbd){
     this.fgui=fgui;
     this.fbd=fbd;
    }

    public Usuario comprobarAutentificacion(String idUsuario, String contraseña) {
        // 1. Buscar usuario por ID
        Usuario u = fbd.obtenerUsuarioPorID(idUsuario);// Debes tener este método
        
        // 2. Verificar contraseña
        if (u != null && PasswordUtil.checkPassword(contraseña, u.getContraseña())) {
            return u;
        } else {
            return null;
        }
    }


    void nuevaCuenta() {
        fgui.nuevaCuenta();
    }

    Boolean crearCuenta(String id, String nombre, String ap1, String ap2, String email, String contraseña, LocalDate fechaNacimiento, TipoUsuario tipo) {
    // Hashear la contraseña antes de crear el objeto Usuario
        String hashedPassword = PasswordUtil.hashPassword(contraseña);

        Usuario us = new Usuario(id, nombre, ap1, ap2, email, hashedPassword, fechaNacimiento, tipo);

        fbd.insertarUsuario(us);  // Guarda el hash en la base de datos

       
        Usuario v = comprobarAutentificacion( us.getIdUsuario(), contraseña);  
        if (v != null) {
            return true;
        } else {
            return false;
        }
    }


    void iniciarAp(Usuario u) {
        fgui.iniciarAp(u);
    }
    
    void configuracionUsuario(Usuario u){
        fgui.configuracionUsuario(u);
    }

    void editarUsuario(Usuario u, String id, String nombre, String ap1, String ap2, String email, String contraseña, LocalDate fechaNacimiento, TipoUsuario tipo){
        u.setIdUsuario(id);
        u.setNombre(nombre);
        u.setAp1(ap1);
        u.setAp2(ap2);
        u.setEmail(email);
        u.setContraseña(contraseña);
        u.setFechaNacimiento(fechaNacimiento);
        u.setTipoUsuario(tipo);
        fbd.editarUsuario(id,  nombre,  ap1,  ap2,  email,  contraseña,  fechaNacimiento,  tipo);
    }

    void eliminarCuenta(Usuario u) {
        fbd.eliminarCuenta(u);
    }

    
}
