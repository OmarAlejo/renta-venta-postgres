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
public class Cliente {
    public long id;
    public String primerNombre;
    public String segundoNombre;
    public String apellidoPaterno;
    public String apellidoMaterno;
    public String domicilio;
    public String telefono;
    public String email;
    public String nTar;
    
    public Cliente () {
        
    }
    
    public ArrayList<Cliente> consultaCliente()
    {
        ArrayList<Cliente> cliente = new ArrayList<Cliente>();
        Conexion_BD conexionbd = new Conexion_BD();
        conexionbd.Conectate();
        try
        {
            Statement st = conexionbd.getConexion().createStatement();
            String sql = "SELECT * FROM Servicios.Cliente";
            ResultSet result = st.executeQuery(sql);
            
            while(result.next())
            {
                Cliente veh = new Cliente();
                veh.id = result.getLong("IdCliente");
                veh.primerNombre = result.getString("PrimerNombre");
                veh.segundoNombre = result.getString("SegundoNombre");
                veh.apellidoPaterno = result.getString("ApellidoPaterno");
                veh.apellidoMaterno = result.getString("ApellidoMaterno");
                veh.domicilio = result.getString("Domicilio");
                veh.telefono = result.getString("Telefono");
                veh.email = result.getString("Email");
                veh.nTar = result.getString("NumTarjeta");
                
                cliente.add(veh);                
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
        return cliente;
    }
    
    public int insertaCliente(Cliente nCli)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "INSERT INTO Servicios.Cliente(PrimerNombre, SegundoNombre, ApellidoPaterno, "
                    + "ApellidoMaterno, Domicilio, Telefono, Email, NumTarjeta) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement st = conexion.getConexion().prepareStatement(dml);
            st.setString(1, nCli.primerNombre);
            st.setString(2, nCli.segundoNombre);
            st.setString(3, nCli.apellidoPaterno);
            st.setString(4, nCli.apellidoMaterno);
            st.setString(5, nCli.domicilio);
            st.setString(6, nCli.telefono);
            st.setString(7, nCli.email);
            st.setString(8, nCli.nTar);
            
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
    
    public int modificaCliente(Cliente veh)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "UPDATE Servicios.Cliente SET "
                    + "PrimerNombre='" + veh.primerNombre + 
                    "', SegundoNombre = '" + veh.segundoNombre +
                    "', ApellidoPaterno='" + veh.apellidoPaterno +
                    "', ApellidoMaterno='" + veh.apellidoMaterno +
                    "', Domicilio='" + veh.domicilio +
                    "', Telefono='" + veh.telefono +
                    "', Email='" + veh.email +
                    "', NumTarjeta=" + veh.nTar + "  WHERE IdCliente = " + veh.id;
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
    
    public int eliminaCliente(String nCli)
    {
	int resp = -1;
	Conexion_BD conexion = new Conexion_BD();
	
	conexion.Conectate();
	try
	{
            String dml = "DELETE FROM Servicios.Cliente WHERE IdCliente = " + nCli;
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
