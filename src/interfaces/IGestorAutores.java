/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import autores.modelos.Alumno;
import autores.modelos.Autor;
import autores.modelos.Cargo;
import autores.modelos.Profesor;
import java.util.ArrayList;

/**
 *
 * @author Ocón Santiago Luis
 */
public interface IGestorAutores {
    public static final String MSJ_OK = "Autor creado con éxito.";
    public static final String MSJ_REP = "Ya existe este autor.";
    public static final String MSJ_ERROR = "Error.";    
    public static final String MSJ_MOD_OK = "Autor modificado.";
    public static final String MSJ_MOD_ERROR = "Error: autor no modificado.";
    public static final String MSJ_MOD_BLANCO = "Modificación ingresada en blanco.";
    public static final String MSJ_MOD_SINAUTOR = "No existe este autor.";
    public static final String MSJ_MOD_SINPROFESOR = "No existe este profesor.";
    public static final String MSJ_MOD_SINALUMNO = "No existe este alumno.";       
    
    public String nuevoAutor(int dni, String apellidos, String nombres, Cargo cargo, String clave, String claveRepetida);
    public String nuevoAutor(int dni, String apellidos, String nombres, String cx, String clave, String claveRepetida);
    public String modificarAutor(Autor autor, String apellidos, String nombre, Cargo cargo, String clave, String claveRepetida);
    public String modificarAutor(Autor autor, String apellidos, String nombre, String cx, String clave, String claveRepetida);
    public boolean existeEsteAutor(Autor autor);
    public ArrayList<Autor> verAutores();
    public ArrayList<Profesor> verProfesores();
    public ArrayList<Alumno> verAlumnos();
    public Autor verAutor(int dni);
}
