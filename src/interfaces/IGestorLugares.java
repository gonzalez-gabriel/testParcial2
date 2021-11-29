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
    public static final String MSJ_OK_BORRAR = "Lugar borrado con éxito.";
    public static final String MSJ_OK_ARCHIVO = "Archivo guardado con éxito.";
    public static final String MSJ_ERROR_ARCHIVO = "No se pudo guardar el archivo.";
    public static final String MSJ_OK_LECTURA = "Archivo recuperado con éxito.";
    public static final String MSJ_ERROR_LECTURA = "No se pudo leer el archivo.";
    
    public String nuevoLugar(String nombre);
    public String borrarLugar(Lugar lugar);
    public ArrayList<Lugar> buscarLugares(String nombre);
    public ArrayList<Lugar> verLugares();
    public Lugar verLugar(String nombre);
    public boolean existeEsteLugar(Lugar lugar);
}
