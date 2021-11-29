/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 *
 * @author Ocón Santiago Luis
 */
public interface IControladorAMAlumno {
    public static final String TITULO_NUEVO = "Nuevo alumno";
    public static final String TITULO_MODIFICAR = "Modificar alumno";
    
    public void btnGuardarClic(ActionEvent evt);
    public void btnCancelarClic(ActionEvent evt);
    public void txtApellidosPresionarTecla(KeyEvent evt);
    public void txtNombresPresionarTecla(KeyEvent evt);    
    public void txtDocumentoPresionarTecla(KeyEvent evt);
    public void txtCXPresionarTecla(KeyEvent evt);
    public void passClavePresionarTecla(KeyEvent evt);
    public void passRepetirClavePresionarTecla(KeyEvent evt);
}
