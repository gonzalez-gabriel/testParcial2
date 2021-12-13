/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicaciones.modelos;

import autores.modelos.GestorAutores;
import grupos.modelos.GestorGrupos;
import interfaces.IGestorAutores;
import interfaces.IGestorGrupos;
import interfaces.IGestorPublicaciones;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ModeloTablaPublicaciones extends AbstractTableModel {
    private IGestorPublicaciones gp = GestorPublicaciones.crear();
    private IGestorAutores ga = GestorAutores.crear();
    private IGestorGrupos gg = GestorGrupos.crear();     
    private ArrayList<String> nombreColumnas = new ArrayList<>();
    private ArrayList<Publicacion> publicaciones = new ArrayList<>() ;
    
    public ModeloTablaPublicaciones() { 
        this.nombreColumnas.add("Título");
        this.nombreColumnas.add("Autor");
        this.nombreColumnas.add("Año");
    }
    
    @Override
    public int getColumnCount() {
        return this.nombreColumnas.size();    
    }

    @Override
    public int getRowCount() { 
        return this.publicaciones.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Publicacion p = this.publicaciones.get(rowIndex);
        switch(columnIndex){
            case 0 : return p.verTitulo();
            case 1 : return p.verMiembroEnGrupo().verAutor().verApellidos();
            default: return p.verFecha().getYear();
        }  
    }

    @Override
    public String getColumnName(int column) {
        return this.nombreColumnas.get(column);
    }
    
    public Publicacion verPublicacion(int row) {
        return this.publicaciones.get(row);
    }
    
    public void actualizar() {
        this.publicaciones = gp.verPublicaciones();
        this.fireTableDataChanged();
    }
    
    public void buscarPublicacion (String titulo) {
        this.publicaciones = gp.buscarPublicaciones(titulo);
        this.fireTableDataChanged();
    }
}
