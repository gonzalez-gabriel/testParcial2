/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.event.ActionEvent;

/**
 *
 * @author Ocón Santiago Luis
 */
public interface IControladorModificarMiembros {
    public static final String TITULO = "Modificar miembros"; 
    public static final String CONFIRMACION = "¿Confirma que desea modificar los miembros de este grupo?"; 
                       
    public void btnTodosClic(ActionEvent evt);              
    public void btnNingunoClic(ActionEvent evt);                
    public void btnAceptarClic(ActionEvent evt);                      
    public void btnCancelarClic(ActionEvent evt);
}
