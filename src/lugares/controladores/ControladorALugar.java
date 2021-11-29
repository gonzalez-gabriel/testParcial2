/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lugares.controladores;

import interfaces.IControladorALugar;
import interfaces.IGestorLugares;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import static javax.management.Query.gt;
import javax.swing.JOptionPane;
import lugares.modelos.GestorLugares;
import lugares.vistas.VentanaALugar;
import lugares.vistas.VentanaLugares;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ControladorALugar implements IControladorALugar {
    private VentanaALugar ventana;
    private IGestorLugares gl = GestorLugares.crear();
    
    public ControladorALugar (VentanaLugares ventanaPadre) {
        this.ventana = new VentanaALugar(this, ventanaPadre, true);
        this.ventana.setTitle(TITULO);
        this.ventana.verTxtNombreLugar().setText(null);
        this.ventana.verTxtNombreLugar().setRequestFocusEnabled(true);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }    
    
    @Override
    public void btnGuardarClic(ActionEvent evt) {
        String nombre = this.ventana.verTxtNombreLugar().getText().trim(); 
        
        if((nombre == null) || (nombre.isBlank()))
            JOptionPane.showMessageDialog(this.ventana, "Ingrese un nombre para guardar.", TITULO, JOptionPane.WARNING_MESSAGE);
        if(gl.verLugares().contains(gl.verLugar(nombre))) 
            JOptionPane.showMessageDialog(this.ventana, "Lugar ingresado repetido.", TITULO, JOptionPane.WARNING_MESSAGE);

        System.out.println(gl.nuevoLugar(nombre));
        this.ventana.verTxtNombreLugar().setText(null);
        this.ventana.verTxtNombreLugar().requestFocus();   
    }

    @Override
    public void btnCancelarClic(ActionEvent evt) {
        int opcion = JOptionPane.showOptionDialog(this.ventana, "¿Desea terminar?", "Lugares", 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
            new Object[] {"Si", "No"}, this);
        if(opcion == JOptionPane.YES_OPTION) {
            this.ventana.setVisible(false);
            this.ventana.dispose();
        }
        else 
            this.ventana.verTxtNombreLugar().requestFocus();
    }

    @Override
    public void txtNombrePresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
            if(c == KeyEvent.VK_ENTER)
                this.btnGuardarClic(null);
    }
}
