/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicaciones.modelos;

import interfaces.IGestorTipos;
import javax.swing.DefaultComboBoxModel;
import tipos.modelos.GestorTipos;
import tipos.modelos.Tipo;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ModeloComboTipo extends DefaultComboBoxModel {
    IGestorTipos gt = GestorTipos.crear();
    
    public ModeloComboTipo() {
        for(Tipo t: gt.verTipos())
            this.addElement(t);
    }
    
    public Tipo obtenerTipo() {
        return (Tipo)this.getSelectedItem();
    }
    
    public void seleccionarTipo(Tipo tipo) {
        this.setSelectedItem(tipo);
    }
}
