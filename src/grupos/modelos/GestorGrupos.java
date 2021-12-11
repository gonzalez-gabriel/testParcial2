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
        for(Grupo g: this.grupos){
            if(g.equals(grupo))
                return true;   
        }
        return false;
    }
    
    public void mostrarGrupos(ArrayList<Grupo> grupos){
        for(Grupo g: grupos)
            g.mostrar();
    }

    @Override
    public String borrarGrupo(Grupo grupo) {
        if((grupo!=null)&&(!grupo.verNombre().isBlank())){
            if(grupo.tieneMiembros())
                return MSJ_ERROR_BORRAR;
            if(this.existeEsteGrupo(grupo)) {
                this.grupos.remove(grupo);
                return MSJ_OK_BORRAR;
            }                
            else
                return MSJ_ERROR;
        }                  
        else 
            return MSJ_ERROR;
    }

    @Override
    public ArrayList<Grupo> buscarGrupos(String nombre) {
        ArrayList<Grupo> gruposBuscados = new ArrayList<>();
        if((nombre!= null) && (!nombre.isBlank())){
            for(Grupo g: grupos){
                if((g.verNombre().equals(nombre)) || (g.verNombre().startsWith(nombre))){
                    if((!gruposBuscados.contains(g)))
                     gruposBuscados.add(g);
                } 
            }
            if(gruposBuscados != null){
                gruposBuscados.sort(comparadorGrupos);
                return gruposBuscados;
            }      
        }
        return gruposBuscados;
    }

    @Override
    public String agregarMiembros(Grupo grupo, ArrayList<MiembroEnGrupo> miembros) {
        if(this.existeEsteGrupo(grupo)) {
            if((miembros!=null) && (!miembros.isEmpty()) ) {
                for(MiembroEnGrupo m: miembros){
                    if(!grupo.verMiembros().contains(m))
                        grupo.agregarMiembro(m.verAutor(), m.verRol());
                }
                return EXITO_MIEMBROS;
            }
            else
                return MIEMBROS_INEXISTENTES;
        }
        return GRUPO_INEXISTENTE;
    }

    @Override
    public String quitarMiembros(Grupo grupo, ArrayList<MiembroEnGrupo> miembros) {
        if (this.existeEsteGrupo(grupo)) {
            if((miembros!=null) && (!miembros.isEmpty()) ) {
                for(MiembroEnGrupo m: miembros){
                    if(grupo.verMiembros().contains(m))
                        grupo.quitarMiembro(m.verAutor());
                }
                return EXITO_MIEMBROS;
            }
            else
                return MIEMBROS_INEXISTENTES;
        }
        return GRUPO_INEXISTENTE;
    }

    @Override
    public String actualizarGrupos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates
    }
}
