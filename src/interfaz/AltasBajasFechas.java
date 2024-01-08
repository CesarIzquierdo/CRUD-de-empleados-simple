/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import bajasayu.Conectar;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author cesar
 */
public class AltasBajasFechas extends javax.swing.JFrame
{

    Conectar enlace = new Conectar();
    Connection co = enlace.conectar();
    private Connection con = enlace.conectar();
    private Conectar conectar;

    String edad = null, nombre = null, ltaAnio = null, bajaAnio = null,
            fn = null, ac = null, ine = null, recidencia = null,
            curp = null, rfc = null, constanciaDomiciliaria = null,
            cartillaMilitar = null, gradoEstudios = null, licenciaturaOficio = null,
            tituloProfesional = null, cedulaProfesional = null,
            actualizacionesCertificacion = null, certificadoAntecedentes = null,
            CertificadoMedico = null, cartaRecomendacion = null, numIsse = null,
            constanciaValidez = null, bajaIssem = null;
    String numeroEmplead = String.valueOf(Altas.filaAltas + 1);
    Altas a = new Altas();

    String numEm = String.valueOf(Altas.numeroEmAltas);

    int cont = 0;
    public static int fila = Altas.filaAltas + 1;
    public int filaEm = Altas.numeroEmAltas;
    public int nlistaAlta = filaEm;
    public int ultimoNumeroInt = 0;

    int numeroDelEmpleado = filaEm;

    public AltasBajasFechas()
    {
        initComponents();
        setLocationRelativeTo(null);
        mostar();
        cargarUltimoNumeroBajas();
    }

    public DefaultComboBoxModel obtener(String v1, String v2, String v3)
    {
        DefaultComboBoxModel valor = new DefaultComboBoxModel();
        valor.addElement(v1);
        valor.addElement(v2);
        valor.addElement(v3);

        return valor;
    }

    public DefaultComboBoxModel obtenerModificar(String v1)
    {
        DefaultComboBoxModel valor = new DefaultComboBoxModel();
        valor.addElement(v1);
        return valor;
    }

    public void mostar()
    {
        DefaultComboBoxModel valor = new DefaultComboBoxModel();
        //pasa el numero del empleado
        lbLista.setText(numEm);
        System.out.println("lista = " + filaEm);
        // buscar por numero
        String[] datos = new String[22];
        // banderas de datos en combobox
        boolean si = false;
        boolean no = false;
        boolean falta = false;

        try
        {
            Statement leer = co.createStatement();
            ResultSet resultado = leer.executeQuery("SELECT * FROM altas WHERE numEmpleado LIKE'%" + filaEm + "%'");

            while (resultado.next())
            {
                if (!resultado.equals(filaEm))
                {
                    datos[0] = resultado.getString(1);//numero de empleado en la base
                    datos[1] = resultado.getString(2);// nombre
                    datos[2] = resultado.getString(3);
                    datos[3] = resultado.getString(4);
                    datos[4] = resultado.getString(5);//fecha alta
                    datos[5] = resultado.getString(6);
                    datos[6] = resultado.getString(7);
                    datos[7] = resultado.getString(8);//fecha baja
                    datos[8] = resultado.getString(9);
                    datos[9] = resultado.getString(10);
                    datos[10] = resultado.getString(11);
                    datos[11] = resultado.getString(12);
                    datos[12] = resultado.getString(13);
                    datos[13] = resultado.getString(14);
                    datos[14] = resultado.getString(15);
                    datos[15] = resultado.getString(16);
                    datos[16] = resultado.getString(17);
                    datos[17] = resultado.getString(18);
                    datos[18] = resultado.getString(19);
                    datos[19] = resultado.getString(20);
                    datos[20] = resultado.getString(21);
                    datos[21] = resultado.getString(22);
                    break;
                }
            }
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e + "error en la consulta ");
        }
        // nombre del empleado
        nombre = datos[1];
        txtNombre.setText(nombre);

        //se crea un formato simple de fecha 
        //se utiliza el tipo de util,date para que no este cambiando de sql a util
        //y se manda el date a jcalendar
        String dateInString = datos[2];
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try
        {
            java.util.Date date = formatter.parse(dateInString);
            dcAlta.setDate(date);
            dcAltatxt.setText(datos[2]);
        } catch (ParseException ex)
        {
            try
            {
                //se pasa lo que este escrito en la fecha cuando no es una fecha correcta
                dcAltatxt.setText(datos[2]);
                dateInString = "0000-00-00";
                java.util.Date date = formatter.parse(dateInString);
                dcAlta.setDate(date);
            } catch (ParseException ex1)
            {
                System.out.println("sss");
            }
        }

        edad = datos[3];
        txtEdad.setText(edad);

        //se crea un formato simple de fecha 
        //se utiliza el tipo de util,date para que no este cambiando de sql a util
        //y se manda el date a jcalendar
        String dateInStringN = datos[4];
        String falso2 = "0000-00";
        SimpleDateFormat formatterN = new SimpleDateFormat("yyyy-MM-dd");

        try
        {
            java.util.Date dateN = formatterN.parse(dateInStringN);
            dcN.setDate(dateN);
            dcNtxt.setText(datos[4]);
        } catch (ParseException ex)
        {
            try
            {
                //se pasa lo que esta en datos [8] que no se es fecha
                // o tiene la fecha incorrecta o un formato especial
                dcNtxt.setText(datos[4]);
                dateInStringN = "0000-00-00";
                java.util.Date dateN = formatterN.parse(dateInStringN);
                dcN.setDate(dateN);
            } catch (ParseException ex1)
            {
                Logger.getLogger(formularioAltasBien.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }

        //mandar a un combobox los datos de acta de nacimiento
        ac = datos[5];
        String cno = "No";
        String csi = "SI";
        String cfalta = "Falta";
        if (datos[5].equals("Si") || datos[5].equals("si"))
        {
            no = true;
            falta = true;
        } else
        {
            if (datos[5].equals("No") || datos[5].equals("no"))
            {
                si = true;
                falta = true;
            } else
            {
                if (datos[5].equals("Falta") || datos[5].equals("falta"))
                {
                    si = true;
                    no = true;
                }
            }
        }
        if (no == true && falta == true)
        {
            valor = obtener(ac, cno, cfalta);
            cbAN.setModel(valor);
        } else
        {
            if (si == true && falta == true)
            {
                valor = obtener(ac, csi, cfalta);
                cbAN.setModel(valor);
            } else
            {
                if (si == true && no == true)
                {
                    valor = obtener(ac, csi, cno);
                    cbAN.setModel(valor);
                }
            }
        }
        si = false;
        no = false;
        falta = false;
        // el valor se hace null para evitar desbordamientos y 
        // datos truncados
        valor = null;

        //mandar a un cb los datos de ine
        ine = datos[6];

        if (datos[6].equals("Si") || datos[6].equals("si"))
        {
            no = true;
            falta = true;
        } else
        {
            if (datos[6].equals("No") || datos[6].equals("no"))
            {
                si = true;
                falta = true;
            } else
            {
                if (datos[6].equals("Falta") || datos[6].equals("falta"))
                {
                    si = true;
                    no = true;
                }
            }
        }
        if (no == true && falta == true)
        {
            valor = obtener(ine, cno, cfalta);
            cbINE.setModel(valor);
        } else
        {
            if (si == true && falta == true)
            {
                valor = obtener(ine, csi, cfalta);
                cbAN.setModel(valor);
            } else
            {
                if (si == true && no == true)
                {
                    valor = obtener(ine, csi, cno);
                    cbAN.setModel(valor);
                }
            }
        }
        si = false;
        no = false;
        falta = false;

        valor = null;

        // mandar un txt los datos de recidencia
        recidencia = datos[7];
        txtReci.setText(recidencia);

        //mandar a un txt los datos de curp
        curp = datos[8];
        txtCURP.setText(curp);

        //mandar a un txt los datos de rfc
        rfc = datos[9];
        System.out.println("rfc" + rfc);
        txtRFC.setText(rfc);

        //se manda el valor de certificado de domicilio
        constanciaDomiciliaria = datos[10];
        if (datos[10].equals("Si") || datos[10].equals("si"))
        {
            no = true;
            falta = true;
        } else
        {
            if (datos[10].equals("No") || datos[10].equals("no"))
            {
                si = true;
                falta = true;
            } else
            {
                if (datos[10].equals("Falta") || datos[10].equals("falta"))
                {
                    si = true;
                    no = true;
                }
            }
        }
        if (no == true && falta == true)
        {
            valor = obtener(constanciaDomiciliaria, cno, cfalta);
            cbCD.setModel(valor);
        } else
        {
            if (si == true && falta == true)
            {
                valor = obtener(constanciaDomiciliaria, csi, cfalta);
                cbCD.setModel(valor);
            } else
            {
                if (si == true && no == true)
                {
                    valor = obtener(constanciaDomiciliaria, csi, cno);
                    cbCD.setModel(valor);
                }
            }
        }
        si = false;
        no = false;
        falta = false;

        valor = null;

        //se manda el valor de cartilla militar
        cartillaMilitar = datos[11];
        if (datos[11].equals("Si") || datos[11].equals("si"))
        {
            no = true;
            falta = true;
        } else
        {
            if (datos[11].equals("No") || datos[11].equals("no"))
            {
                si = true;
                falta = true;
            } else
            {
                if (datos[11].equals("Falta") || datos[11].equals("falta"))
                {
                    si = true;
                    no = true;
                }
            }
        }
        if (no == true && falta == true)
        {
            valor = obtener(cartillaMilitar, cno, cfalta);
            cbCMil.setModel(valor);
        } else
        {
            if (si == true && falta == true)
            {
                valor = obtener(cartillaMilitar, csi, cfalta);
                cbCMil.setModel(valor);
            } else
            {
                if (si == true && no == true)
                {
                    valor = obtener(cartillaMilitar, csi, cno);
                    cbCMil.setModel(valor);
                }
            }
        }
        si = false;
        no = false;
        falta = false;

        valor = null;

        //mandar a un txt los datos de gradoEstudios
        gradoEstudios = datos[12];
        txtGEs.setText(gradoEstudios);

        //mandar a un txt los datos de licenciatura u oficio
        licenciaturaOficio = datos[13];
        txtLO.setText(licenciaturaOficio);

        //mandar a un bc los datos de tituloProfesional
        tituloProfesional = datos[14];
        if (datos[14].equals("Si") || datos[14].equals("si"))
        {
            no = true;
            falta = true;
        } else
        {
            if (datos[14].equals("No") || datos[14].equals("no"))
            {
                si = true;
                falta = true;
            } else
            {
                if (datos[14].equals("Falta") || datos[14].equals("falta"))
                {
                    si = true;
                    no = true;
                }
            }
        }
        if (no == true && falta == true)
        {
            valor = obtener(tituloProfesional, cno, cfalta);
            cbTp.setModel(valor);
        } else
        {
            if (si == true && falta == true)
            {
                valor = obtener(tituloProfesional, csi, cfalta);
                cbTp.setModel(valor);
            } else
            {
                if (si == true && no == true)
                {
                    valor = obtener(tituloProfesional, csi, cno);
                    cbTp.setModel(valor);
                }
            }
        }
        si = false;
        no = false;
        falta = false;

        valor = null;

        //mandar a un bc los datos de cedula profesional
        cedulaProfesional = datos[15];
        if (datos[15].equals("Si") || datos[15].equals("si"))
        {
            no = true;
            falta = true;
        } else
        {
            if (datos[15].equals("No") || datos[15].equals("no"))
            {
                si = true;
                falta = true;
            } else
            {
                if (datos[15].equals("Falta") || datos[15].equals("falta"))
                {
                    si = true;
                    no = true;
                }
            }
        }
        if (no == true && falta == true)
        {
            valor = obtener(cedulaProfesional, cno, cfalta);
            cbCP.setModel(valor);
        } else
        {
            if (si == true && falta == true)
            {
                valor = obtener(cedulaProfesional, csi, cfalta);
                cbCP.setModel(valor);
            } else
            {
                if (si == true && no == true)
                {
                    valor = obtener(cedulaProfesional, csi, cno);
                    cbCP.setModel(valor);
                }
            }
        }
        si = false;
        no = false;
        falta = false;

        valor = null;

        //mandar a un cb los datos de actualizaciones de certificaciones        
        actualizacionesCertificacion = datos[16];
        if (datos[16].equals("Si") || datos[16].equals("si"))
        {
            no = true;
            falta = true;
        } else
        {
            if (datos[16].equals("No") || datos[16].equals("no"))
            {
                si = true;
                falta = true;
            } else
            {
                if (datos[16].equals("Falta") || datos[16].equals("falta"))
                {
                    si = true;
                    no = true;
                }
            }
        }
        if (no == true && falta == true)
        {
            valor = obtener(actualizacionesCertificacion, cno, cfalta);
            cbAC.setModel(valor);
        } else
        {
            if (si == true && falta == true)
            {
                valor = obtener(actualizacionesCertificacion, csi, cfalta);
                cbAC.setModel(valor);
            } else
            {
                if (si == true && no == true)
                {
                    valor = obtener(actualizacionesCertificacion, csi, cno);
                    cbAC.setModel(valor);
                }
            }
        }
        si = false;
        no = false;
        falta = false;

        valor = null;

        //mandar aun cb os datos de certificado de antecedentes no penales
        certificadoAntecedentes = datos[17];
        if (datos[17].equals("Si") || datos[17].equals("si"))
        {
            no = true;
            falta = true;
        } else
        {
            if (datos[17].equals("No") || datos[17].equals("no"))
            {
                si = true;
                falta = true;
            } else
            {
                if (datos[17].equals("Falta") || datos[17].equals("falta"))
                {
                    si = true;
                    no = true;
                }
            }
        }
        if (no == true && falta == true)
        {
            valor = obtener(certificadoAntecedentes, cno, cfalta);
            cbCANP.setModel(valor);
        } else
        {
            if (si == true && falta == true)
            {
                valor = obtener(certificadoAntecedentes, csi, cfalta);
                cbCANP.setModel(valor);
            } else
            {
                if (si == true && no == true)
                {
                    valor = obtener(certificadoAntecedentes, csi, cno);
                    cbCANP.setModel(valor);
                }
            }
        }
        si = false;
        no = false;
        falta = false;

        valor = null;

        //mandar a un cb los datos de certificado medico
        CertificadoMedico = datos[18];
        if (datos[18].equals("Si") || datos[18].equals("si"))
        {
            no = true;
            falta = true;
        } else
        {
            if (datos[18].equals("No") || datos[18].equals("no"))
            {
                si = true;
                falta = true;
            } else
            {
                if (datos[18].equals("Falta") || datos[18].equals("falta"))
                {
                    si = true;
                    no = true;
                }
            }
        }
        if (no == true && falta == true)
        {
            valor = obtener(CertificadoMedico, cno, cfalta);
            cbCM.setModel(valor);
        } else
        {
            if (si == true && falta == true)
            {
                valor = obtener(CertificadoMedico, csi, cfalta);
                cbCM.setModel(valor);
            } else
            {
                if (si == true && no == true)
                {
                    valor = obtener(CertificadoMedico, csi, cno);
                    cbCM.setModel(valor);
                }
            }
        }
        si = false;
        no = false;
        falta = false;

        valor = null;

        //mandar a cb un los datos de carta de recomendacion
        cartaRecomendacion = datos[19];
        if (datos[19].equals("Si") || datos[19].equals("si"))
        {
            no = true;
            falta = true;
        } else
        {
            if (datos[19].equals("No") || datos[19].equals("no"))
            {
                si = true;
                falta = true;
            } else
            {
                if (datos[19].equals("Falta") || datos[19].equals("falta"))
                {
                    si = true;
                    no = true;
                }
            }
        }
        if (no == true && falta == true)
        {
            valor = obtener(cartaRecomendacion, cno, cfalta);
            cbCR.setModel(valor);
        } else
        {
            if (si == true && falta == true)
            {
                valor = obtener(cartaRecomendacion, csi, cfalta);
                cbCR.setModel(valor);
            } else
            {
                if (si == true && no == true)
                {
                    valor = obtener(cartaRecomendacion, csi, cno);
                    cbCR.setModel(valor);
                }
            }
        }
        si = false;
        no = false;
        falta = false;

        valor = null;

        //mandar a un txt los datos de numero issemin 
        numIsse = datos[20];
        txtNoIsse.setText(numIsse);

        //mandar a un cb los datos de constancia de validez
        constanciaValidez = datos[21];
        if (datos[21].equals("Si") || datos[21].equals("si"))
        {
            no = true;
            falta = true;
        } else
        {
            if (datos[21].equals("No") || datos[21].equals("no"))
            {
                si = true;
                falta = true;
            } else
            {
                if (datos[21].equals("Falta") || datos[21].equals("falta"))
                {
                    si = true;
                    no = true;
                }
            }
        }
        if (no == true && falta == true)
        {
            valor = obtener(constanciaValidez, cno, cfalta);
            cbCV.setModel(valor);
        } else
        {
            if (si == true && falta == true)
            {
                valor = obtener(constanciaValidez, csi, cfalta);
                cbCD.setModel(valor);
            } else
            {
                if (si == true && no == true)
                {
                    valor = obtener(constanciaValidez, csi, cno);
                    cbCV.setModel(valor);
                }
            }
        }
        si = false;
        no = false;
        falta = false;

        valor = null;
        filaEm = 0;
        numEm = "";
    }

    public void cargarUltimoNumeroBajas()
    {
        DefaultComboBoxModel valor = new DefaultComboBoxModel();
        //pasa el numero del empleado

        // buscar por numero
        String[] datos = new String[28];
        // banderas de datos en combobox
        boolean si = false;
        boolean no = false;
        boolean falta = false;

        try
        {
            Statement leer = co.createStatement();
            ResultSet resultado = leer.executeQuery("SELECT * FROM isse ");

            while (resultado.next())
            {
                if (resultado.last())
                {
                    datos[0] = resultado.getString(1);//ultimo numero de empleado en la base
                }
            }

        } catch (SQLException e)
        {
            //nada
        }

        if (datos[0] == null)
        {
            ultimoNumeroInt = 0;
        } else
        {
            ultimoNumeroInt = Integer.parseInt(datos[0]);
        }

        ultimoNumeroInt++;
        String numeroBaja = String.valueOf(ultimoNumeroInt);
        lbListaBaja.setText(numeroBaja);
        System.out.println("ultimoNumeroInt  " + ultimoNumeroInt);
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
        jLabel29 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dcAlta = new com.toedter.calendar.JDateChooser();
        dcBaja = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        dcN = new com.toedter.calendar.JDateChooser();
        dcBajaISSE = new com.toedter.calendar.JDateChooser();
        dcBajaIssetxt = new javax.swing.JTextField();
        dcAltatxt = new javax.swing.JTextField();
        dcBajatxt = new javax.swing.JTextField();
        dcNtxt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbLista = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lbEdad = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        lbListaBaja = new javax.swing.JLabel();
        jpTxt = new javax.swing.JPanel();
        txtLO = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtNoIsse = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtReci = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtRFC = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtGEs = new javax.swing.JTextField();
        txtCURP = new javax.swing.JTextField();
        jbSeleccionar = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        cbCD = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbAN = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cbINE = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        btnMod = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jpSeleccionar2 = new javax.swing.JPanel();
        lbmilitar = new javax.swing.JLabel();
        cbCMil = new javax.swing.JComboBox<>();
        lbtp = new javax.swing.JLabel();
        cbTp = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        cbCP = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cbAC = new javax.swing.JComboBox<>();
        jlcap1 = new javax.swing.JLabel();
        jlcap2 = new javax.swing.JLabel();
        cbCANP = new javax.swing.JComboBox<>();
        jlcm = new javax.swing.JLabel();
        cbCM = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cbCR = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        cbCV = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setFont(new java.awt.Font("Dialog", 3, 36)); // NOI18N
        jLabel29.setText("Dar de baja");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 220, 30));

        jButton2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/regresar.png"))); // NOI18N
        jButton2.setText("regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 140, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel23.setText("Fecha Alta");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 140, 40));

        jLabel24.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel24.setText("Fecha Baja");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 150, 20));

        jLabel6.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel6.setText("Fecha Nacimiento");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 180, 20));

        dcAlta.setDateFormatString("yyyy-MM-dd");
        dcAlta.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                dcAltaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt)
            {
                dcAltaMouseEntered(evt);
            }
        });
        dcAlta.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                dcAltaPropertyChange(evt);
            }
        });
        jPanel4.add(dcAlta, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 230, 30, 30));

        dcBaja.setDateFormatString("yyyy-MM-dd");
        dcBaja.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                dcBajaPropertyChange(evt);
            }
        });
        jPanel4.add(dcBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 30, 30));

        jLabel8.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel8.setText("baja de issemmyn");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 180, 30));

        dcN.setDateFormatString("yyyy-MM-dd");
        dcN.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                dcNPropertyChange(evt);
            }
        });
        jPanel4.add(dcN, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 30, 31));

        dcBajaISSE.setDateFormatString("yyyy-MM-dd");
        dcBajaISSE.addPropertyChangeListener(new java.beans.PropertyChangeListener()
        {
            public void propertyChange(java.beans.PropertyChangeEvent evt)
            {
                dcBajaISSEPropertyChange(evt);
            }
        });
        jPanel4.add(dcBajaISSE, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 30, 30));

        dcBajaIssetxt.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        dcBajaIssetxt.setForeground(new java.awt.Color(51, 51, 55));
        dcBajaIssetxt.setText("cdsdcsdc");
        jPanel4.add(dcBajaIssetxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 140, 30));

        dcAltatxt.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        dcAltatxt.setForeground(new java.awt.Color(51, 51, 55));
        dcAltatxt.setText("cdsdcsdc");
        jPanel4.add(dcAltatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 140, 30));

        dcBajatxt.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        dcBajatxt.setForeground(new java.awt.Color(51, 51, 55));
        dcBajatxt.setText("cdsdcsdc");
        dcBajatxt.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                dcBajatxtActionPerformed(evt);
            }
        });
        jPanel4.add(dcBajatxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 140, 30));

        dcNtxt.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        dcNtxt.setForeground(new java.awt.Color(51, 51, 55));
        dcNtxt.setText("cdsdcsdc");
        jPanel4.add(dcNtxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 140, 30));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 380, 170));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel4.setText("Nombre");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 100, 30));

        jLabel22.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel22.setText("Número de baja");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 150, 40));

        lbLista.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        lbLista.setText("0000");
        jPanel3.add(lbLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 70, 20));

        txtNombre.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtNombreKeyTyped(evt);
            }
        });
        jPanel3.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 370, 30));

        lbEdad.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lbEdad.setText("Edad");
        jPanel3.add(lbEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 60, 30));

        txtEdad.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        txtEdad.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtEdadKeyTyped(evt);
            }
        });
        jPanel3.add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 173, -1));

        jLabel31.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel31.setText("Número de alta");
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 40));

        lbListaBaja.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        lbListaBaja.setText("0000");
        jPanel3.add(lbListaBaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 60, 70, 20));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 380, 170));

        jpTxt.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                jpTxtKeyPressed(evt);
            }
        });
        jpTxt.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtLO.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        txtLO.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txtLOActionPerformed(evt);
            }
        });
        txtLO.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtLOKeyTyped(evt);
            }
        });
        jpTxt.add(txtLO, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 390, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel7.setText("Licenciatura / Oficio");
        jpTxt.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 190, -1));

        jLabel20.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel20.setText("No. ISSEMYM");
        jpTxt.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 150, 30));

        txtNoIsse.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        txtNoIsse.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtNoIsseKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtNoIsseKeyTyped(evt);
            }
        });
        jpTxt.add(txtNoIsse, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 390, 30));

        jLabel25.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel25.setText("Recidencia");
        jpTxt.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, 20));

        txtReci.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtReci.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtReciKeyTyped(evt);
            }
        });
        jpTxt.add(txtReci, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 440, -1));

        jLabel26.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel26.setText("CURP");
        jpTxt.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 100, -1));

        txtRFC.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtRFC.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtRFCKeyTyped(evt);
            }
        });
        jpTxt.add(txtRFC, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 440, -1));

        jLabel27.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel27.setText("RFC");
        jpTxt.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 60, 30));

        jLabel30.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel30.setText("Grado De Estudios");
        jpTxt.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 170, 30));

        txtGEs.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtGEs.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtGEsKeyTyped(evt);
            }
        });
        jpTxt.add(txtGEs, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 390, -1));

        txtCURP.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtCURP.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                txtCURPKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txtCURPKeyTyped(evt);
            }
        });
        jpTxt.add(txtCURP, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 440, -1));

        jPanel2.add(jpTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 40, 20));

        jbSeleccionar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel28.setText("Constancia Domiciliaria");
        jbSeleccionar.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 220, 30));

        cbCD.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbCD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No", "Falta" }));
        jbSeleccionar.add(cbCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 150, 30));

        jLabel10.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel10.setText("Acta De Nacimiento");
        jbSeleccionar.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 190, 20));

        cbAN.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbAN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No", "Falta" }));
        jbSeleccionar.add(cbAN, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 150, 30));

        jLabel12.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel12.setText("INE");
        jbSeleccionar.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 70, 20));

        cbINE.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbINE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No", "Falta" }));
        jbSeleccionar.add(cbINE, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, 150, 30));

        jPanel2.add(jbSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 20, 30));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMod.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btnMod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bajaem48.png"))); // NOI18N
        btnMod.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnModActionPerformed(evt);
            }
        });
        jPanel8.add(btnMod, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 90, 70));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Dar de baja");
        jPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 100, 30));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 170, 140));

        jpSeleccionar2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbmilitar.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lbmilitar.setText("Cartilla Militar");
        jpSeleccionar2.add(lbmilitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 130, 30));

        cbCMil.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbCMil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No", "Falta" }));
        jpSeleccionar2.add(cbCMil, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 160, 20));

        lbtp.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        lbtp.setText("Titulo Profesional");
        jpSeleccionar2.add(lbtp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 180, 30));

        cbTp.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbTp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No", "Falta", "n/a" }));
        jpSeleccionar2.add(cbTp, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 160, 20));

        jLabel1.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel1.setText("Cedula Profesional");
        jpSeleccionar2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 190, 30));

        cbCP.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbCP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No", "Falta", "n/a" }));
        cbCP.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                cbCPActionPerformed(evt);
            }
        });
        jpSeleccionar2.add(cbCP, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 160, 20));

        jLabel14.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel14.setText("Actualizaciones Certificación");
        jpSeleccionar2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 250, 30));

        cbAC.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbAC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No", "Falta", "n/a", "Certificado" }));
        jpSeleccionar2.add(cbAC, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 160, 20));

        jlcap1.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jlcap1.setText("Certificado Antecedentes");
        jpSeleccionar2.add(jlcap1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 230, 30));

        jlcap2.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jlcap2.setText("no Penales");
        jpSeleccionar2.add(jlcap2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 100, 30));

        cbCANP.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbCANP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No", "Falta", "n/a", "varias", "ninguna", "Diplomado mejora", "informe", "publica" }));
        jpSeleccionar2.add(cbCANP, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, 130, -1));

        jlcm.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jlcm.setText("Certificado Medico");
        jpSeleccionar2.add(jlcm, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 170, 30));

        cbCM.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbCM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No", "Falta", "n/a", "certificado", "informe" }));
        jpSeleccionar2.add(cbCM, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 130, 30));

        jLabel17.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel17.setText("Carta de Recomendación");
        jpSeleccionar2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, 220, 30));

        cbCR.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbCR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "No", "Falta", "n/a", "pendiente" }));
        jpSeleccionar2.add(cbCR, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, 130, 30));

        jLabel21.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel21.setText("Constancia de validez");
        jpSeleccionar2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 200, 30));

        cbCV.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        cbCV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Si", "Falta", "n/a", "No" }));
        jpSeleccionar2.add(cbCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 140, 130, 30));

        jPanel2.add(jpSeleccionar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 130, 20, 20));
        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jScrollPane1.setViewportView(jPanel2);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 810, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtNombreKeyTyped
    {//GEN-HEADEREND:event_txtNombreKeyTyped
        char validar = evt.getKeyChar();
        char val = validar;
        if (Character.isAlphabetic(validar))
        {
        } else
        {
            if (val == ' ')
            {

            } else
            {
                System.out.println("validaresp " + val);
                getToolkit().beep();
                evt.consume();
            }
        }

        if (Character.isDigit(validar))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtEdadKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtEdadKeyTyped
    {//GEN-HEADEREND:event_txtEdadKeyTyped
        char validar = evt.getKeyChar();
        char val = validar;

        if (cont < 0)
        {
            cont = 0;
        }
        if (val == 8)
        {
            cont--;
        }
        if (cont == 2)
        {
            JOptionPane.showMessageDialog(this, "solo puede escribir una edad de dos numeros");
            getToolkit().beep();
            evt.consume();
        } else
        {
            if (Character.isDigit(validar))
            {
                cont++;
                System.out.println("digitos " + cont);
            } else
            {
                getToolkit().beep();
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtEdadKeyTyped

    private void txtLOActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txtLOActionPerformed
    {//GEN-HEADEREND:event_txtLOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLOActionPerformed

    private void txtLOKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtLOKeyTyped
    {//GEN-HEADEREND:event_txtLOKeyTyped
        char validar = evt.getKeyChar();
        char val = validar;
        if (Character.isAlphabetic(validar))
        {
        } else
        {
            if (val == ' ')
            {

            } else
            {
                System.out.println("validaresp " + val);
                getToolkit().beep();
                evt.consume();
            }
        }

        if (Character.isDigit(validar))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtLOKeyTyped

    private void txtNoIsseKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtNoIsseKeyTyped
    {//GEN-HEADEREND:event_txtNoIsseKeyTyped

    }//GEN-LAST:event_txtNoIsseKeyTyped

    private void txtReciKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtReciKeyTyped
    {//GEN-HEADEREND:event_txtReciKeyTyped
        char validar = evt.getKeyChar();
        char val = validar;
        if (Character.isAlphabetic(validar))
        {
        } else
        {
            if (val == ' ')
            {

            } else
            {
                getToolkit().beep();
                evt.consume();
            }
        }

        if (Character.isDigit(validar))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtReciKeyTyped

    private void txtRFCKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtRFCKeyTyped
    {//GEN-HEADEREND:event_txtRFCKeyTyped

    }//GEN-LAST:event_txtRFCKeyTyped

    private void txtGEsKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtGEsKeyTyped
    {//GEN-HEADEREND:event_txtGEsKeyTyped
        char validar = evt.getKeyChar();
        char val = validar;
        if (Character.isAlphabetic(validar))
        {
        } else
        {
            if (val == ' ')
            {

            } else
            {
                System.out.println("validaresp " + val);
                getToolkit().beep();
                evt.consume();
            }
        }

        if (Character.isDigit(validar))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_txtGEsKeyTyped

    private void btnModActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnModActionPerformed
    {//GEN-HEADEREND:event_btnModActionPerformed
        String nombreMod = txtNombre.getText();
        PreparedStatement pps;
        String sql;
        String fechaAltaMod = dcAltatxt.getText();
        String fechaBajaMod = dcBajatxt.getText();
        if (fechaBajaMod == null || fechaBajaMod == " " || fechaBajaMod == "")
        {
            fechaBajaMod = "0000-00-00";
        }
        System.out.println("fechaBajaMod" + fechaBajaMod);
        String fechaNMod = dcNtxt.getText();
        String fechaBajaIsseMod = dcBajaIssetxt.getText();
        if (fechaBajaIsseMod == null || fechaBajaIsseMod == " " || fechaBajaIsseMod == "")
        {
            fechaBajaIsseMod = "0000-00-00";
        }
        System.out.println(" fechaBajaIsseMod " + fechaBajaIsseMod);
        String actaMod = (String) cbAN.getSelectedItem();
        String ineMod = (String) cbINE.getSelectedItem();
        String recidenciaMod = txtReci.getText();
        String curpMod = txtCURP.getText();
        String rfcMod = txtRFC.getText();
        String constanciaMod = (String) cbCD.getSelectedItem();
        String gradoMod = txtGEs.getText();
        String numIMod = txtNoIsse.getText();
        String liMod = txtLO.getText();
        String tituloPMod = (String) cbTp.getSelectedItem();
        String cedulaMod = (String) cbCP.getSelectedItem();
        String actuMod = (String) cbAC.getSelectedItem();
        String anpMod = (String) cbCANP.getSelectedItem();
        String medicoMod = (String) cbCM.getSelectedItem();
        String cartaMod = (String) cbCR.getSelectedItem();
        String validezMod = (String) cbCV.getSelectedItem();
        String edadMod = txtEdad.getText();
        try
        {

            sql = "insert into isse (numEmpleado, nommbre, altaDia,altaMes, altaAnio, eadPersona, edad, fechaBaja,fechaNacimiento, actaNacimiento, ine, recidencia, CURP, RFC, constanciaDomiciliaria,cartillaMilitar, gradoEstudios, licenciaturaOficio, tituloProfesional, cedulaProfesional,actualizacionesCertificacion, certificadoAntecedentesNoPenales, certificadoMedico, cartaRecomendacion, numIssemym, constanciaValidez,bajaIssemyn) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            pps = con.prepareStatement(sql);

            pps.setInt(1, ultimoNumeroInt);
            pps.setString(2, nombre);
            pps.setString(3, "0");
            pps.setString(4, "0");
            pps.setString(5, fechaAltaMod);
            pps.setString(6, "0");
            pps.setString(7, edadMod);
            pps.setString(8, fechaBajaMod);
            pps.setString(9, fechaNMod);
            pps.setString(10, actaMod);
            pps.setString(11, ineMod);
            pps.setString(12, recidenciaMod);
            pps.setString(13, curp);
            pps.setString(14, rfc);
            pps.setString(15, constanciaMod);
            pps.setString(16, cartillaMilitar);
            pps.setString(17, gradoMod);
            pps.setString(18, liMod);
            pps.setString(19, tituloPMod);
            pps.setString(20, cedulaMod);
            pps.setString(21, actuMod);
            pps.setString(22, anpMod);
            pps.setString(23, medicoMod);
            pps.setString(24, cartaMod);
            pps.setString(25, numIMod);
            pps.setString(26, validezMod);
            pps.setString(27, fechaBajaIsseMod);

            int valorRetorno = JOptionPane.showConfirmDialog(null,
                    "¿Quiere dar de baja a " + nombreMod + " ?",
                    "Modificar",
                    JOptionPane.YES_NO_OPTION);

            if (valorRetorno == 0)
            {
                pps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se envio a " + nombreMod + " a las bajas del ayunamiento");
                // eliminar datos de la tabla altas
                //se va a pedir el numero del empleado por medio de un
                //inputdialog y este tendra que aceptar que lo va a eliminar
                PreparedStatement ps;
                String sqlEli;

                boolean rpta = false;
                int numElis = nlistaAlta;
                String numEli = "";
                System.out.println("num " + numElis);
                try
                {
                    sqlEli = "DELETE FROM altas WHERE numEmpleado=?;";
                    ps = co.prepareStatement(sqlEli);
                    ps.setInt(1, numElis);
                    rpta = ps.executeUpdate() == 1;
                    Altas a = new Altas();
                    a.setVisible(true);
                    super.dispose();
                } catch (SQLException ex)
                {
                    System.out.println("error de " + ex);
                }
            } else
            {
                if (valorRetorno == 1)
                {
                    JOptionPane.showMessageDialog(null, "No se dio de baja");
                }
            }

        } catch (SQLException ex)
        {
            System.out.println("error de " + ex);
        }
    }//GEN-LAST:event_btnModActionPerformed

    private void jpTxtKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jpTxtKeyPressed
    {//GEN-HEADEREND:event_jpTxtKeyPressed
        char validar = evt.getKeyChar();
        char val = validar;
        if (Character.isAlphabetic(validar))
        {
        } else
        {
            if (val == ' ')
            {

            } else
            {
                System.out.println("validaresp " + val);
                getToolkit().beep();
                evt.consume();
            }
        }

        if (Character.isDigit(validar))
        {
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jpTxtKeyPressed

    private void txtCURPKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtCURPKeyTyped
    {//GEN-HEADEREND:event_txtCURPKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCURPKeyTyped

    private void txtCURPKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtCURPKeyPressed
    {//GEN-HEADEREND:event_txtCURPKeyPressed

    }//GEN-LAST:event_txtCURPKeyPressed

    private void txtNoIsseKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txtNoIsseKeyPressed
    {//GEN-HEADEREND:event_txtNoIsseKeyPressed

    }//GEN-LAST:event_txtNoIsseKeyPressed

    private void cbCPActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_cbCPActionPerformed
    {//GEN-HEADEREND:event_cbCPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCPActionPerformed

    private void dcAltaMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_dcAltaMouseClicked
    {//GEN-HEADEREND:event_dcAltaMouseClicked

    }//GEN-LAST:event_dcAltaMouseClicked

    private void dcAltaMouseEntered(java.awt.event.MouseEvent evt)//GEN-FIRST:event_dcAltaMouseEntered
    {//GEN-HEADEREND:event_dcAltaMouseEntered

    }//GEN-LAST:event_dcAltaMouseEntered

    private void dcAltaPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_dcAltaPropertyChange
    {//GEN-HEADEREND:event_dcAltaPropertyChange

        String seleccion = ((JTextField) dcAlta.getDateEditor().getUiComponent()).getText();
        dcAltatxt.setText(seleccion);
    }//GEN-LAST:event_dcAltaPropertyChange

    private void dcBajaPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_dcBajaPropertyChange
    {//GEN-HEADEREND:event_dcBajaPropertyChange
        String seleccion = ((JTextField) dcBaja.getDateEditor().getUiComponent()).getText();
        dcBajatxt.setText(seleccion);
    }//GEN-LAST:event_dcBajaPropertyChange

    private void dcNPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_dcNPropertyChange
    {//GEN-HEADEREND:event_dcNPropertyChange
        String seleccion = ((JTextField) dcN.getDateEditor().getUiComponent()).getText();
        dcNtxt.setText(seleccion);
    }//GEN-LAST:event_dcNPropertyChange

    private void dcBajaISSEPropertyChange(java.beans.PropertyChangeEvent evt)//GEN-FIRST:event_dcBajaISSEPropertyChange
    {//GEN-HEADEREND:event_dcBajaISSEPropertyChange
        String seleccion = ((JTextField) dcBajaISSE.getDateEditor().getUiComponent()).getText();
        dcBajaIssetxt.setText(seleccion);
    }//GEN-LAST:event_dcBajaISSEPropertyChange

    private void dcBajatxtActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_dcBajatxtActionPerformed
    {//GEN-HEADEREND:event_dcBajatxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dcBajatxtActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
    {//GEN-HEADEREND:event_jButton2ActionPerformed
        // Boton de regreso a la tabla de altas

        formularioAltasAgregar fa = new formularioAltasAgregar();
        fa.setVisible(false);
        super.dispose();
        Altas a = new Altas();
        a.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(AltasBajasFechas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(AltasBajasFechas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(AltasBajasFechas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(AltasBajasFechas.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new AltasBajasFechas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMod;
    private javax.swing.JComboBox<String> cbAC;
    private javax.swing.JComboBox<String> cbAN;
    private javax.swing.JComboBox<String> cbCANP;
    private javax.swing.JComboBox<String> cbCD;
    private javax.swing.JComboBox<String> cbCM;
    private javax.swing.JComboBox<String> cbCMil;
    private javax.swing.JComboBox<String> cbCP;
    private javax.swing.JComboBox<String> cbCR;
    private javax.swing.JComboBox<String> cbCV;
    private javax.swing.JComboBox<String> cbINE;
    private javax.swing.JComboBox<String> cbTp;
    private com.toedter.calendar.JDateChooser dcAlta;
    private javax.swing.JTextField dcAltatxt;
    private com.toedter.calendar.JDateChooser dcBaja;
    private com.toedter.calendar.JDateChooser dcBajaISSE;
    private javax.swing.JTextField dcBajaIssetxt;
    private javax.swing.JTextField dcBajatxt;
    private com.toedter.calendar.JDateChooser dcN;
    private javax.swing.JTextField dcNtxt;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel jbSeleccionar;
    private javax.swing.JLabel jlcap1;
    private javax.swing.JLabel jlcap2;
    private javax.swing.JLabel jlcm;
    private javax.swing.JPanel jpSeleccionar2;
    private javax.swing.JPanel jpTxt;
    private javax.swing.JLabel lbEdad;
    private javax.swing.JLabel lbLista;
    private javax.swing.JLabel lbListaBaja;
    private javax.swing.JLabel lbmilitar;
    private javax.swing.JLabel lbtp;
    private javax.swing.JTextField txtCURP;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtGEs;
    private javax.swing.JTextField txtLO;
    private javax.swing.JTextField txtNoIsse;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRFC;
    private javax.swing.JTextField txtReci;
    // End of variables declaration//GEN-END:variables
}
