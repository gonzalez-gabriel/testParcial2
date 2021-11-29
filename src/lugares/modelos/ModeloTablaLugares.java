/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lugares.modelos;

import interfaces.IGestorLugares;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ModeloTablaLugares extends AbstractTableModel {
    private IGestorLugares gl = GestorLugares.crear();
    private ArrayList<String> nombreColumnas = new ArrayList<>();
    private ArrayList<Lugar> lugares = new ArrayList<>() ;
    
    public ModeloTablaLugares(){ 
        this.nombreColumnas.add("Nombre");
    }
    
    @Override
    public int getRowCount() {
        return this.lugares.size();
    }

    @Override
    public int getColumnCount() {
        return this.nombreColumnas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lugar l = this.lugares.get(rowIndex);
        switch(columnIndex){
            case 0 : return l.verNombre();
            default: return l.verNombre();   
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return this.nombreColumnas.get(column);
    }
    
    public Lugar verLugar(int row) {
        return this.lugares.get(row);
    }
    
    public void actualizar(ArrayList<Lugar> lugares) {
        this.lugares = lugares;
        this.fireTableDataChanged();
    }
    
    public void borrarTabla () {
        this.lugares = new ArrayList<>();
        this.fireTableDataChanged();
    }
    
    public void quitarLugar (Lugar lugar) {
        this.lugares.remove(lugar);
        this.fireTableDataChanged();
    }
}
