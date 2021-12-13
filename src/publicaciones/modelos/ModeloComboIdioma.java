/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicaciones.modelos;

import idiomas.modelos.GestorIdiomas;
import idiomas.modelos.Idioma;
import interfaces.IGestorIdiomas;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ModeloComboIdioma extends DefaultComboBoxModel {
    IGestorIdiomas gi = GestorIdiomas.crear();
    
    public ModeloComboIdioma() {
        for(Idioma i: gi.verIdiomas())
            this.addElement(i);
    }
    
    public Idioma obtenerIdioma() {
        return (Idioma) this.getSelectedItem();
    }
    
    public void seleccionarLugar(Idioma idioma) {
        this.setSelectedItem(idioma);
    }
}
