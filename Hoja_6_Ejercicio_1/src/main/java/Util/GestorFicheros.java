package Util;

import Modelo.Pregunta;
import Modelo.Respuesta;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class GestorFicheros
{
    public List<Pregunta> leerPreguntasCSV(File fichero)
    {
        List<Pregunta> preguntas = new ArrayList();
        BufferedReader br = null;
        
        try{
            br = new BufferedReader(new FileReader(fichero));
            String line = br.readLine();
            
            while(line != null) {
                String [] fields = line.split(";");
                
                if(fields[0].equalsIgnoreCase("P")){
                    Pregunta p = new Pregunta();
                    
                    p.setEnunciado(fields[1]);
                    p.setCategoria(fields[2]);
                    p.setNivel(Integer.parseInt(fields[3]));
                    
                    preguntas.add(p);
                }else if(fields[0].equalsIgnoreCase("R")){
                    Respuesta r = new Respuesta();
                    
                    r.setTexto(fields[1]);
                    if(fields[2].equalsIgnoreCase("1")) r.setCorrecta(true);
                    else r.setCorrecta(false);
                    r.setPregunta(preguntas.get(preguntas.size()-1));
                    
                    preguntas.get(preguntas.size()-1).addRespuesta(r);
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException ex)
        {
            System.out.println(ex.toString());
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
        return preguntas;
    }

}
