/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.Sesion;
import aplicacion.TipoUsuario;
import aplicacion.Usuario;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaSesiones extends AbstractTableModel {
    private java.util.List<Sesion> sesiones;

    public ModeloTablaSesiones(){
        this.sesiones=new java.util.ArrayList<Sesion>();
    }

    public int getColumnCount (){
        return 5;
    }

    public int getRowCount(){
        return sesiones.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "Película"; break;
            case 1: nombre= "Sala"; break;
            case 2: nombre="Fecha"; break;
            case 3: nombre="Hora"; break;
            case 4: nombre="Duración"; break;
        }
        return nombre;
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0: 
            case 1: 
            case 2: 
            case 3: 
            case 4: return String.class;
            default: return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Sesion sesion = sesiones.get(row);
        DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");

        switch (col) {
            case 0: return sesion.getPelicula();
            case 1: return sesion.getSala();
            case 2: return sesion.getFecha().format(fechaFormatter);
            case 3: return sesion.getHoraInicio().format(horaFormatter);
            case 4: return sesion.getDuracion() + " min";
            default: return null;
        }
    }

    public void setFilas(List<Sesion> sesiones) {
        this.sesiones = sesiones;
        fireTableDataChanged();
    }

    public Sesion obtenerSesion(int i) {
        return this.sesiones.get(i);
    }

    public void borrarSesion(int indice) {
        this.sesiones.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public void nuevaSesion(Sesion s) {
        this.sesiones.add(s);
        fireTableRowsInserted(this.sesiones.size() - 1, this.sesiones.size() - 1);
    }
}
