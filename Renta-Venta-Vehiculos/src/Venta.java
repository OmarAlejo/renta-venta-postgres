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
public class Venta {
    public long id;
    public long idVeh;
    public long idClien;
    public long idEm;
    public String FechaVen;
    
    public Venta () {
        
    }
    
    public ArrayList<Venta> consultaVenta()
    {
        ArrayList<Venta> venta = new ArrayList<Venta>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT * FROM Servicios.Venta ORDER BY IdVenta ASC";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Venta veh = new Venta();
                veh.id = result.getLong("IdVenta");
                veh.idVeh = result.getLong("IdVehiculo");
                veh.idClien = result.getLong("IdCliente");
                veh.idEm = result.getLong("IdEmpleado");
                veh.FechaVen = result.getString("FechaVenta");
                
                venta.add(veh);                
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
        return venta;
    }
    
    public int insertaVenta(Venta nCli) throws ParseException
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            String dml = "INSERT INTO Servicios.Venta(IdVehiculo, IdCliente, IdEmpleado, FechaVenta)"
                    + "  VALUES (?,?,?,?)";
            PreparedStatement st = conexion.getConexion().prepareStatement(dml);
            st.setLong(1, nCli.idVeh);
            st.setLong(2, nCli.idClien);
            st.setLong(3, nCli.idEm);
            try
            {
                java.util.Date uDate = new java.util.Date();
                SimpleDateFormat df = new SimpleDateFormat("YYYY/MM/dd");
                java.sql.Date sDate = new java.sql.Date(uDate.getTime());
                st.setDate(4, sDate);
            }
            catch(Exception exx)
            {}
            
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
    
    public int modificaVenta(Venta veh)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
            String dml = "UPDATE Servicios.Venta SET "
                    + "IdVehiculo = " + veh.idVeh +
                    ", IdCliente=" + veh.idClien +
                    ", IdEmpleado=" + veh.idEm + "  WHERE IdVenta = " + veh.id;
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
    
    public int eliminaVenta(String nCli)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "DELETE FROM Servicios.Venta WHERE IdVenta = " + nCli;
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
}
