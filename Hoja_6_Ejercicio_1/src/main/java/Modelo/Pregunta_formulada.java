package Modelo;

/**
 *
 * @author usuario
 */
public class Pregunta_formulada {
    private int id;
    private Test test;
    private Pregunta pregunta;
    private boolean acertada;
    private float puntos;

    public Pregunta_formulada()
    {
    }

    public Pregunta_formulada(int id, Test test, Pregunta pregunta, boolean acertada, float puntos)
    {
        this.id = id;
        this.test = test;
        this.pregunta = pregunta;
        this.acertada = acertada;
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

    public Test getTest()
    {
        return test;
    }

    public void setTest(Test test)
    {
        this.test = test;
    }

    public Pregunta getPregunta()
    {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta)
    {
        this.pregunta = pregunta;
    }

    public boolean isAcertada()
    {
        return acertada;
    }

    public void setAcertada(boolean acertada)
    {
        this.acertada = acertada;
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
