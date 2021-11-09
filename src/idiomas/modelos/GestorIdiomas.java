/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idiomas.modelos;

import interfaces.IGestorIdiomas;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Ocón Santiago Luis
 */
public class GestorIdiomas implements IGestorIdiomas{
    public static GestorIdiomas gestor;    
    private ArrayList<Idioma> idiomas = new ArrayList<>();
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
}
