//Package
package fciencias.edatos.motorBusqueda;

//Imports
import java.util.*;
import java.io.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

/**
 * Clase de la GUI Buscar.
 * @author Axel Daniel Malváez Flores
 * @version 2.0
 */
public class Buscar extends javax.swing.JFrame {
    //Atributos de la GUI
    javax.swing.Timer t;
    int x = 0;
    private final ActionListener ac;
    private File[] arrDocs;
    private ArrayList<String>[] stringList;
    private Hashtable<String, Pair<String, Double>> ht;
    private ArrayList<Pair<String,Double>>[] arrTF;
    private ArrayList<Pair<String,Double>>[] arrTFIDF;
    private ArrayList<Pair<ArrayList<String>, ArrayList<String>>> cache;
    
    //Variable que sirve al momento de buscar en el cache
    boolean iguales;
    //Variable que sirve al momento de buscar en el cache
    ArrayList<String> simi;
    //Variable que sirve al momento de calcular los documentos mas relevantes
    ArrayList<String> docsRelevantes;
    //Objeto JFrame para que abra una nueva GUI con los resultados respectivos
    Resultados resultados = null;
    //Variable que sirve al momento de buscar en el cache 
    boolean simiEmpty;
    //Objeto auxiliar de la clase Auxiliar que nos ayuda a hacer los calculos
    Auxiliar aux = new Auxiliar();
    
    /**
     * Metodo constructor de la GUI Buscar
     */
    public Buscar() {
        arrDocs = null;
        stringList = null;
        ht = null;
        arrTF = null;
        arrTFIDF = null;
        cache = null;
        initComponents();
        this.setLocationRelativeTo(null);
        ac = new ActionListener() {
                    @Override
                    public void actionPerformed(java.awt.event.ActionEvent e) {
                        x = x + 1;
                        progressBar.setValue(x);
                        if (progressBar.getValue() == x) {
                            porcentaje.setText(String.valueOf(x) + "%");
                        }
                        if(progressBar.getValue() == 1)
                            warningText.setText("Buscando...");
                        
                        if(progressBar.getValue() == 100){
                            if((!simiEmpty) && iguales == true){
                                t.stop();
                                //Agregar atributos
                                resultados.setSimi(simi);
                                resultados.setArrTF(arrTF);
                                resultados.setArrTFIDF(arrTFIDF);
                                resultados.setCache(cache);
                                resultados.setHt(ht);
                                resultados.setStringList(stringList);
                                resultados.setArrDocs(arrDocs);

                                resultados.setVisible(true);
                                dispose();
                            }else{
                                t.stop();
                                //Agregar atributos
                                resultados.setSimi(docsRelevantes);
                                resultados.setArrTF(arrTF);
                                resultados.setArrTFIDF(arrTFIDF);
                                resultados.setCache(cache);
                                resultados.setHt(ht);
                                resultados.setStringList(stringList);
                                resultados.setArrDocs(arrDocs);

                                resultados.setVisible(true);
                                dispose();
                            }
                        }
                    }
                };        
        //Iniciar timer para la barra.
        t = new javax.swing.Timer(20, ac);
        getIconImage();
    }

    /**
     * Metodo que cambia el icono de la GUI Buscar
     * @return Image - Imagen del nuevo icono.
     */
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imagenes/logoMD.png"));
        return retValue;
    }
    
    /**
     * Setter para arrDocs.
     * @param arrDocs - Arreglo de Files.
     */
    public void setArrDocs(File[] arrDocs) {
        this.arrDocs = arrDocs;
    }

    /**
     * Setter para stringList.
     * @param stringList - Arreglo de listas de tipo String.
     */
    public void setStringList(ArrayList<String>[] stringList) {
        this.stringList = stringList;
    }
    
    /**
     * Setter para el Hashtable ht.
     * @param ht - un Hashtable de tipo String como key y Pair<String, Double>
     * para el value.
     */
    public void setHt(Hashtable<String, Pair<String, Double>> ht) {
        this.ht = ht;
    }

    /**
     * Setter para arrTF
     * @param arrTF - Arreglo de listas de tipo Pair con key String y value de
     * tipo Double.
     */
    public void setArrTF(ArrayList<Pair<String, Double>>[] arrTF) {
        this.arrTF = arrTF;
    }

    /**
     * Setter para arrTFIDF
     * @param arrTFIDF - Arreglo de listas de tipo pair con key String y valor
     * Double. 
     */
    public void setArrTFIDF(ArrayList<Pair<String, Double>>[] arrTFIDF) {
        this.arrTFIDF = arrTFIDF;
    }

    /**
     * Setter para el cache.
     * @param cache - Un arrayList de tipo Pair<ArrayList<String>, 
     * ArrayList<String> para almacenar las busquedas y sus respectivos
     * resultados.
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

        busquedaText = new javax.swing.JLabel();
        textBusqueda = new javax.swing.JTextField();
        buscarButton = new javax.swing.JButton();
        salirPrograma = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        warningText = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        porcentaje = new javax.swing.JLabel();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        busquedaText.setFont(new java.awt.Font("Orator Std", 0, 36)); // NOI18N
        busquedaText.setText("Búsqueda");
        getContentPane().add(busquedaText, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));

        textBusqueda.setBackground(new java.awt.Color(204, 204, 204));
        textBusqueda.setFont(new java.awt.Font("Orator Std", 0, 12)); // NOI18N
        textBusqueda.setForeground(new java.awt.Color(102, 102, 102));
        textBusqueda.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textBusqueda.setText("Ingrese su búsqueda...");
        textBusqueda.setToolTipText("");
        getContentPane().add(textBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 610, 70));

        buscarButton.setBackground(new java.awt.Color(153, 153, 153));
        buscarButton.setFont(new java.awt.Font("Orator Std", 0, 14)); // NOI18N
        buscarButton.setForeground(new java.awt.Color(255, 255, 255));
        buscarButton.setText("Buscar");
        buscarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(buscarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 100, 50));

        salirPrograma.setBackground(new java.awt.Color(153, 153, 153));
        salirPrograma.setFont(new java.awt.Font("Orator Std", 0, 12)); // NOI18N
        salirPrograma.setForeground(new java.awt.Color(255, 255, 255));
        salirPrograma.setText("Salir");
        salirPrograma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirProgramaActionPerformed(evt);
            }
        });
        getContentPane().add(salirPrograma, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 100, 30));

        backButton.setBackground(new java.awt.Color(153, 153, 153));
        backButton.setFont(new java.awt.Font("Orator Std", 0, 12)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        getContentPane().add(backButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 100, 30));

        warningText.setFont(new java.awt.Font("Orator Std", 0, 13)); // NOI18N
        warningText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        warningText.setText("Búsqueda");
        getContentPane().add(warningText, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 160, 20));
        getContentPane().add(progressBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, -1, -1));

        porcentaje.setBackground(new java.awt.Color(255, 255, 255));
        porcentaje.setFont(new java.awt.Font("Orator Std", 0, 12)); // NOI18N
        porcentaje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(porcentaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 60, 20));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/frame1.png"))); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 356));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que modifica el comportamiento del botón salir.
     * @param evt - Objeto de tipo ActionEvent
     */
    private void salirProgramaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirProgramaActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirProgramaActionPerformed

    /**
     * Método que modifica el comportamiento del botón back.
     * @param evt - Objeto de tipo ActionEvent
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        //Creación del Menu.
        Menu menu = new Menu();
        
        //Atributos.
        menu.setArrDocs(arrDocs);
        menu.setArrTF(arrTF);
        menu.setArrTFIDF(arrTFIDF);
        menu.setHt(ht);
        menu.setStringList(stringList);
        menu.setCache(cache);
        
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * Método que modifica el comportamiento del botón buscar.
     * @param evt - Objeto de tipo ActionEvent
     */
    private void buscarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarButtonActionPerformed
        //variable para la busqueda en String.
        String search = textBusqueda.getText();
        
        //Objetos que usaremos para los calculos.
        Reader reader = new Reader();
        Auxiliar aux = new Auxiliar();
        TFIDFcalculator calculator = new TFIDFcalculator();
        
        //Busqueda vista como una lista.
	ArrayList<String> busqueda = null;
        //Hashtable para el IDF de la busqueda.
	Hashtable<String, Pair<String, Double>> htBusqueda = null;
	//Arreglo para el TF de la busqueda.
	ArrayList<Pair<String,Double>>[] arrTFBusqueda = null;
	//Arreglo para el TF-IDF de la busqueda.
	ArrayList<Pair<String, Double>>[] arrTFIDFBusqueda = null;
	//Similitud de la busqueda.
	Pair<Integer, Double>[] similitud = null;
	//Documentos mas relevantes para la busqueda.
	ArrayList<String> docsRelevantes = null;
        
        if(search.length() < 200){
            reader.writeString(search);
            busqueda = reader.readString(search);
            //Verificar si la longitud es 0
            if(busqueda.size() == 0){
                warningText.setText("Busqueda invalida.");
                return;
            }
            
            //Buscar en el cache.
            if(!cache.isEmpty()){
                System.out.println("Si entra al cache");
		//Pareja auxiliar.
                Pair<ArrayList<String>, ArrayList<String>> pareja = null;
        	//Iterador para recorrer el cache.
                Iterator iteradorCache = cache.iterator();          
                //Verificar si son iguales. 
                iguales = true;
                //Mientras el cache tenga siguiente.
                while(iteradorCache.hasNext()){
                    pareja = (Pair<ArrayList<String>, ArrayList<String>>)iteradorCache.next();
                    ArrayList<String> guardada = pareja.getValue();
                    /*Si el tamaño de las listas no coincide sabemos que no es
                    * la misma busqueda. */
                    if(guardada.size() != busqueda.size()){
                        iguales = false;
                        continue;
                    }
				
                    Iterator iteradorGuardada = guardada.iterator();
                    Iterator iteradorBusqueda = busqueda.iterator();

                    while(iteradorGuardada.hasNext()){
                        iguales = true;
			String guar = (String)iteradorGuardada.next();
			String bus = (String)iteradorBusqueda.next();
                        if(!guar.equals(bus)){
                            iguales = false;
                            break;
                        }
                    }
                    //Si son iguales mostrar los resultados.
                    if(iguales == true){
                        System.out.println("Si entra al iguales");
                        simi = pareja.getKey();
                        //Imprimir los documentos relevantes.
                        simiEmpty = simi.isEmpty();
                        if(simiEmpty){
                            warningText.setText("Ningun documento es relevante para su busqueda.");
                            return;
                        } else{
                            //Abrir resultados.
                            resultados = new Resultados(simi);
                            t.start();
                            /*
                            //Abrir resultados.
                            resultados = new Resultados(simi);
                            //Agregar atributos
                            resultados.setSimi(simi);
                            resultados.setArrTF(arrTF);
                            resultados.setArrTFIDF(arrTFIDF);
                            resultados.setCache(cache);
                            resultados.setHt(ht);
                            resultados.setStringList(stringList);
                            resultados.setArrDocs(arrDocs);
                            
                            resultados.setVisible(true);
                            this.dispose();
                            */
                            return;
                        }
                    }
		}//While iterador cache.
            }//Termina de buscar en el cache.
            
            
            //Si no esta en cache, hacer los calculos de la busqueda.
            htBusqueda = calculator.calcularIDF(stringList, busqueda);
            System.out.println("Si calcula el hashtable");
            arrTFBusqueda = calculator.calcularTF(stringList, busqueda);
            System.out.println("Si calcula el TF");
            arrTFIDFBusqueda = calculator.calcularTFIDF(arrTFBusqueda, htBusqueda);
            System.out.println("Si calcula el IDF");
            
            
            //Calcular la similitud de los documentos y la busqueda.
            similitud = calculator.similitud(arrTFIDF, arrTFIDFBusqueda);
            //Ordenamos las parejas con InsertionSort.
            aux.insertionParejas(similitud);
            System.out.println("Si calcula la similitud");
            
            //Guardar los documentos mas relevantes para la busqueda.
            docsRelevantes = new ArrayList<String>();
            int indice = -1;
            for (int i = 0; i < 10; i++) {
                if (i < similitud.length) {
                    if (similitud[i].getKey() == 0.0) {
                        continue;
                    }
                    indice = similitud[i].getValue();
                    docsRelevantes.add(docsRelevantes.size(), arrDocs[indice].getName());
                }
            }

            // Añadir la busqueda y sus resultados al cache.
            if (cache.size() == 10) {
                cache.remove(0);
                cache.add(cache.size(), new Pair<ArrayList<String>, ArrayList<String>>(busqueda, docsRelevantes));
            } else if (cache.size() < 10) {
                cache.add(cache.size(), new Pair<ArrayList<String>, ArrayList<String>>(busqueda, docsRelevantes));
            }

            System.out.println("Si añade al cache");
            
            //Mostrar los documentos relevantes.
            if (docsRelevantes.isEmpty()) {
                warningText.setText("Ningun documento es relevante para su busqueda.");
            } else {
                //Abrir resultados.
                resultados = new Resultados(docsRelevantes);
                t.start();
                /*
                //Abrir resultados.
                resultados = new Resultados(docsRelevantes);
                //Agregar atributos
                resultados.setSimi(docsRelevantes);
                resultados.setArrTF(arrTF);
                resultados.setArrTFIDF(arrTFIDF);
                resultados.setCache(cache);
                resultados.setHt(ht);
                resultados.setStringList(stringList);
                resultados.setArrDocs(arrDocs);

                resultados.setVisible(true);
                this.dispose();
                */
            }
        } else {
            warningText.setText("Búsqueda invalida, rebasa el rango permitido.");
        }
    }//GEN-LAST:event_buscarButtonActionPerformed

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
            java.util.logging.Logger.getLogger(Buscar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Buscar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Buscar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Buscar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Buscar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton backButton;
    private javax.swing.JButton buscarButton;
    private javax.swing.JLabel busquedaText;
    private javax.swing.JLabel porcentaje;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton salirPrograma;
    private javax.swing.JTextField textBusqueda;
    private javax.swing.JLabel warningText;
    // End of variables declaration//GEN-END:variables
}
