/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author alejo
 */
public class Conexion_BD {
    private String jdbc = "jdbc:postgresql://";
    private String ip = "localhost:5432/";
    private String bd = "Renta-Venta";
    private String usuario = "postgres";
    private String password = "postgres";
    private String cadenaConexion = jdbc + ip + bd;
    private Connection conexion;
    
    public Conexion_BD()
    {
        
    }
    
    public Connection getConexion()
    {
        return(this.conexion);
    }
    
    public void Conectate()
    {
        try{
            Class.forName("org.postgresql.Driver");
            this.conexion = DriverManager.getConnection(cadenaConexion, 
                                        usuario, password);
            //JOptionPane.showMessageDialog(null, "La conexión se realizó sin"
              //  + " problemas", "Información ", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error: ", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void Desconectate()
    {
        try
        {
            conexion.close();
            //JOptionPane.showMessageDialog(null, "Conexion finalizada", "informacion",
              //      JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
