/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicaciones.controladores;

import autores.modelos.Autor;
import autores.modelos.GestorAutores;
import grupos.modelos.GestorGrupos;
import interfaces.IControladorAMPublicacion;
import interfaces.IControladorPublicaciones;
import interfaces.IGestorAutores;
import interfaces.IGestorGrupos;
import interfaces.IGestorPublicaciones;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import principal.vistas.VentanaPrincipal;
import publicaciones.modelos.GestorPublicaciones;
import publicaciones.modelos.ModeloTablaPublicaciones;
import publicaciones.modelos.Publicacion;
import publicaciones.vistas.VentanaPublicaciones;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ControladorPublicaciones implements IControladorPublicaciones{
    private VentanaPublicaciones ventana;
    private Autor autor;
    private IGestorGrupos gg = GestorGrupos.crear();
    private IGestorPublicaciones gp = GestorPublicaciones.crear();    
    private IGestorAutores ga = GestorAutores.crear();
    private ModeloTablaPublicaciones mtp = new ModeloTablaPublicaciones();
    
    public ControladorPublicaciones(VentanaPrincipal ventanaPadre, Autor autor) {
        this.ventana = new VentanaPublicaciones(this, ventanaPadre, true);
        this.autor = autor;
        this.ventana.setTitle(TITULO + " - " + autor.verApellidos() + ", " + autor.verNombres());
        this.ventana.verTablaPublicaciones().setModel(mtp);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }
    
    @Override
    public void btnNuevaClic(ActionEvent evt) {
        IControladorAMPublicacion camp = new ControladorAMPublicacion(this.ventana, this.autor);
    }

    @Override
    public void btnModificarClic(ActionEvent evt) {
        Publicacion publicacion = mtp.verPublicacion(this.ventana.verTablaPublicaciones().getSelectedRow());
        IControladorAMPublicacion camp = new ControladorAMPublicacion(this.ventana, this.autor, publicacion);
    }

    @Override
    public void btnBorrarClic(ActionEvent evt) {
        int opcion = JOptionPane.showOptionDialog(this.ventana, CONFIRMACION, TITULO, 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
                new Object[] {"Borrar", "Cancelar"}, this);

        if(opcion == JOptionPane.YES_OPTION) {
            Publicacion publicacionBorrar = mtp.verPublicacion(this.ventana.verTablaPublicaciones().getSelectedRow());
            System.out.println(gp.borrarPublicacion(publicacionBorrar));
        }
    }

    @Override
    public void btnVolverClic(ActionEvent evt) {
        this.ventana.setVisible(false);
        this.ventana.dispose();  
    }

    @Override
    public void btnBuscarClic(ActionEvent evt) {
        String titulo = this.ventana.verTxtTitulo().getText();
        
        if(!gp.verPublicaciones().isEmpty()) {            
            if((titulo!= null) && (!titulo.isBlank())) {
                if(!gp.buscarPublicaciones(titulo).isEmpty())
                    mtp.buscarPublicacion(titulo);
                else {
                    JOptionPane.showMessageDialog(this.ventana, "No existen Publicaciones con ese título.", TITULO, JOptionPane.INFORMATION_MESSAGE);
                    mtp.actualizar();  
                } 
            }
            else 
                mtp.actualizar();            
            this.ventana.verTablaPublicaciones().setRowSelectionInterval(0, 0);
            this.ventana.verBtnBorrar().setEnabled(true);
            this.ventana.verBtnModificar().setEnabled(true);            
        }
        else 
            JOptionPane.showMessageDialog(this.ventana, "La lista de Publicaciones está vacía.", TITULO, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void ventanaObtenerFoco(WindowEvent evt) {
        this.limpiarCampos();
        mtp.actualizar();
        
        if(mtp.getRowCount()>0) {
            this.ventana.verTablaPublicaciones().setRowSelectionInterval(0, 0);
            this.ventana.verBtnBorrar().setEnabled(true);
            this.ventana.verBtnModificar().setEnabled(true);
        }
        else {
            this.ventana.verBtnBorrar().setEnabled(false);
            this.ventana.verBtnModificar().setEnabled(false);
        }
    }

    @Override
    public void txtTituloPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
            if(c == KeyEvent.VK_ENTER)
                this.btnBuscarClic(null);
    }
    
    private void limpiarCampos () {
        this.ventana.verTxtTitulo().setText(null);
        this.ventana.verBtnModificar().setEnabled(false);
        this.ventana.verBtnBorrar().setEnabled(false);
    }
}
