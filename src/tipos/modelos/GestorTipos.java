/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipos.modelos;

import interfaces.IGestorPublicaciones;
import interfaces.IGestorTipos;
import java.util.ArrayList;
import java.util.Comparator;
import publicaciones.modelos.GestorPublicaciones;
import publicaciones.modelos.Publicacion;

/**
 *
 * @author Ocón Santiago Luis
 */
public class GestorTipos implements IGestorTipos {
    private ArrayList<Tipo> tipos= new ArrayList<>();    
    public static GestorTipos gestor;
    IGestorPublicaciones gestorPublicaciones = GestorPublicaciones.crear();
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

    @Override
    public String borrarTipo(Tipo tipo) {
        if((tipo!=null) && (!tipo.verNombre().isBlank())){
            for(Publicacion p: gestorPublicaciones.verPublicaciones()) {
                if(tipo.equals(p.verTipo()))
                    return MSJ_REP;
            }
            if(this.existeEsteTipo(tipo)) {
                this.tipos.remove(tipo);
                return MSJ_OK_BORRAR;
            }                
            else
                return MSJ_ERROR;
        }                  
        else 
            return MSJ_ERROR;
    }

    @Override
    public ArrayList<Tipo> buscarTipos(String nombre) {
        ArrayList<Tipo> tiposBuscados = new ArrayList<>();
        if((nombre!= null) && (!nombre.isBlank())){
            for(Tipo t: tipos){
                if((t.verNombre().equals(nombre)) || (t.verNombre().contains(nombre))){ 
                    if((!tiposBuscados.contains(t)))
                        tiposBuscados.add(t);
                }
            }
            if(tiposBuscados != null){
                tiposBuscados.sort(comparadorTipos);
                return tiposBuscados;
            }
        }
        return tiposBuscados; 
    }

    @Override
    public boolean existeEsteTipo(Tipo tipo) {
        for(Tipo t: this.tipos){
            if(t.equals(tipo))
                return true;
        }
        return false;
    }
}
