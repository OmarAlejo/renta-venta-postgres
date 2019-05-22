/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author alejo
 */
public class Vehiculo {
    
    public long id;
    public String placas;
    public String marca;
    public String modelo;
    public String anio;
    public String descripcion;
    public String numSeguro;
    public float precioVenta;
    public String vendido;
    public float precioRenta;
    public String disponible;
    
    public Vehiculo () {
        
    }
    
    public ArrayList<Vehiculo> consultaVehiculo()
    {
        ArrayList<Vehiculo> vehiculo = new ArrayList<Vehiculo>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT * FROM Servicios.Vehiculo ORDER BY IdVehiculo ASC";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Vehiculo veh = new Vehiculo();
                veh.id = result.getLong("IdVehiculo");
                veh.placas = result.getString("Placas");
                veh.marca = result.getString("Marca");
                veh.modelo = result.getString("Modelo");
                veh.anio = result.getString("Año");
                veh.descripcion = result.getString("Descripcion");
                veh.numSeguro = result.getString("NumSeguro");
                veh.vendido = result.getString("Vendido");
                veh.precioVenta = result.getFloat("PrecioVenta");
                veh.disponible = result.getString("Disponible");
                veh.precioRenta = result.getFloat("PrecioRenta");
                
                vehiculo.add(veh);                
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
        return vehiculo;
    }
    
    public int insertaVehiculo(Vehiculo nCli)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "INSERT INTO Servicios.Vehiculo(Placas, Marca, Modelo, Año, Descripcion,"
                    + "NumSeguro, Vendido, PrecioVenta,Disponible,PrecioRenta) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = conexion.getConexion().prepareStatement(dml);
            st.setString(1, nCli.placas);
            st.setString(2, nCli.marca);
            st.setString(3, nCli.modelo);
            st.setString(4, nCli.anio);
            st.setString(5, nCli.descripcion);
            st.setString(6, nCli.numSeguro);
            st.setBoolean(7, false);
            st.setFloat(8, nCli.precioVenta);
            st.setBoolean(9, true);
            st.setFloat(10, nCli.precioRenta);
            
            resp = st.executeUpdate();
            st.close();
            conexion.Desconectate();
	}
	catch (SQLException ex)
	{
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
	}
        
        return resp;
    }
    
    public int modificaVeiculo(Vehiculo veh)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "UPDATE Servicios.Vehiculo SET Placas = '"
                    + veh.placas + "', Marca='" + veh.marca + "', Modelo = '" + veh.modelo +
                    "', Año='" + veh.anio +
                    "', Descripcion='" + veh.descripcion +
                    "', NumSeguro='" + veh.numSeguro +
                    "', PrecioVenta=" + veh.precioVenta +
                    ", PrecioRenta=" + veh.precioRenta + "  WHERE IdVehiculo = " + veh.id;
            PreparedStatement st = conexion.getConexion().prepareStatement(dml);
            
            resp = st.executeUpdate();
            st.close();
            conexion.Desconectate();
	}
	catch (SQLException ex)
	{
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
	}
        
        return resp;
    }
    
    public int eliminaVehiculo(String nCli)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "DELETE FROM Servicios.Vehiculo WHERE IdVehiculo = " + nCli;
            PreparedStatement st = conexion.getConexion().prepareStatement(dml);
            
            resp = st.executeUpdate();
            st.close();
            conexion.Desconectate();
	}
	catch (SQLException ex)
	{
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
	}
        
        return resp;
    }
    
    public ArrayList<String> obtenVehiculo()
    {
        ArrayList<String> clien = new ArrayList<String>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT IdVehiculo, CONCAT(Marca, ' ', Modelo, ' ', Placas, "
                    + "' ', Año) AS Nombre FROM Servicios.Vehiculo WHERE Vendido = false AND Disponible = true";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Vehiculo veh = new Vehiculo();
                veh.id = result.getLong("IdVehiculo");
                veh.marca = result.getString("Nombre");
                
                String cli = veh.id + "_" + veh.marca;
                
                clien.add(cli);                
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
        return clien;
    }
}
