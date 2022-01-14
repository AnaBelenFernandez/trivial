package Modelo;

import java.sql.Time;
import java.time.LocalDate;

/**
 *
 * @author usuario
 */
public class Test {
 private int id;
 private Usuario usuario;
 private LocalDate fecha;
 private Time hora_inicio;
 private Time hora_fin;
 private boolean general;
 private int numero_preguntas;
 private String categoría;
 private float puntos;

    public Test()
    {
    }

    public Test(int id, Usuario usuario, LocalDate fecha, Time hora_inicio, Time hora_fin, boolean general, int numero_preguntas, String categoría, float puntos)
    {
        this.id = id;
        this.usuario = usuario;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.general = general;
        this.numero_preguntas = numero_preguntas;
        this.categoría = categoría;
        this.puntos = puntos;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Usuario getUsuario()
    {
        return usuario;
    }

    public void setUsuario(Usuario usuario)
    {
        this.usuario = usuario;
    }

    public LocalDate getFecha()
    {
        return fecha;
    }

    public void setFecha(LocalDate fecha)
    {
        this.fecha = fecha;
    }

    public Time getHora_inicio()
    {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio)
    {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin()
    {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin)
    {
        this.hora_fin = hora_fin;
    }

    public boolean isGeneral()
    {
        return general;
    }

    public void setGeneral(boolean general)
    {
        this.general = general;
    }

    public int getNumero_preguntas()
    {
        return numero_preguntas;
    }

    public void setNumero_preguntas(int numero_preguntas)
    {
        this.numero_preguntas = numero_preguntas;
    }

    public String getCategoría()
    {
        return categoría;
    }

    public void setCategoría(String categoría)
    {
        this.categoría = categoría;
    }

    public float getPuntos()
    {
        return puntos;
    }

    public void setPuntos(float puntos)
    {
        this.puntos = puntos;
    }
 
    
 
 
}
