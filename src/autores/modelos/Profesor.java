/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autores.modelos;

/**
 *
 * @author Otros
 */
public class Profesor extends Autor{
    private Cargo cargo;
    
    public Profesor(int dni,String apellidos,String nombres,String clave,Cargo cargo) {
        super(dni,apellidos,nombres,clave);
        this.cargo = cargo;
    }
    
    @Override
    public void mostrar(){
        System.out.println("Apellidos y nombres: " + super.verApellidos() + ", " + super.verNombres() + "\n DNI: " + super.verDni() + "\n Clave: " + super.verClave() + "\n CX: " + cargo);
    }
    
    public void asignarCargo(Cargo cargo){
        this.cargo = cargo;
    }
    
    public Cargo verCargo(){
        return cargo;
    }    
}
