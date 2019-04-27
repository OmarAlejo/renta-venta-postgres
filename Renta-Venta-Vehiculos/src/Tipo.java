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
public class Tipo {
    public long id;
    public String tipoEmpleado;
    public float sueldo;
    
    public Tipo () {
        
    }
    
    public ArrayList<Tipo> consultaTipo()
    {
        ArrayList<Tipo> tipo = new ArrayList<Tipo>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT * FROM Empleado.Tipo";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Tipo tip = new Tipo();
                tip.id = result.getLong("IdTipo");
                tip.tipoEmpleado = result.getString("Tipo");
                tip.sueldo = result.getFloat("Sueldo");
                
                tipo.add(tip);                
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
        return tipo;
    }
    
    public int insertaTipo(Tipo nCli)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "INSERT INTO Empleado.Tipo(Tipo, Sueldo) VALUES (?,?)";
            PreparedStatement st = conexion.getConexion().prepareStatement(dml);
            st.setString(1, nCli.tipoEmpleado);
            st.setFloat(2, nCli.sueldo);
            
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
    
    public int modificaTipo(Tipo veh)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "UPDATE Empleado.Tipo SET Tipo = '"
                    + veh.tipoEmpleado + "', Sueldo=" + veh.sueldo + "  WHERE IdTipo = " + veh.id;
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
    
    public int eliminaTipo(String nCli)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "DELETE FROM Empleado.Tipo WHERE IdTipo = " + nCli;
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
    
    public ArrayList<String> obtenTipo()
    {
        ArrayList<String> clien = new ArrayList<String>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT IdTipo, Tipo FROM Empleado.Tipo";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Tipo veh = new Tipo();
                veh.id = result.getLong("IdTipo");
                veh.tipoEmpleado = result.getString("Tipo");
                
                String cli = veh.id + "_" + veh.tipoEmpleado;
                
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
