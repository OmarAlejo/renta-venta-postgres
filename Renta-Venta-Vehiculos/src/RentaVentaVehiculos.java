/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package renta_venta_vehiculos;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author alejo
 */
public class RentaVentaVehiculos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Conexion_BD conexionBD = new Conexion_BD();
        conexionBD.Conectate();
        conexionBD.Desconectate();
        Ventana_Principal ventana = new Ventana_Principal();
        ventana.setVisible(true);
        ventana.setTitle("Ventana Principal");
    }
    
}
