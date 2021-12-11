/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autores.modelos;

import grupos.modelos.GestorGrupos;
import grupos.modelos.Grupo;
import grupos.modelos.MiembroEnGrupo;
import grupos.modelos.Rol;
import interfaces.IGestorGrupos;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ModeloTablaAutorGrupos extends AbstractTableModel {
    private ArrayList<String> nombreColumnas = new ArrayList<>();
    private ArrayList<Grupo> grupos = new ArrayList<>();
    private IGestorGrupos gg = GestorGrupos.crear();
    private Autor autor;
    
    public ModeloTablaAutorGrupos () {
    }
    
    public ModeloTablaAutorGrupos (Autor autor) {
        this.autor = autor;
        this.nombreColumnas.add("Nombre");
        this.nombreColumnas.add("Rol");
        
        for(MiembroEnGrupo m : this.autor.verGrupos()){
            if(!this.grupos.contains(m.verGrupo()))
                this.grupos.add(m.verGrupo());
        }
    }
    
    @Override
    public int getRowCount() {
        return this.grupos.size();
    }

    @Override
    public int getColumnCount() {
        return this.nombreColumnas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Grupo g = this.grupos.get(rowIndex);
        
        switch(columnIndex) {
            case 0 : return g.verNombre();
            default: return verRol(g);
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return this.nombreColumnas.get(column);
    }
    
    private Rol verRol (Grupo grupo) {
        for(MiembroEnGrupo m : this.autor.verGrupos()){
            if(m.verGrupo().equals(grupo))
                return m.verRol();
        }
        
        return null;
    }
    
    public void actualizar() {
        this.grupos = new ArrayList<>();
        
        for(MiembroEnGrupo m : this.autor.verGrupos()){
            if(!this.grupos.contains(m.verGrupo()))
                this.grupos.add(m.verGrupo());
        }
        this.fireTableDataChanged();
    }
}
