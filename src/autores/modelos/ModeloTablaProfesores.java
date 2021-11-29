/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autores.modelos;

import interfaces.IGestorAutores;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ModeloTablaProfesores extends AbstractTableModel{
    private IGestorAutores ga = GestorAutores.crear();
    private ArrayList<String> nombreColumnas = new ArrayList<>();
    private ArrayList<Profesor> profesores = new ArrayList<>() ;
    
    public ModeloTablaProfesores() { 
        this.nombreColumnas.add("DNI");
        this.nombreColumnas.add("Apellidos");
        this.nombreColumnas.add("Nombres");
        this.nombreColumnas.add("Cargo");
        this.profesores = ga.verProfesores();
    }
    
    @Override
    public int getRowCount() {
        return this.profesores.size();
    }

    @Override
    public int getColumnCount() {
        return this.nombreColumnas.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Profesor p = this.profesores.get(rowIndex);
        switch(columnIndex){
            case 0 : return p.verDni();
            case 1 : return p.verApellidos();
            case 2 : return p.verNombres();
            default: return p.verCargo();   
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return this.nombreColumnas.get(column);
    }
    
    public Profesor verProfesor(int row) {
        return this.profesores.get(row);
    }
    
    public void actualizar() {
        this.profesores = ga.verProfesores();
        this.fireTableDataChanged();
    }
    
    public void borrarTabla() {
        this.profesores.removeAll(this.profesores); 
        this.fireTableDataChanged();
    }
    
    public void quitarProfesor(Profesor profesor) {
        this.profesores.remove(profesor);
        this.fireTableDataChanged();
    }
    
    public void buscarProfesor (String apellidos) {
        this.profesores = ga.buscarProfesores(apellidos);
        this.fireTableDataChanged();
    }
}
