package gui;

import aplicacion.Pedido;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaPedidos extends AbstractTableModel {
    private List<Pedido> pedidos;

    public ModeloTablaPedidos() {
        this.pedidos = new java.util.ArrayList<>();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public int getRowCount() {
        return pedidos.size();
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0: return "Fecha";
            case 1: return "Hora";
            case 2: return "Comida";
            case 3: return "Cantidad";
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0: return LocalDate.class;  // Cambiado a LocalDate
            case 1: return LocalTime.class;  // Cambiado a LocalTime
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
        Pedido p = pedidos.get(row);
        switch (col) {
            case 0: return p.getFecha();   // Se espera que p.getFecha() devuelva un LocalDate
            case 1: return p.getHora();    // Se espera que p.getHora() devuelva un LocalTime
            case 2: return p.getNombreComida();  // Se asume que el pedido tiene acceso al nombre de la comida
            case 3: return p.getCantidad();
            default: return null;
        }
    }

    public void setFilas(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        fireTableDataChanged();
    }

    public Pedido obtenerPedido(int i) {
        return this.pedidos.get(i);
    }

    public void borrarPedido(int indice) {
        this.pedidos.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public void nuevoPedido(Pedido p) {
        this.pedidos.add(p);
        fireTableRowsInserted(this.pedidos.size() - 1, this.pedidos.size() - 1);
    }
}
