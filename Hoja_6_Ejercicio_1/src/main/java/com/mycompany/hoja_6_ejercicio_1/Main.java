/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hoja_6_ejercicio_1;

import BaseDatos.GestorDB;
import Modelo.Pregunta;
import Modelo.Respuesta;
import Modelo.Usuario;
import Util.GestorFicheros;
import java.io.File;
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        GestorFicheros gestorFicheros = new GestorFicheros();
        int opcion = 0;

        do
        {
            Scanner teclado = new Scanner(System.in);
            System.out.println("");
            System.out.println("[======================================]");
            System.out.println("[         QUE COMIENCE EL JUEGO        ]");
            System.out.println("[======================================]");
            System.out.println("[ ¿Que quieres hacer                   ]");
            System.out.println("[                                      ]");
            System.out.println("[ 1 - Añadir un nuevo usuario          ]");
            System.out.println("[ 2 - Cargar fichero                   ]");
            System.out.println("[ 3 - Ver preguntas por categoría      ]");
            System.out.println("[ 4 - Jugar                            ]");
            System.out.println("[ 5 - Salir                            ]");
            System.out.println("[======================================]");
            opcion = teclado.nextInt();
            teclado.nextLine();
            System.out.println("");
            switch (opcion)
            {
                case 1:
                    boolean respuesta;
                    Usuario user = pedirDatos();

                    respuesta = gestor.loginUser(user.getNombre(), user.getApellidos(),
                            user.getFecha_nacimiento(), user.getUsuario(),
                            user.getPassword());

                    System.out.println("");
                    if (respuesta == true)
                        System.out.println("Usuario registrado correctamente");
                    else
                        System.out.println("Error al registrar usuario");
                    System.out.println("");
                    break;
                case 2:

                    File fichero = new File("fichero.csv");
                    List<Pregunta> preguntas = gestorFicheros.leerPreguntasCSV(fichero);

//                    for(Pregunta pregunta: preguntas){
//                        System.out.println(pregunta.getEnunciado() + " - " +
//                                pregunta.getCategoria() + " - " + pregunta.getNivel());
//                        for(Respuesta resp: pregunta.getRespuestas()){
//                            System.out.println(resp.getTexto() + " - " + resp.isCorrecta());    
//                        }
//                    }
                    int pregAdd = gestor.addPreguntas(preguntas);
                    System.out.println(pregAdd + " preguntas añadidas correctamente");
                    break;

                case 3:
                    System.out.println("¡Introduzca la categoria para visualizar las preguntas adecuadas!");
                    String categoria = teclado.nextLine();
                    gestor.preguntasPorCategoria(categoria);
                    break;
                case 4:
                    System.out.println("Introduzca Nombre de usuario:");
                    String usuario = teclado.nextLine();
                    System.out.println("Introduzca contraseña:");
                    String password = teclado.nextLine();
                    //para la interfaz grafica, queda validar que estan correctos
                    gestor.jugar(usuario, password);
                    //INCOMPLETO, FALTA COMPLETAR LAS CONTRASEÑAS EN MD5
                    break;
                case 5:
                    System.out.println("Cerrando aplicacion...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("No es una opción válida");
            }
        } while (opcion != 5);
    }

    private static Usuario pedirDatos()
    {
        Scanner teclado = new Scanner(System.in);
        //NOMBRE
        System.out.println("Introduzca nombre");
        String nombre = teclado.nextLine();
        //APELLIDOS
        System.out.println("Introduzca apellidos");
        String apellidos = teclado.nextLine();
        //FECHA NACIMIENTO
        Date fechaNac = IntroFecha("Introduzca fecha de nacimiento");
        //USUARIO
        System.out.println("Introduzca nombre de usuario");
        String usuario = teclado.nextLine();
        //PASSWORD
        System.out.println("introduzca password");
        String contra = teclado.nextLine();

        Usuario user = new Usuario(nombre, apellidos, fechaNac, usuario, contra);
        return user;
    }

    public static Date IntroFecha(String mensaje)
    {
        boolean valido;
        LocalDate fechaTeclado = LocalDate.now();
        do
        {
            valido = true;
            Scanner teclado = new Scanner(System.in);
            System.out.println("Introduce la fecha (dd-mm-yyyy):");
            try
            {
                DateTimeFormatter formateo = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                fechaTeclado = LocalDate.parse(teclado.next(), formateo);

            } catch (DateTimeException e)
            {
                System.err.println("Formato de fecha incorrecto.");
                valido = false;
            }
        } while (valido != true);

        return Date.valueOf(fechaTeclado);
    }

}
