/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import BaseDatos.Conexion;
import BaseDatos.GestorDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import java.awt.Frame;

import BaseDatos.GestorDB;

/**
 *
 * @author usuario
 */
public class JPanel_jugar extends javax.swing.JPanel
{

    /**
     * Creates new form JPanel_jugar
     */
    JPanel_jugar panelJugar;
    JPanel_pregunta panelPregunta;
    public JPanel_jugar()
    {
        initComponents();
    }

    public JPanel_jugar(Frame parent, boolean modal)
    {

        initComponents();
        setSize(500, 500);

        panelJugar=new JPanel_jugar();
        panelPregunta=new JPanel_pregunta();
        
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jButton_jugar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField_nombreUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField_password = new javax.swing.JTextField();
        jButton_inicio = new javax.swing.JButton();

        jButton_jugar.setBackground(new java.awt.Color(104, 85, 100));
        jButton_jugar.setFont(new java.awt.Font("Ubuntu", 3, 15)); // NOI18N
        jButton_jugar.setText("COMENZAR A JUGAR");
        jButton_jugar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_jugarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel1.setText("NOMBRE DE USUARIO");

        jTextField_nombreUsuario.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jTextField_nombreUsuarioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("CONTRASE??A");

        jTextField_password.setToolTipText("");

        jButton_inicio.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jButton_inicio.setText("INICIO");
        jButton_inicio.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton_inicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField_nombreUsuario)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField_password)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_jugar, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jTextField_nombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton_jugar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_inicio)
                .addContainerGap(21, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_jugarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_jugarActionPerformed
    {//GEN-HEADEREND:event_jButton_jugarActionPerformed
        String usuario = jTextField_nombreUsuario.getText();
        String password = jTextField_password.getText();
        //verificar que nombre y password son correctos
        GestorDB gestor = new GestorDB();
        if (verificar(usuario, password))
        {
           // gestor.jugar(usuario, password);
            panelPregunta.setVisible(true);
            panelJugar.setVisible(false);
        }
        else
            JOptionPane.showMessageDialog(null, "Se han introducido err??neamente la contrase??a o nombre de usuario :S");
    }//GEN-LAST:event_jButton_jugarActionPerformed

    public boolean verificar(String usuario, String password)
    {
        boolean verificado = false;
        String sql = "SELECT usuario, password from usuarios WHERE usuario=? AND password=?";
        try
        {
            PreparedStatement consultaUsuario = Conexion.getInstance().getConnection().prepareStatement(sql);
            consultaUsuario.setString(1, usuario);
            consultaUsuario.setString(2, password);
            ResultSet result = consultaUsuario.executeQuery();

            result.last();
            int numeroConsultas = result.getRow();
            
            if (numeroConsultas > 0)
            {
                verificado = true;
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(JPanel_jugar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return verificado;
    }

    private void jTextField_nombreUsuarioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jTextField_nombreUsuarioActionPerformed
    {//GEN-HEADEREND:event_jTextField_nombreUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_nombreUsuarioActionPerformed

    private void jButton_inicioActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton_inicioActionPerformed
    {//GEN-HEADEREND:event_jButton_inicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_inicioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_inicio;
    private javax.swing.JButton jButton_jugar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField_nombreUsuario;
    private javax.swing.JTextField jTextField_password;
    // End of variables declaration//GEN-END:variables
}
