package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author usuario
 */
public class GestorDB
{
    private Connection conexion;
    private static GestorDB gestorDB;

    private GestorDB()
    {
        try
        {
            Class.forName("org.mariadb.jdbc.Driver"); //Ojo que no esta en apuntes
            conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3310/"
                    + "preguntastest?user=root&password=root");
        } catch (ClassNotFoundException ex)
        {
            System.out.println(ex.toString());
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }
    
    public static GestorDB gestor(){
        if(gestorDB == null) gestorDB = new GestorDB();
        return gestorDB;
    }
}
