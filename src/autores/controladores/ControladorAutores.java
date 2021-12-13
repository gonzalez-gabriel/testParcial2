/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autores.controladores;

import autores.modelos.Alumno;
import autores.modelos.GestorAutores;
import autores.modelos.ModeloTablaAlumnos;
import autores.modelos.ModeloTablaProfesores;
import autores.modelos.Profesor;
import autores.vistas.VentanaAMAutores;
import interfaces.IControladorAMAlumno;
import interfaces.IControladorAMProfesor;
import interfaces.IControladorAutores;
import static interfaces.IGestorAutores.EXITO_A;
import static interfaces.IGestorAutores.EXITO_P;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import principal.vistas.VentanaPrincipal;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ControladorAutores implements IControladorAutores {
    private VentanaAMAutores ventana;
    private GestorAutores ga = GestorAutores.crear();    
    private ModeloTablaAlumnos mta = new ModeloTablaAlumnos();
    private ModeloTablaProfesores mtp = new ModeloTablaProfesores();
    
    public ControladorAutores(VentanaPrincipal ventanaPadre) {
        this.ventana = new VentanaAMAutores(this, ventanaPadre, true);
        this.ventana.setTitle(TITULO);
        this.ventana.verTablaProfesores().setModel(mtp);
        this.ventana.verTablaAlumnos().setModel(mta);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }

    private void limpiarCampos () {
        this.ventana.verTxtApellidosProfesor().setText(null);
        this.ventana.verTxtApellidosAlumno().setText(null);
        this.ventana.verTxtApellidosProfesor().setRequestFocusEnabled(true);
        this.ventana.verBtnModificarProfesor().setEnabled(false);
        this.ventana.verBtnModificarAlumno().setEnabled(false);
        this.ventana.verBtnBorrarP().setEnabled(false);
        this.ventana.verBtnBorrarA().setEnabled(false);
    }
    
    @Override
    public void btnNuevoProfesorClic(ActionEvent evt) {
        IControladorAMProfesor camp = new ControladorAMProfesor(this.ventana);
    }

    @Override
    public void btnNuevoAlumnoClic(ActionEvent evt) {
        IControladorAMAlumno cama = new ControladorAMAlumno(this.ventana); 
    }

    @Override
    public void btnModificarProfesorClic(ActionEvent evt) {
        Profesor profesor = mtp.verProfesor(this.ventana.verTablaProfesores().getSelectedRow());
        IControladorAMProfesor camp = new ControladorAMProfesor(this.ventana, profesor);
    }

    @Override
    public void btnModificarAlumnoClic(ActionEvent evt) {
        Alumno alumno = mta.verAlumno(this.ventana.verTablaAlumnos().getSelectedRow());
        IControladorAMAlumno cama = new ControladorAMAlumno(this.ventana, alumno);
    }

    @Override
    public void btnBorrarProfesorClic(ActionEvent evt) {
        int opcion = JOptionPane.showOptionDialog(this.ventana, CONFIRMACION_PROFESOR, TITULO, 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
                new Object[] {"Borrar", "Cancelar"}, this);

        if (opcion == JOptionPane.YES_OPTION) {
            Profesor profesorBorrar = mtp.verProfesor(this.ventana.verTablaProfesores().getSelectedRow());
            System.out.println(ga.borrarAutor(profesorBorrar));
            mtp.quitarProfesor(profesorBorrar);
            JOptionPane.showMessageDialog(null, EXITO_P, "Profesor borrado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void btnBorrarAlumnoClic(ActionEvent evt) {
        int opcion = JOptionPane.showOptionDialog(this.ventana, CONFIRMACION_ALUMNO, TITULO, 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
                new Object[] {"Borrar", "Cancelar"}, this);

        if (opcion == JOptionPane.YES_OPTION) {
            Alumno alumnoBorrar = mta.verAlumno(this.ventana.verTablaAlumnos().getSelectedRow());
            System.out.println(ga.borrarAutor(alumnoBorrar));
            mta.quitarAlumno(alumnoBorrar);
            JOptionPane.showMessageDialog(null, EXITO_A, "Alumno borrado", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    @Override
    public void btnVolverClic(ActionEvent evt) {
        this.ventana.setVisible(false);
        this.ventana.dispose();    
    }

    @Override
    public void btnBuscarProfesorClic(ActionEvent evt) { 
        String apellidos = this.ventana.verTxtApellidosProfesor().getText();        
        if(!ga.verProfesores().isEmpty()) {    
            if((apellidos!= null) && (!apellidos.isBlank())){                
                if(!ga.buscarProfesores(apellidos).isEmpty())
                    mtp.buscarProfesor(apellidos);
                else {
                    JOptionPane.showMessageDialog(this.ventana, "No se encontraron Profesores con ese apellido.", TITULO, JOptionPane.INFORMATION_MESSAGE);
                    mtp.actualizar();
                }
            }
            else 
                mtp.actualizar();            
            this.ventana.verTablaProfesores().setRowSelectionInterval(0, 0);
            this.ventana.verBtnBorrarP().setEnabled(true);
            this.ventana.verBtnModificarProfesor().setEnabled(true);
        }
        else 
            JOptionPane.showMessageDialog(this.ventana, "La lista de Profesores está vacía.", TITULO, JOptionPane.INFORMATION_MESSAGE); 
    }

    @Override
    public void btnBuscarAlumnoClic(ActionEvent evt) {
        String apellidos = this.ventana.verTxtApellidosAlumno().getText();
        if(!ga.verAlumnos().isEmpty()) {
            if((apellidos!= null) && (!apellidos.isBlank())) {
                if(!ga.buscarAlumnos(apellidos).isEmpty())
                    mta.buscarAlumno(apellidos);
                else{ 
                    JOptionPane.showMessageDialog(this.ventana, "No se encontraron Alumnos con ese apellido.", TITULO, JOptionPane.INFORMATION_MESSAGE);
                    mta.actualizar();
                }
            }
            else
                mta.actualizar();
            this.ventana.verTablaAlumnos().setRowSelectionInterval(0, 0);
            this.ventana.verBtnBorrarA().setEnabled(true);
            this.ventana.verBtnModificarAlumno().setEnabled(true);
        }
        else
            JOptionPane.showMessageDialog(this.ventana, "La lista de Alumnos está vacía.", TITULO, JOptionPane.INFORMATION_MESSAGE); 
    }    

    @Override
    public void ventanaObtenerFoco(WindowEvent evt) {
        this.limpiarCampos();
        mta.actualizar(); 
        mtp.actualizar();
        if(mtp.getRowCount()>0) {
            this.ventana.verTablaProfesores().setRowSelectionInterval(0, 0);
            this.ventana.verBtnBorrarP().setEnabled(true);
            this.ventana.verBtnModificarProfesor().setEnabled(true);
        }
        else {
            this.ventana.verBtnBorrarP().setEnabled(false);
            this.ventana.verBtnModificarProfesor().setEnabled(false);
        }
        if(mta.getRowCount()>0) {
            this.ventana.verTablaAlumnos().setRowSelectionInterval(0, 0);
            this.ventana.verBtnBorrarA().setEnabled(true);
            this.ventana.verBtnModificarAlumno().setEnabled(true);
        }
        else {
            this.ventana.verBtnBorrarA().setEnabled(false);
            this.ventana.verBtnModificarAlumno().setEnabled(false);
        }
    }

    @Override
    public void txtApellidosProfesorPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
            if(c == KeyEvent.VK_ENTER)
                this.btnBuscarProfesorClic(null);
    }

    @Override
    public void txtApellidosAlumnoPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        if(c == KeyEvent.VK_ENTER)
            this.btnBuscarAlumnoClic(null);
    }
}
