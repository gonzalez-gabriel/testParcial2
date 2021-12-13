/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupos.controladores;

import grupos.modelos.GestorGrupos;
import grupos.modelos.Grupo;
import grupos.modelos.ModeloTablaGrupos;
import grupos.vistas.VentanaAMGrupo;
import grupos.vistas.VentanaGrupos;
import interfaces.IControladorAMGrupo;
import interfaces.IControladorGrupos;
import static interfaces.IGestorGrupos.MSJ_OK;
import static interfaces.IGestorGrupos.MSJ_OK_BORRAR;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import principal.vistas.VentanaPrincipal;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ControladorGrupos implements IControladorGrupos{
    private VentanaGrupos ventana;
    private GestorGrupos gg = GestorGrupos.crear();
    private ModeloTablaGrupos mtg = new ModeloTablaGrupos();
        
    public ControladorGrupos(VentanaPrincipal ventanaPadre) {       
        this.ventana = new VentanaGrupos(this, ventanaPadre, true);
        this.ventana.setTitle(TITULO);        
        this.ventana.verTablaGrupos().setModel(mtg);
        this.ventana.verTxtNombre().setRequestFocusEnabled(true);
        this.mtg.actualizar(gg.verGrupos());
        this.ventana.verBtnBorrar().setEnabled(false);
        this.ventana.verBtnModificar().setEnabled(false);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);       
    }    
    
    @Override
    public void btnNuevoClic(ActionEvent evt) {
        mtg.borrarTabla();
        IControladorAMGrupo camg = new ControladorAMGrupo(this.ventana);
        mtg.actualizar(gg.verGrupos());
    }

    @Override
    public void btnModificarClic(ActionEvent evt) {
        Grupo grupoModificar = mtg.verGrupo(this.ventana.verTablaGrupos().getSelectedRow());
        if(gg.verGrupos().contains(grupoModificar)){
            IControladorAMGrupo camg = new ControladorAMGrupo(this.ventana, grupoModificar);
        }
        mtg.actualizar(gg.verGrupos());
    }

    @Override
    public void btnBorrarClic(ActionEvent evt) {
        int opcion = JOptionPane.showOptionDialog(this.ventana, CONFIRMACION, TITULO, 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
                new Object[] {"Borrar", "Cancelar"}, this);

        if (opcion == JOptionPane.YES_OPTION) {
            Grupo grupoBorrar = mtg.verGrupo(this.ventana.verTablaGrupos().getSelectedRow());
            System.out.println(gg.borrarGrupo(grupoBorrar));
            mtg.quitarGrupo(grupoBorrar);
            JOptionPane.showMessageDialog(null, MSJ_OK_BORRAR, "Grupo borrado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void btnVolverClic(ActionEvent evt) {
        this.ventana.setVisible(false);
        this.ventana.dispose(); 
    }

    @Override
    public void btnBuscarClic(ActionEvent evt) {
        String nombreGrupo = this.ventana.verTxtNombre().getText();
        
        if((nombreGrupo!= null) && (!nombreGrupo.isBlank())) 
            mtg.actualizar(gg.buscarGrupos(nombreGrupo));
        else 
            mtg.actualizar(gg.verGrupos());
        
        if(!gg.verGrupos().isEmpty()) {
            if((nombreGrupo!= null) && (!nombreGrupo.isBlank())) {
                if(mtg.getRowCount()>0) { 
                    this.ventana.verTablaGrupos().setRowSelectionInterval(0, 0);
                    this.ventana.verBtnBorrar().setEnabled(true);
                    this.ventana.verBtnModificar().setEnabled(true);
                }    
                else {
                    mtg.actualizar(gg.verGrupos()); 
                    JOptionPane.showMessageDialog(this.ventana, "No existen Grupos con ese nombre.", TITULO, JOptionPane.INFORMATION_MESSAGE);}
            }
            else { 
                this.ventana.verTablaGrupos().setRowSelectionInterval(0, 0);
                this.ventana.verBtnBorrar().setEnabled(true);
                this.ventana.verBtnModificar().setEnabled(true);
            }
        }
        else 
            JOptionPane.showMessageDialog(this.ventana, "La lista de Grupos está vacía.", TITULO, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void ventanaObtenerFoco(WindowEvent evt) {
        this.ventana.verBtnModificar().setEnabled(false);
        this.ventana.verBtnBorrar().setEnabled(false);
        
        ModeloTablaGrupos mtg =(ModeloTablaGrupos) this.ventana.verTablaGrupos().getModel();;
        if(mtg.getRowCount()>0) {
            this.ventana.verTablaGrupos().setRowSelectionInterval(0, 0);
            this.ventana.verBtnModificar().setEnabled(true);
            this.ventana.verBtnBorrar().setEnabled(true);
            
            if(this.ventana.verTxtNombre().isEnabled())
                this.ventana.verTxtNombre().requestFocus(true);
            else
                mtg.actualizar(gg.verGrupos());
        }
    }

    @Override
    public void txtNombrePresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if(c == KeyEvent.VK_ENTER)
            this.btnBuscarClic(null);
    }
    
}
