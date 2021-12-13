/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupos.modelos;

import interfaces.IGestorGrupos;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ModeloTablaGrupos extends AbstractTableModel {
    private IGestorGrupos gg = GestorGrupos.crear();
    private ArrayList<String> nombreColumnas = new ArrayList<>();
    private ArrayList<Grupo> grupos = gg.verGrupos();
    
    public ModeloTablaGrupos(){
        nombreColumnas.add("Nombre");
        nombreColumnas.add("Descripción");
    }
    
    @Override
    public int getColumnCount() {
        return this.nombreColumnas.size();
    }
    
    @Override
    public int getRowCount() {
        return this.grupos.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Grupo grupo = this.grupos.get(rowIndex);
        switch(columnIndex){
            case 0 : return grupo.verNombre();
            case 1 : return grupo.verDescripcion();
            default: return grupo.verNombre();   
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return this.nombreColumnas.get(column);
    }
    
    public Grupo verGrupo(int row) {
        return this.grupos.get(row);
    }
    
    public void borrarTabla () {
        this.grupos = new ArrayList<>();
        this.fireTableDataChanged();
    }
    
    public void quitarGrupo(Grupo grupo) {
        this.grupos.remove(grupo);
        this.fireTableDataChanged();
    }
    
    public void actualizar(ArrayList<Grupo> grupos) {
        this.grupos = grupos;
        this.fireTableDataChanged();
    }
}
