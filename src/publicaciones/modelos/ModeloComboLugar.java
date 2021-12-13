/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicaciones.modelos;

import interfaces.IGestorLugares;
import javax.swing.DefaultComboBoxModel;
import lugares.modelos.GestorLugares;
import lugares.modelos.Lugar;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ModeloComboLugar extends DefaultComboBoxModel {
    IGestorLugares gl = GestorLugares.crear();
    
    public ModeloComboLugar() {
        for(Lugar l: gl.verLugares())
            this.addElement(l);
    }
    
    public Lugar obtenerLugar() {
        return (Lugar)this.getSelectedItem();
    }
    
    public void seleccionarLugar(Lugar lugar) {
        this.setSelectedItem(lugar);
    }
}
