/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import grupos.modelos.Grupo;
import java.util.ArrayList;

/**
 *
 * @author Ocón Santiago Luis
 */
public interface IGestorGrupos {
    public static final String MSJ_OK = "Grupo creado con éxito.";
    public static final String MSJ_REP = "Ya existe este grupo.";
    public static final String MSJ_ERROR = "Error.";    
    public static final String MSJ_MOD_OK = "Descripción modificada.";
    public static final String MSJ_MOD_ERROR = "Error: descripción no modificada.";
    public static final String MSJ_MOD_BLANCO = "Descripción ingresada en blanco.";
    public static final String MSJ_MOD_SINGRUPO = "No existe este grupo.";
    
    public String nuevoGrupo(String nombre, String descripcion);
    public String modificarGrupo(Grupo grupo, String descripcion);
    public ArrayList<Grupo> verGrupos();
    public Grupo verGrupo(String nombre);
    public boolean existeEsteGrupo(Grupo grupo);
}
