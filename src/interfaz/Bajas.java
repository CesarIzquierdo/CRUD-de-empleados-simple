/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import bajasayu.Conectar;
import java.sql.Connection;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.sql.Statement;

/**
 *
 * @author cesar
 */
class Bajas extends javax.swing.JFrame
{

    Conectar enlace = new Conectar();
    Connection co = enlace.conectar();

    public static int fila;
    public static int numeroEm = 0;
    public int filaTabla;
    public int filareal = 0;
    private TextAutoCompleter ac;
    public int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    String[] nombre = null;
    public int ii = 0;
    String[] datos2 = new String[6];
    public String ultimoNumeroEmBaja = "";

    /**
     * Creates new form Bajas
     */
    public Bajas()
    {

        initComponents();
        jp1Altas.setVisible(false);
        setLocationRelativeTo(null);
        cargar();
        int opc = 0;
        mostar(opc);

    }

    public void mostar(int opc)
    {
        boolean altass = false;
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Número empleado");
        dtm.addColumn("Nombre");
        dtm.addColumn("Grado de estudios");
        dtm.addColumn("Licenciatura u Oficio");
        dtm.addColumn("Alta Año");
        dtm.addColumn("Baja Año");
        ResultSet resultado = null;
        tablaBajasNormal.setModel(dtm);

        String[] datos = new String[6];

        try
        {
            Statement leer = co.createStatement();

            switch (opc)
            {
                case 0:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio, altaAnio, fechaBaja FROM isse");
                    altass = false;
                    break;
                //-------------    
                // fechas de altas de personal
                //-------
                case 1:
                    jpNormal.setVisible(false);
                    jp1Altas.setVisible(true);
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio, altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 01 OR MONTH (altaAnio) = 02 OR MONTH (altaAnio) = 03");
                    altass = true;
                    break;
                case 2:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 04 OR MONTH (altaAnio) = 05 OR MONTH (altaAnio) = 06");
                    break;
                case 3:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio, altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 07 OR MONTH (altaAnio) = 08 OR MONTH (altaAnio) = 09");
                    break;
                case 4:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio, altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 10 OR MONTH (altaAnio) = 11 OR MONTH (altaAnio) = 12");
                    break;
                case 5:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse where altaAnio = '0000-00-00' OR altaAnio = '00-00-0000'");
                    break;
                //--------   
                //fechas de Bajas del personal
                //-----
                case 6:

                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio, altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 01 OR MONTH (fechaBaja) = 02 OR MONTH (fechaBaja) = 03 ");
                    break;
                case 7:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 04 OR MONTH (fechaBaja) = 05 OR MONTH (fechaBaja) = 06");
                    break;
                case 8:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio, altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 07 OR MONTH (fechaBaja) = 08 OR MONTH (fechaBaja) = 09");
                    break;
                case 9:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio, altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 10 OR MONTH (fechaBaja) = 11 OR MONTH (fechaBaja) = 12");
                    break;
                case 10:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse where fechaBaja = '0000-00-00' OR fechaBaja = '00-00-0000'");
                    break;

                // filtros por fechas de alta
                case 11:
                    dtm.setRowCount(0);
                    resultado = null;
                    String fechaAltaF = "";
                    fechaAltaF = JOptionPane.showInputDialog(null, "ingrese el año de alta  ejemplo ' 2020 '");
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse where YEAR(altaAnio) = " + fechaAltaF + " ");
                    break;

                //filstros por fechas de baja
                case 12:
                    dtm.setRowCount(0);
                    resultado = null;
                    String fechaBajaF = "";
                    fechaBajaF = JOptionPane.showInputDialog(null, "ingrese el año de baja  ejemplo ' 2020 '");
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse where YEAR(fechaBaja) = " + fechaBajaF);
                    break;

                // muestra por mes
                case 13:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT  numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 01");
                    break;
                case 14:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT  numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 02");
                    break;
                case 15:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 03");
                    break;
                case 16:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 04");
                    break;
                case 17:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 05");
                    break;
                case 18:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 06");
                    break;
                case 19:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 07");
                    break;
                case 20:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 08");
                    break;
                case 21:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 09");
                    break;
                case 22:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 10");
                    break;
                case 23:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 11");
                    break;
                case 24:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (altaAnio) = 12");
                    break;
                // filtar por mes de baja
                case 25:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 01");
                    break;
                case 26:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 02");
                    break;
                case 27:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 03");
                    break;
                case 28:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 04");
                    break;
                case 29:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 05");
                    break;
                case 30:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 06");
                    break;
                case 31:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 07");
                    break;
                case 32:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 08");
                    break;
                case 33:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 09");
                    break;
                case 34:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 10");
                    break;
                case 35:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 11");
                    break;
                case 36:
                    dtm.setRowCount(0);
                    resultado = null;
                    resultado = leer.executeQuery("SELECT numEmpleado, nommbre,gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE MONTH (fechaBaja) = 12");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "ocurrio un eror al seleccionar el filtro o cargar la tabla normal");
            }

            while (resultado.next())
            {
                datos[0] = resultado.getString(1);
                ultimoNumeroEmBaja = datos[0];
                datos[1] = resultado.getString(2);
                datos[2] = resultado.getString(3);
                datos[3] = resultado.getString(4);
                datos[4] = resultado.getString(5);
                datos[5] = resultado.getString(6);

                dtm.addRow(datos);
            }

            tablaBajasNormal.setModel(dtm);

        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e + "error en la consulta ");
        }
    }

    public void cargar()
    {
        try
        {
            Statement leer = co.createStatement();
            ResultSet resultado = leer.executeQuery("SELECT nommbre FROM isse");
            String valores = new String();

            while (resultado.next())
            {
                valores += resultado.getString(1) + "/n";
                ii++;
            }

            String texto = valores;
            nombre = texto.split("/n");
        } catch (SQLException ex)
        {
            System.out.println("error al cargar los datos " + ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtBuscarNombre = new javax.swing.JTextField();
        jpBotones = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        btnEli1 = new javax.swing.JButton();
        btnMod1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jpNormal = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBajasNormal = new javax.swing.JTable();
        jp1Altas = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenuItem29 = new javax.swing.JMenuItem();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenuItem32 = new javax.swing.JMenuItem();
        jMenuItem33 = new javax.swing.JMenuItem();
        jMenuItem34 = new javax.swing.JMenuItem();
        jMenuItem35 = new javax.swing.JMenuItem();
        jMenuItem36 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(java.awt.Color.white);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Dialog", 3, 48)); // NOI18N
        jLabel11.setText("BAJAS");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 200, 45));

        jButton1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/regresar.png"))); // NOI18N
        jButton1.setText("Regresar");
        jButton1.setToolTipText("Regresar al Menú principal");
        jButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 20, -1, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1270, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        jLabel5.setText("BUSCAR POR NOMBRE");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 290, 40));

        txtBuscarNombre.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        txtBuscarNombre.setToolTipText("Ingrese un nombre ");
        txtBuscarNombre.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtBuscarNombreActionPerformed(evt);
            }
        });
        txtBuscarNombre.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtBuscarNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtBuscarNombreKeyTyped(evt);
            }
        });
        jPanel3.add(txtBuscarNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 930, -1));

        jpBotones.setBackground(new java.awt.Color(255, 255, 255));
        jpBotones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpBotones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnEli1.setFont(new java.awt.Font("Dialog", 2, 36)); // NOI18N
        btnEli1.setText("Eliminar");
        btnEli1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEli1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnEli1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 230, 80));

        btnMod1.setFont(new java.awt.Font("Dialog", 2, 36)); // NOI18N
        btnMod1.setText("Modificar");
        btnMod1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnMod1ActionPerformed(evt);
            }
        });
        jPanel6.add(btnMod1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 80));

        jpBotones.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 840, 540, 150));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Eliminar");
        jpBotones.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 80, 30));

        btnEliminar.setFont(new java.awt.Font("Dialog", 2, 24)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar64p.png"))); // NOI18N
        btnEliminar.setToolTipText("Eliminar a la persona seleccionada permanentemente");
        btnEliminar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEliminarActionPerformed(evt);
            }
        });
        jpBotones.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 60, 50));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Agregar");
        jpBotones.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 90, 30));

        btnAgregar.setFont(new java.awt.Font("Dialog", 2, 24)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        btnAgregar.setToolTipText("Agregar persona a las bajas");
        btnAgregar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAgregarActionPerformed(evt);
            }
        });
        jpBotones.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 60, 50));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Consultar");
        jpBotones.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 90, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/consultar64p.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });
        jpBotones.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 60, 50));

        jPanel3.add(jpBotones, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 50, 150, 350));

        jpNormal.setBackground(new java.awt.Color(255, 255, 255));
        jpNormal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpNormal.setForeground(new java.awt.Color(0, 0, 0));
        jpNormal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaBajasNormal.setBackground(new java.awt.Color(255, 255, 255));
        tablaBajasNormal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tablaBajasNormal.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        tablaBajasNormal.setForeground(new java.awt.Color(0, 0, 0));
        tablaBajasNormal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {
                {},
                {},
                {},
                {}
            },
            new String []
            {

            }
        ));
        tablaBajasNormal.setColumnSelectionAllowed(true);
        tablaBajasNormal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablaBajasNormal.setGridColor(new java.awt.Color(204, 204, 255));
        tablaBajasNormal.getTableHeader().setReorderingAllowed(false);
        tablaBajasNormal.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mousePressed(java.awt.event.MouseEvent evt)
            {
                tablaBajasNormalMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaBajasNormal);
        tablaBajasNormal.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jpNormal.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 510));

        jPanel3.add(jpNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1080, 530));

        jp1Altas.setBackground(new java.awt.Color(204, 204, 204));
        jp1Altas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jp1Altas.setForeground(new java.awt.Color(0, 0, 0));
        jp1Altas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(jp1Altas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 1080, 530));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1490, 780));

        jMenuBar1.setBackground(new java.awt.Color(255, 102, 153));
        jMenuBar1.setForeground(new java.awt.Color(255, 102, 153));
        jMenuBar1.setToolTipText("filtros de fechas");

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario32.png"))); // NOI18N
        jMenu1.setText("Altas");
        jMenu1.setToolTipText("Filtro de fechas de altas");
        jMenu1.setFocusable(false);
        jMenu1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jMenuItem1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem1.setText("1 trimestre");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem2.setText("2 trimestre");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem3.setText("3 trimestre");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem4.setText("4 trimestres");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem5.setText("Especiales");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario32.png"))); // NOI18N
        jMenu2.setText("Bajas");
        jMenu2.setToolTipText("Filtro de fechas de Bajas");
        jMenu2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jMenuItem6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem6.setText("1 Trimestre");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem7.setText("2 Trimestre");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem8.setText("3 Trimestre");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem9.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem9.setText("4 Trimestre");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem10.setText("Especiales");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario32.png"))); // NOI18N
        jMenu3.setText("Normal");
        jMenu3.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenu3ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario32.png"))); // NOI18N
        jMenu4.setText("Filtar por año");
        jMenu4.setToolTipText("Filtro de fechas por año ");
        jMenu4.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jMenu4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenu4ActionPerformed(evt);
            }
        });

        jMenuItem12.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem12.setText("Alta de año");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuItem11.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem11.setText("Baja de año");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario32.png"))); // NOI18N
        jMenu5.setText("Filtar por mes de alta");
        jMenu5.setToolTipText("filtrar por mes");
        jMenu5.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jMenuItem13.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem13.setText("Enero");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem13);

        jMenuItem14.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem14.setText("Febrero");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem14);

        jMenuItem15.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem15.setText("Marzo");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem15);

        jMenuItem16.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem16.setText("Abril");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem16);

        jMenuItem17.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem17.setText("Mayo");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem17);

        jMenuItem18.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem18.setText("Junio");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem18);

        jMenuItem19.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem19.setText("Julio");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem19);

        jMenuItem20.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem20.setText("Agosto");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem20);

        jMenuItem21.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem21.setText("Septiembre");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem21);

        jMenuItem22.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem22.setText("Octubre");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem22);

        jMenuItem23.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem23.setText("Noviembre");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem23);

        jMenuItem24.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem24.setText("Diciembre");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem24);

        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/calendario32.png"))); // NOI18N
        jMenu6.setText("Filtar por mes de baja");
        jMenu6.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N

        jMenuItem25.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem25.setText("Enero");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem25);

        jMenuItem26.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem26.setText("Febrero");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem26);

        jMenuItem27.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem27.setText("Marzo");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem27);

        jMenuItem28.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem28.setText("Abril");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem28);

        jMenuItem29.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem29.setText("Mayo");
        jMenuItem29.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem29ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem29);

        jMenuItem30.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem30.setText("Junio");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem30);

        jMenuItem31.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem31.setText("Julio");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem31);

        jMenuItem32.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem32.setText("Agosto");
        jMenuItem32.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem32ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem32);

        jMenuItem33.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem33.setText("Septiembre");
        jMenuItem33.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem33ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem33);

        jMenuItem34.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem34.setText("Octubre");
        jMenuItem34.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem34ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem34);

        jMenuItem35.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem35.setText("Noviembre");
        jMenuItem35.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem35ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem35);

        jMenuItem36.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jMenuItem36.setText("Diciembre");
        jMenuItem36.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jMenuItem36ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem36);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEli1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEli1ActionPerformed
    {//GEN-HEADEREND:event_btnEli1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEli1ActionPerformed

    private void btnMod1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnMod1ActionPerformed
    {//GEN-HEADEREND:event_btnMod1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMod1ActionPerformed

    private void txtBuscarNombreKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtBuscarNombreKeyTyped
    {//GEN-HEADEREND:event_txtBuscarNombreKeyTyped
        char validar = evt.getKeyChar();

        if (Character.isDigit(validar))
        {
            getToolkit().beep();

            evt.consume();
        }
    }//GEN-LAST:event_txtBuscarNombreKeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem1ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem1ActionPerformed
        // muestra el primer trimestre los primeros tres meses
        //de enero a marzo
        int fechaB = 1;
        mostar(fechaB);

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem2ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem2ActionPerformed
        //muestra el segundo trimestre
        // de abril a juni
        int fechaB = 2;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem3ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem3ActionPerformed
        // muestra el terecer trimestre
        // de julio a septiembre
        int fechaB = 3;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem4ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem4ActionPerformed
        // muestra el 4 trimestre 
        // de octubre a dicimbre
        int fechaB = 4;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void txtBuscarNombreKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtBuscarNombreKeyPressed
    {//GEN-HEADEREND:event_txtBuscarNombreKeyPressed
        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("numEmpleado");
        dtm.addColumn("nombre");
        // dtm.addColumn("edad");
        dtm.addColumn("Grado de estudios");
        dtm.addColumn("Licenciatura u Oficio");
        dtm.addColumn("Alta Año");
        dtm.addColumn("Baja Año");

        tablaBajasNormal.setModel(dtm);

        String nom = "";
        try
        {
            ac = new TextAutoCompleter(txtBuscarNombre);
            for (int i = 0; i < ii; i++)
            {
                ac.addItem(nombre[i]);
            }

            Statement leer = co.createStatement();
            nom = txtBuscarNombre.getText();
            System.out.println("nom " + nom);
            ResultSet resultado = leer.executeQuery("SELECT numEmpleado, nommbre, gradoEstudios, licenciaturaOficio,altaAnio, fechaBaja FROM isse WHERE nommbre LIKE'%" + nom + "%'");
            while (resultado.next())
            {
                if (!resultado.equals(nom))
                {
                    datos2[0] = resultado.getString(1);
                    datos2[1] = resultado.getString(2);
                    datos2[2] = resultado.getString(3);
                    datos2[3] = resultado.getString(4);
                    datos2[4] = resultado.getString(5);
                    datos2[5] = resultado.getString(6);
                }

                dtm.addRow(datos2);
            }
            tablaBajasNormal.setModel(dtm);
            if (datos2[0] == null)
            {
                filareal = 0;
            } else
            {
                filareal = Integer.parseInt(datos2[0]) - 1;
            }

        } catch (SQLException ex)
        {
            System.out.println("error de " + ex);
        }
    }//GEN-LAST:event_txtBuscarNombreKeyPressed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jMenu3MouseClicked
    {//GEN-HEADEREND:event_jMenu3MouseClicked
        int filtro = 0;
        mostar(filtro);
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem5ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem5ActionPerformed
        // busca las fechas que no tien registro esoecial como 00/00/0000
        int f = 5;
        mostar(f);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem6ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem6ActionPerformed
        // busca las fechas que no tien registro esoecial como 00/00/0000
        int f = 6;
        mostar(f);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem7ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem7ActionPerformed
        // busca las fechas que no tien registro esoecial como 00/00/0000
        int f = 7;
        mostar(f);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem8ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem8ActionPerformed
        // busca las fechas que no tien registro esoecial como 00/00/0000
        int f = 8;
        mostar(f);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem9ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem9ActionPerformed
        // busca las fechas que no tien registro esoecial como 00/00/0000
        int f = 9;
        mostar(f);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem10ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem10ActionPerformed
        // busca las fechas que no tien registro esoecial como 00/00/0000
        int f = 10;
        mostar(f);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void txtBuscarNombreActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtBuscarNombreActionPerformed
    {//GEN-HEADEREND:event_txtBuscarNombreActionPerformed

    }//GEN-LAST:event_txtBuscarNombreActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
    {//GEN-HEADEREND:event_jButton1ActionPerformed
        // Boton de regreso al emnú principal
        MenuBajas mb = new MenuBajas();
        mb.setVisible(true);
        Bajas b = new Bajas();
        b.setVisible(false);
        super.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEliminarActionPerformed
    {//GEN-HEADEREND:event_btnEliminarActionPerformed
        // eliminar datos de la tabla bajas
        //se va a pedir el numero del empleado por medio de un
        //inputdialog y este tendra que aceptar que lo va a eliminar
        PreparedStatement ps;
        String sql;

        boolean corecto = false;
        boolean rpta = false;
        int numEli = 0;
        String numEliS = "";
        numEliS = JOptionPane.showInputDialog(null, "ingrese el número del empleado ");
        int n = numEliS.length();
        char numElisC = ' ';
        for (int i = 0; i < n; i++)
        {
            numElisC = numEliS.charAt(i);
            if (numElisC >= 48 && numElisC <= 57)
            {
                numEli = Integer.parseInt(numEliS);
                corecto = true;
            } else
            {
                JOptionPane.showMessageDialog(null, "Debe ingresar solo numeros");
                corecto = false;
                i = n + 1;
            }
        }

        if (corecto == true)
        {
            try
            {
                boolean confirmarUltimoNumero = false;
                sql = "DELETE FROM isse WHERE numEmpleado=?;";
                ps = co.prepareStatement(sql);
                ps.setInt(1, numEli);
                int ultimoNumeroBajas = 0;
                //if (ultimoNumeroEmBaja == "" || ultimoNumeroEmBaja == "")
                if ("".equals(ultimoNumeroEmBaja) || "".equals(ultimoNumeroEmBaja))
                {
                    ultimoNumeroBajas = -1;
                    confirmarUltimoNumero = true;
                }
                if (confirmarUltimoNumero == false)
                {
                    ultimoNumeroBajas = Integer.parseInt(ultimoNumeroEmBaja);
                }
                if (ultimoNumeroBajas >= numEli)
                {
                    int valorRetorno = JOptionPane.showConfirmDialog(null,
                            "¿Quiere eliminar a " + numEliS + "?",
                            "Eliminar",
                            JOptionPane.YES_NO_OPTION);

                    if (valorRetorno == 0)
                    {
                        rpta = ps.executeUpdate() == 1;
                        JOptionPane.showMessageDialog(null, "Se elimino a " + numEliS + " de las bajas del ayuntamiento");
                        mostar(0);
                    } else
                    {
                        if (valorRetorno == 1)
                        {
                            JOptionPane.showMessageDialog(null, "no se elimino a " + numEliS);
                        }
                    }
                } else
                {
                    JOptionPane.showMessageDialog(null, "el número que ingreso no existe en las bajas");
                }

            } catch (SQLException ex)
            {
                System.out.println("error de " + ex);
            }
        }


    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAgregarActionPerformed
    {//GEN-HEADEREND:event_btnAgregarActionPerformed
        formularioBajasAgregar fba = new formularioBajasAgregar();
        fba.setVisible(true);
        super.dispose();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenu4ActionPerformed
    {//GEN-HEADEREND:event_jMenu4ActionPerformed

    }//GEN-LAST:event_jMenu4ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem12ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem12ActionPerformed
        int fechaA = 11;
        mostar(fechaA);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem11ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem11ActionPerformed
        int fechaB = 12;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void tablaBajasNormalMousePressed(java.awt.event.MouseEvent evt)//GEN-FIRST:event_tablaBajasNormalMousePressed
    {//GEN-HEADEREND:event_tablaBajasNormalMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tablaBajasNormalMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        //modificar los registros\

        numeroEm = tablaBajasNormal.getSelectedRow();
        if (numeroEm != -1)
        {

            numeroEm = tablaBajasNormal.getSelectedRow();

            DefaultTableModel modelo = (DefaultTableModel) tablaBajasNormal.getModel();
            modelo.getValueAt(numeroEm, 0);
            Object inte = modelo.getValueAt(numeroEm, 0);
            inte = modelo.getValueAt(numeroEm, 0);
            String filo = String.valueOf(inte);
            numeroEm = Integer.valueOf(filo);
            fila = numeroEm;
        } else
        {
            JOptionPane.showMessageDialog(null, "Debe seleccionar a una persona");
        }

        if (numeroEm >= 0)
        {
            formularioBajasBien fb = new formularioBajasBien();
            fb.setVisible(true);
            super.dispose();
        } else
        {
            JOptionPane.showMessageDialog(null, "para acceder a este formulario debe seleccionar a una persona, por favor intentelo de nuevo");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem14ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem14ActionPerformed
        //muestra el mes de febrero
        int fechaB = 14;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem13ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem13ActionPerformed
        //muestra el mes de enero
        int fechaB = 13;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem15ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem15ActionPerformed
        //muestra el mes de marzo
        int fechaB = 15;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem16ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem16ActionPerformed
        //muestra el mes de abril
        int fechaB = 16;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem17ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem17ActionPerformed
        //muestra el mes de mayo
        int fechaB = 17;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem18ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem18ActionPerformed
        //muestra el mes de junio
        int fechaB = 18;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem19ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem19ActionPerformed
        //muestra el mes de julio
        int fechaB = 19;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem20ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem20ActionPerformed
        //muestra el mes de agosto
        int fechaB = 20;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem21ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem21ActionPerformed
        //muestra el mes de septiembre
        int fechaB = 21;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem22ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem22ActionPerformed
        //muestra el mes de octubre
        int fechaB = 22;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem23ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem23ActionPerformed
        //muestra el mes de noviembre
        int fechaB = 23;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem24ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem24ActionPerformed
        //muestra el mes de diciembre
        int fechaB = 24;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem33ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem33ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem33ActionPerformed
        //muestra el mes de septiembre baja
        int fechaB = 33;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem33ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem25ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem25ActionPerformed
        //muestra el mes de enero baja
        int fechaB = 25;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem26ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem26ActionPerformed
        //muestra el mes de febrero baja
        int fechaB = 26;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem27ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem27ActionPerformed
        //muestra el mes de Marzo baja
        int fechaB = 27;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem28ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem28ActionPerformed
        //muestra el mes de abril baja
        int fechaB = 28;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem29ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem29ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem29ActionPerformed
        //muestra el mes de mayo baja
        int fechaB = 29;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem29ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem30ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem30ActionPerformed
        //muestra el mes de junio baja
        int fechaB = 30;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem31ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem31ActionPerformed
        //muestra el mes de julio baja
        int fechaB = 31;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jMenuItem32ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem32ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem32ActionPerformed
        //muestra el mes de agosto baja
        int fechaB = 32;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem32ActionPerformed

    private void jMenuItem34ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem34ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem34ActionPerformed
        //muestra el mes de octubre baja
        int fechaB = 34;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem34ActionPerformed

    private void jMenuItem35ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem35ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem35ActionPerformed
        //muestra el mes de noviembre baja
        int fechaB = 35;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem35ActionPerformed

    private void jMenuItem36ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenuItem36ActionPerformed
    {//GEN-HEADEREND:event_jMenuItem36ActionPerformed
        //muestra el mes de diciembre baja
        int fechaB = 36;
        mostar(fechaB);
    }//GEN-LAST:event_jMenuItem36ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jMenu3ActionPerformed
    {//GEN-HEADEREND:event_jMenu3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed

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
            java.util.logging.Logger.getLogger(Bajas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Bajas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Bajas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Bajas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Bajas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEli1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMod1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem29;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem32;
    private javax.swing.JMenuItem jMenuItem33;
    private javax.swing.JMenuItem jMenuItem34;
    private javax.swing.JMenuItem jMenuItem35;
    private javax.swing.JMenuItem jMenuItem36;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jp1Altas;
    private javax.swing.JPanel jpBotones;
    private javax.swing.JPanel jpNormal;
    private javax.swing.JTable tablaBajasNormal;
    private javax.swing.JTextField txtBuscarNombre;
    // End of variables declaration//GEN-END:variables

}
