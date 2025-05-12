package gui;

import aplicacion.Comida;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaMenu extends AbstractTableModel {
    private List<Comida> comidas;

    public ModeloTablaMenu() {
        this.comidas = new java.util.ArrayList<>();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return comidas.size();
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0: return "Nombre";
            case 1: return "Descripción";
            case 2: return "Precio (€)";
            case 3: return "Tamaño";
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
            case 1:
            case 3: return String.class;
            case 2: return Double.class;
            default: return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Comida c = comidas.get(row);
        switch (col) {
            case 0: return c.getNombre();
            case 1: return c.getDescripcion();
            case 2: return c.getPrecio();
            case 3: return c.getTamaño();
            default: return null;
        }
    }

    public void setFilas(List<Comida> comidas) {
        this.comidas = comidas;
        fireTableDataChanged();
    }

    public Comida obtenerComida(int i) {
        return this.comidas.get(i);
    }

    public void borrarComida(int indice) {
        this.comidas.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public void nuevaComida(Comida c) {
        this.comidas.add(c);
        fireTableRowsInserted(this.comidas.size() - 1, this.comidas.size() - 1);
    }
}

