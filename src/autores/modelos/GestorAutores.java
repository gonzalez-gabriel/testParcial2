/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autores.modelos;

import grupos.modelos.Grupo;
import grupos.modelos.MiembroEnGrupo;
import interfaces.IGestorAutores;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Ocón Santiago Luis
 */
public class GestorAutores implements IGestorAutores {
    public static GestorAutores gestor;    
    private ArrayList<Autor> autores = new ArrayList<>();
    Comparator<Autor> comparadorAutores=(autorA, autorB) -> autorA.verApellidos().compareTo(autorB.verApellidos());    
    
    
    private GestorAutores() {
    }
    
    public static GestorAutores crear(){
        if(gestor == null){
            gestor = new GestorAutores();
        }
        return gestor;
    }

    @Override
    public String nuevoAutor(int dni, String apellidos, String nombres, Cargo cargo, String clave, String claveRepetida) {
        if((dni>0) && (apellidos != null) && (!apellidos.isBlank()) && (nombres !=null) && (!nombres.isBlank()) && (cargo!=null) 
                && (clave!=null) && (!clave.isBlank()) && (clave.equals(claveRepetida))){
            Autor autorNuevo= new Profesor(dni,apellidos,nombres,clave,cargo);              
            if(!this.autores.contains(autorNuevo)){
                this.autores.add(autorNuevo);
                return EXITO_P;
            }
            else
                return MSJ_REP;
        }
        if(dni<=0) 
            return ERROR_DNI_P;
        if((apellidos == null)||(apellidos.isBlank()))
            return ERROR_APELLIDOS_P;
        if((nombres == null)||(nombres.isBlank()))    
            return ERROR_NOMBRES_P;
        if((clave==null)||(clave.isBlank())||(!clave.equals(claveRepetida)))
            return ERROR_CLAVES; 
        return "Error";
    }

    @Override
    public String nuevoAutor(int dni, String apellidos, String nombres, String cx, String clave, String claveRepetida) {
        if((dni>0) && (apellidos != null) && (!apellidos.isBlank()) && (nombres !=null) && (!nombres.isBlank()) 
                && (cx!=null) && (!cx.isBlank()) && (clave!=null) && (clave.equals(claveRepetida))){
            Autor autorNuevo= new Alumno(dni,apellidos,nombres,clave,cx);              
            if(!this.autores.contains(autorNuevo)){
                this.autores.add(autorNuevo);
                return EXITO_A;
            }
            else
                return MSJ_REP;
        }
        if(dni<=0) 
            return ERROR_DNI_A;
        if((apellidos == null)||(apellidos.isBlank()))
            return ERROR_APELLIDOS_A;
        if((nombres == null)||(nombres.isBlank()))    
            return ERROR_NOMBRES_A;
        if((clave==null)||(clave.isBlank())||(!clave.equals(claveRepetida)))
            return ERROR_CLAVES; 
        return "Error";
    }

    @Override
    public String modificarAutor(Autor autor, String apellidos, String nombres, Cargo cargo, String clave, String claveRepetida) {
        if(autores.contains(autor)){
            if((apellidos != null) && (!apellidos.isBlank()) && (nombres !=null) && (!nombres.isBlank()) && (cargo!=null) ){
                int index = autores.indexOf(autor);
                if(index == (-1))
                    return MSJ_MOD_ERROR;
                else {
                    if(autor instanceof Profesor){
                        ((Profesor)autores.get(index)).asignarApellidos(apellidos);
                        ((Profesor)autores.get(index)).asignarNombres(nombres);
                        ((Profesor)autores.get(index)).asignarCargo(cargo);
                        ((Profesor)autores.get(index)).asignarClave(clave);
                        return MSJ_MOD_OK;
                    }
                    else
                        return MSJ_MOD_SINPROFESOR;
                }
            }
            else
                return MSJ_MOD_BLANCO;
        }
        else
            return MSJ_MOD_SINAUTOR;
    }

    @Override
    public String modificarAutor(Autor autor, String apellidos, String nombres, String cx, String clave, String claveRepetida) {
        if(autores.contains(autor)){
            if((apellidos != null) && (!apellidos.isBlank()) && (nombres !=null) && (!nombres.isBlank()) && (cx!=null) && (!cx.isBlank())){
                int indice=autores.indexOf(autor);
                if(indice == (-1))
                    return MSJ_MOD_ERROR;
                else {
                    if(autor instanceof Alumno) {
                        ((Alumno)autores.get(indice)).asignarApellidos(apellidos);
                        ((Alumno)autores.get(indice)).asignarNombres(nombres);
                        ((Alumno)autores.get(indice)).asignarCx(cx);
                        ((Alumno)autores.get(indice)).asignarClave(clave);
                        return MSJ_MOD_OK;
                    }
                    else
                        return MSJ_MOD_SINALUMNO;
                }
            }
            else
                return MSJ_MOD_BLANCO;
        }
        else
            return MSJ_MOD_SINAUTOR;
    }

    @Override
    public boolean existeEsteAutor(Autor autor) {
        if(autores.contains(autor))
            return true;
        else
            return false;
    }

    @Override
    public ArrayList<Autor> verAutores() {
        this.autores.sort(comparadorAutores);
        return this.autores; 
    }

    @Override
    public ArrayList<Profesor> verProfesores() {
        ArrayList<Profesor> profesores = new ArrayList<>();
        for(Autor a: this.autores) {
            if(a instanceof Profesor)
                profesores.add((Profesor) a);
        }
        profesores.sort(comparadorAutores);
        return profesores;
    }

    @Override
    public ArrayList<Alumno> verAlumnos() {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        for(Autor a: autores) {
            if(a instanceof Alumno)
                alumnos.add((Alumno) a);
        }
        alumnos.sort(comparadorAutores);
        return alumnos;
    }

    @Override
    public Autor verAutor(int dni) {
        Autor profesorBuscado = new Profesor(dni,null,null,null,null);
        Autor alumnoBuscado = new Alumno(dni,null,null,null,null);
        int index = autores.indexOf(profesorBuscado);
        if(index == (-1)){
            index = autores.indexOf(alumnoBuscado);
            if(index == (-1)){
                    return null;
            }
            else
                return autores.get(index);
        }
        else
            return autores.get(index);
    }

    @Override
    public String borrarAutor(Autor autor) {
        if( (autor != null) && (autor.verDni() != 0) && (autor.verApellidos() != null) && (!autor.verApellidos().isBlank())
            && (autor.verNombres() !=null) && (!autor.verNombres().isBlank())  ){
            if(this.existeEsteAutor(autor)) {
                this.autores.remove(autor);
                return MSJ_OK;
            }                
            else
                return MSJ_ERROR;
        }                  
        else 
            return MSJ_ERROR;
    }

    @Override
    public ArrayList<Profesor> buscarProfesores(String apellidos) {
        ArrayList<Profesor> profesoresBuscados = new ArrayList<>();
        if((apellidos != null) && (!apellidos.isBlank()) ){
            for(Autor a: autores){
                if(a instanceof Profesor) {
                    if((a.verApellidos().equals(apellidos)) || (a.verApellidos().startsWith(apellidos))){
                        if((!profesoresBuscados.contains(a)))
                            profesoresBuscados.add((Profesor) a);
                    } 
                }
            }
            if(profesoresBuscados != null){
                profesoresBuscados.sort(comparadorAutores);
                return profesoresBuscados;
            }
        }
        return profesoresBuscados;
    }

    @Override
    public ArrayList<Alumno> buscarAlumnos(String apellidos) {
        ArrayList<Alumno> alumnosBuscados = new ArrayList<>();
        if( (apellidos != null) && (!apellidos.isBlank()) ){
            for(Autor a: autores){
                if(a instanceof Alumno) {
                    if((a.verApellidos().equals(apellidos)) || (a.verApellidos().startsWith(apellidos))){
                        if((!alumnosBuscados.contains(a)))
                            alumnosBuscados.add((Alumno) a);
                    } 
                }
            }
            if(alumnosBuscados != null){
                alumnosBuscados.sort(comparadorAutores);
                return alumnosBuscados;
            }
        }
        return alumnosBuscados;
    }

    @Override
    public boolean hayAutoresConEsteGrupo(Grupo grupo) {
        for(Autor a : autores){
            for(MiembroEnGrupo m: a.verGrupos()){
                if(grupo.equals(m.verGrupo()))
                    return true;   
                }
        }
        return false;
    }

    @Override
    public String quitarGrupos(Autor autor, ArrayList<MiembroEnGrupo> grupos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String agregarGrupos(Autor autor, ArrayList<MiembroEnGrupo> grupos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
