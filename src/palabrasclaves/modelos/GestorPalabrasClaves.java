/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palabrasclaves.modelos;

import interfaces.IGestorPalabrasClaves;
import interfaces.IGestorPublicaciones;
import java.util.ArrayList;
import java.util.Comparator;
import publicaciones.modelos.GestorPublicaciones;
import publicaciones.modelos.Publicacion;

/**
 *
 * @author Ocón Santiago Luis
 */
public class GestorPalabrasClaves implements IGestorPalabrasClaves {
    public static GestorPalabrasClaves gestor;    
    private ArrayList<PalabraClave> palabrasClaves = new ArrayList<>();
    IGestorPublicaciones gestorPublicaciones = GestorPublicaciones.crear();
    Comparator<PalabraClave> comparadorPC=(PalabraClave pcA, PalabraClave pcB) -> pcA.verNombre().compareTo(pcB.verNombre());    
    
    private GestorPalabrasClaves() {
    }
    
    public static GestorPalabrasClaves crear(){
        if(gestor == null){
            gestor = new GestorPalabrasClaves();
        }
        return gestor;
    }
    
    @Override
    public String nuevaPalabraClave(String nombre) {
        if((nombre!= null) && (!nombre.isBlank())){
            PalabraClave palabraClaveNueva= new PalabraClave(nombre);              
                if(!this.palabrasClaves.contains(palabraClaveNueva)){
                    this.palabrasClaves.add(palabraClaveNueva);
                    return MSJ_OK;
                }
                else 
                    return MSJ_REP;
        }
        else
            return MSJ_ERROR;
    }

    @Override
    public ArrayList<PalabraClave> verPalabrasClaves() {
        this.palabrasClaves.sort(comparadorPC);
        return this.palabrasClaves;
    }

    @Override
    public PalabraClave verPalabraClave(String nombre) {
        PalabraClave palabraClaveNueva = new PalabraClave(nombre); 
        int index = palabrasClaves.indexOf(palabraClaveNueva);
        if(index == (-1)) {
            return null;
        }
        else {
            return palabrasClaves.get(index);
        }
    }

    @Override
    public String borrarPalabraClave(PalabraClave palabraClave) {
        if((palabraClave!=null) && (!palabraClave.verNombre().isBlank())){
            for(Publicacion p: gestorPublicaciones.verPublicaciones()) {
                if(palabraClave.equals(p.verPalabrasClave()))
                    return MSJ_REP;
            }
            if(this.existeEstaPalabraClave(palabraClave)) {
                this.palabrasClaves.remove(palabraClave);
                return MSJ_OK_BORRAR;
            }                
            else
                return MSJ_ERROR;
        }                  
        else 
            return MSJ_ERROR;
    }

    @Override
    public ArrayList<PalabraClave> buscarPalabrasClaves(String nombre) {
        ArrayList<PalabraClave> palabrasClavesBuscadas= new ArrayList<>();
        if((nombre!= null) && (!nombre.isBlank())){
            for(PalabraClave p: palabrasClaves){
                if((p.verNombre().equals(nombre)) || (p.verNombre().startsWith(nombre))){
                    if((!palabrasClavesBuscadas.contains(p)))
                        palabrasClavesBuscadas.add(p);
                } 
            }
            if(palabrasClavesBuscadas != null){
                palabrasClavesBuscadas.sort(comparadorPC);
                return palabrasClavesBuscadas;
            }      
        }
        return palabrasClavesBuscadas;
    }

    @Override
    public boolean existeEstaPalabraClave(PalabraClave palabraClave) {
        for(PalabraClave pc: this.palabrasClaves){
            if(pc.equals(palabraClave))
                return true;
        }
        return false;
    }
}
