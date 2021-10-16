/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal.controladores;

import autores.modelos.Alumno;
import autores.modelos.Autor;
import autores.modelos.Cargo;
import autores.modelos.Profesor;
import grupos.modelos.Grupo;
import grupos.modelos.MiembroEnGrupo;
import grupos.modelos.Rol;
import idiomas.modelos.Idioma;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import lugares.modelos.Lugar;
import palabrasclaves.modelos.PalabraClave;
import publicaciones.modelos.Publicacion;
import tipos.modelos.Tipo;


/**
 *
 * @author Cel
 */
public class ControladorPrincipal {
    public static void main(String[] args) {
    //<editor-fold defaultstate="collapsed" desc="Sin intefaz gráfica"> 
        ArrayList<Grupo> grupos = new ArrayList<>();
//        ArrayList<Alumno> alumnos = new ArrayList<>();
//        ArrayList<Profesor> profesores = new ArrayList<>();
        ArrayList<Autor> autores = new ArrayList<>();
        ArrayList<Tipo> tipos = new ArrayList<>();
        ArrayList<Lugar> lugares = new ArrayList<>();
        ArrayList<Idioma> idiomas = new ArrayList<>();
        ArrayList<PalabraClave> palabrasClaves = new ArrayList<>();
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        
        Grupo grupo1 = new Grupo("Grupo 1", "Descripción 1");
        Grupo grupo2 = new Grupo("Grupo 2", "Descripción 2");
        Grupo grupo3 = new Grupo("Grupo 3", "Descripción 3");
        Grupo grupo4 = new Grupo("Grupo 4", "Descripción 4");
        Grupo grupo5 = new Grupo("Grupo 5", "Descripción 5");
        Grupo grupo6 = new Grupo("Grupo 1", "Descripción 5"); //nombre repetido
        
        if (!grupos.contains(grupo1))
            grupos.add(grupo1);
        if (!grupos.contains(grupo2))
            grupos.add(grupo2);
        if (!grupos.contains(grupo3))
            grupos.add(grupo3);
        if (!grupos.contains(grupo4))
            grupos.add(grupo4);
        if (!grupos.contains(grupo5))
            grupos.add(grupo5);
        if (!grupos.contains(grupo6))
            grupos.add(grupo6);
        
        for(Grupo g : grupos)
            g.mostrar();
        
        Autor alumno1 = new Alumno(1, "Apellido1", "Nombre1", "Clave1", "1");
        Autor alumno2 = new Alumno(2, "Apellido2", "Nombre2", "Clave2", "2");
        Autor alumno3 = new Alumno(3, "Apellido3", "Nombre3", "Clave3", "3");
        Autor alumno4 = new Alumno(4, "Apellido4", "Nombre4", "Clave4", "4");
        Autor alumno5 = new Alumno(5, "Apellido5", "Nombre5", "Clave5", "5");
        Autor alumno6 = new Alumno(1, "Apellido6", "Nombre6", "Clave6", "6");
        //dni repetido con un alumno
        
        if (!autores.contains(alumno1))
            autores.add(alumno1);
        if (!autores.contains(alumno2))
            autores.add(alumno2);
        if (!autores.contains(alumno3))
            autores.add(alumno3);
        if (!autores.contains(alumno4))
            autores.add(alumno4);
        if (!autores.contains(alumno5))
            autores.add(alumno5);
        if (!autores.contains(alumno6))
            autores.add(alumno6);
        
        Autor profesor1 = new Profesor(10, "Apellido10", "Nombre10", "Clave10", Cargo.TITULAR);
        Autor profesor2 = new Profesor(20, "Apellido20", "Nombre20", "Clave20", Cargo.ASOCIADO);
        Autor profesor3 = new Profesor(30, "Apellido30", "Nombre30", "Clave30", Cargo.ADJUNTO);
        Autor profesor4 = new Profesor(40, "Apellido40", "Nombre40", "Clave40", Cargo.JTP);
        Autor profesor5 = new Profesor(50, "Apellido50", "Nombre50", "Clave50", Cargo.ADG);
        Autor profesor6 = new Profesor(10, "Apellido60", "Nombre60", "Clave60", Cargo.ADG); 
        //dni repetido con otro profesor
        Autor profesor7 = new Profesor(1, "Apellido70", "Nombre70", "Clave70", Cargo.ADG); 
        //dni repetido con otro alumno
        Autor alumno7 = new Alumno(50, "Apellido7", "Nombre7", "Clave7", "7");
        //dni repetido con un profesor
        Autor alumno8 = new Alumno(8, "Apellido8", "Nombre8", "Clave8", "5");
        //cx repetido con un alumno
                
        if (!autores.contains(profesor1))
            autores.add(profesor1);
        if (!autores.contains(profesor2))
            autores.add(profesor2);
        if (!autores.contains(profesor3))
            autores.add(profesor3);
        if (!autores.contains(profesor4))
            autores.add(profesor4);
        if (!autores.contains(profesor5))
            autores.add(profesor5);
        if (!autores.contains(profesor6))
            autores.add(profesor6);
        
        if (!autores.contains(profesor7))
            autores.add(profesor7);
        if (!autores.contains(alumno7))
            autores.add(alumno7);
        if (!autores.contains(alumno8))
            autores.add(alumno8);
        
        
        for(Autor a : autores)
            a.mostrar();
    }
}
