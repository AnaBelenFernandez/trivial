package BaseDatos;

import Modelo.Pregunta;
import Modelo.Respuesta;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

                contabilizarAcceso(resultUsuario.getInt("num_accesos") + 1, usuario);

                preguntarAUsuario();

                aumentarTestRealizados(resultUsuario.getInt("test_realizados"), usuario);
            }

        } catch (SQLException ex)
        {
            System.out.println(ex.toString());
        }
    }

    //aumenta el numero de accesos de un usuario cada vez que inicia sesion
    public void contabilizarAcceso(int numeroAccesos, String usuario) throws SQLException
    {
        String sql = "UPDATE usuarios SET num_accesos=? WHERE usuario=?";

        PreparedStatement update = Conexion.getInstance().getConnection().prepareStatement(sql);
        update.setInt(1, numeroAccesos);
        update.setString(2, usuario);
        int numeroRegistros = update.executeUpdate();
        // System.out.println("Aumentado el numero de registros del usuario");
    }

    //aumenta el numero de test realizados de un usuario cuando realiza un test
    public void aumentarTestRealizados(int test_realizados, String usuario) throws SQLException
    {
        String sql = "UPDATE usuarios SET test_realizados=? WHERE usuario=?";

        PreparedStatement update = Conexion.getInstance().getConnection().prepareStatement(sql);
        update.setInt(1, test_realizados + 1);
        update.setString(2, usuario);
        int numeroRegistros = update.executeUpdate();
        // System.out.println("Aumentado el numero de registros del usuario");
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
            //obtenemos las respuestas de dicha pregunta y las mostramos
            List<Respuesta> respuestas = respuestasPorPregunta(id);
            int contador = 1;
            String cadenaRespuestas = "";
            for (Respuesta respuesta : respuestas)
            {
                cadenaRespuestas += contador + " " + respuesta.getTexto() + "\n";
                contador++;
            }
            System.out.println(cadenaRespuestas);

            //el usuario responde 1/2/3/4 en funcion de la respuesta. Se comprueba si dicha respuesta es correcta
            System.out.println("Introduzca su respuesta: (1/2/3/4)");
            int respuestaUsuario = teclado.nextInt();
            teclado.nextLine();
            if (respuestas.get(respuestaUsuario - 1).isCorrecta())
            {
                System.out.println("Enhorabuena, ¡respuesta acertada!");
                aumentarPreguntaAcertada(veces_acertada, id);
            } else
            {
                System.out.println("¡Oh vaya! ha fallado. Intentelo de nuevo D: ");
            }
            //Contabilizamos las respuestas en la base de datos
            aumentarRespuestaRespondida(respuestas.get(respuestaUsuario - 1).getVeces_respondida(), id);
            aumentarPreguntaFormulada(veces_formulada, id);
        }
    }

    //Devuelve una lista de respuestas posibles a una pregunta
    public List<Respuesta> respuestasPorPregunta(int idPregunta) throws SQLException
    {
        List<Respuesta> respuestas = new ArrayList();
        String sqlRespuestas = "SELECT * FROM respuestas WHERE pregunta_id=?";
        PreparedStatement consultaRespuestas = Conexion.getInstance().getConnection().prepareStatement(sqlRespuestas);
        consultaRespuestas.setInt(1, idPregunta);
        ResultSet result = consultaRespuestas.executeQuery();

        while (result.next())
        {
            Respuesta r = new Respuesta();

            r.setId(result.getInt("id"));
            //r.setPregunta(result.getObject("pregunta_id"));
            r.setTexto(result.getString("texto"));
            r.setCorrecta(result.getBoolean("correcta"));
            r.setFoto(result.getString("foto"));
            r.setVeces_respondida(result.getInt("veces_respondida"));
            respuestas.add(r);
        }
        return respuestas;
    }

    //Aumenta el numero de veces que una pregunta es formulada
    public void aumentarPreguntaFormulada(int veces_formulada, int id) throws SQLException
    {
        String sql = "UPDATE preguntas SET veces_formulada=? WHERE id=?";
        PreparedStatement update = Conexion.getInstance().getConnection().prepareStatement(sql);
        update.setInt(1, veces_formulada + 1);
        update.setInt(2, id);
        update.executeUpdate();
        // System.out.println("Pregunta formulada " + veces_formulada + 1 + " veces");
    }

    //Aumenta el numero de veces que una pregunta es acertada
    public void aumentarPreguntaAcertada(int veces_acertada, int id) throws SQLException
    {
        String sql = "UPDATE preguntas SET veces_acertada=? WHERE id=?";
        PreparedStatement update = Conexion.getInstance().getConnection().prepareStatement(sql);
        update.setInt(1, veces_acertada + 1);
        update.setInt(2, id);
        update.executeUpdate();
    }

    //Aumenta el numero de veces que se responde una respuesta
    public void aumentarRespuestaRespondida(int veces_respondida, int id) throws SQLException
    {
        String sql = "UPDATE respuestas SET veces_respondida=? WHERE id=?";
        PreparedStatement update = Conexion.getInstance().getConnection().prepareStatement(sql);
        update.setInt(1, veces_respondida + 1);
        update.setInt(2, id);
        update.executeUpdate();
    }
}
