/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_6_ejercicio_1;

import BaseDatos.GestorDB;
import Modelo.Usuario;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import java.util.Scanner;


/**
 *
 * @author usuario
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here

        GestorDB gestor = new GestorDB();
           int opcion=0;
        do{
        Scanner teclado=new Scanner(System.in);
        System.out.println("Que comience el juego");
        System.out.println("¿Que quieres hacer");
        System.out.println("1 -Añadir un nuevo usuario");
        System.out.println("2-Cargar fichero");
        System.out.println("3- Ver preguntas por categoría");
        System.out.println("4- Jugar");
            System.out.println("5-Salir");
        switch(opcion){
            case 1:
                Usuario user=pedirDatos();
                gestor.loginUser(user.getNombre(), user.getApellidos(), user.getFecha_nacimiento(), user.getUsuario(), user.getPassword());
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("No es una opción válida");
        }}while(opcion !=5);}    
    
    private static Usuario pedirDatos(){
    Scanner teclado=new Scanner (System.in);
        System.out.println("Introduzca nombre");
        String nombre=teclado.nextLine();
        System.out.println("Introduzca apellidos");
        String apellidos=teclado.nextLine();
        System.out.println("Introduzca fecha de nacimiento");
        LocalDate fechaNac=IntroFecha("Introduzca fecha de nacimiento");
        System.out.println("Introduzca nombre de usuario");
        String usuario=teclado.nextLine();
        System.out.println("introduzca password");
        String contra=teclado.nextLine();   
        Usuario user=new Usuario(nombre, apellidos, fechaNac,usuario,contra);
        return user;}
        public static LocalDate IntroFecha(String mensaje) {
        boolean valido = false;
        LocalDate fechaTeclado = LocalDate.now();
        do {

            Scanner teclado = new Scanner(System.in);
            System.out.println("Introduce la fecha (dd-mm-yyyy):");
            try {
                DateTimeFormatter formateo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                fechaTeclado = LocalDate.parse(teclado.next(), formateo);

            } catch (DateTimeException e) {
                System.err.println("Formato de fecha incorrecto.");
               
            }
        } while (!valido);
        return fechaTeclado;
    }
    
}
