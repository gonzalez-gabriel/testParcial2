/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import idiomas.modelos.Idioma;
import java.util.ArrayList;

/**
 *
 * @author Ocón Santiago Luis
 */
public interface IGestorIdiomas {
    public static final String MSJ_OK = "Idioma creado con éxito.";
    public static final String MSJ_REP = "Ya existe este idioma.";
    public static final String MSJ_ERROR = "Error.";    
    
    public String nuevoIdioma(String nombre);
    public ArrayList<Idioma> verIdiomas();
    public Idioma verIdioma(String nombre);
}
