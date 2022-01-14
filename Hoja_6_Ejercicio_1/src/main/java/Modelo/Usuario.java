package Modelo;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author usuario
 */
public class Usuario {
    private int id;
    private String usuario;
    private String password;
    private String nombre;
    private String apellidos;
    private LocalDate fecha_nacimiento;
    private int num_accesos;
    private int test_realizados;
    private float puntuacion_media;
    private String pwd;

    public Usuario()
    {
    }

    public Usuario(String nombre, String apellidos, LocalDate fecha_nacimiento, String usuario, String password)
    {
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha_nacimiento = fecha_nacimiento;
    }

   

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellidos()
    {
        return apellidos;
    }

    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    public LocalDate getFecha_nacimiento()
    {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento)
    {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public int getNum_accesos()
    {
        return num_accesos;
    }

    public void setNum_accesos(int num_accesos)
    {
        this.num_accesos = num_accesos;
    }

    public int getTest_realizados()
    {
        return test_realizados;
    }

    public void setTest_realizados(int test_realizados)
    {
        this.test_realizados = test_realizados;
    }

    public float getPuntuacion_media()
    {
        return puntuacion_media;
    }

    public void setPuntuacion_media(float puntuacion_media)
    {
        this.puntuacion_media = puntuacion_media;
    }
    
}
