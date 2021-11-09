/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupos.modelos;

import interfaces.IGestorGrupos;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Ocón Santiago Luis
 */
public class GestorGrupos implements IGestorGrupos{
    public static GestorGrupos gestor;    
    private ArrayList<Grupo> grupos = new ArrayList<>();
    Comparator<Grupo> comparadorGrupos=(Grupo grupoA, Grupo grupoB) -> grupoA.verNombre().compareTo(grupoB.verNombre());    
        
    private GestorGrupos() {
    }
    
    public static GestorGrupos crear(){
        if(gestor == null){
            gestor = new GestorGrupos();
        }
        return gestor;
    }
    
    @Override
    public String nuevoGrupo(String nombre, String descripcion) {
        if((nombre!= null) && (!nombre.isBlank())){
            Grupo grupoNuevo = new Grupo(nombre,descripcion);              
            if(!this.grupos.contains(grupoNuevo)) {
                this.grupos.add(grupoNuevo);
                return MSJ_OK;
            }
            else
                return MSJ_REP;
        }
        else 
            return MSJ_ERROR;  
    }

    @Override
    public String modificarGrupo(Grupo grupo, String descripcion) {
        if(grupos.contains(grupo)){
            if((descripcion != null) && (!descripcion.isBlank())) {
                int indice=grupos.indexOf(grupo);
                if(indice == (-1)){
                    return MSJ_MOD_ERROR;
                }
                else {
                    grupos.get(indice).asignarDescripcion(descripcion);
                    return MSJ_MOD_OK;
                }
            }
            else
                return MSJ_MOD_BLANCO;
        }
        else
            return MSJ_MOD_SINGRUPO;
    }

    @Override
    public ArrayList<Grupo> verGrupos() {
        this.grupos.sort(comparadorGrupos); 
        return this.grupos;
    }

    @Override
    public Grupo verGrupo(String nombre) {
        Grupo grupoBuscado = new Grupo(nombre,null);
        int index = grupos.indexOf(grupoBuscado);
        if(index == (-1))
            return null;
        else
            return grupos.get(index);
    }

    @Override
    public boolean existeEsteGrupo(Grupo grupo) {
        if(grupos.contains(grupo))
            return true;
        else
            return false;
    }
}
