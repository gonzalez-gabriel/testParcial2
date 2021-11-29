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
public interface IControladorALugar {
    public static final String TITULO = "Nueva palabra clave";
    
    public void btnGuardarClic(ActionEvent evt);
    public void btnCancelarClic(ActionEvent evt);
    public void txtNombrePresionarTecla(KeyEvent evt);
}
