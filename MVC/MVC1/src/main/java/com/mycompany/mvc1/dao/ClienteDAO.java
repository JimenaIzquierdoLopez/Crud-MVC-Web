/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvc1.dao; 

/**
 *
 * @author PC
 */
 
import com.mycompany.mvc1.config.conexion;
import com.mycompany.mvc1.modelos.Cliente;
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.util.ArrayList; 
import java.util.List; 
 
public class ClienteDAO implements CrudDAO<Cliente> { 
 
        @Override 
    public boolean insertar(Cliente cliente) { 

        String sql = "INSERT INTO clientes (nit, nombre, apellidos, email) VALUES (?, ?, ?, ?)"; 

        try { 

            Connection conn = conexion.IniciarConexion(); 
            PreparedStatement ps = conn.prepareStatement(sql); 

            ps.setString(1, cliente.getNit()); 
            ps.setString(2, cliente.getNombre()); 
            ps.setString(3, cliente.getApellidos()); 
            ps.setString(4, cliente.getEmail()); 

            ps.executeUpdate(); 

            return true; 

        } catch (SQLException e) { 

            System.out.println("Error al insertar el cliente: " + e.getMessage()); 
            return false; 
        } 
    }
 
        @Override 
    public boolean actualizar(Cliente cliente) { 

        String sql = "UPDATE clientes SET " + "nit = ?, " + "nombre = ?, " + "apellidos = ?, " + "email = ? " + "WHERE id_clientes = ?"; 

        try { 

            Connection conn = conexion.IniciarConexion(); 
            PreparedStatement ps = conn.prepareStatement(sql); 

            ps.setString(1, cliente.getNit()); 
            ps.setString(2, cliente.getNombre()); 
            ps.setString(3, cliente.getApellidos()); 
            ps.setString(4, cliente.getEmail()); 
            ps.setInt(5, cliente.getIdCliente()); 

            int filas = ps.executeUpdate(); 

            if (filas > 0) {
                return true;
            }

        } catch (SQLException e) { 

            System.out.println("Error al actualizar el cliente: " + e.getMessage()); 
        } 

        return false; 
    }
     
        @Override 
public boolean eliminar(Cliente cliente) { 

    String sql = "DELETE FROM clientes WHERE id_clientes = ?"; 

    try { 
        Connection conn = conexion.IniciarConexion(); 
        PreparedStatement ps = conn.prepareStatement(sql); 

        ps.setInt(1, cliente.getIdCliente()); 
        ps.executeUpdate(); 
        return true; 

    } catch (SQLException e) { 
        System.out.println("Error al eliminar el cliente: " + e.getMessage()); 
    } 

    return false;  
}
 
    @Override 
    public Cliente buscarporId(int id) { 
 
        String sql = "SELECT id_clientes, nit, nombre, apellidos, email FROM clientes WHERE id_clientes = ?"; 
 
        try { 
            Connection conn = conexion.IniciarConexion(); 
            PreparedStatement ps = conn.prepareStatement(sql); 
             
            ps.setInt(1, id); 
            ResultSet rs = ps.executeQuery(); 
            
            while(rs.next()) { 
                return mapearCliente(rs); 
            } 
        } 
        catch (SQLException e) { 
            System.out.println("Error al buscar el cliente: " + e.getMessage());  
        } 
        return null; 
    } 
 
    @Override 
    public List<Cliente> listaTodos() { 
 
        List<Cliente> clientes = new ArrayList<>(); 
        String sql = "SELECT id_clientes, nit, nombre, apellidos, email FROM clientes"; 
 
        try { 
            Connection conn = conexion.IniciarConexion(); 
            PreparedStatement ps = conn.prepareStatement(sql); 
            ResultSet rs = ps.executeQuery(); 
            
            while(rs.next()) { 
                clientes.add(mapearCliente(rs)); 
            } 
        } catch(SQLException e) { 
            System.out.println("Error al listar los clientes: " + e.getMessage()); 
        } 
        return clientes; 
    } 
    
    private Cliente mapearCliente(ResultSet rs) { 
    
        try { 
 
            return new Cliente( 
                rs.getInt("id_clientes"), 
                rs.getString("nit"), 
                rs.getString("nombre"), 
                rs.getString("apellidos"), 
                rs.getString("email") 
            ); 
 
        } catch (SQLException e) { 
 
            System.out.println("Error al momento de leer el cliente: " + e.getMessage()); 
        } 
 
        return null; 
    } 
}