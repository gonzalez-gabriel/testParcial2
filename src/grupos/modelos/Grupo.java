/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupos.modelos;

import autores.modelos.Autor;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Otros
 */
public class Grupo {
    private String nombre;
    private String descripcion;
    private ArrayList<MiembroEnGrupo> miembroEnGrupo;
    private boolean superAdministradores = false;
    private boolean hayMiembros = false;
    
    public Grupo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public void mostrar(){
        System.out.println("Nombre del grupo: " + nombre + "\t Descripción: " + descripcion);
        for(MiembroEnGrupo m : miembroEnGrupo)
            System.out.println("Nombre del miembro: " + m.verAutor().verApellidos()+", "+m.verAutor().verNombres()  + "\t Rol: " + m.verRol());
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
    
    public ArrayList<MiembroEnGrupo> verMiembros(){
        return miembroEnGrupo;
    }

    public void agregarMiembro(Autor autor, Rol rol){
        MiembroEnGrupo miembro = new MiembroEnGrupo(autor,this,rol);
        if(!miembroEnGrupo.contains(miembro)){
            this.hayMiembros = true;
            miembroEnGrupo.add(miembro);
        }
    }
    
    public void quitarMiembro(Autor autor){
        miembroEnGrupo.remove(autor);
        if(miembroEnGrupo.isEmpty()){
            this.hayMiembros = false;
        }
    }
    
    public boolean esSuperAdministradores(){
        for(MiembroEnGrupo m : miembroEnGrupo)
            if(m.verRol() != Rol.ADMINISTRADOR){
                return false;
            }
        return true;
    }
    
    public boolean tieneMiembros(){
        if(hayMiembros){
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Grupo other = (Grupo) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
    
}
