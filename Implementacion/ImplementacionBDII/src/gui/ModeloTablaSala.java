/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.Equipo;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaSala extends AbstractTableModel {
    private List<Equipo> equipos;

    public ModeloTablaSala() {
        this.equipos = new ArrayList<>();
    }

    public int getColumnCount() {
        return 6;
    }

    public int getRowCount() {
        return equipos.size();
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0: return "Nombre";
            case 1: return "Tipo";
            case 2: return "Marca";
            case 3: return "Modelo";
            case 4: return "Fecha Adquisición";
            case 5: return "Precio";
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 4: return String.class; // fecha como texto formateado
            case 5: return Double.class; // supondremos precio como Double
            default: return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Equipo equipo = equipos.get(row);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        switch (col) {
            case 0: return equipo.getNombreEquipo();
            case 1: return equipo.getTipo();
            case 2: return equipo.getMarca();
            case 3: return equipo.getModelo();
            case 4: return equipo.getFechaAdquisicion().format(formatter);
            case 5: return equipo.getPrecio();
            default: return null;
        }
    }

    public void setFilas(List<Equipo> equipos) {
        this.equipos = equipos;
        fireTableDataChanged();
    }

    public Equipo obtenerEquipo(int i) {
        return this.equipos.get(i);
    }

    public void borrarEquipo(int indice) {
        this.equipos.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public void nuevoEquipo(Equipo e) {
        this.equipos.add(e);
        fireTableRowsInserted(this.equipos.size() - 1, this.equipos.size() - 1);
    }
}

