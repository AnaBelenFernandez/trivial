/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import BaseDatos.Conexion;
import BaseDatos.GestorDB;
import Modelo.Respuesta;
import java.awt.Frame;
import java.awt.Panel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

/**
 *
 * @author usuario
 */
public class JPanel_pregunta extends javax.swing.JPanel
{
    boolean siguientePregunta = false;
    /**
     * Creates new form JPanel_pregunta
     */
    GestorDB gestor;
    public JPanel_pregunta()
    {
        initComponents();
         setSize(500,500);
     ButtonGroup radioGroup = new ButtonGroup();
     radioGroup.add(RBRespuesta1);
     radioGroup.add(RBRespuesta2);
     radioGroup.add(RBRespuesta3);
     radioGroup.add(RBRespuesta4);
     gestor=new GestorDB();

     

    }
    
     public JPanel_pregunta(JPanel parent, boolean modal) {
        
        initComponents();
         setSize(500,500);
        
       
    }
     
    public void cargarPregunta(){
    }
public void colorear(){
 


}

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
            siguientePregunta = false;
            
            int id = resultPreguntas.getInt("id");
            String enunciado = resultPreguntas.getString("enunciado");
            int veces_formulada = resultPreguntas.getInt("veces_formulada");
            int veces_acertada = resultPreguntas.getInt("veces_acertada");

            //System.out.printf("%-5s %-10s\n", id, enunciado); EN VEZ DE SOUT QUE SE VEA EN EL LABEL
            jLabel_pregunta.setText(id + " - " +enunciado);
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
            //teclado.nextLine(); NO PASA AL DAR AL ENTER, SINO AL CLICAR EN EL BUTTON
            
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

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        RBRespuesta1 = new javax.swing.JRadioButton();
        RBRespuesta3 = new javax.swing.JRadioButton();
        RBRespuesta2 = new javax.swing.JRadioButton();
        RBRespuesta4 = new javax.swing.JRadioButton();
        btnSigPregunta = new javax.swing.JButton();
        jLabel_pregunta = new javax.swing.JLabel();

        btnSigPregunta.setText("Siguiente Pregunta");
        btnSigPregunta.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSigPreguntaActionPerformed(evt);
            }
        });

        jLabel_pregunta.setText("PREGUNTA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(btnSigPregunta)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_pregunta, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(76, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RBRespuesta2)
                            .addComponent(RBRespuesta1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RBRespuesta3)
                            .addComponent(RBRespuesta4, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel_pregunta)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(RBRespuesta1)
                    .addComponent(RBRespuesta3))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(RBRespuesta2)
                    .addComponent(RBRespuesta4))
                .addGap(52, 52, 52)
                .addComponent(btnSigPregunta)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSigPreguntaActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSigPreguntaActionPerformed
    {//GEN-HEADEREND:event_btnSigPreguntaActionPerformed
        siguientePregunta = true;
    }//GEN-LAST:event_btnSigPreguntaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RBRespuesta1;
    private javax.swing.JRadioButton RBRespuesta2;
    private javax.swing.JRadioButton RBRespuesta3;
    private javax.swing.JRadioButton RBRespuesta4;
    private javax.swing.JButton btnSigPregunta;
    private javax.swing.JLabel jLabel_pregunta;
    // End of variables declaration//GEN-END:variables
}
