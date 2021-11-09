/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import autores.modelos.Autor;
import grupos.modelos.MiembroEnGrupo;
import idiomas.modelos.Idioma;
import java.time.LocalDate;
import java.util.ArrayList;
import lugares.modelos.Lugar;
import palabrasclaves.modelos.PalabraClave;
import publicaciones.modelos.Publicacion;
import tipos.modelos.Tipo;

/**
 *
 * @author Ocón Santiago Luis
 */
public interface IGestorPublicaciones {
    public static final String MSJ_OK = "Publicación creada con éxito.";
    public static final String MSJ_REP = "Ya existe esta publicación.";
    public static final String MSJ_ERROR = "Error.";    
    public static final String MSJ_MOD_OK = "Publicación modificada.";
    public static final String MSJ_MOD_ERROR = "Error: publicación no modificada.";
    public static final String MSJ_MOD_BLANCO = "Modificación ingresada en blanco.";
    public static final String MSJ_MOD_SINPUBLICACION = "No existe esta publicación."; 
    
    public String nuevaPublicacion(String titulo, MiembroEnGrupo miembroEnGrupo, LocalDate fechaPublicacion, Tipo tipo, Idioma idioma, Lugar lugar, ArrayList<PalabraClave> palabrasClaves, String enlace, String resumen);
    public String modificarPublicacion(Publicacion publicacion, MiembroEnGrupo miembroEnGrupo, LocalDate fechaPublicacion, Tipo tipo, Idioma idioma, Lugar lugar, ArrayList<PalabraClave> palabrasClaves, String enlace, String resumen);
    public boolean hayPublicacionesConEstaPalabraClave(PalabraClave palabraClave);
    public boolean hayPublicacionesConEsteLugar(Lugar lugar);
    public boolean hayPublicacionesConEsteTipo(Tipo tipo);
    public boolean hayPublicacionesConEsteIdioma(Idioma idioma);
    public boolean hayPublicacionesConEsteAutor(Autor autor);
    public boolean existeEstaPublicacion(Publicacion publicacion);
    public ArrayList<Publicacion> verPublicaciones();
    public Publicacion verPublicacion(String titulo);
}
