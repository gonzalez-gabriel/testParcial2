/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autores.controladores;

import autores.modelos.Alumno;
import autores.modelos.GestorAutores;
import autores.vistas.VentanaAMAlumno;
import autores.vistas.VentanaAMAutores;
import interfaces.IControladorAMAlumno;
import static interfaces.IControladorPrincipal.CONFIRMACION;
import interfaces.IGestorAutores;
import static interfaces.IGestorAutores.ERROR_DNI_A;
import static interfaces.IGestorAutores.EXITO_A;
import static interfaces.IGestorAutores.MSJ_MOD_OK;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ControladorAMAlumno implements IControladorAMAlumno{
    private VentanaAMAlumno ventana;
    private IGestorAutores ga = GestorAutores.crear();
    
    public ControladorAMAlumno(VentanaAMAutores ventanaPadre) { 
        this.ventana = new VentanaAMAlumno(this, ventanaPadre,true);
        this.ventana.setTitle(TITULO_NUEVO);
        this.ventana.verTxtDNI().requestFocus();  
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }
    
    public ControladorAMAlumno(VentanaAMAutores ventanaPadre, Alumno alumno) { 
        this.ventana = new VentanaAMAlumno(this, ventanaPadre,true);        
        String dni = Integer.toString(alumno.verDni());
        String apellidos = alumno.verApellidos();
        String nombres = alumno.verNombres();
        String cx = alumno.verCx();
        String clave = alumno.verClave();
        this.ventana.verTxtDNI().setText(dni);
        this.ventana.verTxtApellidos().setText(apellidos);
        this.ventana.verTxtNombres().setText(nombres);
        this.ventana.verTxtCX().setText(cx);
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
            String cx = this.ventana.verTxtCX().getText().trim(); 
            String clave = new String(this.ventana.verPassClave().getPassword());
            String claveRepetida = new String(this.ventana.verPassClaveRepetida().getPassword());

            String resultado;
            if(this.ventana.verTxtDNI().isEnabled()) 
                resultado = ga.nuevoAutor(dni, apellidos, nombres, cx, clave, claveRepetida);
            else 
                resultado = ga.modificarAutor(ga.verAutor(dni), apellidos, nombres, cx, clave, claveRepetida);
        
            if(resultado == EXITO_A || resultado == MSJ_MOD_OK) {
                JOptionPane.showMessageDialog(null, resultado, "Alumno Guardado", JOptionPane.INFORMATION_MESSAGE);       
                this.ventana.setVisible(false);
                this.ventana.dispose();
            }
            else
                JOptionPane.showMessageDialog(this.ventana, resultado, "ERROR", JOptionPane.WARNING_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(this.ventana, ERROR_DNI_A, "ERROR", JOptionPane.WARNING_MESSAGE);
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
    public void txtCXPresionarTecla(KeyEvent evt) {
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
}
