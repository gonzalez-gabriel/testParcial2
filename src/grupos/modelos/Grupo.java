/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupos.modelos;

/**
 *
 * @author Otros
 */
public class Grupo {
    private String nombre;
    private String descripcion;
    
    public Grupo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public void mostrar(){
        System.out.println("Nombre del grupo: " + nombre + "\t Descripción: " + descripcion);
    }
    
    public void asignarNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void asignarDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public String verNombre(){
        return nombre;
    }
    
    public String verDescripcion(){
        return descripcion;
    }
}
