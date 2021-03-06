//Package
package fciencias.edatos.motorBusqueda;

//Imports
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * Clase de la GUI Historial.
 * @author Axel Daniel Malváez Flores
 * @version 1.0
 */
public class Historial extends javax.swing.JFrame {

    //Atributos de la GUI Historial.
    private File[] arrDocs;
    private ArrayList<String>[] stringList;
    private Hashtable<String, Pair<String, Double>> ht;
    private ArrayList<Pair<String,Double>>[] arrTF;
    private ArrayList<Pair<String,Double>>[] arrTFIDF;
    private ArrayList<Pair<ArrayList<String>, ArrayList<String>>> cache;
    
    /**
     * Método constructor de la GUI Historial.
     * @param text - El string que mostraremos.
     */
    public Historial(String text) {
        arrDocs = null;
        stringList = null;
        ht = null;
        arrTF = null;
        arrTFIDF = null;
        cache = null;
        initComponents();
        this.setLocationRelativeTo(null);
        getIconImage();
        addHistorial(text);
    }
    
    /**
     * Método que modifica el icono de la GUI
     * @return Image - imagen para el icono de la GUI.
     */
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/logoMD.png"));
        return retValue;
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textHistorial = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        backButton = new javax.swing.JButton();
        cleanHistorial = new javax.swing.JButton();
        salirPrograma = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textHistorial.setFont(new java.awt.Font("Orator Std", 0, 36)); // NOI18N
        textHistorial.setText("HISTORIAL");
        getContentPane().add(textHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        jScrollPane1.setBackground(new java.awt.Color(102, 102, 102));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 370, 170));

        backButton.setBackground(new java.awt.Color(102, 102, 102));
        backButton.setFont(new java.awt.Font("Orator Std", 0, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, 40));

        cleanHistorial.setBackground(new java.awt.Color(102, 102, 102));
        cleanHistorial.setFont(new java.awt.Font("Orator Std", 0, 14)); // NOI18N
        cleanHistorial.setForeground(new java.awt.Color(255, 255, 255));
        cleanHistorial.setText("Clean Historial");
        cleanHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanHistorialActionPerformed(evt);
            }
        });
        getContentPane().add(cleanHistorial, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 170, 40));

        salirPrograma.setBackground(new java.awt.Color(102, 102, 102));
        salirPrograma.setFont(new java.awt.Font("Orator Std", 0, 12)); // NOI18N
        salirPrograma.setForeground(new java.awt.Color(255, 255, 255));
        salirPrograma.setText("Salir");
        salirPrograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirProgramaActionPerformed(evt);
            }
        });
        getContentPane().add(salirPrograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 310, -1, 40));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/frame1.png"))); // NOI18N
        fondo.setText("jLabel1");
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 356));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        //Create a new Menu Frame.
        Menu menu = new Menu();
        
        //Attributes.
        menu.setArrDocs(arrDocs);
        menu.setArrTF(arrTF);
        menu.setArrTFIDF(arrTFIDF);
        menu.setCache(cache);
        menu.setHt(ht);
        menu.setStringList(stringList);
        
        //Display the Menu Frame.
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * Método que modifica el comportamiendo del cleanHistorialButton.
     * @param evt - Objeto de tipo ActionEvent.
     */
    private void cleanHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanHistorialActionPerformed
        //Create new Reader Object.
        Reader reader = new Reader();
        
        //Clean HistorialDeBusqueda.txt
        reader.cleanDocument("HistorialDeBusqueda.txt");
        String hi = reader.readDocument("HistorialDeBusqueda.txt");
        textArea.setText(hi);
    }//GEN-LAST:event_cleanHistorialActionPerformed

    /**
     * Método que modifica el comportamiento del salirButton
     * @param evt - Objeto de tipo ActionEvent.
     */
    private void salirProgramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirProgramaActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirProgramaActionPerformed

    /**
     * Método que añade el String pasado como parámetro al área de texto.
     * @param text - String historial ya calculado.
     */
    public void addHistorial(String text){
        textArea.setText(text);
    }
    
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
            java.util.logging.Logger.getLogger(Historial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Historial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Historial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Historial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String historial = "";
                new Historial(historial).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton cleanHistorial;
    private javax.swing.JLabel fondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton salirPrograma;
    private javax.swing.JTextArea textArea;
    private javax.swing.JLabel textHistorial;
    // End of variables declaration//GEN-END:variables
}
