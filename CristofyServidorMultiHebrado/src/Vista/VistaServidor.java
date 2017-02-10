/*

    - como hago para pasar la variable extendedLog, para que me funcione en todas las clases??
    tiene que ser por el constructor?

    - el boton detener, no me funciona... como lo hago??


    - 




 */
package Vista;

import Controlador.HebraCreaSocketYLanzaHebras;

/**
 *
 * @author JuanPablo
 */
public final class VistaServidor extends javax.swing.JFrame {

    //numero de puerto
    int portNumber;
    String ip;
    
    //para la conexion a la base de datos
    String usuarioBD;
    String passBD;
    
    //la hebra
    HebraCreaSocketYLanzaHebras hebraPrincipal;
    
    //para el Log extendido
    Boolean extendedLog = false;
    
    //constructor
    public VistaServidor() {
        initComponents();
        this.setLocationRelativeTo(null);
        taVentanaTexto.append("Bienvenido al servidor Cristofy\n");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taVentanaTexto = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        tfPuerto = new javax.swing.JTextField();
        btnIniciar = new javax.swing.JButton();
        btnDetener = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        tfIp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfUsuarioBD = new javax.swing.JTextField();
        tfPasswordBD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        taVentanaTexto.setColumns(20);
        taVentanaTexto.setRows(5);
        jScrollPane1.setViewportView(taVentanaTexto);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 112, 574, 267));

        jLabel1.setText("Puerto:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 34, -1, -1));

        tfPuerto.setText("5000");
        getContentPane().add(tfPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 31, 51, -1));

        btnIniciar.setText("Iniciar Servidor");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(137, 30, -1, -1));

        btnDetener.setText("Detener Servidor");
        btnDetener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetenerActionPerformed(evt);
            }
        });
        getContentPane().add(btnDetener, new org.netbeans.lib.awtextra.AbsoluteConstraints(481, 30, -1, -1));

        jCheckBox1.setText("Activa la casilla para ver Mensajes de procesamiento local del servidor extendidos");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 386, -1, -1));

        jLabel2.setText("Ip DataBase:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 34, -1, -1));

        tfIp.setText("178.62.241.66");
        tfIp.setToolTipText("");
        getContentPane().add(tfIp, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 31, 89, -1));

        jLabel3.setText("UsuarioBD:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 77, -1, -1));

        jLabel4.setText("ContraseñaBD:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(324, 77, -1, -1));

        tfUsuarioBD.setText("cristofy");
        getContentPane().add(tfUsuarioBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(173, 74, 58, -1));

        tfPasswordBD.setText("cristofy");
        getContentPane().add(tfPasswordBD, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 74, 55, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\JuanPablo\\Documents\\NetBeansProjects\\CristofyCliente\\src\\resources\\fondo.jpg")); // NOI18N
        jLabel5.setToolTipText("");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    //el boton de iniciar el servidor, ha de coger el numero de puerto y activar el servidor
    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        
        //cogemos el puerto del textField
        portNumber = Integer.parseInt(tfPuerto.getText());
        
        taVentanaTexto.append("Server encendido!!\n");
        if(extendedLog){
            taVentanaTexto.append("\tEstas abriendo el puerto " + portNumber + " para los clientes... ...\n");
        }
        
        
        //cogemos los campos de la base de datos, el user y la contraseña
        usuarioBD = tfUsuarioBD.getText();
        passBD = tfPasswordBD.getText();
        
        
        //cojo la ip donde va a estar la base de datos
        ip = tfIp.getText();
        
        
        //se lanza una hebra que haga todo el proceso de crear las conexiones y lanzar mas hebras,
        //porque sino se queda pillada la interfaz en el bucle while escuchando
        hebraPrincipal = new HebraCreaSocketYLanzaHebras(portNumber, ip, usuarioBD, passBD, taVentanaTexto, jCheckBox1 );
        hebraPrincipal.start();
        
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnDetenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetenerActionPerformed
        
        //guardar la hebra en una variable global aki en la vista
        //crear un metodo para poner el listener a false
        hebraPrincipal.pararHebra();
        taVentanaTexto.append("Server apagado!!\n");        
        hebraPrincipal.destroy();
               
    }//GEN-LAST:event_btnDetenerActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        
        extendedLog = jCheckBox1.isSelected();
            
    }//GEN-LAST:event_jCheckBox1ActionPerformed


    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaServidor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetener;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea taVentanaTexto;
    private javax.swing.JTextField tfIp;
    private javax.swing.JTextField tfPasswordBD;
    private javax.swing.JTextField tfPuerto;
    private javax.swing.JTextField tfUsuarioBD;
    // End of variables declaration//GEN-END:variables
}