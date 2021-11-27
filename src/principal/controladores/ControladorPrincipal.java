/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.controladores;


import autores.modelos.Autor;
import autores.modelos.Cargo;
import autores.modelos.GestorAutores;
import grupos.modelos.GestorGrupos;
import grupos.modelos.MiembroEnGrupo;
import grupos.modelos.Rol;
import idiomas.modelos.GestorIdiomas;
import idiomas.modelos.Idioma;
import java.time.LocalDate;
import java.util.Arrays;
import lugares.modelos.GestorLugares;
import lugares.modelos.Lugar;
import palabrasclaves.modelos.GestorPalabrasClaves;
import palabrasclaves.modelos.PalabraClave;
import publicaciones.modelos.GestorPublicaciones;
import tipos.modelos.GestorTipos;
import tipos.modelos.Tipo;


/**
 *
 * @author Cel
 */
public class ControladorPrincipal {
    public static void main(String[] args) {
    //<editor-fold defaultstate="collapsed" desc="Sin intefaz gráfica"> 
        
    // Creo los diversos gestores!
    GestorTipos gt1 = GestorTipos.crear();
    GestorTipos gt2 = GestorTipos.crear();
    
    GestorPalabrasClaves gpc1 = GestorPalabrasClaves.crear();
    GestorPalabrasClaves gpc2 = GestorPalabrasClaves.crear();
    
    GestorLugares gl1 = GestorLugares.crear();
    GestorLugares gl2 = GestorLugares.crear();
    
    GestorIdiomas gi1 = GestorIdiomas.crear();
    GestorIdiomas gi2 = GestorIdiomas.crear();
    
    GestorGrupos gg1 = GestorGrupos.crear();
    GestorGrupos gg2 = GestorGrupos.crear();
    
    GestorAutores ga1 = GestorAutores.crear();
    GestorAutores ga2 = GestorAutores.crear();
    
    GestorPublicaciones gp1 = GestorPublicaciones.crear();
    GestorPublicaciones gp2 = GestorPublicaciones.crear();
    
    // Pruebo métodos de GestorTipos considerar que PalabrasClaves, Lugares e Idiomas son identicos.
    System.out.println("Intento crear tipo1: " + gt1.nuevoTipo("tipo1"));
    System.out.println("Intento crear tipo1 de nuevo: " + gt1.nuevoTipo("tipo1")); //repetido
    System.out.println("Intento crear un tipo en blanco: " + gt1.nuevoTipo(" ")); // blanco
    System.out.println("Intento crear tipo2: " + gt1.nuevoTipo("tipo2"));
    System.out.println("Intento ver todos los tipos: " + gt1.verTipos());
    System.out.println("Intento ver si existe tipo1: " + gt1.existeEsteTipo(gt1.verTipo("tipo1")));
    System.out.println("Intento ver si existe tipo3: " + gt1.existeEsteTipo(gt1.verTipo("tipo3")));
    System.out.println("Intento ver todos los tipos pero de Gestor2: " + gt2.verTipos());
    System.out.println("Intento crear tipo tesis: " + gt1.nuevoTipo("tesis"));
    System.out.println("Intento crear tipo informe: " + gt1.nuevoTipo("informe"));
    System.out.println("Intento crear tipo documento: " + gt1.nuevoTipo("documento")); 
    System.out.println("Intento ver todos los tipos ordenados: " + gt1.verTipos());
    System.out.println("Intento borrar tipo informe: " + gt2.borrarTipo(gt1.verTipo("informe")));
    System.out.println("Intento ver todos los tipos ordenados: " + gt1.verTipos());
    System.out.println("Intento buscar tipos que contengan las letras ti: ");
    for(Tipo t: gt1.buscarTipos("ti")){
        System.out.println(t.verNombre());
    }
    System.out.println("Intento buscar tipos que contengan la letra t: ");
    for(Tipo t: gt1.buscarTipos("t")){
        System.out.println(t.verNombre());
    }
    System.out.println("\n\n");
    
    // Pruebo funcionamiento de GestorGrupos
    System.out.println("Intento crear grupo1 con descripcion1: " + gg1.nuevoGrupo("grupo1", "descripcion1"));
    System.out.println("Intento crear grupo1 con descripcion1 de nuevo: " + gg1.nuevoGrupo("grupo1", "descripcion1"));
    System.out.println("Intento crear grupo2 con descripcion2: " + gg1.nuevoGrupo("grupo2", "descripcion2"));
    System.out.print("Intento ver todos los grupos: ");
    gg1.mostrarGrupos(gg1.verGrupos());
    System.out.println("\nIntento ver solo a grupo 1: " + gg1.verGrupo("grupo1").verNombre() + ", " + gg1.verGrupo("grupo1").verDescripcion());
    System.out.println("Intento modificar descripcion grupo1: " + gg1.modificarGrupo(gg1.verGrupo("grupo1"), "nuevaDescripcion1"));
    System.out.println("Intento ver cambios en grupo 1: " + gg1.verGrupo("grupo1").verNombre() + ", " + gg1.verGrupo("grupo1").verDescripcion());
    System.out.println("Intento ver si existe grupo1: " + gg1.existeEsteGrupo(gg1.verGrupo("grupo1")));
    System.out.println("Intento ver si existe grupo3: " + gg1.existeEsteGrupo(gg1.verGrupo("grupo3")));
    System.out.println("\n\n");
    
    // Pruebo funcionamiento de GestorAutores
    System.out.println("Intento crear autor1(profesor): " + ga1.nuevoAutor(11111111, "apellido1", "nombre1", Cargo.TITULAR, "010101", "010101"));
    System.out.println("Intento crear autor2(alumno): " + ga1.nuevoAutor(22222222, "apellido2", "nombre2", "99999", "020202", "020202"));
    System.out.println("Intento crear autor3(alumno) repetido cx: " + ga1.nuevoAutor(33333333, "apellido3", "nombre3", "99999", "030303", "030303"));
    System.out.println("Intento crear autor3(alumno) repetido dni: " + ga1.nuevoAutor(11111111, "apellido3", "nombre3", "545454", "030303", "030303"));
    System.out.println("Intento crear autor3(profesor) repetido dni: " + ga1.nuevoAutor(22222222, "apellido3", "nombre3", Cargo.ADG, "030303", "030303"));
    System.out.print("Inteno ver todos los autores: ");
    for (Autor a: ga1.verAutores()){
        a.mostrar();
    };
    System.out.println("\nIntento modificar autor1(profesor): " + ga1.modificarAutor(ga1.verAutor(11111111), "nuevoApellido1", "nuevoNombre1", Cargo.ADJUNTO, "111101", "111101"));
    System.out.print("Inteno ver todos los autores luego de la modificación: ");
    for (Autor a: ga1.verAutores()){
        a.mostrar();
    };
    System.out.println("\n\n");
    
    gpc1.nuevaPalabraClave("palabra1");
    gpc1.nuevaPalabraClave("palabra2");
    gpc1.nuevaPalabraClave("palabra3");
    //Pruebo funcionamiento de GestorPublicaciones
    System.out.println("Intento crear publicacion1: " + gp1.nuevaPublicacion("publicacion1",new MiembroEnGrupo(ga1.verAutor(11111111), gg1.verGrupo("grupo1"), Rol.ADMINISTRADOR), LocalDate.EPOCH, new Tipo("tipo1"), new Idioma("idioma1"),new Lugar("lugar1"), Arrays.asList(new PalabraClave[] {gpc1.verPalabraClave("palabra1"),gpc1.verPalabraClave("palabra2"),gpc1.verPalabraClave("palabra3")}), "enlace1", "resumen1"));
    gp1.verPublicacion("publicacion1").mostrar();
    System.out.println("Intento modificar publicacion1: " + gp1.modificarPublicacion(gp1.verPublicacion("publicacion1"), null, null, new Tipo ("Tipo nuevo"), null, null, null, null, null));
    System.out.println("Intento ver si hay publicaciones con palabra1: " + gp1.hayPublicacionesConEstaPalabraClave(new PalabraClave("palabra1")));
    System.out.println("Intento ver si hay publicaciones con tipo3: " + gp1.hayPublicacionesConEsteTipo(new Tipo("tipo3")));


    //</editor-fold>   
        
    //     //<editor-fold defaultstate="collapsed" desc="Intefaz gráfica"> 
//         VentanaAMGrupo ventanaGrupo = new VentanaAMGrupo(null); //se instancia la ventana
///*
////        ventanaGrupo.setLocationRelativeTo(null); //se centra la ventana
////        ventanaGrupo.setVisible(true); //se hace visible la ventana
//        
////        VentanaAMAlumno ventanaAlumno = new VentanaAMAlumno(null); //se instancia la ventana
////        ventanaAlumno.setLocationRelativeTo(null); //se centra la ventana
////        ventanaAlumno.setVisible(true); //se hace visible la ventana
//*/        
//        VentanaAMProfesor ventanaProfesor = new VentanaAMProfesor(null); //se instancia la ventana
//        ventanaProfesor.setLocationRelativeTo(null); //se centra la ventana
//        ventanaProfesor.setVisible(true); //se hace visible la ventana        
///*        
////        VentanaAIdioma ventanaIdioma = new VentanaAIdioma(null); //se instancia la ventana
////        ventanaIdioma.setLocationRelativeTo(null); //se centra la ventana
////        ventanaIdioma.setVisible(true); //se hace visible la ventana                
//        
////        VentanaALugar ventanaLugar = new VentanaALugar(null); //se instancia la ventana
////        ventanaLugar.setLocationRelativeTo(null); //se centra la ventana
////        ventanaLugar.setVisible(true); //se hace visible la ventana                        
//        
////        VentanaAPalabraClave ventanaPalabraClave = new VentanaAPalabraClave(null); //se instancia la ventana
////        ventanaPalabraClave.setLocationRelativeTo(null); //se centra la ventana
////        ventanaPalabraClave.setVisible(true); //se hace visible la ventana                                
//        
////        VentanaATipo ventanaTipo = new VentanaATipo(null); //se instancia la ventana
////        ventanaTipo.setLocationRelativeTo(null); //se centra la ventana
////        ventanaTipo.setVisible(true); //se hace visible la ventana   
//*/
//
//     //</editor-fold>
    }
}
