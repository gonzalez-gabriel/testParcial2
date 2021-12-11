/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autores.controladores;

import autores.modelos.Cargo;
import autores.modelos.GestorAutores;
import autores.modelos.ModeloTablaAutorGrupos;
import autores.modelos.Profesor;
import autores.vistas.VentanaAMAutores;
import autores.vistas.VentanaAMProfesor;
import interfaces.IControladorAMProfesor;
import interfaces.IGestorAutores;
import static interfaces.IGestorAutores.ERROR_DNI_P;
import static interfaces.IGestorAutores.EXITO_P;
import static interfaces.IGestorAutores.MSJ_MOD_OK;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ControladorAMProfesor implements IControladorAMProfesor {
    private VentanaAMProfesor ventana;
    private IGestorAutores ga = GestorAutores.crear();
    
    public ControladorAMProfesor(VentanaAMAutores ventanaPadre) { 
        this.ventana = new VentanaAMProfesor(this, ventanaPadre,true);
        this.ventana.setTitle(TITULO_NUEVO);        
        this.ventana.verTxtDNI().requestFocus(); 
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true); 
    }
    
    public ControladorAMProfesor(VentanaAMAutores ventanaPadre, Profesor profesor) { 
        this.ventana = new VentanaAMProfesor(this, ventanaPadre,true);   
        this.ventana.setTitle(TITULO_MODIFICAR);
        String dni = Integer.toString(profesor.verDni());
        String apellidos = profesor.verApellidos();
        String nombres = profesor.verNombres();
        Cargo cargo = profesor.verCargo();
        String clave = profesor.verClave();
        this.ventana.verTxtDNI().setText(dni);
        this.ventana.verTxtApellidos().setText(apellidos);
        this.ventana.verTxtNombres().setText(nombres);
        this.ventana.verComboCargo();        
        this.ventana.verPassClave().setText(clave);
        this.ventana.verPassClaveRepetida().setText(clave);
        this.ventana.verTxtDNI().setEnabled(false);
        this.ventana.verTxtApellidos().requestFocus();     
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true); 
    }
    
    public void guardar () { 
        int dni = 0;
        if(this.ventana.verTxtDNI().getText().trim().matches("[0-9]+")){
            if(!this.ventana.verTxtDNI().getText().trim().isBlank())    
                dni = Integer.parseInt(this.ventana.verTxtDNI().getText().trim()); 
            String apellidos = this.ventana.verTxtApellidos().getText().trim();
            String nombres = this.ventana.verTxtNombres().getText().trim();
            Cargo cargo = this.ventana.verComboCargo(); 
            String clave = new String(this.ventana.verPassClave().getPassword());
            String claveRepetida = new String(this.ventana.verPassClaveRepetida().getPassword());
            String resultado;
        
            if(this.ventana.verTxtDNI().isEnabled()) 
                resultado = ga.nuevoAutor(dni, apellidos, nombres, cargo, clave, claveRepetida);
            else 
                resultado = ga.modificarAutor(ga.verAutor(dni), apellidos, nombres, cargo, clave, claveRepetida);

            if(resultado == EXITO_P || resultado == MSJ_MOD_OK) {
                JOptionPane.showMessageDialog(null, resultado, "Profesor Guardado", JOptionPane.INFORMATION_MESSAGE);       
                this.ventana.setVisible(false);
                this.ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(this.ventana, resultado, "ERROR", JOptionPane.WARNING_MESSAGE);       
        }
        else
            JOptionPane.showMessageDialog(this.ventana, ERROR_DNI_P, "ERROR", JOptionPane.WARNING_MESSAGE);       
    }
    
    public void limpiarCampos() {
       this.ventana.verTxtDNI().setText(null);
       this.ventana.verTxtApellidos().setText(null);
       this.ventana.verTxtNombres().setText(null);
       this.ventana.verPassClave().setText(null);
       this.ventana.verPassClaveRepetida().setText(null);
   }
    
    @Override
    public void btnGuardarClic(ActionEvent evt) {
        this.guardar();
    }

    @Override
    public void btnCancelarClic(ActionEvent evt) {
        int opcion = JOptionPane.showOptionDialog(null, CONFIRMACION, "Cancelar", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
                new Object[] {"Si", "No"}, this);
        if (opcion == JOptionPane.YES_OPTION) {
            this.ventana.setVisible(false);
            this.ventana.dispose();
        }
    }

    @Override
    public void txtApellidosPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
            if(c == KeyEvent.VK_ENTER)
                this.guardar();
    }

    @Override
    public void txtNombresPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
            if(c == KeyEvent.VK_ENTER)
                this.guardar();
    }

    @Override
    public void txtDocumentoPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
            if(c == KeyEvent.VK_ENTER)
                this.guardar();
    }

    @Override
    public void passClavePresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
            if(c == KeyEvent.VK_ENTER)
                this.guardar();
    }

    @Override
    public void passRepetirClavePresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
            if(c == KeyEvent.VK_ENTER)
                this.guardar();
    }

    @Override
    public void ventanaObtenerFoco(WindowEvent evt) {
        if(this.ventana.verTxtDNI().isEnabled())
            this.ventana.verTxtDNI().requestFocus(true);
        else {
            ModeloTablaAutorGrupos mtag =(ModeloTablaAutorGrupos) this.ventana.verTablaGrupos().getModel();
            mtag.actualizar();
        }
    }
}
