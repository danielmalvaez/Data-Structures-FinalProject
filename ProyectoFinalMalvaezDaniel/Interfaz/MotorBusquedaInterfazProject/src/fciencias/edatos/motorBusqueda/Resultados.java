//Package
package fciencias.edatos.motorBusqueda;

//Imports
import java.util.*;
import java.io.*;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Clase de la GUI Resultados
 * @author Axel Daniel Malváez Flores
 * @version 1.0
 */
public class Resultados extends javax.swing.JFrame {

    //Atributos de la GUI Resultados.
    private ArrayList<String> simi;
    private File[] arrDocs;
    private ArrayList<String>[] stringList;
    private Hashtable<String, Pair<String, Double>> ht;
    private ArrayList<Pair<String,Double>>[] arrTF;
    private ArrayList<Pair<String,Double>>[] arrTFIDF;
    private ArrayList<Pair<ArrayList<String>, ArrayList<String>>> cache;
    
    /**
     * Método constructor de la GUI Resultados.
     * @param simi 
     */
    public Resultados(ArrayList<String> simi) {
        this.simi = simi;
        arrDocs = null;
        stringList = null;
        ht = null;
        arrTF = null;
        arrTFIDF = null;
        cache = null;
        initComponents();
        this.setLocationRelativeTo(null);
        mostrarResultados();
        getIconImage();
    }
    
    /**
     * Método que cambia el icono de la GUI.
     * @return Image - imagen para el icono.
     */
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/logoMD.png"));
        return retValue;
    }

    /**
     * Setter del array simi.
     * @param simi - Un arraylist de tipo String.
     */
    public void setSimi(ArrayList<String> simi) {
        this.simi = simi;
    }

    /**
     * Setter para arrDocs
     * @param arrDocs - Arreglo de Files.
     */
    public void setArrDocs(File[] arrDocs) {
        this.arrDocs = arrDocs;
    }

    /**
     * Setter para stringList
     * @param stringList - Arreglo de ArrayLists de tipo String.
     */
    public void setStringList(ArrayList<String>[] stringList) {
        this.stringList = stringList;
    }

    /**
     * Setter para el Hashtable ht
     * @param ht - Hashtable con key String y con value de tipo Pair<Stirng, 
     * Double>.
     */
    public void setHt(Hashtable<String, Pair<String, Double>> ht) {
        this.ht = ht;
    }

    /**
     * Setter para el arrTF
     * @param arrTF - Arreglo de Arraylists de tipo Pair<String, Double>.
     */
    public void setArrTF(ArrayList<Pair<String, Double>>[] arrTF) {
        this.arrTF = arrTF;
    }

    /**
     * Setter para el arrTFIDF
     * @param arrTFIDF - Arreglo que ArrayLists de tipo Pair<String, Double>.
     */
    public void setArrTFIDF(ArrayList<Pair<String, Double>>[] arrTFIDF) {
        this.arrTFIDF = arrTFIDF;
    }

    /**
     * Setter para le cache.
     * @param cache - Un ArrayLists de tipo Pair con value ArrayList<String> y
     * value ArrayList<String>.
     */
    public void setCache(ArrayList<Pair<ArrayList<String>, ArrayList<String>>> cache) {
        this.cache = cache;
    }
    
    /**
     * Método que muestra los mejores 10 resultados en la pantalla.
     */
    public void mostrarResultados(){
        //Creamos un iterador.
        for(int i = 0; i < simi.size(); i++){
            System.out.println(simi.get(i));
            if(i == 0)
                coincidencia1.setText("1." + simi.get(i));
            else if(i == 1)
                coincidencia2.setText("2." + simi.get(i));
            else if(i == 2)
                coincidencia3.setText("3." + simi.get(i));
            else if(i == 3)
                coincidencia4.setText("4." + simi.get(i));
            else if(i == 4)
                coincidencia5.setText("5." + simi.get(i));
            else if(i == 5)
                coincidencia6.setText("6." + simi.get(i));
            else if(i == 6)
                coincidencia7.setText("7." + simi.get(i));
            else if(i == 7)
                coincidencia8.setText("8." + simi.get(i));
            else if(i == 8)
                coincidencia9.setText("9." + simi.get(i));
            else if(i == 9)
                coincidencia10.setText("10." + simi.get(i));
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        coincidencia1 = new javax.swing.JLabel();
        coincidencia2 = new javax.swing.JLabel();
        coincidencia3 = new javax.swing.JLabel();
        coincidencia4 = new javax.swing.JLabel();
        coincidencia5 = new javax.swing.JLabel();
        coincidencia6 = new javax.swing.JLabel();
        coincidencia7 = new javax.swing.JLabel();
        coincidencia8 = new javax.swing.JLabel();
        coincidencia9 = new javax.swing.JLabel();
        coincidencia10 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        salirButton = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Orator Std", 0, 18)); // NOI18N
        jLabel1.setText("Coincidencias con su búsqueda:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        coincidencia1.setFont(new java.awt.Font("Orator Std", 0, 14)); // NOI18N
        coincidencia1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coincidencia1.setText(" ");
        getContentPane().add(coincidencia1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, 200, -1));

        coincidencia2.setFont(new java.awt.Font("Orator Std", 0, 14)); // NOI18N
        coincidencia2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coincidencia2.setText(" ");
        getContentPane().add(coincidencia2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 200, -1));

        coincidencia3.setFont(new java.awt.Font("Orator Std", 0, 14)); // NOI18N
        coincidencia3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coincidencia3.setText(" ");
        getContentPane().add(coincidencia3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 200, -1));

        coincidencia4.setFont(new java.awt.Font("Orator Std", 0, 14)); // NOI18N
        coincidencia4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coincidencia4.setText(" ");
        getContentPane().add(coincidencia4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 200, -1));

        coincidencia5.setFont(new java.awt.Font("Orator Std", 0, 14)); // NOI18N
        coincidencia5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coincidencia5.setText(" ");
        getContentPane().add(coincidencia5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 200, -1));

        coincidencia6.setFont(new java.awt.Font("Orator Std", 0, 14)); // NOI18N
        coincidencia6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coincidencia6.setText(" ");
        getContentPane().add(coincidencia6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 200, -1));

        coincidencia7.setFont(new java.awt.Font("Orator Std", 0, 14)); // NOI18N
        coincidencia7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(coincidencia7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 168, 200, 20));

        coincidencia8.setFont(new java.awt.Font("Orator Std", 0, 14)); // NOI18N
        coincidencia8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coincidencia8.setText(" ");
        getContentPane().add(coincidencia8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 200, -1));

        coincidencia9.setFont(new java.awt.Font("Orator Std", 0, 14)); // NOI18N
        coincidencia9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coincidencia9.setText(" ");
        getContentPane().add(coincidencia9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 200, -1));

        coincidencia10.setFont(new java.awt.Font("Orator Std", 0, 14)); // NOI18N
        coincidencia10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        coincidencia10.setText(" ");
        getContentPane().add(coincidencia10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 200, -1));

        backButton.setBackground(new java.awt.Color(102, 102, 102));
        backButton.setFont(new java.awt.Font("Orator Std", 0, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 319, 80, 30));

        salirButton.setBackground(new java.awt.Color(102, 102, 102));
        salirButton.setFont(new java.awt.Font("Orator Std", 0, 12)); // NOI18N
        salirButton.setForeground(new java.awt.Color(255, 255, 255));
        salirButton.setText("Salir");
        salirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirButtonActionPerformed(evt);
            }
        });
        getContentPane().add(salirButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, 90, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/frame1.png"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 356));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que modifica el comportamiento del backButton.
     * @param evt - Objeto de tipo ActionEvent
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        Buscar buscar = new Buscar();
        //Atributos
        buscar.setArrDocs(arrDocs);
        buscar.setArrTF(arrTF);
        buscar.setArrTFIDF(arrTFIDF);
        buscar.setCache(cache);
        buscar.setStringList(stringList);
        buscar.setHt(ht);
        
        buscar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * Método que modifica el comportamiento del salirButton.
     * @param evt - Objeto de tipo ActionEvent
     */
    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Resultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Resultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Resultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Resultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ArrayList<String> simi = null;
                new Resultados(simi).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel coincidencia1;
    private javax.swing.JLabel coincidencia10;
    private javax.swing.JLabel coincidencia2;
    private javax.swing.JLabel coincidencia3;
    private javax.swing.JLabel coincidencia4;
    private javax.swing.JLabel coincidencia5;
    private javax.swing.JLabel coincidencia6;
    private javax.swing.JLabel coincidencia7;
    private javax.swing.JLabel coincidencia8;
    private javax.swing.JLabel coincidencia9;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton salirButton;
    // End of variables declaration//GEN-END:variables
}
