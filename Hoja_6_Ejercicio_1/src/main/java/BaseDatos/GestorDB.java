package BaseDatos;

import Modelo.Pregunta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class GestorDB
{
    //Ejercicio_1: Registrar un usuario nuevo en la base de datos
    public boolean loginUser(String nombre, String apellidos, Date fNacim, String user, String pass)
    {
        boolean respuesta = false;

        Connection conexion = Conexion.getInstance().getConnection();
        String sql = "INSERT INTO usuarios (nombre, apellidos, fecha_nacimiento, usuario, password) "
                + "VALUES (?,?,?,?,?)";

        try ( PreparedStatement consulta = conexion.prepareStatement(sql))
        {
            consulta.setString(1, nombre);
            consulta.setString(2, apellidos);
            consulta.setDate(3, fNacim);
            consulta.setString(4, user);
            consulta.setString(5, pass);

            int resul = consulta.executeUpdate();

            //El metodo returnea true si se registra correctamente
            //o false si no se registra.
            if (resul != 0)
                respuesta = true;
        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
        return respuesta;
    }

    //Ejercicio_2: Subir a la base de datos la lista de preguntas.
    public int addPreguntas(List<Pregunta> preguntas)
    {
        int contadorCorrectos = 0;
        Connection conexion = Conexion.getInstance().getConnection();
        String sql = "INSERT INTO preguntas (enunciado, categoria, nivel) "
                + "VALUES (?,?,?)";

        for (Pregunta pregunta : preguntas)
        {
            try ( PreparedStatement consulta = conexion.prepareStatement(sql))
            {
                consulta.setString(1, pregunta.getEnunciado());
                consulta.setString(2, pregunta.getCategoria());
                consulta.setInt(3, pregunta.getNivel());

                int resul = consulta.executeUpdate();
                contadorCorrectos = contadorCorrectos + resul;
            } catch (SQLException ex)
            {
                System.out.println(ex.toString());
            }
        }
        return contadorCorrectos;
    }

    public void preguntasCategoria(String categoria)
    {
        Connection conexion = Conexion.getInstance().getConnection();
        String sql = "SELECT id, enunciado FROM preguntas where categoria = " + categoria;

    }

    //Ejercicio_3: Visualizar preguntas de una categoria.
    public void preguntasPorCategoria(String categoria)
    {
        Scanner teclado = new Scanner(System.in);
        Connection conexion = Conexion.getInstance().getConnection();
        String sqlCategoria = "SELECT id, enunciado FROM preguntas WHERE categoria=?";
        String sqlCategoriaAleatoria = "SELECT id, enunciado FROM preguntas WHERE categoria=? ORDER BY RAND() LIMIT 5";
        String sqlRespuesta = "SELECT texto FROM respuestas WHERE pregunta_id=?";

        try ( PreparedStatement consultaCategorias = conexion.prepareStatement(sqlCategoria))
        {
            consultaCategorias.setString(1, categoria);
            ResultSet result = consultaCategorias.executeQuery();

            //obtenemos el ultimo registro, para comprobar la cantidad de elementos disponibles en dicha categoria. Despues volvemos al inicio
            result.last();
            int cantidadRegistros = result.getRow();
            result.beforeFirst();

            if (cantidadRegistros > 5)//ING >5, DEP < 5
            {
                PreparedStatement consultaCategoriasAleatoria = conexion.prepareStatement(sqlCategoriaAleatoria);
                consultaCategoriasAleatoria.setString(1, categoria);
                result = consultaCategoriasAleatoria.executeQuery();
            }
            while (result.next())
            {
                int id = result.getInt("id");
                String enunciado = result.getString("enunciado");

                System.out.printf("%15s %-15s\n", id, enunciado);

                //Recorremos un nuevo resultset para obtener las respuestas a partir de una pregunta
                PreparedStatement consultaRespuestas = conexion.prepareStatement(sqlRespuesta);
                consultaRespuestas.setInt(1, id);
                ResultSet resultRespuesta = consultaRespuestas.executeQuery();
                while (resultRespuesta.next())
                {
                    String texto = resultRespuesta.getString("texto");
                    System.out.println(texto);
                }

                teclado.nextLine();
            }

        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }

    }

    //Ejercicio4: Jugar
    public void jugar(String nombreUsuario, String password)
    {
        Connection conexion = Conexion.getInstance().getConnection();

        String sqlAcceso = "SELECT * FROM usuarios WHERE usuario=? AND password=?";

        try ( PreparedStatement consultaCategorias = conexion.prepareStatement(sqlAcceso))
        {
            consultaCategorias.setString(1, nombreUsuario);
            consultaCategorias.setString(2, password);

            ResultSet resultUsuario = consultaCategorias.executeQuery();
            while (resultUsuario.next())
            {
                String usuario = resultUsuario.getString("usuario");
                String nombre = resultUsuario.getString("nombre");
                String apellidos = resultUsuario.getString("apellidos");

                System.out.print("Se ha iniciado sesion con:");
                System.out.printf("%-10s Nombre: %-10s Apellidos: %-25s\n", usuario, nombre, apellidos);

                contabilizarAcceso(resultUsuario.getInt("num_accesos") + 1);

                preguntarAUsuario();
            }

        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }

    //aumenta el numero de accesos de un usuario cada vez que inicia sesion
    public void contabilizarAcceso(int numeroAccesos) throws SQLException
    {
        String sql = "UPDATE usuarios SET num_accesos=?";

        PreparedStatement update = Conexion.getInstance().getConnection().prepareStatement(sql);
        update.setInt(1, numeroAccesos);
        int numeroRegistros = update.executeUpdate();
        System.out.println("Aumentado el numero de registros del usuario");
    }

    //ofrece 4 preguntas aleatorias al usuario
    public void preguntarAUsuario() throws SQLException
    {
        Scanner teclado = new Scanner(System.in);
        String sqlPreguntas = "SELECT id, enunciado, veces_formulada, veces_acertada "
                + "FROM preguntas ORDER BY RAND() LIMIT 4";

        PreparedStatement consultaPreguntas = Conexion.getInstance().getConnection().prepareStatement(sqlPreguntas);
        ResultSet resultPreguntas = consultaPreguntas.executeQuery();
        System.out.println("¡Que comience el juego!");
        while (resultPreguntas.next())
        {
            int id = resultPreguntas.getInt("id");
            String enunciado = resultPreguntas.getString("enunciado");
            int veces_formulada = resultPreguntas.getInt("veces_formulada");
            int veces_acertada = resultPreguntas.getInt("veces_acertada");

            System.out.printf("%-5s %-10s\n", id, enunciado);
            String respuestas = respuestasPorPregunta(id);
            System.out.println(respuestas);
            //el usuario responde 1/2/3/4 en funcion de la respuesta
            System.out.println("Introduzca su respuesta: ");
            int respuestaUsuario = teclado.nextInt();
            teclado.nextLine();
            //PEENDIENTE comprobar si la respuesta del usuario es una respuesta acertada o no. BBDD respuestas -> correcta -> 1.
            
            aumentarPreguntaFormulada(veces_formulada);
        }
    }

    //Devuelve una cadena de respuestas posibles a una pregunta
    public String respuestasPorPregunta(int idPregunta) throws SQLException
    {
        String cadena = "";
        String sqlRespuestas = "SELECT * FROM respuestas WHERE pregunta_id=?";
        PreparedStatement consultaRespuestas = Conexion.getInstance().getConnection().prepareStatement(sqlRespuestas);
        consultaRespuestas.setInt(1, idPregunta);
        ResultSet result = consultaRespuestas.executeQuery();
        int contador = 1;
        while (result.next())
        {
            cadena += contador + " " + result.getString("texto") + "\n";
            contador++;
        }
        return cadena;
    }

    public void aumentarPreguntaFormulada(int veces_formulada) throws SQLException
    {
        String sql = "UPDATE preguntas SET veces_formulada=?";
        PreparedStatement update = Conexion.getInstance().getConnection().prepareStatement(sql);
        update.setInt(1, veces_formulada + 1);
        update.executeUpdate();
        System.out.println("Pregunta formulada " + veces_formulada + 1 + " veces");
    }
}
