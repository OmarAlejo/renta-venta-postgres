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
public class Empleado {
    public long id;
    public long idTipo;
    public String primerNombre;
    public String segundoNombre;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public String domicilio;
    public String telefono;
    
    public Empleado () {
        
    }
    
    public ArrayList<Empleado> consultaEmpleado()
    {
        ArrayList<Empleado> empleado = new ArrayList<Empleado>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT * FROM Empleado.Empleado";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Empleado veh = new Empleado();
                veh.id = result.getLong("IdEmpleado");
                veh.idTipo = result.getLong("IdTipo");
                veh.primerNombre = result.getString("PrimerNombre");
                veh.segundoNombre = result.getString("SegundoNombre");
                veh.apellidoPaterno = result.getString("ApellidoPaterno");
                veh.apellidoMaterno = result.getString("ApellidoMaterno");
                veh.domicilio = result.getString("Domicilio");
                veh.telefono = result.getString("Telefono");
                
                empleado.add(veh);                
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
        return empleado;
    }
    
    public int insertaEmpleado(Empleado nCli)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "INSERT INTO Empleado.Empleado(IdTipo, PrimerNombre, SegundoNombre, ApellidoPaterno, "
                    + "ApellidoMaterno, Domicilio, Telefono) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement st = conexion.getConexion().prepareStatement(dml);
            st.setLong(1, nCli.idTipo);
            st.setString(2, nCli.primerNombre);
            st.setString(3, nCli.segundoNombre);
            st.setString(4, nCli.apellidoPaterno);
            st.setString(5, nCli.apellidoMaterno);
            st.setString(6, nCli.domicilio);
            st.setString(7, nCli.telefono);
            
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
    
    public int modificaEmpleado(Empleado veh)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "UPDATE Empleado.Empleado SET "
                    + "IdTipo = " + veh.idTipo + 
                    ", PrimerNombre='" + veh.primerNombre + 
                    "', SegundoNombre = '" + veh.segundoNombre +
                    "', ApellidoPaterno='" + veh.apellidoPaterno +
                    "', ApellidoMaterno='" + veh.apellidoMaterno +
                    "', Domicilio='" + veh.domicilio +
                    "', Telefono=" + veh.telefono + "  WHERE IdEmpleado = " + veh.id;
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
    
    public int eliminaEmpleado(String nCli)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "DELETE FROM Empleado.Empleado WHERE IdEmpleado = " + nCli;
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
    
    public ArrayList<String> obtenEmpleado()
    {
        ArrayList<String> clien = new ArrayList<String>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT IdEmpleado, CONCAT(PrimerNombre, ' ', SegundoNombre, ' ', ApellidoPaterno, "
                    + "' ', ApellidoMaterno) AS Nombre FROM Empleado.Empleado";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Empleado veh = new Empleado();
                veh.id = result.getLong("IdEmpleado");
                veh.primerNombre = result.getString("Nombre");
                
                String cli = veh.id + "_" + veh.primerNombre;
                
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
