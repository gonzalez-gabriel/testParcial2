/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.controladores;

import autores.controladores.ControladorAutores;
import autores.modelos.Autor;
import autores.modelos.Cargo;
import autores.modelos.GestorAutores;
import grupos.controladores.ControladorGrupos;
import grupos.modelos.GestorGrupos;
import grupos.modelos.MiembroEnGrupo;
import grupos.modelos.Rol;
import idiomas.modelos.GestorIdiomas;
import interfaces.IControladorAutores;
import interfaces.IControladorGrupos;
import interfaces.IControladorLugares;
import interfaces.IControladorPrincipal;
import static interfaces.IControladorPrincipal.CONFIRMACION;
import static interfaces.IControladorPrincipal.TITULO;
import interfaces.IControladorPublicaciones;
import interfaces.IGestorAutores;
import interfaces.IGestorGrupos;
import interfaces.IGestorIdiomas;
import interfaces.IGestorLugares;
import interfaces.IGestorPalabrasClaves;
import interfaces.IGestorPublicaciones;
import interfaces.IGestorTipos;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import lugares.controladores.ControladorLugares;
import lugares.modelos.GestorLugares;
import palabrasclaves.modelos.GestorPalabrasClaves;
import palabrasclaves.modelos.PalabraClave;
import principal.vistas.VentanaPrincipal;
import publicaciones.controladores.ControladorPublicaciones;
import publicaciones.modelos.GestorPublicaciones;
import tipos.modelos.GestorTipos;



/**
 *
 * @author Ocón Santiago Luis
 */
public class ControladorPrincipal implements IControladorPrincipal {
    private VentanaPrincipal ventana;
    
    public ControladorPrincipal(){
        this.ventana = new VentanaPrincipal(this);
        this.ventana.setTitle(TITULO);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }
    
    public static void main(String[] args) {
        System.out.println("---> Creación de 4 grupos precargados.");
        IGestorGrupos gg = GestorGrupos.crear();
        System.out.println(gg.nuevoGrupo("Grupo 1", "Descripcion 1"));
        System.out.println(gg.nuevoGrupo("Grupo 2", "Descripcion 2"));
        System.out.println(gg.nuevoGrupo("Grupo 3", "Descripcion 3"));
        System.out.println(gg.nuevoGrupo("Grupo 4", "Descripcion 4"));

        System.out.println("---> Creación de 3 Profesores y 3 Alumnos.");
        IGestorAutores ga = GestorAutores.crear();
        System.out.println(ga.nuevoAutor(5, "Andrade", "Silvio", Cargo.TITULAR, "Clave5", "Clave5"));
        System.out.println(ga.nuevoAutor(4, "Soria", "Daniela", Cargo.ASOCIADO,"Clave4", "Clave4"));
        System.out.println(ga.nuevoAutor(6, "Rodriguez", "Alberto", Cargo.ADJUNTO, "Clave6", "Clave6"));
        System.out.println(ga.nuevoAutor(3, "Araoz", "Lucía", "3", "Clave3", "Clave3"));
        System.out.println(ga.nuevoAutor(1, "Cardozo", "Cristian", "1", "Clave1", "Clave1"));        
        System.out.println(ga.nuevoAutor(2, "Mendoza", "Micaela", "2", "Clave2", "Clave2"));  
        
        System.out.println("---> Creación de 4 tipos de publicaciones.");
        IGestorTipos gt = GestorTipos.crear();
        System.out.println(gt.nuevoTipo("Tesis"));
        System.out.println(gt.nuevoTipo("Investigación"));
        System.out.println(gt.nuevoTipo("Ensayo"));
        System.out.println(gt.nuevoTipo("Reporte"));

        System.out.println("---> Creación de 4 lugares.");
        IGestorLugares gl = GestorLugares.crear();
        System.out.println(gl.nuevoLugar("Universidad"));
        System.out.println(gl.nuevoLugar("Centro de investigación"));
        System.out.println(gl.nuevoLugar("Ministerio"));
        System.out.println(gl.nuevoLugar("Laboratorio"));

        System.out.println("---> Creación de 4 idiomas.");
        IGestorIdiomas gi = GestorIdiomas.crear();
        System.out.println(gi.nuevoIdioma("Español"));
        System.out.println(gi.nuevoIdioma("Inglés"));
        System.out.println(gi.nuevoIdioma("Francés"));        
        System.out.println(gi.nuevoIdioma("Alemán"));

        System.out.println("---> Creación de 5 palabras clave");
        IGestorPalabrasClaves gpp = GestorPalabrasClaves.crear();
        System.out.println(gpp.nuevaPalabraClave("Tecnología"));
        System.out.println(gpp.nuevaPalabraClave("Salud"));
        System.out.println(gpp.nuevaPalabraClave("Innovación"));
        System.out.println(gpp.nuevaPalabraClave("Ciencia"));
        System.out.println(gpp.nuevaPalabraClave("Medio Ambiente"));
        
        System.out.println("---> Se le asignan grupos al primer autor ordeno alfabéticamente");
        ga.verAutores().get(0).agregarGrupo(gg.verGrupos().get(0), Rol.ADMINISTRADOR);
        ga.verAutores().get(0).agregarGrupo(gg.verGrupos().get(1), Rol.ADMINISTRADOR);
        ga.verAutores().get(0).agregarGrupo(gg.verGrupos().get(2), Rol.ADMINISTRADOR);

        System.out.println("---> Se le asignan miembros a los grupos");
        gg.verGrupos().get(0).agregarMiembro(ga.verAutores().get(0), Rol.COLABORADOR);
        gg.verGrupos().get(0).agregarMiembro(ga.verAutores().get(1), Rol.COLABORADOR);
        gg.verGrupos().get(0).agregarMiembro(ga.verAutores().get(2), Rol.COLABORADOR);
        gg.verGrupos().get(0).agregarMiembro(ga.verAutores().get(3), Rol.COLABORADOR);
        
        System.out.println("---> Creación de 3 publicaciones");
        IGestorPublicaciones gp = GestorPublicaciones.crear();

        MiembroEnGrupo mg1 = gg.verGrupos().get(0).verMiembros().get(0);  
        LocalDate fecha1 = LocalDate.of(2021, 12, 11);
        ArrayList<PalabraClave> palabras1 = new ArrayList<>();
        palabras1.add(gpp.verPalabrasClaves().get(0));
        palabras1.add(gpp.verPalabrasClaves().get(1));
        palabras1.add(gpp.verPalabrasClaves().get(2));
        System.out.println(gp.nuevaPublicacion("Título1", mg1, fecha1, gt.verTipos().get(0), gi.verIdiomas().get(0), gl.verLugares().get(0), palabras1, "ENLACE1", "Este es el resumen 1."));
        
        MiembroEnGrupo mg2 = gg.verGrupos().get(1).verMiembros().get(0);  
        LocalDate fecha2 = LocalDate.of(2021, 12, 10);
        ArrayList<PalabraClave> palabras2 = new ArrayList<>();
        palabras2.add(gpp.verPalabrasClaves().get(0));
        palabras2.add(gpp.verPalabrasClaves().get(3));
        palabras2.add(gpp.verPalabrasClaves().get(4));
        System.out.println(gp.nuevaPublicacion("Título2", mg2, fecha2, gt.verTipos().get(1), gi.verIdiomas().get(1), gl.verLugares().get(1), palabras2, "ENLACE2", "Este es el resumen 2."));
        
        MiembroEnGrupo mg3 = gg.verGrupos().get(2).verMiembros().get(0);  
        LocalDate fecha3 = LocalDate.of(2018, 04, 10);
        ArrayList<PalabraClave> palabras3 = new ArrayList<>();
        palabras3.add(gpp.verPalabrasClaves().get(1));
        palabras3.add(gpp.verPalabrasClaves().get(4));        
        System.out.println(gp.nuevaPublicacion("Título3", mg3, fecha3, gt.verTipos().get(2), gi.verIdiomas().get(2), gl.verLugares().get(2), palabras3, "ENLACE3", "Este es el resumen 3."));
        
        IControladorPrincipal cp = new ControladorPrincipal();   
    }

    @Override
    public void btnAutoresClic(ActionEvent evt) {
        IControladorAutores ca = new ControladorAutores(this.ventana);
    }

    @Override
    public void btnSalirClic(ActionEvent evt) {
        int eleccion = JOptionPane.showOptionDialog(this.ventana, CONFIRMACION, TITULO, 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Si", "No"}, this);
        if (eleccion == JOptionPane.YES_OPTION) 
            System.exit(0);
    }

    @Override
    public void btnLugaresClic(ActionEvent evt) {
        IControladorLugares cl = new ControladorLugares(this.ventana);
    }

    @Override
    public void btnPublicacionesClic(ActionEvent evt) {
        IGestorAutores ga = GestorAutores.crear();
        Autor autor = ga.verAutores().get(0);
        IControladorPublicaciones cp = new ControladorPublicaciones(this.ventana,autor);
    }

    @Override
    public void btnGruposClic(ActionEvent evt) {
        IControladorGrupos cg = new ControladorGrupos(this.ventana);
    }
}