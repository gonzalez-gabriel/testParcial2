/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupos.modelos;

import autores.modelos.Autor;
import java.util.Objects;

/**
 *
 * @author Ocón Santiago Luis
 */
public class MiembroEnGrupo {
    private Autor autor;
    private Grupo grupo;
    private Rol rol;
    
    public MiembroEnGrupo(Autor autor, Grupo grupo, Rol rol){
        this.autor = autor;
        this.grupo = grupo;
        this.rol = rol;
    }

    public Autor verAutor() {
        return autor;
    }

    public void asignarAutor(Autor autor) {
        this.autor = autor;
    } 
    
    public void asignarGrupo(Grupo grupo){
        this.grupo = grupo;
    }
    
    public void asignarRol(Rol rol){
        this.rol = rol;
    }
    
    public Grupo verGrupo(){
        return grupo;
    }
    
    public Rol verRol(){
        return rol;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.autor);
        hash = 89 * hash + Objects.hashCode(this.grupo);
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
        final MiembroEnGrupo other = (MiembroEnGrupo) obj;
        if (!Objects.equals(this.autor, other.autor)) {
            return false;
        }
        if (!Objects.equals(this.grupo, other.grupo)) {
            return false;
        }
        return true;
    }
    
    
}
