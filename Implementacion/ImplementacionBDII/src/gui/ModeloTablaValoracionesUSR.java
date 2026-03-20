package gui;

import aplicacion.Valoracion;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaValoracionesUSR extends AbstractTableModel {
    private java.util.List<Valoracion> valoraciones;

    public ModeloTablaValoracionesUSR() {
        this.valoraciones = new java.util.ArrayList<>();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return valoraciones.size();
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0: return "Fecha";
            case 1: return "Película";
            case 2: return "Comentario";
            case 3: return "Puntuación";
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
            case 1:
            case 2: return String.class;
            case 3: return Integer.class;
            default: return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Valoracion val = valoraciones.get(row);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        switch (col) {
            case 0: return val.getFecha().format(formatter);
            case 1: return val.getPelicula();
            case 2: return val.getOpinion();
            case 3: return val.getPuntuacion();
            default: return null;
        }
    }

    public void setFilas(List<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
        fireTableDataChanged();
    }

    public Valoracion obtenerValoracion(int i) {
        return this.valoraciones.get(i);
    }

    public void borrarValoracion(int indice) {
        this.valoraciones.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public void nuevaValoracion(Valoracion v) {
        this.valoraciones.add(v);
        fireTableRowsInserted(this.valoraciones.size() - 1, this.valoraciones.size() - 1);
    }
}
