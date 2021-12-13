/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicaciones.modelos;

import interfaces.IGestorPalabrasClaves;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import palabrasclaves.modelos.GestorPalabrasClaves;
import palabrasclaves.modelos.PalabraClave;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ModeloTablaPalabrasClaves extends AbstractTableModel {
    private IGestorPalabrasClaves gpc = GestorPalabrasClaves.crear();
    private ArrayList<PalabraClave> palabras = gpc.verPalabrasClaves();
    private ArrayList<String> nombreColumnas = new ArrayList<>();    
    
    public ModeloTablaPalabrasClaves() { 
        this.nombreColumnas.add("Nombre");
    }
    
    @Override
    public int getColumnCount() {
        return this.nombreColumnas.size();    
    }

    @Override
    public int getRowCount() { 
        return this.palabras.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PalabraClave p = this.palabras.get(rowIndex);
        switch(columnIndex){
            case 0 : return p.verNombre();
            default: return p.verNombre();   
        }  
    }

    @Override
    public String getColumnName(int column) {
        return this.nombreColumnas.get(column);
    }
    
    public PalabraClave verPalabraClave(int row) {
        return this.palabras.get(row);
    }
    
    public void actualizar() {
        this.palabras = gpc.verPalabrasClaves();;
        this.fireTableDataChanged();
    }
}
