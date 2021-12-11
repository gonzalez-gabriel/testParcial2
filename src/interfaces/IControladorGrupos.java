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
public interface IControladorGrupos {
    public static final String TITULO = "Grupos";
    public static final String CONFIRMACION = "¿Desea borrar el grupo?";
    public static final String TOOL_TIP_TEXT_SUPER_ADMINISTRADOR = "Todos los grupos definidos";
    public static final String TOOL_TIP_TEXT_AUTOR = "Sólo los grupos del autor";
    
    public void btnNuevoClic(ActionEvent evt);        
    public void btnModificarClic(ActionEvent evt);  
    public void btnBorrarClic(ActionEvent evt);
    public void btnVolverClic(ActionEvent evt);           
    public void btnBuscarClic(ActionEvent evt);
    public void ventanaObtenerFoco(WindowEvent evt);
    public void txtNombrePresionarTecla(KeyEvent evt);
}
