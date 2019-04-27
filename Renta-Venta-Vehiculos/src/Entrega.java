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
public class Entrega {
    public long id;
    public long idRen;
    public long idEm;
    public String fechaEn;
    public String descri;
    public float cargos;
    public float costo;
    
    public Entrega () {
        
    }
    
    public ArrayList<Entrega> consultaEntrega()
    {
        ArrayList<Entrega> entrega = new ArrayList<Entrega>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT * FROM Servicios.Entrega";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Entrega veh = new Entrega();
                veh.id = result.getLong("IdEntrega");
                veh.idRen = result.getLong("IdRenta");
                veh.idEm = result.getLong("IdEmpleado");
                veh.fechaEn = result.getString("FechaEntrega");
                veh.cargos = result.getFloat("Cargos");
                veh.descri = result.getString("DescripcionMantenimiento");
                veh.costo = result.getFloat("Costo");
                
                entrega.add(veh);                
            }
            result.close();
            st.close();
            conexionbd.Desconectate();
            
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
        return entrega;
    }
    
    public int insertaEntrega(Entrega nCli)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "INSERT INTO Servicios.Entrega(IdRenta, IdEmpleado, FechaEntrega, Cargos"
                    + ",DescripcionMantenimiento, Costo)  VALUES (?,?,?,?,?,?)";
            PreparedStatement st = conexion.getConexion().prepareStatement(dml);
            st.setLong(1, nCli.idRen);
            st.setLong(2, nCli.idEm);
            try
            {               
                
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date fecha = null;
                try {
                     fecha = formatoDelTexto.parse(nCli.fechaEn);
                 } catch (ParseException ex) {
                    ex.printStackTrace();
                 }
                java.sql.Date sDate = new java.sql.Date(fecha.getTime());
                st.setDate(3, sDate);
            }
            catch(Exception exx)
            {}
            st.setFloat(4, nCli.cargos);
            st.setString(5, nCli.descri);
            st.setFloat(6, nCli.costo);
            
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
    
    public int modificaEntrega(Entrega veh)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "UPDATE Servicios.Entrega SET "
                    + "IdRenta = " + veh.idRen +
                    ", IdEmpleado=" + veh.idEm +
                    ", FechaEntrega='" + veh.fechaEn +
                    "', Cargos='" + veh.cargos +
                    "', DescripcionMantenimiento='" + veh.descri +
                    "', Costo='" + veh.costo + "'  WHERE IdEntrega = " + veh.id;
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
    
    public int eliminaEntrega(String nCli)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "DELETE FROM Servicios.Entrega WHERE IdEntrega = " + nCli;
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
