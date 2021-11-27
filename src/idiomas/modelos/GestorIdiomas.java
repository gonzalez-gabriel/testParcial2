/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idiomas.modelos;

import interfaces.IGestorIdiomas;
import interfaces.IGestorPublicaciones;
import java.util.ArrayList;
import java.util.Comparator;
import publicaciones.modelos.GestorPublicaciones;
import publicaciones.modelos.Publicacion;

/**
 *
 * @author Ocón Santiago Luis
 */
public class GestorIdiomas implements IGestorIdiomas{
    public static GestorIdiomas gestor;    
    private ArrayList<Idioma> idiomas = new ArrayList<>();
    IGestorPublicaciones gestorPublicaciones = GestorPublicaciones.crear();
    Comparator<Idioma> comparadorIdiomas =(Idioma idiomaA, Idioma idiomaB) -> idiomaA.verNombre().compareTo(idiomaB.verNombre());    
    
    private GestorIdiomas() {
    }
    
    public static GestorIdiomas crear(){
        if(gestor == null){
            gestor = new GestorIdiomas();
        }
        return gestor;
    }

    @Override
    public String nuevoIdioma(String nombre) {
        if((nombre!= null) && (!nombre.isBlank())){
            Idioma idiomaNuevo = new Idioma(nombre);              
            if(!this.idiomas.contains(idiomaNuevo)) {
                this.idiomas.add(idiomaNuevo);
                return MSJ_OK;
            }
            else 
                return MSJ_REP;
        }
        else
            return MSJ_ERROR;
    }

    @Override
    public ArrayList<Idioma> verIdiomas() {
       this.idiomas.sort(comparadorIdiomas);
       return this.idiomas;
    }

    @Override
    public Idioma verIdioma(String nombre) {
        Idioma idiomaNuevo= new Idioma(nombre); 
        int index = idiomas.indexOf(idiomaNuevo);
        if(index == (-1))
            return null;
        else
            return idiomas.get(index);
    }

    @Override
    public String borrarIdioma(Idioma idioma) {
        if((idioma!=null) && (!idioma.verNombre().isBlank())){
            for(Publicacion p: gestorPublicaciones.verPublicaciones()) {
                if(idioma.equals(p.verIdioma()))
                    return MSJ_REP;
            }
            if(this.existeEsteIdioma(idioma)) {
                this.idiomas.remove(idioma);
                return MSJ_OK_BORRAR;
            }                
            else
                return MSJ_ERROR;
        }                  
        else 
            return MSJ_ERROR;
    }

    @Override
    public ArrayList<Idioma> buscarIdiomas(String nombre) {
        ArrayList<Idioma> idiomasBuscados = new ArrayList<>();
        if((nombre!= null) && (!nombre.isBlank())){
            for(Idioma i: idiomas){
                if((i.verNombre().equals(nombre)) || (i.verNombre().startsWith(nombre))){
                    if((!idiomasBuscados.contains(i)))
                        idiomasBuscados.add(i);
                } 
            }
            if(idiomasBuscados != null){
                idiomasBuscados.sort(comparadorIdiomas);
                return idiomasBuscados;
            }      
        }
        return idiomasBuscados;
    }

    @Override
    public boolean existeEsteIdioma(Idioma idioma) {
        for(Idioma i: this.idiomas){
            if(i.equals(idioma))
                return true;   
        }
        return false;
    }
}
