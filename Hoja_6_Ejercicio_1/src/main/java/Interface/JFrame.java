/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;


import java.awt.Color;
import Interface.InterfazRegistrar;


/**
 *
 * @author usuario
 */
public class JFrame extends javax.swing.JFrame
{

    /**
     * Creates new form JFrame
     */
    JPanel_jugar panelJugar;
    InterfazRegistrar registro;
 
   
    //InterfazRegistro registro;
    public JFrame()
    {
        initComponents();
        panelJugar = new JPanel_jugar(this, true);
        registro = new InterfazRegistrar(this,true);
        this.setTitle("Concurso de preguntas");
        setSize(500, 500);
        setLocationRelativeTo(null);
        this.getContentPane().add(this.panelJugar);
        this.getContentPane().add(this.registro);
       
         
      
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        inicio = new javax.swing.JPanel();
        botonIniciarSesion = new javax.swing.JButton();
        botonRegistro = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        botonIniciarSesion.setText("Iniciar Sesion");
        botonIniciarSesion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botonIniciarSesionActionPerformed(evt);
            }
        });

        botonRegistro.setText("Registro");
        botonRegistro.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                botonRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inicioLayout = new javax.swing.GroupLayout(inicio);
        inicio.setLayout(inicioLayout);
        inicioLayout.setHorizontalGroup(
            inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioLayout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonIniciarSesion))
                .addContainerGap(173, Short.MAX_VALUE))
        );
        inicioLayout.setVerticalGroup(
            inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(botonIniciarSesion)
                .addGap(43, 43, 43)
                .addComponent(botonRegistro)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        getContentPane().add(inicio, "card2");

        fileMenu.setMnemonic('f');
        fileMenu.setText("Archivo");

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Salir");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Ayuda");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void botonIniciarSesionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botonIniciarSesionActionPerformed
    {//GEN-HEADEREND:event_botonIniciarSesionActionPerformed

panelJugar.setVisible(true);
inicio.setVisible(false);
            // TODO add your handling code here:
//this.add(panelJugar);
    }//GEN-LAST:event_botonIniciarSesionActionPerformed

    private void botonRegistroActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_botonRegistroActionPerformed
    {//GEN-HEADEREND:event_botonRegistroActionPerformed

        registro.setVisible(true);
        inicio.setVisible(false);
//TODO add your handling code here:

    }//GEN-LAST:event_botonRegistroActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton botonIniciarSesion;
    private javax.swing.JButton botonRegistro;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JPanel inicio;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}
