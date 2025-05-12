/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacion;

/**
 *
 * @author alumnogreibd
 */
public class Comida {
    private int idComida;
    private String nombre;
    private String descripcion;
    private double precio;
    private String tamaño;
    private int stockDisponible;

    // Constructor
    public Comida(int idComida, String nombre, String descripcion, double precio, String tamaño, int stockDisponible) {
        this.idComida = idComida;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tamaño = tamaño;
        this.stockDisponible = stockDisponible;
    }

    // Getters y Setters
    public int getIdComida() {
        return idComida;
    }

    public void setIdComida(int idComida) {
        this.idComida = idComida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    @Override
    public String toString() {
        return nombre + " (" + tamaño + ") - " + String.format("%.2f€", precio);
    }
}

