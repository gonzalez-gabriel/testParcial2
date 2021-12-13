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
public interface IControladorAMPublicacion {
    public static final String TITULO_NUEVA = "Nueva publicación"; 
    public static final String TITULO_MODIFICAR = "Modificar publicación";
    public static final String SALIR = "¿Desea vover a la anterior ventana?";
    
    public void btnGuardarClic(ActionEvent evt);          
    public void btnCancelarClic(ActionEvent evt);
    public void txtTituloPresionarTecla(KeyEvent evt);                   
    public void btnTodasLasPalabrasClavesClic(ActionEvent evt);                   
    public void btnNingunaPalabraClaveClic(ActionEvent evt);
    public void btnAbrirClic(ActionEvent evt);
}
