/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autores.modelos;

import java.util.Objects;

/**
 *
 * @author Ocón Santiago Luis
 */
public class Alumno extends Autor{
    private String cx;
    
    public Alumno(int dni,String apellidos,String nombres,String clave,String cx) {
        super(dni,apellidos,nombres,clave);
        this.cx = cx;
    }
    
    @Override
    public void mostrar(){
        System.out.print("\nCX: " + cx + " ");
        super.mostrar();       
    }
    
    public void asignarCx(String cx){
        this.cx = cx;
    }
    
    public String verCx(){
        return cx;
    }
    
    @Override
    public int hashCode() {        
        int hash = 5;
        hash = super.hashCode() + Objects.hashCode(this.cx);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj)){
            return true;
        }
        else{
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Alumno other = (Alumno) obj;
            if (!Objects.equals(this.cx, other.cx)) {
                return false;
            }
            return true; 
        }        
    }    
}
