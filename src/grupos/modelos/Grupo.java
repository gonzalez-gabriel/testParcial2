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
 * @author Ocón Santiago Luis
 */
public class Grupo {
    private String nombre;
    private String descripcion;
    private ArrayList<MiembroEnGrupo> miembroEnGrupo;
    
    public Grupo(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.miembroEnGrupo = new ArrayList<>();
    }
    
    public void mostrar(){
        System.out.print("\nGRUPO: " + this.nombre + "\t\tDESCRIPCION: " + this.descripcion);
        if(this.tieneMiembros()){
            System.out.println("\n\t___MIEMBROS EN EL GRUPO___ ");
            for(MiembroEnGrupo m : this.miembroEnGrupo)
                System.out.println("Nombre: "+m.verAutor().verApellidos()+", "+m.verAutor().verNombres()+"\t Rol: "+m.verRol());
        }
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
        if(this.esSuperAdministradores())
            miembro.asignarRol(Rol.ADMINISTRADOR);
        if(!this.miembroEnGrupo.contains(miembro)){
            this.miembroEnGrupo.add(miembro);
            autor.agregarGrupo(this, rol);
        }
    }
    
    public void quitarMiembro(Autor autor){
        MiembroEnGrupo miembro = new MiembroEnGrupo(autor,this,null);
        if(this.miembroEnGrupo.contains(miembro)){
            this.miembroEnGrupo.remove(miembro);
            autor.quitarGrupo(this);
        }
    }
    
    public boolean esSuperAdministradores(){
        if(this.equals(new Grupo("Super Administradores",null))){
            return true;
        }
        return false;
    }
    
    public boolean tieneMiembros(){
        if(this.miembroEnGrupo.isEmpty()){
            return false;
        }
        else{            
            return true;
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
