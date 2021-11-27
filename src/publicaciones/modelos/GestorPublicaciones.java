/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicaciones.modelos;

import autores.modelos.Autor;
import grupos.modelos.MiembroEnGrupo;
import idiomas.modelos.Idioma;
import interfaces.IGestorPublicaciones;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lugares.modelos.Lugar;
import palabrasclaves.modelos.PalabraClave;
import tipos.modelos.Tipo;

/**
 *
 * @author Ocón Santiago Luis
 */
public class GestorPublicaciones implements IGestorPublicaciones{
    public static GestorPublicaciones gestor;    
    Comparator<Publicacion> comparadorPublicaciones=(Publicacion p1, Publicacion p2) -> p1.verTitulo().compareTo(p2.verTitulo());
    private ArrayList<Publicacion> publicaciones = new ArrayList<>();
    
    private GestorPublicaciones() {
    }
    
    public static GestorPublicaciones crear(){
        if(gestor == null){
            gestor = new GestorPublicaciones();
        }
        return gestor;
    }

    @Override
    public String nuevaPublicacion(String titulo, MiembroEnGrupo miembroEnGrupo, LocalDate fechaPublicacion, Tipo tipo, Idioma idioma, Lugar lugar, List<PalabraClave> palabrasClaves, String enlace, String resumen){
        if((titulo != null) && (!titulo.isBlank()) 
            && (miembroEnGrupo != null) && (miembroEnGrupo.verAutor() != null) && (miembroEnGrupo.verAutor().verDni() != 0) 
            && (miembroEnGrupo.verAutor().verApellidos() != null) && (!miembroEnGrupo.verAutor().verApellidos().isBlank())
            && (miembroEnGrupo.verAutor().verNombres() !=null) && (!miembroEnGrupo.verAutor().verNombres().isBlank())
            && (miembroEnGrupo.verGrupo() != null) && (miembroEnGrupo.verGrupo().verNombre() != null) && (!miembroEnGrupo.verGrupo().verNombre().isBlank())
            && (miembroEnGrupo.verGrupo().verDescripcion() != null) && (!miembroEnGrupo.verGrupo().verDescripcion().isBlank())
            && (miembroEnGrupo.verRol() != null) 
            && (fechaPublicacion !=null) 
            && (tipo != null) && (tipo.verNombre() != null) && (!tipo.verNombre().isBlank())
            && (idioma != null) && (idioma.verNombre() != null) && (!idioma.verNombre().isBlank())
            && (lugar != null) && (lugar.verNombre() != null) && (!lugar.verNombre().isBlank())
            && (palabrasClaves != null) && (!palabrasClaves.isEmpty()) 
            && (enlace != null) && (!enlace.isBlank())
            && (resumen != null) && (!resumen.isBlank())
            ){
            Publicacion nuevaPublicacion = new Publicacion(titulo, miembroEnGrupo, fechaPublicacion,tipo, idioma, lugar, palabrasClaves,enlace,resumen);
            if(!publicaciones.contains(nuevaPublicacion)){
                this.publicaciones.add(nuevaPublicacion);
                return MSJ_OK;
            }
            else
                return MSJ_REP;
        }
        else
            return MSJ_ERROR;
    }

    @Override
    public String modificarPublicacion(Publicacion publicacion, MiembroEnGrupo miembroEnGrupo, LocalDate fechaPublicacion, Tipo tipo, Idioma idioma, Lugar lugar, List<PalabraClave> palabrasClaves, String enlace, String resumen) {
        if(publicaciones.contains(publicacion)){
                if((miembroEnGrupo != null) && (miembroEnGrupo.verAutor() != null) && (miembroEnGrupo.verAutor().verDni() != 0) 
                    && (miembroEnGrupo.verAutor().verApellidos() != null) && (!miembroEnGrupo.verAutor().verApellidos().isBlank())
                    && (miembroEnGrupo.verAutor().verNombres() !=null) && (!miembroEnGrupo.verAutor().verNombres().isBlank())
                    && (miembroEnGrupo.verGrupo() != null) && (miembroEnGrupo.verGrupo().verNombre() != null) && (!miembroEnGrupo.verGrupo().verNombre().isBlank())
                    && (miembroEnGrupo.verGrupo().verDescripcion() != null) && (!miembroEnGrupo.verGrupo().verDescripcion().isBlank())
                    && (miembroEnGrupo.verRol() != null) 
                    && (fechaPublicacion !=null) 
                    && (tipo != null) && (tipo.verNombre() != null) && (!tipo.verNombre().isBlank())
                    && (idioma != null) && (idioma.verNombre() != null) && (!idioma.verNombre().isBlank())
                    && (lugar != null) && (lugar.verNombre() != null) && (!lugar.verNombre().isBlank())
                    && (palabrasClaves != null) && (!palabrasClaves.isEmpty()) 
                    && (enlace != null) && (!enlace.isBlank())
                    && (resumen != null) && (!resumen.isBlank())
                    ){
                    int index = publicaciones.indexOf(publicacion);
                    if(index == (-1))
                        return MSJ_MOD_ERROR;
                    else {
                        publicaciones.get(index).asignarMiembroEnGrupo(miembroEnGrupo);
                        publicaciones.get(index).asignarFecha(fechaPublicacion);
                        publicaciones.get(index).asignarTipo(tipo);
                        publicaciones.get(index).asignarIdioma(idioma);
                        publicaciones.get(index).asignarLugar(lugar);
                        publicaciones.get(index).asignarPalabras((ArrayList<PalabraClave>) palabrasClaves);
                        publicaciones.get(index).asignarEnlace(enlace);
                        publicaciones.get(index).asignarResumen(resumen);
                        return MSJ_MOD_OK;
                    }
                }
                else
                    return MSJ_MOD_BLANCO;
        }
        else
            return MSJ_MOD_SINPUBLICACION;
    }

    @Override
    public boolean hayPublicacionesConEstaPalabraClave(PalabraClave palabraClave) {
        if((palabraClave != null) && (!palabraClave.verNombre().isBlank()) ){
            for(Publicacion p: publicaciones){
                for(PalabraClave c: p.verPalabrasClave()){
                    if(c.equals(palabraClave))
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hayPublicacionesConEsteLugar(Lugar lugar) {
        if((lugar != null) && (!lugar.verNombre().isBlank())){  
            for(Publicacion p: publicaciones){
                if(lugar.equals(p.verLugar()))
                    return true;   
            }
        }
        return false;
    }

    @Override
    public boolean hayPublicacionesConEsteTipo(Tipo tipo) {
        if((tipo != null) && (!tipo.verNombre().isBlank())){  
            for(Publicacion p: publicaciones){
                if(tipo.equals(p.verTipo()))
                    return true;   
            }
        }
        return false;
    }

    @Override
    public boolean hayPublicacionesConEsteIdioma(Idioma idioma) {
        if((idioma != null) && (!idioma.verNombre().isBlank())){  
            for(Publicacion p: publicaciones){
                if(idioma.equals(p.verIdioma()))
                    return true;   
            }
        }
        return false;
    }

    @Override
    public boolean hayPublicacionesConEsteAutor(Autor autor) {
        if((autor != null) && (autor.verDni() !=0) && (autor.verApellidos() != null) && (!autor.verApellidos().isBlank()) && (autor.verNombres() !=null) && (!autor.verNombres().isBlank())) {
            for(Publicacion p: publicaciones){
                if(autor.equals(p.verMiembroEnGrupo().verAutor()))
                    return true;   
            }
        }
        return false;
    }

    @Override
    public boolean existeEstaPublicacion(Publicacion publicacion) {
        if(publicaciones.contains(publicacion)){
                return true;
        }
        return false;
    }

    @Override
    public ArrayList<Publicacion> verPublicaciones() {
        this.publicaciones.sort(comparadorPublicaciones);
        return this.publicaciones;
    }

    @Override
    public Publicacion verPublicacion(String titulo) {
        Publicacion publicacionBuscada= new Publicacion(titulo,null,null,null,null,null,null,null,null);
        int index = publicaciones.indexOf(publicacionBuscada);
        if(index != (-1))
            return publicaciones.get(index);
        else
            return null;
    }

    @Override
    public String borrarPublicacion(Publicacion publicacion) {
        if(this.existeEstaPublicacion(publicacion)){
            this.publicaciones.remove(publicacion);
            return MSJ_OK_BORRAR;
        }
        return MSJ_MOD_SINPUBLICACION;
    }

    @Override
    public ArrayList<Publicacion> buscarPublicaciones(String titulo) {
        ArrayList<Publicacion> publicacionesBuscadas = new ArrayList<>();
        if((titulo!= null) && (!titulo.isBlank())){
            for(Publicacion p: publicaciones){
                if((p.verTitulo().equals(titulo)) || (p.verTitulo().startsWith(titulo))){
                    if((!publicacionesBuscadas.contains(p)))
                        publicacionesBuscadas.add(p);
                } 
            }
            if(publicacionesBuscadas != null){
                publicacionesBuscadas.sort(comparadorPublicaciones);
                return publicacionesBuscadas;
            }      
        }
        return publicacionesBuscadas;
    }
}
