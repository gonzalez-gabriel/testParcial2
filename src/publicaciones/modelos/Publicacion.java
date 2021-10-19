/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package publicaciones.modelos;

import grupos.modelos.MiembroEnGrupo;
import idiomas.modelos.Idioma;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lugares.modelos.Lugar;
import palabrasclaves.modelos.PalabraClave;
import tipos.modelos.Tipo;

/**
 *
 * @author Ocón Santiago Luis
 */
public class Publicacion {
    private String titulo;
    private MiembroEnGrupo miembroEnGrupo;
    private LocalDate fechaPublicacion;
    private Tipo tipo;
    private Idioma idioma;
    private Lugar lugar;
    private List<PalabraClave> palabrasClave;
    private String enlace;
    private String resumen;
    
    public Publicacion(String titulo,MiembroEnGrupo miembroEnGrupo,LocalDate fechaPublicacion,Tipo tipo,Idioma idioma, Lugar lugar, List<PalabraClave> palabrasClave, String enlace, String resumen) {
        this.titulo = titulo;
        this.miembroEnGrupo = miembroEnGrupo;
        this.fechaPublicacion = fechaPublicacion;
        this.tipo = tipo;
        this.idioma = idioma;
        this.lugar = lugar;
        this.palabrasClave = palabrasClave;
        this.enlace = enlace;
        this.resumen = resumen;
    }
    
    public void asignarTitulo(String titulo){
        this.titulo = titulo;
    }
    
    public void asignarMiembroEnGrupo(MiembroEnGrupo miembroEnGrupo){
        this.miembroEnGrupo = miembroEnGrupo;
    }
    
    public void asignarFecha(LocalDate fechaPublicacion){
        this.fechaPublicacion = fechaPublicacion;
    }
    
    public void asignarTipo(Tipo tipo){
        this.tipo = tipo;
    }
    
    public void asignarIdioma(Idioma idioma){
        this.idioma = idioma;
    }
    
    public void asignarLugar(Lugar lugar){
        this.lugar = lugar;
    }
    
    public void asignarPalabras(ArrayList<PalabraClave> palabrasClave){
        this.palabrasClave = palabrasClave;
    }
    
    public void asignarEnlace(String enlace){
        this.enlace = enlace;
    }
        
    public void asignarResumen(String resumen){
        this.resumen = resumen;
    }    
    
    public String verTitulo(){
        return titulo;
    }
    
    public MiembroEnGrupo verMiembroEnGrupo(){
        return miembroEnGrupo;
    }
    
    public LocalDate verFecha(){
        return fechaPublicacion;
    }
    
    public Tipo verTipo(){
        return tipo;
    }
    
    public Idioma verIdioma(){
        return idioma;
    }
    
    public Lugar verLugar(){
        return lugar;
    }
    
    public List<PalabraClave> verPalabrasClave(){
        return palabrasClave;
    }
    
    public String verEnlace(){
        return enlace;
    }
    
    public String verResumen(){
        return resumen;
    }
    
    public void mostrar(){
        System.out.println("Título: " + this.titulo);
        System.out.println("Autor: " + this.miembroEnGrupo.verAutor().verApellidos() + ", " + this.miembroEnGrupo.verAutor().verNombres());
        System.out.println("Grupo: " + this.miembroEnGrupo.verGrupo().verNombre());
        System.out.println("Rol: " + this.miembroEnGrupo.verRol());
        System.out.println("Fecha de publicación: " + this.fechaPublicacion);
        System.out.println("Tipo: " + this.tipo);
        System.out.println("Idioma: " + this.idioma);
        System.out.println("Lugar: " + this.lugar);
        System.out.println("Palabras claves");
        System.out.println("---------------");
        for(PalabraClave pc : this.palabrasClave)
            System.out.println("\t" + pc);
        System.out.println("Enlace: " + this.enlace);
        System.out.println("Resumen: " + this.resumen);
        
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.titulo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Publicacion other = (Publicacion) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        return true;
    }
    
    
}
