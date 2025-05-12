/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.Opinion;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaOpiniones extends AbstractTableModel {
    private List<Opinion> opiniones;

    public ModeloTablaOpiniones() {
        this.opiniones = new java.util.ArrayList<>();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return opiniones.size();
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0: return "IdUsuario";
            case 1: return "Opinión";
            case 2: return "Puntuación";
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
            case 1: return String.class;
            case 2: return Integer.class;
            default: return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Opinion op = opiniones.get(row);
        switch (col) {
            case 0: return op.getIdUsuario();
            case 1: return op.getOpinion();
            case 2: return op.getPuntuacion();
            default: return null;
        }
    }

    public void setFilas(List<Opinion> opiniones) {
        this.opiniones = opiniones;
        fireTableDataChanged();
    }

    public Opinion obtenerOpinion(int i) {
        return this.opiniones.get(i);
    }

    public void borrarOpinion(int indice) {
        this.opiniones.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public void nuevaOpinion(Opinion op) {
        this.opiniones.add(op);
        fireTableRowsInserted(this.opiniones.size() - 1, this.opiniones.size() - 1);
    }
}
