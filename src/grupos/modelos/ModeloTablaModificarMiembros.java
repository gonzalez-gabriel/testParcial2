/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupos.modelos;

import autores.modelos.Autor;
import autores.modelos.GestorAutores;
import interfaces.IGestorAutores;
import interfaces.IGestorGrupos;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ModeloTablaModificarMiembros extends AbstractTableModel {
    private IGestorGrupos gg = GestorGrupos.crear();
    private IGestorAutores ga = GestorAutores.crear();
    private ArrayList<String> nombreColumnas = new ArrayList<>();
    private ArrayList<MiembroEnGrupo> miembros= new ArrayList<>();
    private Grupo grupoModificar;

    public ModeloTablaModificarMiembros(Grupo grupoModificar){
        nombreColumnas.add("Nombre");
        nombreColumnas.add("Rol");
        this.grupoModificar=grupoModificar;
        this.miembrosEnGrupo(grupoModificar);
    }
    
    public void miembrosEnGrupo(Grupo grupoModificar){
        Grupo grupoAuxiliar = new Grupo("Grupo Auxiliar","Descripcion Auxiliar");
        boolean mismoAutor=false;
        
        for(Autor a: ga.verAutores()){
            for(int indiceMiembro=0;indiceMiembro<grupoModificar.verMiembros().size();indiceMiembro++){
                if((a.verApellidos().concat(a.verNombres())).equals((grupoModificar.verMiembros().get(indiceMiembro).verAutor().verApellidos().concat(grupoModificar.verMiembros().get(indiceMiembro).verAutor().verNombres()))))
                    mismoAutor=true;
            }
            if(!mismoAutor)
                this.miembros.add(new MiembroEnGrupo(a,grupoAuxiliar,null));
            else
                mismoAutor = false;
        }
        
        
        for(int indiceMiembro=0;indiceMiembro<grupoModificar.verMiembros().size();indiceMiembro++){
            this.miembros.add(grupoModificar.verMiembros().get(indiceMiembro));
        }
        
        Comparator<MiembroEnGrupo> miembroComparador=(MiembroEnGrupo miembro1, MiembroEnGrupo miembro2) -> (miembro1.verAutor().verApellidos().concat(miembro1.verAutor().verNombres())).compareTo(miembro2.verAutor().verApellidos().concat(miembro2.verAutor().verNombres()));
        this.miembros.sort(miembroComparador);
    }
    
    @Override
    public int getRowCount() {
        return ga.verAutores().size();
    }

    @Override
    public int getColumnCount() {
        return this.nombreColumnas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0 : return ga.verAutores().get(rowIndex).verApellidos().concat(", ").concat(ga.verAutores().get(rowIndex).verNombres());
            case 1 : return this.miembros.get(rowIndex).verRol();
            default: return ga.verAutores().get(rowIndex).verApellidos().concat(", ").concat(ga.verAutores().get(rowIndex).verNombres());  
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return this.nombreColumnas.get(column);
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch(column){
            case 0: return String.class;
            default: return Rol.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 1)
            return true;
        else
            return false;
    }

    @Override
    public void setValueAt(Object valor, int rowIndex, int columnIndex) {
        MiembroEnGrupo meg=this.miembros.get(rowIndex);
        Autor a=ga.verAutores().get(rowIndex);
        
        switch(columnIndex){
            case 0: String cadena=(String)valor;
                    String[] cadenas=cadena.split(", ");
                    a.asignarApellidos(cadenas[0]);
                    a.asignarNombres(cadenas[1]);
            default:meg.asignarRol((Rol)valor);
        }        
    }
    
    public Autor verAutor(int row) {
        return this.ga.verAutores().get(row);
    }
    
    public Rol verRol(int row) {
        return this.miembros.get(row).verRol();
    }
}
