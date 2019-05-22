/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
/**
 *
 * @author alejo
 */
public class Renta {
    public long id;
    public long idVeh;
    public long idClien;
    public long idEm;
    public String diaRen;
    public String diaDev;
    public float total;
    
    public Renta () {
        
    }
    
    public ArrayList<Renta> consultaRenta()
    {
        ArrayList<Renta> renta = new ArrayList<Renta>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT * FROM Servicios.Renta ORDER BY IdRenta ASC";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Renta veh = new Renta();
                veh.id = result.getLong("IdRenta");
                veh.idVeh = result.getLong("IdVehiculo");
                veh.idClien = result.getLong("IdCliente");
                veh.idEm = result.getLong("IdEmpleado");
                veh.diaRen = result.getDate("DiaPrestamo").toString();
                veh.diaDev = result.getDate("DiaDevolucion").toString();
                veh.total = result.getFloat("Total");
                
                renta.add(veh);                
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
        return renta;
    }
    
    public int insertaRenta(Renta nCli) throws ParseException
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "INSERT INTO Servicios.Renta(IdVehiculo, IdCliente, IdEmpleado, DiaPrestamo"
                    + ",DiaDevolucion, Total)  VALUES (?,?,?,?,?,?)";
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
                
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fecha = null;
                try {
                     fecha = formatoDelTexto.parse(nCli.diaDev);
                 } catch (ParseException ex) {
                    ex.printStackTrace();
                 }
                System.out.println (uDate);
                sDate = new java.sql.Date(fecha.getTime());
                st.setDate(5, sDate);
            }
            catch(SQLException exx)
            {}
            st.setFloat(6, nCli.total);
            
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
    
    public int modificaRenta(Renta veh)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "UPDATE Servicios.Renta SET "
                    + "IdVehiculo = " + veh.idVeh +
                    ", IdCliente=" + veh.idClien +
                    ", IdEmpleado=" + veh.idEm +
                    "', DiaDevolucion='" + veh.diaDev + "'  WHERE IdRenta = " + veh.id;
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
    
    public int eliminaRenta(String nCli)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "DELETE FROM Servicios.Renta WHERE IdRenta = " + nCli;
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
    
    public ArrayList<String> obtenRenta()
    {
        ArrayList<String> clien = new ArrayList<String>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT Servicios.Renta.IdRenta, CONCAT(Servicios.Vehiculo.Modelo,'_',Servicios.Cliente.PrimerNombre, ' ', "
                    + "Servicios.Cliente.ApellidoPaterno) "
                    + "AS Nombre FROM Servicios.Renta INNER JOIN Servicios.Cliente" 
                    + " ON Servicios.Renta.IdCliente = Servicios.Cliente.IdCliente"
                    + " INNER JOIN Servicios.Vehiculo ON Servicios.Renta.IdVehiculo = Servicios.Vehiculo.IdVehiculo";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Renta veh = new Renta();
                veh.id = result.getLong("IdRenta");
                veh.diaDev = result.getString("Nombre");
                
                String cli = veh.id + "_" + veh.diaDev;
                
                clien.add(cli);                
            }
            result.close();
            st.close();
            conexionbd.Desconectate();
            
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error2",
                    JOptionPane.ERROR_MESSAGE);
        }
        return clien;
    }
}
