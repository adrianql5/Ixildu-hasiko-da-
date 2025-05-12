/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import aplicacion.InfoReserva;
import aplicacion.Reserva;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alumnogreibd
 */
public class ModeloTablaReservas extends AbstractTableModel {
    private List<InfoReserva> reservas;

    public ModeloTablaReservas() {
        this.reservas = new ArrayList<>();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public int getRowCount() {
        return reservas.size();
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0: return "Fecha de Reserva";
            case 1: return "Nombre Sala";
            case 2: return "Nº Butacas";
            case 3: return "Película";
            case 4: return "Fecha de Sesión";
            case 5: return "Hora de Sesión";
            case 6: return "Precio Total";
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 2: return Integer.class;
            case 6: return Double.class;
            default: return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        InfoReserva reserva = reservas.get(row);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        switch (col) {
            case 0: return reserva.getFechaReserva().format(dateFormatter);
            case 1: return reserva.getNombreSala();
            case 2: return reserva.getNumeroButacas();
            case 3: return reserva.getPelicula();
            case 4: return reserva.getFechaSesion().format(dateFormatter);
            case 5: return reserva.getHoraSesion().format(timeFormatter);
            case 6: return reserva.getPrecioTotal();
            default: return null;
        }
    }

    public void setFilas(List<InfoReserva> reservas) {
        this.reservas = reservas;
        fireTableDataChanged();
    }

    public InfoReserva obtenerReserva(int i) {
        return this.reservas.get(i);
    }

    public void borrarReserva(int indice) {
        this.reservas.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public void nuevaReserva(InfoReserva r) {
        this.reservas.add(r);
        fireTableRowsInserted(this.reservas.size() - 1, this.reservas.size() - 1);
    }
}
