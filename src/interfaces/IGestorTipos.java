/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import tipos.modelos.Tipo;

/**
 *
 * @author Ocón Santiago Luis
 */
public interface IGestorTipos {
    public static final String MSJ_OK = "Tipo creado con éxito.";
    public static final String MSJ_REP = "Ya existe este tipo.";
    public static final String MSJ_ERROR = "Error.";    
    
    public String nuevoTipo(String nombre);
    public ArrayList<Tipo> verTipos();
    public Tipo verTipo(String nombre);
}
