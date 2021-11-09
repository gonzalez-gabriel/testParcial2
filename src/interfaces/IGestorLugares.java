/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.ArrayList;
import lugares.modelos.Lugar;

/**
 *
 * @author Ocón Santiago Luis
 */
public interface IGestorLugares {
    public static final String MSJ_OK = "Lugar creado con éxito.";
    public static final String MSJ_REP = "Ya existe este lugar.";
    public static final String MSJ_ERROR = "Error.";    
    
    public String nuevoLugar(String nombre);
    public ArrayList<Lugar> verLugares();
    public Lugar verLugar(String nombre);
}
