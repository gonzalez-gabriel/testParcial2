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
public interface IControladorAMGrupo {
    public static final String TITULO_NUEVO = "Nuevo grupo"; 
    public static final String TITULO_MODIFICAR = "Modificar grupo";
    
    public void btnGuardarClic(ActionEvent evt);                       
    public void btnCancelarClic(ActionEvent evt);                        
    public void btnModificarMiembrosClic(ActionEvent evt);
    public void txtNombrePresionarTecla(KeyEvent evt); 
    public void txtDescripcionPresionarTecla(KeyEvent evt);
    public void ventanaObtenerFoco(WindowEvent evt);
}
