/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lugares.controladores;

import interfaces.IControladorALugar;
import interfaces.IControladorLugares;
import interfaces.IGestorLugares;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import lugares.modelos.GestorLugares;
import lugares.modelos.Lugar;
import lugares.modelos.ModeloTablaLugares;
import lugares.vistas.VentanaLugares;
import principal.vistas.VentanaPrincipal;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ControladorLugares implements IControladorLugares {
    private VentanaLugares ventana;
    private IGestorLugares gl = GestorLugares.crear();
    private ModeloTablaLugares mtl = new ModeloTablaLugares();
    
    public ControladorLugares(VentanaPrincipal ventanaPadre) {
        this.ventana = new VentanaLugares(this, ventanaPadre, true);
        this.ventana.setTitle(TITULO);
        this.ventana.verTablaLugares().setModel(mtl);
        this.ventana.verTxtNombreLugar().setText(null);
        this.ventana.verTxtNombreLugar().setRequestFocusEnabled(true);
        this.mtl.actualizar(gl.verLugares()); 
        this.ventana.verBtnBorrar().setEnabled(false);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }
    
    @Override
    public void btnNuevoClic(ActionEvent evt) {
        ModeloTablaLugares mtl = (ModeloTablaLugares) this.ventana.verTablaLugares().getModel(); 
        mtl.borrarTabla();
        IControladorALugar cal = new ControladorALugar(this.ventana);
    }

    @Override
    public void btnBorrarClic(ActionEvent evt) {
        int opcion = JOptionPane.showOptionDialog(this.ventana, CONFIRMACION, BORRAR_TITULO, 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
            new Object[] {"Borrar", "Cancelar"}, this);

        if (opcion == JOptionPane.YES_OPTION) {
            ModeloTablaLugares mtl = (ModeloTablaLugares) this.ventana.verTablaLugares().getModel(); 
            Lugar lugarABorrar = mtl.verLugar(this.ventana.verTablaLugares().getSelectedRow());
            System.out.println(gl.borrarLugar(lugarABorrar));
            mtl.quitarLugar(lugarABorrar);
        } 
    }

    @Override
    public void btnVolverClic(ActionEvent evt) {
        this.ventana.setVisible(false);
        this.ventana.dispose();
    }

    @Override
    public void btnBuscarClic(ActionEvent evt) {
        ModeloTablaLugares mtl = (ModeloTablaLugares) this.ventana.verTablaLugares().getModel(); 
        String nombre = this.ventana.verTxtNombreLugar().getText();
        
        if((nombre!= null) && (!nombre.isBlank())) 
            mtl.actualizar(gl.buscarLugares(nombre));
        else
            mtl.actualizar(gl.verLugares());  
        
        if(!gl.verLugares().isEmpty()) {
            if((nombre!= null) && (!nombre.isBlank())) {
                if(mtl.getRowCount()>0) { 
                    this.ventana.verTablaLugares().setRowSelectionInterval(0, 0);
                    this.ventana.verBtnBorrar().setEnabled(true);
                }    
                else 
                    JOptionPane.showMessageDialog(this.ventana, "No existe un lugar con ese nombre.", TITULO, JOptionPane.INFORMATION_MESSAGE);
            }
            else { 
                this.ventana.verTablaLugares().setRowSelectionInterval(0, 0);
                this.ventana.verBtnBorrar().setEnabled(true);
            }
        }
        else 
            JOptionPane.showMessageDialog(this.ventana, "No hay lugares guardados.", TITULO, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void ventanaObtenerFoco(WindowEvent evt) {
        this.ventana.verTxtNombreLugar().setText(null);
        this.ventana.verTxtNombreLugar().setRequestFocusEnabled(true);
        this.ventana.verBtnBorrar().setEnabled(false);
        
        ModeloTablaLugares mtl = (ModeloTablaLugares) this.ventana.verTablaLugares().getModel();
        if(mtl.getRowCount()>0) {
                this.ventana.verTablaLugares().setRowSelectionInterval(0, 0);
                this.ventana.verBtnBorrar().setEnabled(true);
        }
    }

    @Override
    public void txtNombrePresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
            if(c == KeyEvent.VK_ENTER)
                this.btnBuscarClic(null); 
    }    
}
