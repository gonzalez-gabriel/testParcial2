/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autores.modelos;

import interfaces.IGestorAutores;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Ocón Santiago Luis
 */
public class GestorAutores implements IGestorAutores {
    public static GestorAutores gestor;    
    private ArrayList<Autor> autores = new ArrayList<>();
    Comparator<Autor> comparadorAutores=(Autor autorA, Autor autorB) -> autorA.verNombres().compareTo(autorB.verNombres());    
    
    
    private GestorAutores() {
    }
    
    public static GestorAutores crear(){
        if(gestor == null){
            gestor = new GestorAutores();
        }
        return gestor;
    }

    @Override
    public String nuevoAutor(int dni, String apellidos, String nombres, Cargo cargo, String clave, String claveRepetida) {
        if((dni!= 0) && (apellidos != null) && (!apellidos.isBlank()) && (nombres !=null) && (!nombres.isBlank()) && (cargo!=null) ){
            Autor autorNuevo= new Profesor(dni,apellidos,nombres,clave,cargo);              
            if(!this.autores.contains(autorNuevo)){
                this.autores.add(autorNuevo);
                return MSJ_OK;
            }
            else
                return MSJ_REP;
        }
        else 
            return MSJ_ERROR;
    }

    @Override
    public String nuevoAutor(int dni, String apellidos, String nombres, String cx, String clave, String claveRepetida) {
        if((dni!= 0) && (apellidos != null) && (!apellidos.isBlank()) && (nombres !=null) && (!nombres.isBlank()) && (cx!=null) && (!cx.isBlank()) ){
            Autor autorNuevo= new Alumno(dni,apellidos,nombres,clave,cx);              
            if(!this.autores.contains(autorNuevo)){
                this.autores.add(autorNuevo);
                return MSJ_OK;
            }
            else
                return MSJ_REP;
        }
        else 
            return MSJ_ERROR;
    }

    @Override
    public String modificarAutor(Autor autor, String apellidos, String nombres, Cargo cargo, String clave, String claveRepetida) {
        if(autores.contains(autor)){
            if((apellidos != null) && (!apellidos.isBlank()) && (nombres !=null) && (!nombres.isBlank()) && (cargo!=null) ){
                int index = autores.indexOf(autor);
                if(index == (-1))
                    return MSJ_MOD_ERROR;
                else {
                    if(autor instanceof Profesor){
                        ((Profesor)autores.get(index)).asignarApellidos(apellidos);
                        ((Profesor)autores.get(index)).asignarNombres(nombres);
                        ((Profesor)autores.get(index)).asignarCargo(cargo);
                        ((Profesor)autores.get(index)).asignarClave(clave);
                        return MSJ_MOD_OK;
                    }
                    else
                        return MSJ_MOD_SINPROFESOR;
                }
            }
            else
                return MSJ_MOD_BLANCO;
        }
        else
            return MSJ_MOD_SINAUTOR;
    }

    @Override
    public String modificarAutor(Autor autor, String apellidos, String nombres, String cx, String clave, String claveRepetida) {
        if(autores.contains(autor)){
            if((apellidos != null) && (!apellidos.isBlank()) && (nombres !=null) && (!nombres.isBlank()) && (cx!=null) && (!cx.isBlank())){
                int indice=autores.indexOf(autor);
                if(indice == (-1))
                    return MSJ_MOD_ERROR;
                else {
                    if(autor instanceof Alumno) {
                        ((Alumno)autores.get(indice)).asignarApellidos(apellidos);
                        ((Alumno)autores.get(indice)).asignarNombres(nombres);
                        ((Alumno)autores.get(indice)).asignarCx(cx);
                        ((Alumno)autores.get(indice)).asignarClave(clave);
                        return MSJ_MOD_OK;
                    }
                    else
                        return MSJ_MOD_SINALUMNO;
                }
            }
            else
                return MSJ_MOD_BLANCO;
        }
        else
            return MSJ_MOD_SINAUTOR;
    }

    @Override
    public boolean existeEsteAutor(Autor autor) {
        if(autores.contains(autor))
            return true;
        else
            return false;
    }

    @Override
    public ArrayList<Autor> verAutores() {
        this.autores.sort(comparadorAutores);
        return this.autores; 
    }

    @Override
    public ArrayList<Profesor> verProfesores() {
        ArrayList<Profesor> profesores = new ArrayList<>();
        for(Autor a: this.autores) {
            if(a instanceof Profesor)
                profesores.add((Profesor) a);
        }
        profesores.sort(comparadorAutores);
        return profesores;
    }

    @Override
    public ArrayList<Alumno> verAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        for(Autor a: autores) {
            if(a instanceof Alumno)
                alumnos.add((Alumno) a);
        }
        alumnos.sort(comparadorAutores);
        return alumnos;
    }

    @Override
    public Autor verAutor(int dni) {
        Autor profesorBuscado = new Profesor(dni,null,null,null,null);
        Autor alumnoBuscado = new Alumno(dni,null,null,null,null);
        int index = autores.indexOf(profesorBuscado);
        if(index == (-1)){
            index = autores.indexOf(alumnoBuscado);
            if(index == (-1)){
                    return null;
            }
            else
                return autores.get(index);
        }
        else
            return autores.get(index);
    }
}
