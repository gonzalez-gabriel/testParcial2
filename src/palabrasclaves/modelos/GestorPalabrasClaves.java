/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palabrasclaves.modelos;

import interfaces.IGestorPalabrasClaves;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Ocón Santiago Luis
 */
public class GestorPalabrasClaves implements IGestorPalabrasClaves {
    public static GestorPalabrasClaves gestor;    
    private ArrayList<PalabraClave> palabrasClaves = new ArrayList<>();
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
}
