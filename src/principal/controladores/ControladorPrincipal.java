/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.controladores;

import autores.controladores.ControladorAutores;
import grupos.controladores.ControladorGrupos;
import interfaces.IControladorAutores;
import interfaces.IControladorGrupos;
import interfaces.IControladorLugares;
import interfaces.IControladorPrincipal;
import static interfaces.IControladorPrincipal.CONFIRMACION;
import static interfaces.IControladorPrincipal.TITULO;
import interfaces.IControladorPublicaciones;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import lugares.controladores.ControladorLugares;
import principal.vistas.VentanaPrincipal;
import publicaciones.controladores.ControladorPublicaciones;



/**
 *
 * @author Ocón Santiago Luis
 */
public class ControladorPrincipal implements IControladorPrincipal {
    private VentanaPrincipal ventana;
    
    public ControladorPrincipal(){
        this.ventana = new VentanaPrincipal(this);
        this.ventana.setTitle(TITULO);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }
    
    public static void main(String[] args) {
        IControladorPrincipal cp = new ControladorPrincipal();   
    }

    @Override
    public void btnAutoresClic(ActionEvent evt) {
        IControladorAutores ca = new ControladorAutores(this.ventana);
    }

    @Override
    public void btnSalirClic(ActionEvent evt) {
        int eleccion = JOptionPane.showOptionDialog(this.ventana, CONFIRMACION, TITULO, 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Si", "No"}, this);
        if (eleccion == JOptionPane.YES_OPTION) 
            System.exit(0);
    }

    @Override
    public void btnLugaresClic(ActionEvent evt) {
        IControladorLugares cl = new ControladorLugares(this.ventana);
    }

    @Override
    public void btnPublicacionesClic(ActionEvent evt) {
        IControladorPublicaciones cp = new ControladorPublicaciones(this.ventana);
    }

    @Override
    public void btnGruposClic(ActionEvent evt) {
        IControladorGrupos cg = new ControladorGrupos(this.ventana);
    }
}