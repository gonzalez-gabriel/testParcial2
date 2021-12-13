/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicaciones.modelos;

import autores.modelos.Autor;
import grupos.modelos.GestorGrupos;
import grupos.modelos.Grupo;
import grupos.modelos.MiembroEnGrupo;
import interfaces.IGestorGrupos;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ModeloComboGrupo extends DefaultComboBoxModel {
    private Autor autor;
    IGestorGrupos gg = GestorGrupos.crear();
    
    public ModeloComboGrupo(Autor autor) {        
        this.autor = autor;        
        for(MiembroEnGrupo m: autor.verGrupos())
            this.addElement(m.verGrupo().verNombre());
    }
    
    public MiembroEnGrupo obtenerMiembro() {
        for(MiembroEnGrupo m: autor.verGrupos()) {
            if(m.verGrupo().equals((Grupo) gg.verGrupo((String) this.getSelectedItem())))
                return m;
        }
        return null;
    }
    
    public Grupo obtenerGrupo() {
        return (Grupo) gg.verGrupo((String)this.getSelectedItem());
    }
    
    public void seleccionarGrupo(Grupo grupo){ 
        this.setSelectedItem(grupo);
    }
}
