/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupos.controladores;

import autores.modelos.Autor;
import grupos.modelos.GestorGrupos;
import grupos.modelos.Grupo;
import grupos.modelos.MiembroEnGrupo;
import grupos.modelos.ModeloComboRol;
import grupos.modelos.ModeloTablaModificarMiembros;
import grupos.vistas.VentanaAMGrupo;
import grupos.vistas.VentanaMMiembros;
import interfaces.IControladorModificarMiembros;
import interfaces.IGestorGrupos;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ControladorModificarMiembros implements IControladorModificarMiembros{
    private VentanaMMiembros ventana;
    private ModeloTablaModificarMiembros mtmm;
    private IGestorGrupos gg = GestorGrupos.crear();
    private Grupo grupoModificar;

    public ControladorModificarMiembros(VentanaAMGrupo ventanaPadre, Grupo grupoModificar){
        this.grupoModificar = grupoModificar;
        ArrayList<MiembroEnGrupo> miembros = grupoModificar.verMiembros();
        this.ventana = new VentanaMMiembros(this, ventanaPadre,true);
        mtmm = new ModeloTablaModificarMiembros(grupoModificar);
        this.ventana.verTablaMiembrosModificar().setModel(mtmm);
        JComboBox comboRol = new JComboBox();
        comboRol.setModel(new ModeloComboRol());
        TableColumn colRol=this.ventana.verTablaMiembrosModificar().getColumnModel().getColumn(1);
        colRol.setCellEditor(new DefaultCellEditor(comboRol));
        this.ventana.setTitle(this.grupoModificar.verNombre());
        this.ventana.verTablaMiembrosModificar().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ListSelectionModel modeloSeleccion = this.ventana.verTablaMiembrosModificar().getSelectionModel();
        
        for(int indice=0;indice<miembros.size();indice++){
            Autor miembro = miembros.get(indice).verAutor();

            for(int fila=0; fila<mtmm.getRowCount(); fila++){
                Autor mm = mtmm.verAutor(fila);
                if(miembro.equals(mm)){
                    modeloSeleccion.addSelectionInterval(fila, fila);
                }
            }
        }
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }
    
    @Override
    public void btnTodosClic(ActionEvent evt) {
        mtmm = (ModeloTablaModificarMiembros)this.ventana.verTablaMiembrosModificar().getModel();
        ListSelectionModel modeloSeleccion = this.ventana.verTablaMiembrosModificar().getSelectionModel();
        modeloSeleccion.addSelectionInterval(0, mtmm.getRowCount() - 1);
    }

    @Override
    public void btnNingunoClic(ActionEvent evt) {
        ListSelectionModel modeloSeleccion = this.ventana.verTablaMiembrosModificar().getSelectionModel();
        modeloSeleccion.clearSelection();
    }

    @Override
    public void btnAceptarClic(ActionEvent evt) {
        this.aceptar();
    }

    @Override
    public void btnCancelarClic(ActionEvent evt) {
        int opcion = JOptionPane.showOptionDialog(null, "Salir", "Cancelar", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
                new Object[] {"Si", "No"}, this);
        if (opcion == JOptionPane.YES_OPTION) {
            this.ventana.setVisible(false);
            this.ventana.dispose();
        }
    }
    
    
    public void aceptar(){  
        int opcion = JOptionPane.showOptionDialog(this.ventana, CONFIRMACION, TITULO, 
        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
        new Object[] {"Si", "No"}, this);

        if (opcion == JOptionPane.YES_OPTION){            
            ArrayList<MiembroEnGrupo> meg = new ArrayList<>();                         
            
            for(MiembroEnGrupo m: grupoModificar.verMiembros()){  
                meg.add(m);
            }
            gg.quitarMiembros(grupoModificar, meg);

            int nFilas = this.ventana.verTablaMiembrosModificar().getSelectedRowCount();
            int[] indice = this.ventana.verTablaMiembrosModificar().getSelectedRows();
            ArrayList<MiembroEnGrupo> miembrosSeleccionados = new ArrayList<>();
            for(int fila = 0; fila < nFilas; fila++) {
                miembrosSeleccionados.add(new MiembroEnGrupo(mtmm.verAutor(indice[fila]), this.grupoModificar, mtmm.verRol(indice[fila])));
            }
            boolean rolNulo = false;
            for(MiembroEnGrupo m: miembrosSeleccionados){
                if(m.verRol() == null)
                    rolNulo = true;
            }

            if(!rolNulo){
                System.out.println(gg.agregarMiembros(grupoModificar, miembrosSeleccionados));
                this.ventana.setVisible(false);
                this.ventana.dispose(); 
            }
            else 
                JOptionPane.showMessageDialog(this.ventana, "Asigne un rol a todos los miembros seleccionados.", "Cuidado.", JOptionPane.WARNING_MESSAGE);
        }
    }
}
