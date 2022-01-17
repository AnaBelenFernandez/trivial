/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo2;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



/**
 *
 * @author Nuria
 */
public class Ejemplo2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int respuesta;
        String mensaje="";
        //Para escoger entre botones
        Object[] options={"Si, fundamental","No, para nada","Cancelar"};
        
        respuesta=JOptionPane.showOptionDialog(null, "¿Es importante unir "
        + "programacion y creatividad?","Pregunta",
        JOptionPane.YES_NO_CANCEL_OPTION, 
        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
        //Para mostrar informacion
        if(respuesta==0){mensaje="tu si que sabes";}
        if(respuesta==1){mensaje="apaga y vamonos";}
        if (respuesta == 2) {
            mensaje = "no sabe, no contesta";
        }
        JOptionPane.showMessageDialog(null, mensaje, "Respuesta", JOptionPane.WARNING_MESSAGE);

        //Para introducir un string
        String contesta = JOptionPane.showInputDialog("Dime tu nombre: ");
        JOptionPane.showMessageDialog(null, "Hola " + contesta);

        //Para elegir entre diferentes opciones
        Object color = JOptionPane.showInputDialog(null, "Seleccione Un Color",
                "COLORES", JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{"Seleccione", "Amarillo", "Azul", "Rojo"}, "Seleccione");
        JOptionPane.showMessageDialog(null, color.toString());
    }

}
