package BaseDatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class GestorDB
{
    //Ejercicio_1: Registrar un usuario nuevo en la base de datos
    public void loginUser(String nombre, String apellidos, Date fNacim, String user, String pass){
        Connection conexion = Conexion.getInstance().getConnection();
        String sql = "INSERT INTO usuarios (nombre, apellidos, fecha_nacimiento, usuario, password) "
                + "VALUES (?,?,?,?,?)";
        
        try(PreparedStatement consulta = conexion.prepareStatement(sql)){
            consulta.setString(1, nombre);
            consulta.setString(2, apellidos);
            consulta.setDate(3, fNacim);
            consulta.setString(4, user);
            consulta.setString(5, pass);
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }
}
