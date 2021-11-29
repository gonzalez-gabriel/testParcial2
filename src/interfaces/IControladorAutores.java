/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

/**
 *
 * @author Ocón Santiago Luis
 */
public interface IControladorAutores {
    public static final String PROFESOR_NUEVO = "Nuevo Profesor";
    public static final String PROFESOR_MODIFICAR = "Modificar Profesor";
    public static final String ALUMNO_NUEVO = "Nuevo Alumno";
    public static final String ALUMNO_MODIFICAR = "Modificar Alumno";
    public static final String TITULO = "Autores";
    public static final String CONFIRMACION_PROFESOR = "¿Desea borrar el profesor especificado?";
    public static final String CONFIRMACION_ALUMNO = "¿Desea borrar el alumno especificado?";
    
    public void btnNuevoProfesorClic(ActionEvent evt);
    public void btnNuevoAlumnoClic(ActionEvent evt);
    public void btnModificarProfesorClic(ActionEvent evt);
    public void btnModificarAlumnoClic(ActionEvent evt);
    public void btnBorrarProfesorClic(ActionEvent evt);
    public void btnBorrarAlumnoClic(ActionEvent evt);
    public void btnVolverClic(ActionEvent evt);
    public void btnBuscarProfesorClic(ActionEvent evt);
    public void btnBuscarAlumnoClic(ActionEvent evt);
    public void ventanaObtenerFoco(WindowEvent evt);
    public void txtApellidosProfesorPresionarTecla(KeyEvent evt);
    public void txtApellidosAlumnoPresionarTecla(KeyEvent evt);
}
