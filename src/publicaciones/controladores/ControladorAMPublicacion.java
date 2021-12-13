/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicaciones.controladores;

import autores.modelos.Autor;
import autores.modelos.GestorAutores;
import grupos.modelos.GestorGrupos;
import grupos.modelos.Grupo;
import grupos.modelos.MiembroEnGrupo;
import idiomas.modelos.Idioma;
import interfaces.IControladorAMPublicacion;
import interfaces.IControladorPrincipal;
import interfaces.IGestorAutores;
import interfaces.IGestorGrupos;
import interfaces.IGestorPalabrasClaves;
import interfaces.IGestorPublicaciones;
import static interfaces.IGestorPublicaciones.MSJ_MOD_OK;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import lugares.modelos.Lugar;
import palabrasclaves.modelos.GestorPalabrasClaves;
import palabrasclaves.modelos.PalabraClave;
import publicaciones.modelos.GestorPublicaciones;
import publicaciones.modelos.ModeloComboGrupo;
import publicaciones.modelos.ModeloComboIdioma;
import publicaciones.modelos.ModeloComboLugar;
import publicaciones.modelos.ModeloComboTipo;
import publicaciones.modelos.ModeloTablaPalabrasClaves;
import publicaciones.modelos.Publicacion;
import publicaciones.vistas.VentanaAMPublicacion;
import publicaciones.vistas.VentanaPublicaciones;
import tipos.modelos.Tipo;

/**
 *
 * @author Ocón Santiago Luis
 */
public class ControladorAMPublicacion implements IControladorAMPublicacion{
    private VentanaAMPublicacion ventana;
    private Publicacion publicacion;
    private Autor autor;    
    private IGestorPublicaciones gp = GestorPublicaciones.crear();
    private IGestorAutores ga = GestorAutores.crear();
    private IGestorGrupos gg = GestorGrupos.crear();
    private IGestorPalabrasClaves gpc = GestorPalabrasClaves.crear();
    private ModeloTablaPalabrasClaves mtpc = new ModeloTablaPalabrasClaves();

    public ControladorAMPublicacion(VentanaPublicaciones ventanaPadre, Autor autor) { 
        this.ventana = new VentanaAMPublicacion(this, ventanaPadre,true);
        this.autor = autor;
        this.ventana.setTitle(TITULO_NUEVA + " - " + autor.verApellidos() + ", " + autor.verNombres());
        this.ventana.verTablaPalabras().setModel(mtpc);
        this.ventana.verTablaPalabras().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.mtpc.actualizar();
        this.ventana.verComboLugar().setModel(new ModeloComboLugar()); 
        this.ventana.verComboLugar().setSelectedIndex(-1);
        this.ventana.verComboIdioma().setModel(new ModeloComboIdioma()); 
        this.ventana.verComboIdioma().setSelectedIndex(-1);
        this.ventana.verComboTipo().setModel(new ModeloComboTipo()); 
        this.ventana.verComboTipo().setSelectedIndex(-1);      
        this.ventana.verComboGrupo().setModel(new ModeloComboGrupo(this.autor)); 
        this.ventana.verComboGrupo().setSelectedIndex(-1);      
        this.ventana.verTxtTitulo().requestFocus();
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }
    
    public ControladorAMPublicacion(VentanaPublicaciones ventanaPadre, Autor autor, Publicacion publicacion) {
        this.ventana = new VentanaAMPublicacion(this, ventanaPadre,true);
        this.autor = autor;
        this.publicacion = publicacion;
        this.ventana.setTitle(TITULO_MODIFICAR + " - " + autor.verApellidos() + ", " + autor.verNombres());
        this.ventana.verTablaPalabras().setModel(mtpc);
        this.ventana.verTablaPalabras().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.mtpc.actualizar();
        this.ventana.verComboLugar().setModel(new ModeloComboLugar()); 
        this.ventana.verComboIdioma().setModel(new ModeloComboIdioma()); 
        this.ventana.verComboTipo().setModel(new ModeloComboTipo()); 
        this.ventana.verComboGrupo().setModel(new ModeloComboGrupo(this.autor)); 
        String titulo = publicacion.verTitulo();
        Grupo grupo = publicacion.verMiembroEnGrupo().verGrupo();
        LocalDate fecha = publicacion.verFecha();
        Tipo tipo = publicacion.verTipo();
        Idioma idioma = publicacion.verIdioma();
        Lugar lugar = publicacion.verLugar();
        String enlace = publicacion.verEnlace();
        String resumen = publicacion.verResumen();
        List<PalabraClave> palabras = publicacion.verPalabrasClave();        
        this.ventana.verTxtTitulo().setText(titulo);
        this.ventana.verComboGrupo().setSelectedItem(grupo.verNombre());
        this.ventana.verDateChooserFecha().setDate(Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        this.ventana.verComboTipo().setSelectedItem(tipo);
        this.ventana.verComboIdioma().setSelectedItem(idioma);
        this.ventana.verComboLugar().setSelectedItem(lugar);
        this.ventana.verTxtEnlace().setText(enlace);
        this.ventana.verTxtResumen().setText(resumen);
        
        ListSelectionModel modeloSeleccion = this.ventana.verTablaPalabras().getSelectionModel();
        for(PalabraClave palabraClave: palabras) {
            for(int fila = 0; fila < mtpc.getRowCount(); fila++) {                
                PalabraClave pc = mtpc.verPalabraClave(fila);
                
                if (palabraClave.equals(pc)) {
                    modeloSeleccion.addSelectionInterval(fila, fila);
                }
            }
        }
        this.ventana.verTxtTitulo().setEnabled(false);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }

    public void guardar () { 
        String titulo = this.ventana.verTxtTitulo().getText().trim(); 
        MiembroEnGrupo meg = ((ModeloComboGrupo)this.ventana.verComboGrupo().getModel()).obtenerMiembro();
        Date date = this.ventana.verDateChooserFecha().getCalendar().getTime();
        LocalDate fecha = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
        Tipo tipo = ((ModeloComboTipo)this.ventana.verComboTipo().getModel()).obtenerTipo();
        Idioma idioma = ((ModeloComboIdioma)this.ventana.verComboIdioma().getModel()).obtenerIdioma();
        Lugar lugar = ((ModeloComboLugar)this.ventana.verComboLugar().getModel()).obtenerLugar();
        ArrayList<PalabraClave> palabras = new ArrayList<>();
        
        int nFilas = this.ventana.verTablaPalabras().getSelectedRowCount();
        int[] indice = this.ventana.verTablaPalabras().getSelectedRows();
        for(int fila = 0; fila < nFilas; fila++){
            palabras.add(mtpc.verPalabraClave(indice[fila]));
        }
        String enlace = this.ventana.verTxtEnlace().getText().trim();
        String resumen = this.ventana.verTxtResumen().getText().trim();
        
        String resultado;
        if(this.ventana.verTxtTitulo().isEnabled()) 
            resultado = gp.nuevaPublicacion(titulo, meg, fecha, tipo, idioma, lugar, palabras, enlace, resumen);
        else 
            resultado = gp.modificarPublicacion(this.publicacion, meg, fecha, tipo, idioma, lugar, palabras, enlace, resumen);
        
        if(resultado == MSJ_MOD_OK) {
            JOptionPane.showMessageDialog(null, resultado, "Publicacion guardada.", JOptionPane.INFORMATION_MESSAGE);       
            this.ventana.setVisible(false);
            this.ventana.dispose();
        }
        else
            JOptionPane.showMessageDialog(this.ventana, resultado, "Campo Inválido.", JOptionPane.WARNING_MESSAGE);       
        System.out.println(resultado);
    }
   
    @Override
    public void btnGuardarClic(ActionEvent evt) {
        this.guardar();
    }
       
    @Override
    public void txtTituloPresionarTecla(KeyEvent evt) { 
        char c = evt.getKeyChar();
        if(c == KeyEvent.VK_ENTER)
            this.guardar();
    }
   
    @Override
    public void btnCancelarClic(ActionEvent evt) {                                            
        int opcion = JOptionPane.showOptionDialog(this.ventana, SALIR, "Cancelar", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, 
                new Object[] {"si", "no"}, this);
        if (opcion == JOptionPane.YES_OPTION) {
            this.ventana.setVisible(false);
            this.ventana.dispose();
        }
    }

    @Override
    public void btnTodasLasPalabrasClavesClic(ActionEvent evt) {
        this.ventana.verTablaPalabras().getSelectionModel().addSelectionInterval(0, mtpc.getRowCount() - 1);
    }

    @Override
    public void btnNingunaPalabraClaveClic(ActionEvent evt) {
        this.ventana.verTablaPalabras().getSelectionModel().clearSelection();
    }

    @Override
    public void btnAbrirClic(ActionEvent evt) {
        UIManager.put("FileChooser.openButtonText","Abrir");
        UIManager.put("FileChooser.openButtonToolTipText", "Abrir");
        UIManager.put("FileChooser.cancelButtonText","Cancelar");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Cancelar");
        UIManager.put("FileChooser.lookInLabelText", "Buscar en:");
        UIManager.put("FileChooser.fileNameLabelText", "Archivo:");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Archivos del tipo:");
        UIManager.put("FileChooser.upFolderToolTipText", "Subir un nivel");
        UIManager.put("FileChooser.homeFolderToolTipText", "Inicio");
        UIManager.put("FileChooser.newFolderToolTipText", "Carpeta nueva");
        UIManager.put("FileChooser.listViewButtonToolTipText", "Lista");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Detalles");
        
        JFileChooser selector = new JFileChooser();
        selector.setCurrentDirectory(new File(System.getProperty("user.home")));
        selector.setDialogTitle(IControladorPrincipal.TITULO);
        selector.setAcceptAllFileFilterUsed(false);
        
        int resultado = selector.showOpenDialog(null);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File selectedFile = selector.getSelectedFile();
            this.ventana.verTxtEnlace().setText(selectedFile.getAbsolutePath());
        }           
    }
}
