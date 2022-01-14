package BaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author usuario
 */
public class GestorDB
{
    //Ejercicio_1: Registrar un usuario nuevo en la base de datos
    public boolean loginUser(String nombre, String apellidos,Date fNacim, String user, String pass){
        boolean respuesta = false;
        
        Connection conexion = Conexion.getInstance().getConnection();
        String sql = "INSERT INTO usuarios (nombre, apellidos, fecha_nacimiento, usuario, password) "
                + "VALUES (?,?,?,?,?)";
        
        try(PreparedStatement consulta = conexion.prepareStatement(sql)){
            consulta.setString(1, nombre);
            consulta.setString(2, apellidos);
            consulta.setDate(3, fNacim);
            consulta.setString(4, user);
            consulta.setString(5, pass);
            
            int resul = consulta.executeUpdate();
            
            //El metodo returnea true si se registra correctamente
            //o false si no se registra.
            if(resul != 0) respuesta = true;
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return respuesta;
    }
    
    
    public void preguntasCategoria(String categoria){
    Connection conexion = Conexion.getInstance().getConnection();
        String sql = "SELECT id, enunciado FROM preguntas where categoria = " + categoria;
               
    
    }
}
