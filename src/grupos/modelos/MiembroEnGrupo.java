/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupos.modelos;

import autores.modelos.Profesor;
import autores.modelos.Alumno;
import java.util.Objects;

/**
 *
 * @author Otros
 */
public class MiembroEnGrupo {
    private Profesor profesor;
    private Alumno alumno;
    private Grupo grupo;
    private Rol rol;
    
    public MiembroEnGrupo(Profesor profesor, Grupo grupo, Rol rol){
        this.profesor = profesor;
        this.grupo = grupo;
        this.rol = rol;
    }
    
    public MiembroEnGrupo(Alumno alumno, Grupo grupo, Rol rol){
        this.alumno = alumno;
        this.grupo = grupo;
        this.rol = rol;
    }
    
    public void asignarProfesor(Profesor profesor){
        this.profesor = profesor;
    }
    
    public void asignarAlumno(Alumno alumno){
        this.alumno = alumno;
    }
    
    public void asignarGrupo(Grupo grupo){
        this.grupo = grupo;
    }
    
    public void asignarRol(Rol rol){
        this.rol = rol;
    }
    
    public Profesor verProfesor(){
        return profesor;
    }
    
    public Alumno verAlumno(){
        return alumno;
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
        hash = 89 * hash + Objects.hashCode(this.profesor);
        hash = 89 * hash + Objects.hashCode(this.alumno);
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
        if (!Objects.equals(this.profesor, other.profesor)) {
            return false;
        }
        if (!Objects.equals(this.alumno, other.alumno)) {
            return false;
        }
        return true;
    }
    
    
}
