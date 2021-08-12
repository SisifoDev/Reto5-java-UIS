/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import persistencia.ConexionDB;

/**
 *
 * @author manue
 */
public class Producto {
    
     public int id;
    public String nombre;
    public int cantidad;
    public String categoria;
    public double precio;

    public Producto(){

    }

    public Producto(String nombre, int cantidad, String categoria, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.precio = precio;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }
    
    public boolean guardarProducto(){
        ConexionDB conexion = new ConexionDB();
        
        String sentencia = "INSERT INTO productos(nombre, cantidad, categoria, precio)"
                + " VALUES('" + this.nombre + "', " + this.cantidad + ", '"+ this.categoria +"', "+ this.precio + " );";
        System.out.println(sentencia);
        
        conexion.insertarDB(sentencia);
        conexion.closeConnection();
        
        return true;
      }
    
    
    public boolean actualizarProducto(int id){
         ConexionDB conexion = new ConexionDB();
        
        //String sentencia = "UPDATE productos SET nombre='" + this.nombre + "', cantidad='" + this.cantidad 
        //        + "', categoria='" + this.categoria +"', precio='" + this.precio + " WHERE id =" + id + ";";
        String sentencia = String.format("UPDATE productos SET nombre = '%s' , cantidad = %d, categoria = '%s', precio = %s WHERE id = %d", this.nombre, this.cantidad, this.categoria, this.precio, id);
        System.out.println(sentencia);
        conexion.actualizarDB(sentencia);
        conexion.closeConnection();

        return true;
    }
    
 
    //Metodo de borrar producto
    public boolean borrarProducto(int id){
         ConexionDB conexion = new ConexionDB();
        
        
        String sentencia = String.format("DELETE FROM productos  WHERE id=%d", id);
        System.out.println(sentencia);
        conexion.borrarDB(sentencia);
        conexion.closeConnection();

        return true;
    }
    
    
    public ArrayList<Producto> listarProductos(){
        ArrayList<Producto> listaProductos = new ArrayList<>();
        ConexionDB conexion = new ConexionDB();
        String sentencia = "SELECT * FROM productos";
        ResultSet rs = conexion.consultarDB(sentencia);
        Producto p;
        
        try{
            while(rs.next()){
                p = new Producto();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setCategoria(rs.getString("categoria"));
                p.setPrecio(rs.getDouble("precio"));
                listaProductos.add(p);
            }
        }catch(SQLException error){
            System.out.println("Error en la consulta a la DB: " + error.getMessage());
        }
        
        return listaProductos;
    }
    
   
    


    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", categoria=" + categoria + ", precio=" + precio + '}';
    }
    
}
