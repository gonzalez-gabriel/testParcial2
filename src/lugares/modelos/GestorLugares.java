/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lugares.modelos;

import interfaces.IGestorLugares;
import interfaces.IGestorPublicaciones;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    
    // constantes para manejo de archivo
    public static final String NOMBRE_ARCHIVO = "lugares.txt";
    //public static final String NOMBRE_ARCHIVO = "C:\\Users\\Otros\\Documents\\NetBeansProjects\\TrabajosGraduacion2021G11\\src\\archivos\\lugares.txt";
    
    private GestorLugares() {
        String resultado = this.leerArchivo();
    }
    
    public static GestorLugares crear(){
        if(gestor == null){
            gestor = new GestorLugares();
        }
        return gestor;
    }
    
    // Abrir archivo
    private File obtenerArchivo(){
        File file = new File(NOMBRE_ARCHIVO);
        try{
            if(!file.exists())
                file.createNewFile();
            
            return file;
        }
        catch(IOException e){
            return null;
        }
    }
    
    // Modificar/guardar archivo
    private String escribirArchivo(){
        File file = this.obtenerArchivo();
        if(file != null){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
                ArrayList<Lugar> lugaresAGuardar = new ArrayList<>();
                lugaresAGuardar.addAll(this.verLugares());
                for(Lugar l: lugaresAGuardar){
                    String cadena = l.verNombre();
                    bw.write(cadena);
                    bw.newLine();
                }
                return MSJ_OK_ARCHIVO;
            }
            catch(IOException e){
                return MSJ_ERROR_ARCHIVO;
            }
        }
        else
            return MSJ_ERROR_ARCHIVO;
    }
    
    // Leer Archivo
    private String leerArchivo() {
        File file = this.obtenerArchivo();
        if(file != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String cadena;
                while((cadena = br.readLine()) != null) {
                    String nombreLugar = cadena;
                    Lugar lugarLeido = new Lugar (nombreLugar);
                    this.lugares.add(lugarLeido);
                }
                return MSJ_OK_LECTURA;
            }
            catch(NullPointerException | IOException ioe) {
                return MSJ_ERROR_LECTURA;
            }            
        }
        else
            return MSJ_ERROR_LECTURA;        
    }
    
    @Override
    public String nuevoLugar(String nombre) {
        if((nombre!= null) && (!nombre.isBlank())){
            Lugar lugarNuevo = new Lugar(nombre);              
            if(!this.lugares.contains(lugarNuevo)) {
                this.lugares.add(lugarNuevo);
                String mensaje = this.escribirArchivo();
                
                if(mensaje.equals(MSJ_OK_ARCHIVO))
                    return MSJ_OK;
                else
                    return MSJ_ERROR;
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
                String mensaje = this.escribirArchivo();
                
                if(mensaje.equals(MSJ_OK_ARCHIVO))
                    return MSJ_OK_BORRAR;
                else
                    return MSJ_ERROR;
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
        for(Lugar l: this.lugares){
            if(l.equals(lugar))
                return true;
        }
        return false;
    }
}
