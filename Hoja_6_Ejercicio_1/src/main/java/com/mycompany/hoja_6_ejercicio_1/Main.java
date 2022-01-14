/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_6_ejercicio_1;

import BaseDatos.GestorDB;
import static BaseDatos.GestorDB.gestor;
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
        GestorDB gestor = gestor();
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
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("No es una opción válida");
        }}while(opcion !=5);
    }
    
}
