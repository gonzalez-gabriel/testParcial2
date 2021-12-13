/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupos.controladores;

import grupos.modelos.ModeloTablaMiembro;
import grupos.modelos.GestorGrupos;
import grupos.modelos.Grupo;
import grupos.vistas.VentanaAMGrupo;
import grupos.vistas.VentanaGrupos;
import interfaces.IControladorAMGrupo;
import interfaces.IControladorModificarMiembros;
import interfaces.IGestorGrupos;
import static interfaces.IGestorGrupos.MSJ_MOD_OK;
import static interfaces.IGestorGrupos.MSJ_OK;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ControladorAMGrupo implements IControladorAMGrupo{
    private VentanaAMGrupo ventana;
    private IGestorGrupos gg = GestorGrupos.crear();
    private ModeloTablaMiembro mtm = new ModeloTablaMiembro();
    private Grupo grupoModificar = new Grupo(null, null);
    
    public ControladorAMGrupo(VentanaGrupos ventanaPadre) {
        this.ventana = new VentanaAMGrupo(this, ventanaPadre,true);
        this.ventana.setTitle(TITULO_NUEVO);
        this.ventana.verTablaMiembros().setModel(mtm);        
        this.ventana.verTxtNombre().requestFocus(); 
        this.ventana.verTablaMiembros().setEnabled(false);
        this.ventana.verBtnModificarMiembros().setEnabled(false);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }
    
    public ControladorAMGrupo(VentanaGrupos ventanaPadre, Grupo grupoModificar) { 
        mtm.miembrosEnGrupo(grupoModificar);
        this.grupoModificar = grupoModificar;
        this.ventana = new VentanaAMGrupo(this, ventanaPadre,true);
        this.ventana.setTitle(TITULO_MODIFICAR);
        this.ventana.asignarNombre(grupoModificar.verNombre());
        this.ventana.asignarDescripcion(grupoModificar.verDescripcion());
        this.ventana.verTxtNombre().setEnabled(false);        
        this.ventana.verTablaMiembros().setModel(mtm);
        this.ventana.verTablaMiembros().setEnabled(false);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }
    
    @Override
    public void btnGuardarClic(ActionEvent evt) {
        this.guardar();
    }

    @Override
    public void btnCancelarClic(ActionEvent evt) {
        int opcion = JOptionPane.showOptionDialog(null, "¿Desea cerrar esta ventana?", "Cancelar", 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
            new Object[] {"Si", "No"}, this);
        if (opcion == JOptionPane.YES_OPTION) {
            this.ventana.setVisible(false);
            this.ventana.dispose();
        }
    }

    @Override
    public void btnModificarMiembrosClic(ActionEvent evt) {
        IControladorModificarMiembros camm = new ControladorModificarMiembros(this.ventana,this.grupoModificar);
        mtm.actualizar(this.grupoModificar.verMiembros());
    }

    @Override
    public void txtNombrePresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();        
        if(c == KeyEvent.VK_ENTER){
            if(this.ventana.verTxtNombre().isEnabled())
                this.ventana.verTxtDescripcion().requestFocus(true);
            else 
                this.guardar();
        }
    }

    @Override
    public void txtDescripcionPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if(c == KeyEvent.VK_ENTER){
            this.guardar();
        }
    }

    @Override
    public void ventanaObtenerFoco(WindowEvent evt) {
        if(this.ventana.verTxtNombre().isEnabled())
            this.ventana.verTxtNombre().requestFocus(true);
    }
    
    public void guardar() {        
        String nombreGrupo = this.ventana.verTxtNombre().getText().trim();
        String descripcionGrupo = this.ventana.verTxtDescripcion().getText().trim();
    
        if(this.ventana.getTitle() == TITULO_NUEVO){
            String resultado = gg.nuevoGrupo(nombreGrupo, descripcionGrupo);

            if(resultado == MSJ_OK) {
                JOptionPane.showMessageDialog(null, resultado, TITULO_NUEVO, JOptionPane.INFORMATION_MESSAGE);
                this.ventana.setVisible(false);
                this.ventana.dispose(); 
            }
            else
                JOptionPane.showMessageDialog(this.ventana, "Campo inválido",resultado , JOptionPane.WARNING_MESSAGE);}

        if(this.ventana.getTitle() == TITULO_MODIFICAR){
            Grupo grupoModificar = gg.verGrupo(nombreGrupo);
            String resultado = gg.modificarGrupo(grupoModificar, descripcionGrupo);

            if(resultado == MSJ_MOD_OK) {
                JOptionPane.showMessageDialog(null, "Se modifico con éxito el " + this.grupoModificar.verNombre(), TITULO_MODIFICAR, JOptionPane.INFORMATION_MESSAGE);       
                this.ventana.setVisible(false);
                this.ventana.dispose();     
            }
            else
                JOptionPane.showMessageDialog(this.ventana, resultado, "Error al ingresar la modificación.", JOptionPane.WARNING_MESSAGE);   
        }
    }
}
