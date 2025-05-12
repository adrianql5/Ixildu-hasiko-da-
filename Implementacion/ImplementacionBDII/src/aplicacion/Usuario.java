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
import java.time.LocalDate;

public class Usuario {
    private String idUsuario;
    private String nombre;
    private String ap1;
    private String ap2;
    private String email;
    private String contraseña;
    private LocalDate fechaNacimiento;
    private TipoUsuario tipo;

    // Constructor
    public Usuario(String idUsuario, String nombre, String ap1, String ap2,
                   String email, String contraseña, LocalDate fechaNacimiento, TipoUsuario tipo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.ap1 = ap1;
        this.ap2 = ap2;
        this.email = email;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.tipo = tipo;
    }

    // Getters
    public String getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAp1() {
        return ap1;
    }

    public String getAp2() {
        return ap2;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public TipoUsuario getTipoUsuario() {
        return tipo;
    }

    // Setters
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAp1(String ap1) {
        this.ap1 = ap1;
    }

    public void setAp2(String ap2) {
        this.ap2 = ap2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTipoUsuario(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}

