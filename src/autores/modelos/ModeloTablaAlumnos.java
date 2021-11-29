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
public class ModeloTablaAlumnos extends AbstractTableModel {
    private IGestorAutores ga = GestorAutores.crear();
    private ArrayList<String> nombreColumnas = new ArrayList<>();
    private ArrayList<Alumno> alumnos = new ArrayList<>() ;
    
    public ModeloTablaAlumnos() { 
        this.nombreColumnas.add("DNI");
        this.nombreColumnas.add("Apellidos");
        this.nombreColumnas.add("Nombres");
        this.nombreColumnas.add("CX");
        this.alumnos = ga.verAlumnos();
    }
    
    @Override
    public int getRowCount() { 
        return this.alumnos.size();
    }
    
    @Override
    public int getColumnCount() {
        return this.nombreColumnas.size();    
    }   

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Alumno a = this.alumnos.get(rowIndex);
        switch(columnIndex){
            case 0 : return a.verDni();
            case 1 : return a.verApellidos();
            case 2 : return a.verNombres();
            default: return a.verCx();   
        }  
    }

    @Override
    public String getColumnName(int column) {
        return this.nombreColumnas.get(column);
    }
    
    public Alumno verAlumno(int row) {
        return this.alumnos.get(row);
    }
    
    public void actualizar() {
        this.alumnos = ga.verAlumnos();
        this.fireTableDataChanged();
    }
    
    public void borrarTabla () {
        this.alumnos.removeAll(this.alumnos); 
        this.fireTableDataChanged();
    }
    
    public void quitarAlumno (Alumno alumno) {
        this.alumnos.remove(alumno);
        this.fireTableDataChanged();
    }
    
    public void buscarAlumno (String apellidos) {
        this.alumnos = ga.buscarAlumnos(apellidos);
        this.fireTableDataChanged();
    }
}
