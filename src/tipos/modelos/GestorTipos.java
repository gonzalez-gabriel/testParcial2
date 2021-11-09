/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipos.modelos;

import interfaces.IGestorTipos;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Ocón Santiago Luis
 */
public class GestorTipos implements IGestorTipos {
    private ArrayList<Tipo> tipos= new ArrayList<>();    
    public static GestorTipos gestor;
    Comparator<Tipo> comparadorTipos=(Tipo tipoA, Tipo tipoB) -> tipoA.verNombre().compareTo(tipoB.verNombre());
    
    private GestorTipos() {
    }
    
    public static GestorTipos crear(){
        if(gestor == null){
            gestor = new GestorTipos();
        }
        return gestor;
    }

    @Override
    public String nuevoTipo(String nombre) {
        if((!nombre.isBlank()) && (nombre!= null)){
            Tipo tipoNuevo= new Tipo(nombre);              
            if(!this.tipos.contains(tipoNuevo)){
                this.tipos.add(tipoNuevo);
                return MSJ_OK;
            }
            else 
                return MSJ_REP;
        }
        else
            return MSJ_ERROR;
    }

    @Override
    public ArrayList<Tipo> verTipos() {
        this.tipos.sort(comparadorTipos);
        return this.tipos;
    }

    @Override
    public Tipo verTipo(String nombre) {
        Tipo tipoNuevo = new Tipo(nombre); 
        int index = this.tipos.indexOf(tipoNuevo);
        if(index == (-1))
            return null;
        else
            return tipos.get(index);
    }
}
