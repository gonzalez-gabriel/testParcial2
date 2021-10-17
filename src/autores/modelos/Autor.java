/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autores.modelos;

import grupos.modelos.Grupo;
import grupos.modelos.MiembroEnGrupo;
import grupos.modelos.Rol;
import java.util.ArrayList;

/**
 *
 * @author Otros
 */
public abstract class Autor {
    private int dni;
    private String apellidos;
    private String nombres;
    private String clave;
    private ArrayList<MiembroEnGrupo> miembroEnGrupo;
    
    public Autor(int dni,String apellidos,String nombres,String clave) {
        this.dni = dni;
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.clave = clave;
        this.miembroEnGrupo = new ArrayList<>();
    }
    
    public void asignarDni(int dni){
        this.dni = dni;
    }
    
    public void asignarApellidos(String apellidos){
        this.apellidos = apellidos;
    }
    
    public void asignarNombres(String nombres){
        this.nombres = nombres;
    }
    
    public void asignarClave(String clave){
        this.clave = clave;
    }
    
    public int verDni(){
        return dni;
    }
    
    public String verNombres(){
        return nombres;
    }
    
    public String verApellidos(){
        return apellidos;
    }
    
    public String verClave(){
        return clave;
    }
    
    public void mostrar(){
        System.out.println("["+dni+"] "+apellidos+", "+nombres+".");
        if(!this.miembroEnGrupo.isEmpty()){
            System.out.println("Miembro de grupo: ");
            for(MiembroEnGrupo m : this.miembroEnGrupo)
                System.out.println("Nombre de grupo: "+m.verGrupo().verNombre()+"\tRol: "+m.verRol()+".");
        }
    }

    public ArrayList<MiembroEnGrupo> verGrupos(){
        return miembroEnGrupo;
    }
    
    public void agregarGrupo(Grupo grupo,Rol rol){
        MiembroEnGrupo miembro = new MiembroEnGrupo(this,grupo,rol);
        if(!this.miembroEnGrupo.contains(miembro)){
            this.miembroEnGrupo.add(miembro);
            grupo.agregarMiembro(this, rol);
        }
    }
    
    public void quitarGrupo(Grupo grupo){
        MiembroEnGrupo miembro = new MiembroEnGrupo(this,grupo,null);
        if(this.miembroEnGrupo.contains(miembro)){
            this.miembroEnGrupo.remove(miembro);
            grupo.quitarMiembro(this);
        }
    }
    
    public boolean esSuperAdministrador(){
        for(MiembroEnGrupo m: miembroEnGrupo){
            if(m.verGrupo().esSuperAdministradores())
                return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.dni;
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
        if (getClass().getSuperclass() != obj.getClass().getSuperclass()) {
            return false;
        }
        final Autor other = (Autor) obj;
        if (this.dni != other.dni) {
            return false;
        }
        return true;
    }    
}
