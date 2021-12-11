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
import grupos.modelos.Grupo;
import grupos.modelos.MiembroEnGrupo;
import java.util.ArrayList;

/**
 *
 * @author Ocón Santiago Luis
 */
public interface IGestorAutores {
    public static final String MSJ_OK = "Acción realizada con éxito.";
    public static final String MSJ_REP = "Ya existe este autor.";
    public static final String MSJ_ERROR = "Algún campo es inválido.";    
    public static final String MSJ_MOD_OK = "Autor modificado.";
    public static final String MSJ_MOD_ERROR = "Error: autor no modificado.";
    public static final String MSJ_MOD_BLANCO = "Modificación ingresada en blanco.";
    public static final String MSJ_MOD_SINAUTOR = "No existe este autor.";
    public static final String MSJ_MOD_SINPROFESOR = "No existe este profesor.";
    public static final String MSJ_MOD_SINALUMNO = "No existe este alumno.";     
    
    public static final String EXITO_P = "Profesor creado/borrado con éxito.";
    public static final String EXITO_A = "Alumno creado/borrado con éxito.";
    public static final String ERROR_DNI_P = "El DNI del profesor es incorrecto.";      
    public static final String ERROR_APELLIDOS_P = "Los apellidos del profesor son incorrectos.";  
    public static final String ERROR_NOMBRES_P = "Los nombres del profesor son incorrectos.";
    public static final String ERROR_DNI_A = "El DNI del alumno es incorrecto."; 
    public static final String ERROR_APELLIDOS_A = "Los apellidos del alumno son incorrectos.";  
    public static final String ERROR_NOMBRES_A = "Los nombres del alumno son incorrectos.";  
    public static final String ERROR_CARGO = "El cargo del profesor es incorrecto.";  
    public static final String ERROR_CX = "El cx del alumno es incorrecto.";  
    public static final String ERROR_CLAVES = "Las claves especificadas no coinciden o son incorrectas."; 
    
    public String nuevoAutor(int dni, String apellidos, String nombres, Cargo cargo, String clave, String claveRepetida);
    public String nuevoAutor(int dni, String apellidos, String nombres, String cx, String clave, String claveRepetida);
    public String modificarAutor(Autor autor, String apellidos, String nombres, Cargo cargo, String clave, String claveRepetida);
    public String modificarAutor(Autor autor, String apellidos, String nombres, String cx, String clave, String claveRepetida);
    public boolean existeEsteAutor(Autor autor);
    public ArrayList<Autor> verAutores();
    public ArrayList<Profesor> verProfesores();
    public ArrayList<Alumno> verAlumnos();
    public Autor verAutor(int dni);
    public String borrarAutor(Autor autor);
    public ArrayList<Profesor> buscarProfesores(String apellidos);
    public ArrayList<Alumno> buscarAlumnos(String apellidos);
    public boolean hayAutoresConEsteGrupo(Grupo grupo);
    public String quitarGrupos(Autor autor, ArrayList<MiembroEnGrupo> grupos);
    public String agregarGrupos(Autor autor, ArrayList<MiembroEnGrupo> grupos);    
}
