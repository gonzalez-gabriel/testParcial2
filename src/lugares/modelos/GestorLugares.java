/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lugares.modelos;

import interfaces.IGestorLugares;
import interfaces.IGestorPublicaciones;
import java.util.ArrayList;
import java.util.Comparator;
import publicaciones.modelos.GestorPublicaciones;
import publicaciones.modelos.Publicacion;

/**
 *
 * @author Ocón Santiago Luis
 */
public class GestorLugares implements IGestorLugares {
    public static GestorLugares gestor;    
    private ArrayList<Lugar> lugares = new ArrayList<>();
    IGestorPublicaciones gestorPublicaciones = GestorPublicaciones.crear();
    Comparator<Lugar> comparadorLugares = (Lugar lugarA, Lugar lugarB) -> lugarA.verNombre().compareTo(lugarB.verNombre());    
        
    private GestorLugares() {
    }
    
    public static GestorLugares crear(){
        if(gestor == null){
            gestor = new GestorLugares();
        }
        return gestor;
    }
    
    @Override
    public String nuevoLugar(String nombre) {
        if((nombre!= null) && (!nombre.isBlank())){
            Lugar lugarNuevo = new Lugar(nombre);              
            if(!this.lugares.contains(lugarNuevo)) {
                this.lugares.add(lugarNuevo);
                return MSJ_OK;
            }
            else 
                return MSJ_REP;
        }
        else
            return MSJ_ERROR;
    }

    @Override
    public ArrayList<Lugar> verLugares() {
       this.lugares.sort(comparadorLugares);
       return this.lugares;
    }

    @Override
    public Lugar verLugar(String nombre) {
        Lugar lugarNuevo= new Lugar(nombre); 
        int index = lugares.indexOf(lugarNuevo);
        if(index == (-1))
            return null;
        else
            return lugares.get(index);
    }

    @Override
    public String borrarLugar(Lugar lugar) {
        if((lugar!=null) && (!lugar.verNombre().isBlank())){
            for(Publicacion p: gestorPublicaciones.verPublicaciones()) {
                if(lugar.equals(p.verLugar()))
                    return MSJ_REP;
            }
            if(this.existeEsteLugar(lugar)) {
                this.lugares.remove(lugar);
                return MSJ_OK_BORRAR;
            }                
            else
                return MSJ_ERROR;
        }                  
        else 
            return MSJ_ERROR;
    }

    @Override
    public ArrayList<Lugar> buscarLugares(String nombre) {
        ArrayList<Lugar> lugaresBuscados = new ArrayList<>();
        if((nombre!= null) && (!nombre.isBlank())){
            for(Lugar l: lugares){
                if((l.verNombre().equals(nombre)) || (l.verNombre().startsWith(nombre))){
                    if((!lugaresBuscados.contains(l)))
                        lugaresBuscados.add(l);
                } 
            }
            if(lugaresBuscados != null){
                lugaresBuscados.sort(comparadorLugares);
                return lugaresBuscados;
            }      
        }
        return lugaresBuscados;
    }

    @Override
    public boolean existeEsteLugar(Lugar lugar) {
        for(Lugar t: this.lugares){
            if(t.equals(lugar))
                return true;
        }
        return false;
    }
}
