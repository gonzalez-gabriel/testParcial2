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
public interface IControladorPrincipal {
    public static final String TITULO = "Repositorios";
    public static final String CONFIRMACION = "¿Desea terminar la sesión?";
    
    public void btnLugaresClic(ActionEvent evt);
    public void btnAutoresClic(ActionEvent evt);
    public void btnSalirClic(ActionEvent evt);
    public void btnPublicacionesClic(ActionEvent evt);
    public void btnGruposClic(ActionEvent evt);
}
