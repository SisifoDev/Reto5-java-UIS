/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;

public class ConexionDB {
    // Atributos de la conexión a la base de datos
    private String url = ""; // Dirección de la DB
    public Connection con = null;
    public Statement stmt = null;
    private ResultSet rs = null;

    // Constructor
    public ConexionDB(){
        url = "jdbc:sqlite:reto5db.db";

        try {
            //Realizamos la conexión
            con = DriverManager.getConnection(url);
            if(con != null){
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("Base de datos conectada!!!" + meta.getDriverName());
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }
    
    //Retornar la conexiòn
    public Connection getConnection(){
        return con;
    }
    
    public void closeConnection(){
        if(con != null){
            try {
                con.close(); // Cierre de la conexiòn
            } catch (SQLException error) {
                Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, error);
            }
        }
    }
    
      // Método de consulta a la DB
    public ResultSet consultarDB(String sentencia){
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sentencia);
        } catch (SQLException | RuntimeException error) {
            System.out.println("El error es: " + error.getMessage());
        }catch(Exception error1){
            System.out.println("El error es: " + error1.getMessage());
        }
        return rs;
    }
    
    // Método de inserción a la DB
    public boolean insertarDB(String sentencia){
        try {
            stmt = con.createStatement();
            stmt.execute(sentencia);
            JOptionPane.showMessageDialog(null, "¡Los datos han sido guardados exitosamente!");
        } catch (SQLException | RuntimeException error) {
            System.out.println("Error al intentar insertar los datos en la tabla" + error);
            return false;
        }
        return true;
    }
    
    // Método para borrar a la DB
    public boolean borrarDB(String sentencia){
        try {
            stmt = con.createStatement();
            stmt.execute(sentencia);
            
        } catch (SQLException | RuntimeException error) {
            System.out.println("Error al intentar borrar los datos en la tabla" + error);
            return false;
        }
        return true;
    }
    
     // Método para Actualizar a la DB
    public boolean actualizarDB(String sentencia){
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sentencia);
                    JOptionPane.showMessageDialog(null, "¡Los datos han sido Actualizados correctamente!");
        } catch (SQLException | RuntimeException error) {
            System.out.println("Error al intentar Actualizar los datos en la tabla" + error);
            return false;
        }
        return true;
    }
    
    
    
  
}
