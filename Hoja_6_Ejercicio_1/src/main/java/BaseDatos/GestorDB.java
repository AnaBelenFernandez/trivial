package BaseDatos;

import java.sql.Connection;
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
    public void loginUser(String nombre, String apellidos,LocalDate fNacim, String user, String pass){
        Connection conexion = Conexion.getInstance().getConnection();
        String sql = "INSERT INTO usuarios (nombre, apellidos, fecha_nacimiento, usuario, password) "
                + "VALUES (?,?,?,?,?)";
        
        try(PreparedStatement consulta = conexion.prepareStatement(sql)){
            consulta.setString(1, nombre);
            consulta.setString(2, apellidos);
            consulta.setString(3, fNacim).toString();
            consulta.setString(4, user);
            consulta.setString(5, pass);
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }
    
    
    public void preguntasCategoria(String categoria){
    Connection conexion = Conexion.getInstance().getConnection();
        String sql = "SELECT id, enunciado FROM preguntas where categoria= "+categoria;
               
    
    }
}
