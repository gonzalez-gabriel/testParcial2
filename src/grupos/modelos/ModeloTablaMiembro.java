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
public class ModeloTablaMiembro extends AbstractTableModel {
    private IGestorGrupos gg = GestorGrupos.crear();
    private ArrayList<String> nombreColumnas = new ArrayList<>();
    private ArrayList<MiembroEnGrupo>  miembros = new ArrayList<>();
    
    public ModeloTablaMiembro(){
        nombreColumnas.add("Nombre");
        nombreColumnas.add("Rol");
    }
    
    public void miembrosEnGrupo(Grupo grupoModificar){
        miembros = gg.verGrupo(grupoModificar.verNombre()).verMiembros();
    }
    
    @Override
    public int getColumnCount() {
        return this.nombreColumnas.size();
    }
    
    @Override
    public int getRowCount() {
        return this.miembros.size();
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MiembroEnGrupo meg = this.miembros.get(rowIndex);
        switch(columnIndex){
            case 0 : return meg.verAutor().verApellidos().concat(", ").concat(meg.verAutor().verNombres());
            case 1 : return meg.verRol();
            default: return meg.verAutor().verApellidos().concat(", ").concat(meg.verAutor().verNombres());   
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return this.nombreColumnas.get(column);
    }
    
    public MiembroEnGrupo verMimebroEnGrupo(int fila) {
        return this.miembros.get(fila);
    }
    
    public void actualizar(ArrayList<MiembroEnGrupo> miembros) {
        this.miembros = miembros;
        this.fireTableDataChanged();
    }
    
    public void borrarTabla () {
        this.miembros = new ArrayList<>();
        this.fireTableDataChanged();
    }
    
    public void quitarMiembro (MiembroEnGrupo miembro) {
        this.miembros.remove(miembro);
        this.fireTableDataChanged();
    }
}
