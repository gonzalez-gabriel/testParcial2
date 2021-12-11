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
public interface IControladorPublicaciones {
    public static final String PUBLICACION_NUEVA = "Nueva Publicación";
    public static final String PUBLICACION_MODIFICAR = "Modificar Publicación";
    public static final String TITULO = "Publicaciones";
    public static final String CONFIRMACION = "¿Desea borrar la publicación especificada?";    
                               
    public void btnNuevaClic(ActionEvent evt);         
    public void btnModificarClic(ActionEvent evt);          
    public void btnBorrarClic(ActionEvent evt); 
    public void btnVolverClic(ActionEvent evt);       
    public void btnBuscarClic(ActionEvent evt);
    public void ventanaObtenerFoco(WindowEvent evt); 
    public void txtTituloPresionarTecla(KeyEvent evt);
}
