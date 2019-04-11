/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.text.ParseException;
import javax.swing.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author alejo
 */
public class Ventana_Principal extends javax.swing.JFrame {

    /**
     * Creates new form Ventana_Principal
     */
    private DefaultTableModel modeloTabla;
    private DefaultTableModel modeloTabla2;
    private DefaultTableModel modeloTabla3;
    private DefaultTableModel modeloTabla4;
    private DefaultTableModel modeloTabla5;
    private DefaultTableModel modeloTabla6;
    private DefaultTableModel modeloTabla7;
    private String id;
    Calendar fecha = new GregorianCalendar();
    
    public Ventana_Principal() {
        initComponents();
        creaEncabezado();
        llenaTabla();
        id = "";
        fecha = new GregorianCalendar();
        int ano = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        jTFechaV.setText(Integer.toString(ano) + "-" + Integer.toString(mes + 1)  + "-" +Integer.toString(dia));
        jTFechaR.setText(Integer.toString(ano) + "-" + Integer.toString(mes + 1)  + "-" +Integer.toString(dia));
    }
    
    public void creaEncabezado()
    {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("IdVehiculo");
        modeloTabla.addColumn("Placas");
        modeloTabla.addColumn("Marca");
        modeloTabla.addColumn("Modelo");
        modeloTabla.addColumn("Año");
        modeloTabla.addColumn("Descripcion");
        modeloTabla.addColumn("Num Seguro");
        modeloTabla.addColumn("Vendido");
        modeloTabla.addColumn("Precio Venta");
        modeloTabla.addColumn("Disponible");
        modeloTabla.addColumn("Precio Renta");
        jTable1.setModel(modeloTabla);
        
        modeloTabla2 = new DefaultTableModel();
        modeloTabla2.addColumn("IdTipo");
        modeloTabla2.addColumn("Tipo Empleado");
        modeloTabla2.addColumn("Sueldo");
        jTable2.setModel(modeloTabla2);
        
        modeloTabla3 = new DefaultTableModel();
        modeloTabla3.addColumn("IdEmpleado");
        modeloTabla3.addColumn("IdTipo");
        modeloTabla3.addColumn("Primer Nombre");
        modeloTabla3.addColumn("Segundo Nombre");
        modeloTabla3.addColumn("Apellido Paterno");
        modeloTabla3.addColumn("Apellido Materno");
        modeloTabla3.addColumn("Domicilio");
        modeloTabla3.addColumn("Telefono");
        jTable3.setModel(modeloTabla3);
        
        modeloTabla4 = new DefaultTableModel();
        modeloTabla4.addColumn("IdCliente");
        modeloTabla4.addColumn("Primer Nombre");
        modeloTabla4.addColumn("Segundo Nombre");
        modeloTabla4.addColumn("Apellido Paterno");
        modeloTabla4.addColumn("Apellido Materno");
        modeloTabla4.addColumn("Domicilio");
        modeloTabla4.addColumn("Telefono");
        modeloTabla4.addColumn("Email");
        modeloTabla4.addColumn("Num Tarjeta");
        jTable4.setModel(modeloTabla4);
        
        modeloTabla5 = new DefaultTableModel();
        modeloTabla5.addColumn("IdVenta");
        modeloTabla5.addColumn("IdVehiculo");
        modeloTabla5.addColumn("IdCliente");
        modeloTabla5.addColumn("IdEmpleado");
        modeloTabla5.addColumn("Fecha Venta");
        jTable5.setModel(modeloTabla5);
        
        modeloTabla6 = new DefaultTableModel();
        modeloTabla6.addColumn("IdRenta");
        modeloTabla6.addColumn("IdVehiculo");
        modeloTabla6.addColumn("IdCliente");
        modeloTabla6.addColumn("IdEmpleado");
        modeloTabla6.addColumn("Dia Prestamo");
        modeloTabla6.addColumn("Dia Devolucion");
        modeloTabla6.addColumn("Total");
        jTable6.setModel(modeloTabla6);
        
        modeloTabla7 = new DefaultTableModel();
        modeloTabla7.addColumn("IdEntrega");
        modeloTabla7.addColumn("IdRenta");
        modeloTabla7.addColumn("IdEmpleado");
        modeloTabla7.addColumn("Fecha Entrega");
        modeloTabla7.addColumn("Cargos");
        modeloTabla7.addColumn("Descripcion Mantenimiento");
        modeloTabla7.addColumn("Costo");
        jTable7.setModel(modeloTabla7);
    }
    
    public void llenaTabla()
    {
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            modeloTabla.removeRow(i);
            i-=1;
        }
        ArrayList<Vehiculo> cliens;
        Vehiculo veh = new Vehiculo();
        Iterator<Vehiculo> iterador;
        
        cliens = veh.consultaVehiculo();
        iterador = cliens.iterator();
        
        while(iterador.hasNext())
        {
            Vehiculo cli = iterador.next();
            modeloTabla.addRow(new Object[] {
            cli.id, cli.placas, cli.marca, cli.modelo, cli.anio, cli.descripcion, cli.numSeguro,
            cli.vendido, cli.precioVenta, cli.disponible, cli.precioRenta});
        }
        
        for (int i = 0; i < modeloTabla2.getRowCount(); i++) {
            modeloTabla2.removeRow(i);
            i-=1;
        }
        ArrayList<Tipo> cliens2;
        Tipo tip = new Tipo();
        Iterator<Tipo> iterador2;
        
        cliens2 = tip.consultaTipo();
        iterador2 = cliens2.iterator();
        
        while(iterador2.hasNext())
        {
            Tipo cli = iterador2.next();
            modeloTabla2.addRow(new Object[] {
            cli.id, cli.tipoEmpleado, cli.sueldo});
        }
        
        for (int i = 0; i < modeloTabla3.getRowCount(); i++) {
            modeloTabla3.removeRow(i);
            i-=1;
        }
        ArrayList<Empleado> cliens3;
        Empleado em = new Empleado();
        Iterator<Empleado> iterador3;
        
        cliens3 = em.consultaEmpleado();
        iterador3 = cliens3.iterator();
        
        while(iterador3.hasNext())
        {
            Empleado cli = iterador3.next();
            modeloTabla3.addRow(new Object[] {
            cli.id, cli.idTipo, cli.primerNombre, cli.segundoNombre, cli.apellidoPaterno, cli.apellidoMaterno, cli.domicilio, cli.telefono});
        }
        
        for (int i = 0; i < modeloTabla4.getRowCount(); i++) {
            modeloTabla4.removeRow(i);
            i-=1;
        }
        ArrayList<Cliente> cliens4;
        Cliente cl = new Cliente();
        Iterator<Cliente> iterador4;
        
        cliens4 = cl.consultaCliente();
        iterador4 = cliens4.iterator();
        
        while(iterador4.hasNext())
        {
            Cliente cli = iterador4.next();
            modeloTabla4.addRow(new Object[] {
            cli.id, cli.primerNombre, cli.segundoNombre, cli.apellidoPaterno, cli.apellidoMaterno, cli.domicilio, cli.telefono, cli.email, cli.nTar});
        }
        
        for (int i = 0; i < modeloTabla5.getRowCount(); i++) {
            modeloTabla5.removeRow(i);
            i-=1;
        }
        ArrayList<Venta> cliens5;
        Venta ven = new Venta();
        Iterator<Venta> iterador5;
        
        cliens5 = ven.consultaVenta();
        iterador5 = cliens5.iterator();
        
        while(iterador5.hasNext())
        {
            Venta cli = iterador5.next();
            modeloTabla5.addRow(new Object[] {
            cli.id, cli.idVeh, cli.idClien, cli.idEm, cli.FechaVen});
        }
        
        for (int i = 0; i < modeloTabla6.getRowCount(); i++) {
            modeloTabla6.removeRow(i);
            i-=1;
        }
        ArrayList<Renta> cliens6;
        Renta ren = new Renta();
        Iterator<Renta> iterador6;
        
        cliens6 = ren.consultaRenta();
        iterador6 = cliens6.iterator();
        
        while(iterador6.hasNext())
        {
            Renta cli = iterador6.next();
            modeloTabla6.addRow(new Object[] {
            cli.id, cli.idVeh, cli.idClien, cli.idEm, cli.diaRen, cli.diaDev, cli.total});
        }
        
        for (int i = 0; i < modeloTabla7.getRowCount(); i++) {
            modeloTabla7.removeRow(i);
            i-=1;
        }
        ArrayList<Entrega> cliens7;
        Entrega en = new Entrega();
        Iterator<Entrega> iterador7;
        
        cliens7 = en.consultaEntrega();
        iterador7 = cliens7.iterator();
        
        while(iterador7.hasNext())
        {
            Entrega cli = iterador7.next();
            modeloTabla7.addRow(new Object[] {
            cli.id, cli.idRen, cli.idEm, cli.fechaEn, cli.cargos, cli.descri, cli.costo});
        }
    }
    
    private void limpiaControles()
    {
        jTPlacas.setText("");
        jTMarca.setText("");
        jTModelo.setText("");
        jTAnio.setText("");
        jTPVenta.setText("");
        jTPRenta.setText("");
        jTDescV.setText("");
        jTNSeg.setText("");
        
        jTTipo.setText("");
        jTSueldo.setText("");
        
        jTPNombreE.setText("");
        jTIdTipo.setText("");
        jTSNombreE.setText("");
        jTAPaternoE.setText("");
        jTAMaternoE.setText("");
        jTDomicilioE.setText("");
        jTTelefonoE.setText("");
        
        jTPNombreC.setText("");
            jTSNombreC.setText("");
            jTAPaternoC.setText("");
            jTAMaternoC.setText("");
            jTDomicilioC.setText("");
            jTTelefonoC.setText("");
            jTEmailC.setText("");
            jTNTC.setText("");
            
        jTIdVV.setText("");
            jTIdCV.setText("");
            jTIdEV.setText("");
            
        jTIdVR.setText("");
            jTIdCR.setText("");
            jTIdER.setText("");
        
        jTIdVV.setText("");
            jTIdEV.setText("");
            jTCargos.setText("");
            jTDE.setText("");
            jTCosto.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTPVenta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTPRenta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTNSeg = new javax.swing.JTextField();
        jButtonAlta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTMarca = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTPlacas = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTModelo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButtonModificar = new javax.swing.JButton();
        jTAnio = new javax.swing.JTextField();
        jButtonEliminar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTDescV = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTTipo = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTSueldo = new javax.swing.JTextField();
        jButtonAlta1 = new javax.swing.JButton();
        jButtonModificar1 = new javax.swing.JButton();
        jButtonEliminar1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jTPNombreE = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTSNombreE = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTAPaternoE = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTAMaternoE = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTDomicilioE = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTTelefonoE = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTIdTipo = new javax.swing.JTextField();
        jButtonAlta2 = new javax.swing.JButton();
        jButtonModificar2 = new javax.swing.JButton();
        jButtonEliminar2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jTPNombreC = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTSNombreC = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTAPaternoC = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTAMaternoC = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTDomicilioC = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTTelefonoC = new javax.swing.JTextField();
        jButtonAlta3 = new javax.swing.JButton();
        jButtonModificar3 = new javax.swing.JButton();
        jButtonEliminar3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jTEmailC = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jTNTC = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jTIdVV = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jTIdCV = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTIdEV = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTFechaV = new javax.swing.JTextField();
        jButtonAlta4 = new javax.swing.JButton();
        jButtonModificar4 = new javax.swing.JButton();
        jButtonEliminar4 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jTIdVR = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTIdCR = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTIdER = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTFechaR = new javax.swing.JTextField();
        jButtonAlta5 = new javax.swing.JButton();
        jButtonModificar5 = new javax.swing.JButton();
        jButtonEliminar5 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jTIdRE = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTIdEE = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jTCargos = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTCosto = new javax.swing.JTextField();
        jButtonAlta6 = new javax.swing.JButton();
        jButtonModificar6 = new javax.swing.JButton();
        jButtonEliminar6 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel40 = new javax.swing.JLabel();
        jTDE = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jLabel5.setText("Precio Venta:");

        jLabel6.setText("Precio Renta:");

        jLabel8.setText("Num Seguro:");

        jButtonAlta.setText("Alta");
        jButtonAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAltaActionPerformed(evt);
            }
        });

        jLabel1.setText("Marca:");

        jLabel2.setText("Placas:");

        jLabel3.setText("Modelo:");

        jLabel4.setText("Año:");

        jButtonModificar.setText("Modificar");
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jLabel9.setText("Descripcion:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jTNSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTDescV))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTPVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTPRenta, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(jButtonAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 42, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTPlacas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTPVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTPRenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTNSeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTDescV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlta, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Vehiculo", jPanel1);

        jLabel7.setText("Tipo:");

        jLabel10.setText("Sueldo:");

        jButtonAlta1.setText("Alta");
        jButtonAlta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlta1ActionPerformed(evt);
            }
        });

        jButtonModificar1.setText("Modificar");
        jButtonModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificar1ActionPerformed(evt);
            }
        });

        jButtonEliminar1.setText("Eliminar");
        jButtonEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jTTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jTSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(344, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(125, 125, 125)
                            .addComponent(jButtonAlta1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(282, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(41, 41, 41)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonAlta1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonModificar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonEliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addGap(42, 42, 42)))
        );

        jTabbedPane1.addTab("Tipo", jPanel2);

        jLabel11.setText("Primer Nombre:");

        jLabel12.setText("ApPaterno:");

        jLabel13.setText("ApMaterno:");

        jLabel14.setText("Segundo Nombre:");

        jLabel15.setText("ID Tipo:");

        jLabel16.setText("Domicilio:");

        jLabel17.setText("Telefono:");

        jButtonAlta2.setText("Alta");
        jButtonAlta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlta2ActionPerformed(evt);
            }
        });

        jButtonModificar2.setText("Modificar");
        jButtonModificar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificar2ActionPerformed(evt);
            }
        });

        jButtonEliminar2.setText("Eliminar");
        jButtonEliminar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar2ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTTelefonoE, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(34, 34, 34)
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTIdTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTAMaternoE, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel11)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTPNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTSNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(20, 20, 20)
                                            .addComponent(jLabel12)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTAPaternoE, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(jLabel16)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jTDomicilioE)))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(jButtonAlta2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonModificar2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTPNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTSNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTAPaternoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTAMaternoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTDomicilioE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel15)
                    .addComponent(jTTelefonoE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTIdTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlta2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModificar2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminar2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Empleado", jPanel3);

        jLabel18.setText("Primer Nombre:");

        jLabel19.setText("ApPaterno:");

        jLabel20.setText("ApMaterno:");

        jLabel21.setText("Email:");

        jLabel23.setText("Domicilio:");

        jLabel24.setText("Telefono:");

        jButtonAlta3.setText("Alta");
        jButtonAlta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlta3ActionPerformed(evt);
            }
        });

        jButtonModificar3.setText("Modificar");
        jButtonModificar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificar3ActionPerformed(evt);
            }
        });

        jButtonEliminar3.setText("Eliminar");
        jButtonEliminar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar3ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jLabel25.setText("Segundo Nombre:");

        jLabel41.setText("Num Tarjeta:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(125, 125, 125)
                                        .addComponent(jButtonAlta3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonModificar3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonEliminar3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTTelefonoC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(70, 70, 70)
                                        .addComponent(jLabel21)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTEmailC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel41)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTNTC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTPNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel20)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTAMaternoC, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTSNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTAPaternoC, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTDomicilioC)))
                                .addGap(0, 23, Short.MAX_VALUE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTPNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jTSNombreC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTAPaternoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTAMaternoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(jTDomicilioC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTTelefonoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTEmailC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTNTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel41)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlta3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModificar3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminar3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cliente", jPanel4);

        jLabel26.setText("Id Vehiculo:");

        jLabel27.setText("Id Cliente:");

        jLabel28.setText("Id Empleado:");

        jLabel29.setText("Fecha Venta:");

        jButtonAlta4.setText("Alta");
        jButtonAlta4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlta4ActionPerformed(evt);
            }
        });

        jButtonModificar4.setText("Modificar");
        jButtonModificar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificar4ActionPerformed(evt);
            }
        });

        jButtonEliminar4.setText("Eliminar");
        jButtonEliminar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar4ActionPerformed(evt);
            }
        });

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable5);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTIdVV, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel29)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTFechaV, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTIdCV, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTIdEV, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jButtonAlta4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonModificar4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEliminar4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jTIdVV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(jTIdCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTIdEV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFechaV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlta4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModificar4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminar4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Venta", jPanel5);

        jLabel30.setText("Id Vehiculo:");

        jLabel31.setText("Id Cliente:");

        jLabel32.setText("Id Empleado:");

        jLabel33.setText("Fecha Prestamo:");

        jButtonAlta5.setText("Alta");
        jButtonAlta5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlta5ActionPerformed(evt);
            }
        });

        jButtonModificar5.setText("Modificar");
        jButtonModificar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificar5ActionPerformed(evt);
            }
        });

        jButtonEliminar5.setText("Eliminar");
        jButtonEliminar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar5ActionPerformed(evt);
            }
        });

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable6MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable6);

        jLabel34.setText("Fecha Devolucion (D-M-A):");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2020" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTIdVR, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTFechaR, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel31)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTIdCR, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTIdER, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(jButtonAlta5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonModificar5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonEliminar5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jTIdVR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jTIdCR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTIdER, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTFechaR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlta5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModificar5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminar5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Renta", jPanel6);

        jLabel35.setText("Id Renta:");

        jLabel36.setText("Cargos:");

        jLabel37.setText("Id Empleado:");

        jLabel38.setText("Fecha Entrega:");

        jLabel39.setText("Costos:");

        jButtonAlta6.setText("Alta");
        jButtonAlta6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlta6ActionPerformed(evt);
            }
        });

        jButtonModificar6.setText("Modificar");
        jButtonModificar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificar6ActionPerformed(evt);
            }
        });

        jButtonEliminar6.setText("Eliminar");
        jButtonEliminar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminar6ActionPerformed(evt);
            }
        });

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable7);

        jLabel40.setText("Descripcion:");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2019", "2020" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(18, 18, 18)
                                .addComponent(jTDE))
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel35)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTIdRE, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel37))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(jLabel36)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(28, 28, 28)))
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addComponent(jLabel39)
                                            .addGap(18, 18, 18)
                                            .addComponent(jTCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel7Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addComponent(jTIdEE, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel38)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addGap(133, 133, 133)
                                    .addComponent(jButtonAlta6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonModificar6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonEliminar6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTIdRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jTIdEE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTCargos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel36))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTDE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlta6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonModificar6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEliminar6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Entrega", jPanel7);

        getContentPane().add(jTabbedPane1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAltaActionPerformed
        // TODO add your handling code here:
        Vehiculo cli = new Vehiculo();
        
        cli.marca = jTMarca.getText();
        cli.placas = jTPlacas.getText();
        cli.modelo = jTModelo.getText();
        cli.descripcion = jTDescV.getText();
        cli.anio = jTAnio.getText();
        cli.numSeguro = jTNSeg.getText();
        cli.precioVenta = Float.parseFloat(jTPVenta.getText());
        cli.precioRenta = Float.parseFloat(jTPRenta.getText());
        
        if(cli.insertaVehiculo(cli) > 0)
        {
            JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
                    "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonAltaActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        // TODO add your handling code here:
        Vehiculo cli = new Vehiculo();
        
        cli.id = Long.parseLong(id);
        cli.marca = jTMarca.getText();
        cli.placas = jTPlacas.getText();
        cli.modelo = jTModelo.getText();
        cli.descripcion = jTDescV.getText();
        cli.anio = jTAnio.getText();
        cli.numSeguro = jTNSeg.getText();
        cli.precioVenta = Float.parseFloat(jTPVenta.getText());
        cli.precioRenta = Float.parseFloat(jTPRenta.getText());
        
        if(cli.modificaVeiculo(cli) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        Vehiculo fn = new Vehiculo();
        
        if(fn.eliminaVehiculo(id) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1)
        {
            int fila = this.jTable1.getSelectedRow();
            id = jTable1.getModel().getValueAt(fila, 0).toString();
            jTPlacas.setText(jTable1.getModel().getValueAt(fila, 1).toString());
            jTMarca.setText(jTable1.getModel().getValueAt(fila, 2).toString());
            jTModelo.setText(jTable1.getModel().getValueAt(fila, 3).toString());
            jTAnio.setText(jTable1.getModel().getValueAt(fila, 4).toString());
            jTPVenta.setText(jTable1.getModel().getValueAt(fila, 8).toString());
            jTPRenta.setText(jTable1.getModel().getValueAt(fila, 10).toString());
            jTDescV.setText(jTable1.getModel().getValueAt(fila, 5).toString());
            jTNSeg.setText(jTable1.getModel().getValueAt(fila, 6).toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButtonAlta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlta1ActionPerformed
        // TODO add your handling code here:
        Tipo cli = new Tipo();
        
        cli.tipoEmpleado = jTTipo.getText();
        cli.sueldo = Float.parseFloat(jTSueldo.getText());
        
        if(cli.insertaTipo(cli) > 0)
        {
            JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
                    "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonAlta1ActionPerformed

    private void jButtonModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificar1ActionPerformed
        // TODO add your handling code here:
        Tipo cli = new Tipo();
        
        cli.id = Long.parseLong(id);
        cli.tipoEmpleado = jTTipo.getText();
        cli.sueldo = Float.parseFloat(jTSueldo.getText());
        
        if(cli.modificaTipo(cli) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonModificar1ActionPerformed

    private void jButtonEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar1ActionPerformed
        // TODO add your handling code here:
        Tipo fn = new Tipo();
        
        if(fn.eliminaTipo(id) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonEliminar1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1)
        {
            int fila = this.jTable2.getSelectedRow();
            id = jTable2.getModel().getValueAt(fila, 0).toString();
            jTTipo.setText(jTable2.getModel().getValueAt(fila, 1).toString());
            jTSueldo.setText(jTable2.getModel().getValueAt(fila, 2).toString());
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButtonAlta2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlta2ActionPerformed
        // TODO add your handling code here:
        Empleado cli = new Empleado();
        
        cli.primerNombre = jTPNombreE.getText();
        cli.segundoNombre = jTSNombreE.getText();
        cli.apellidoPaterno = jTAPaternoE.getText();
        cli.apellidoMaterno = jTAMaternoE.getText();
        cli.domicilio = jTDomicilioE.getText();
        cli.telefono = jTTelefonoE.getText();
        cli.idTipo = Long.parseLong(jTIdTipo.getText());
        
        if(cli.insertaEmpleado(cli) > 0)
        {
            JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
                    "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonAlta2ActionPerformed

    private void jButtonModificar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificar2ActionPerformed
        // TODO add your handling code here:
        Empleado cli = new Empleado();
        
        cli.id = Long.parseLong(id);
        cli.primerNombre = jTPNombreE.getText();
        cli.segundoNombre = jTSNombreE.getText();
        cli.apellidoPaterno = jTAPaternoE.getText();
        cli.apellidoMaterno = jTAMaternoE.getText();
        cli.domicilio = jTDomicilioE.getText();
        cli.telefono = jTTelefonoE.getText();
        cli.idTipo = Long.parseLong(jTIdTipo.getText());
        
        if(cli.modificaEmpleado(cli) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonModificar2ActionPerformed

    private void jButtonEliminar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar2ActionPerformed
        // TODO add your handling code here:
        Empleado fn = new Empleado();
        
        if(fn.eliminaEmpleado(id) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonEliminar2ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1)
        {
            int fila = this.jTable3.getSelectedRow();
            id = jTable3.getModel().getValueAt(fila, 0).toString();
            jTIdTipo.setText(jTable3.getModel().getValueAt(fila, 1).toString());
            jTPNombreE.setText(jTable3.getModel().getValueAt(fila, 2).toString());
            jTSNombreE.setText(jTable3.getModel().getValueAt(fila, 3).toString());
            jTAPaternoE.setText(jTable3.getModel().getValueAt(fila, 4).toString());
            jTAMaternoE.setText(jTable3.getModel().getValueAt(fila, 5).toString());
            jTDomicilioE.setText(jTable3.getModel().getValueAt(fila, 6).toString());
            jTTelefonoE.setText(jTable3.getModel().getValueAt(fila, 7).toString());
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButtonAlta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlta3ActionPerformed
        // TODO add your handling code here:
        Cliente cli = new Cliente();
        
        cli.primerNombre = jTPNombreC.getText();
        cli.segundoNombre = jTSNombreC.getText();
        cli.apellidoPaterno = jTAMaternoC.getText();
        cli.apellidoMaterno = jTAMaternoC.getText();
        cli.domicilio = jTDomicilioC.getText();
        cli.telefono = jTTelefonoC.getText();
        cli.email = jTEmailC.getText();
        cli.nTar = jTNTC.getText();
        
        if(cli.insertaCliente(cli) > 0)
        {
            JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
                    "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
        
    }//GEN-LAST:event_jButtonAlta3ActionPerformed

    private void jButtonModificar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificar3ActionPerformed
        // TODO add your handling code here:
        Cliente cli = new Cliente();
        
        cli.id = Long.parseLong(id);
        cli.primerNombre = jTPNombreC.getText();
        cli.segundoNombre = jTSNombreC.getText();
        cli.apellidoPaterno = jTAMaternoC.getText();
        cli.apellidoMaterno = jTAMaternoC.getText();
        cli.domicilio = jTDomicilioC.getText();
        cli.telefono = jTTelefonoC.getText();
        cli.email = jTEmailC.getText();
        cli.nTar = jTNTC.getText();
        
        if(cli.modificaCliente(cli) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
        
    }//GEN-LAST:event_jButtonModificar3ActionPerformed

    private void jButtonEliminar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar3ActionPerformed
        // TODO add your handling code here:
        Cliente fn = new Cliente();
        
        if(fn.eliminaCliente(id) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
        
    }//GEN-LAST:event_jButtonEliminar3ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1)
        {
            int fila = this.jTable4.getSelectedRow();
            id = jTable4.getModel().getValueAt(fila, 0).toString();
            jTPNombreC.setText(jTable4.getModel().getValueAt(fila, 1).toString());
            jTSNombreC.setText(jTable4.getModel().getValueAt(fila, 2).toString());
            jTAPaternoC.setText(jTable4.getModel().getValueAt(fila, 3).toString());
            jTAMaternoC.setText(jTable4.getModel().getValueAt(fila, 4).toString());
            jTDomicilioC.setText(jTable4.getModel().getValueAt(fila, 5).toString());
            jTTelefonoC.setText(jTable4.getModel().getValueAt(fila, 6).toString());
            jTEmailC.setText(jTable4.getModel().getValueAt(fila, 7).toString());
            jTNTC.setText(jTable4.getModel().getValueAt(fila, 8).toString());
        }
        
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButtonAlta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlta4ActionPerformed
        // TODO add your handling code here:
        Venta cli = new Venta();
        
        cli.idVeh = Long.parseLong(jTIdVV.getText());
        cli.idClien = Long.parseLong(jTIdCV.getText());
        cli.idEm = Long.parseLong(jTIdEV.getText());
        
        try {
            if(cli.insertaVenta(cli) > 0)
            {
                JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
                        "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Ventana_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonAlta4ActionPerformed

    private void jButtonModificar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificar4ActionPerformed
        // TODO add your handling code here:
        Venta cli = new Venta();
        
        cli.id = Long.parseLong(id);
        cli.idVeh = Long.parseLong(jTIdVV.getText());
        cli.idClien = Long.parseLong(jTIdCV.getText());
        cli.idEm = Long.parseLong(jTIdEV.getText());
        cli.FechaVen = jTFechaV.getText();
        
        if(cli.modificaVenta(cli) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonModificar4ActionPerformed

    private void jButtonEliminar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar4ActionPerformed
        // TODO add your handling code here:
        Venta fn = new Venta();
        
        if(fn.eliminaVenta(id) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonEliminar4ActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1)
        {
            int fila = this.jTable5.getSelectedRow();
            id = jTable5.getModel().getValueAt(fila, 0).toString();
            jTIdVV.setText(jTable5.getModel().getValueAt(fila, 1).toString());
            jTIdCV.setText(jTable5.getModel().getValueAt(fila, 2).toString());
            jTIdEV.setText(jTable5.getModel().getValueAt(fila, 3).toString());
        }
    }//GEN-LAST:event_jTable5MouseClicked

    private void jButtonAlta5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlta5ActionPerformed
        // TODO add your handling code here:
        Renta cli = new Renta();
        
        cli.idVeh = Long.parseLong(jTIdVR.getText());
        cli.idClien = Long.parseLong(jTIdCR.getText());
        cli.idEm = Long.parseLong(jTIdER.getText());
        cli.diaDev = jComboBox3.getSelectedItem().toString() + "-" + jComboBox2.getSelectedItem().toString() + "-" + jComboBox1.getSelectedItem().toString();
        
        try {
            if(cli.insertaRenta(cli) > 0)
            {
                JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
                        "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Ventana_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonAlta5ActionPerformed

    private void jButtonModificar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificar5ActionPerformed
        // TODO add your handling code here:
        Renta cli = new Renta();
        
        cli.id = Long.parseLong(id);
        cli.idVeh = Long.parseLong(jTIdVR.getText());
        cli.idClien = Long.parseLong(jTIdCR.getText());
        cli.idEm = Long.parseLong(jTIdER.getText());
        cli.diaDev = jComboBox3.getSelectedItem().toString() + "-" + jComboBox2.getSelectedItem().toString() + "-" + jComboBox1.getSelectedItem().toString();
        
        if(cli.modificaRenta(cli) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonModificar5ActionPerformed

    private void jButtonEliminar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar5ActionPerformed
        // TODO add your handling code here:
        Renta fn = new Renta();
        
        if(fn.eliminaRenta(id) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonEliminar5ActionPerformed

    private void jTable6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable6MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1)
        {
            int fila = this.jTable6.getSelectedRow();
            id = jTable6.getModel().getValueAt(fila, 0).toString();
            jTIdVR.setText(jTable6.getModel().getValueAt(fila, 1).toString());
            jTIdCR.setText(jTable6.getModel().getValueAt(fila, 2).toString());
            jTIdER.setText(jTable6.getModel().getValueAt(fila, 3).toString());
        }
    }//GEN-LAST:event_jTable6MouseClicked

    private void jButtonAlta6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlta6ActionPerformed
        // TODO add your handling code here:
        Entrega cli = new Entrega();
        
        cli.idRen = Long.parseLong(jTIdRE.getText());
        cli.idEm = Long.parseLong(jTIdEE.getText());
        cli.descri = jTDE.getText();
        cli.cargos = Float.parseFloat(jTCargos.getText());
        cli.costo = Float.parseFloat(jTCosto.getText());
        cli.fechaEn = jComboBox6.getSelectedItem().toString() + "-" + jComboBox5.getSelectedItem().toString() + "-" + jComboBox4.getSelectedItem().toString();
        
        if(cli.insertaEntrega(cli) > 0)
        {
            JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
                    "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonAlta6ActionPerformed

    private void jButtonModificar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificar6ActionPerformed
        // TODO add your handling code here:
        Entrega cli = new Entrega();
        
        cli.id = Long.parseLong(id);
        cli.idRen = Long.parseLong(jTIdRE.getText());
        cli.idEm = Long.parseLong(jTIdEE.getText());
        cli.descri = jTDE.getText();
        cli.cargos = Float.parseFloat(jTCargos.getText());
        cli.costo = Float.parseFloat(jTCosto.getText());
        cli.fechaEn = jComboBox6.getSelectedItem().toString() + "-" + jComboBox5.getSelectedItem().toString() + "-" + jComboBox4.getSelectedItem().toString();
        
        if(cli.modificaEntrega(cli) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonModificar6ActionPerformed

    private void jButtonEliminar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminar6ActionPerformed
        // TODO add your handling code here:
        Entrega fn = new Entrega();
        
        if(fn.eliminaEntrega(id) > 0)
        {
            //JOptionPane.showMessageDialog(null, "Cliente Agregado exitosamente",
              //      "Nuevo Cliente",JOptionPane.INFORMATION_MESSAGE);
        }
        
        limpiaControles();
        llenaTabla();
    }//GEN-LAST:event_jButtonEliminar6ActionPerformed

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1)
        {
            int fila = this.jTable7.getSelectedRow();
            id = jTable7.getModel().getValueAt(fila, 0).toString();
            jTIdRE.setText(jTable7.getModel().getValueAt(fila, 1).toString());
            jTIdEE.setText(jTable7.getModel().getValueAt(fila, 2).toString());
            jTCargos.setText(jTable7.getModel().getValueAt(fila, 4).toString());
            jTDE.setText(jTable7.getModel().getValueAt(fila, 5).toString());
            jTCosto.setText(jTable7.getModel().getValueAt(fila, 6).toString());
        }
    }//GEN-LAST:event_jTable7MouseClicked

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
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlta;
    private javax.swing.JButton jButtonAlta1;
    private javax.swing.JButton jButtonAlta2;
    private javax.swing.JButton jButtonAlta3;
    private javax.swing.JButton jButtonAlta4;
    private javax.swing.JButton jButtonAlta5;
    private javax.swing.JButton jButtonAlta6;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonEliminar1;
    private javax.swing.JButton jButtonEliminar2;
    private javax.swing.JButton jButtonEliminar3;
    private javax.swing.JButton jButtonEliminar4;
    private javax.swing.JButton jButtonEliminar5;
    private javax.swing.JButton jButtonEliminar6;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonModificar1;
    private javax.swing.JButton jButtonModificar2;
    private javax.swing.JButton jButtonModificar3;
    private javax.swing.JButton jButtonModificar4;
    private javax.swing.JButton jButtonModificar5;
    private javax.swing.JButton jButtonModificar6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTextField jTAMaternoC;
    private javax.swing.JTextField jTAMaternoE;
    private javax.swing.JTextField jTAPaternoC;
    private javax.swing.JTextField jTAPaternoE;
    private javax.swing.JTextField jTAnio;
    private javax.swing.JTextField jTCargos;
    private javax.swing.JTextField jTCosto;
    private javax.swing.JTextField jTDE;
    private javax.swing.JTextField jTDescV;
    private javax.swing.JTextField jTDomicilioC;
    private javax.swing.JTextField jTDomicilioE;
    private javax.swing.JTextField jTEmailC;
    private javax.swing.JTextField jTFechaR;
    private javax.swing.JTextField jTFechaV;
    private javax.swing.JTextField jTIdCR;
    private javax.swing.JTextField jTIdCV;
    private javax.swing.JTextField jTIdEE;
    private javax.swing.JTextField jTIdER;
    private javax.swing.JTextField jTIdEV;
    private javax.swing.JTextField jTIdRE;
    private javax.swing.JTextField jTIdTipo;
    private javax.swing.JTextField jTIdVR;
    private javax.swing.JTextField jTIdVV;
    private javax.swing.JTextField jTMarca;
    private javax.swing.JTextField jTModelo;
    private javax.swing.JTextField jTNSeg;
    private javax.swing.JTextField jTNTC;
    private javax.swing.JTextField jTPNombreC;
    private javax.swing.JTextField jTPNombreE;
    private javax.swing.JTextField jTPRenta;
    private javax.swing.JTextField jTPVenta;
    private javax.swing.JTextField jTPlacas;
    private javax.swing.JTextField jTSNombreC;
    private javax.swing.JTextField jTSNombreE;
    private javax.swing.JTextField jTSueldo;
    private javax.swing.JTextField jTTelefonoC;
    private javax.swing.JTextField jTTelefonoE;
    private javax.swing.JTextField jTTipo;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    // End of variables declaration//GEN-END:variables
}
