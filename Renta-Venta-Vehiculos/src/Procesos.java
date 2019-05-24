/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.sql.*;
import java.text.ParseException;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author alejo
 */
public class Procesos {
    public String nombre;
    public String marca;
    public String modelo;
    public String placas;
    public String tipo;
    public long contador;
    public String fecha;
    
    public Procesos() {
    }
    
    public ArrayList<Procesos> get_renta_vehiculo(String variable)
    {
        ArrayList<Procesos> procesos = new ArrayList<Procesos>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT * FROM get_renta_vehiculo('" + variable + "')";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Procesos veh = new Procesos();
                veh.marca = result.getString("marca");
                veh.modelo = result.getString("modelo");
                veh.placas = result.getString("placas");
                veh.contador = result.getLong("rentas");
                
                procesos.add(veh);                
            }
            result.close();
            st.close();
            conexionbd.Desconectate();
            
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return procesos;
    }
    
    public ArrayList<Procesos> get_rentas_empleado(String variable, String variable2)
    {
        ArrayList<Procesos> procesos = new ArrayList<Procesos>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT * FROM get_rentas_empleado('" + variable + "', '" + variable2 + "')";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Procesos veh = new Procesos();
                veh.nombre = result.getString("nombre");
                veh.contador = result.getLong("rentas");
                
                procesos.add(veh);                
            }
            result.close();
            st.close();
            conexionbd.Desconectate();
            
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return procesos;
    }
    
    public ArrayList<Procesos> get_rentas_cliente(String variable, String variable2)
    {
        ArrayList<Procesos> procesos = new ArrayList<Procesos>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT * FROM get_rentas_cliente('" + variable + "', '" + variable2 + "');";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Procesos veh = new Procesos();
                veh.nombre = result.getString("nombre");
                veh.placas = result.getString("placas");
                veh.fecha = result.getString("dia_prestamo");
                
                procesos.add(veh);                
            }
            result.close();
            st.close();
            conexionbd.Desconectate();
            
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return procesos;
    }
    
    public ArrayList<Procesos> get_ventas_empleado(String variable)
    {
        ArrayList<Procesos> procesos = new ArrayList<Procesos>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT * FROM get_ventas_empleado('" + variable + "')";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Procesos veh = new Procesos();
                veh.tipo = result.getString("tipo");
                veh.nombre = result.getString("nombre");
                veh.fecha = result.getString("fecha_venta");
                
                procesos.add(veh);                
            }
            result.close();
            st.close();
            conexionbd.Desconectate();
            
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return procesos;
    }
}
