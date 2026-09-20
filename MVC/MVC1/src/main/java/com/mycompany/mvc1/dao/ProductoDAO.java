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
import com.mycompany.mvc1.modelos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO implements CrudDAO<Producto> {

    @Override
    public boolean insertar(Producto producto) {

        String sql = "INSERT INTO productos(id_marca, nombre, precio, stock) VALUES (?, ?, ?, ?)";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, producto.getIdMarca());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al insertar el producto: " + e.getMessage());

            return false;
        }
    }

    @Override
    public boolean actualizar(Producto producto) {

        String sql = "UPDATE productos SET id_marca = ?, nombre = ?, precio = ?, stock = ? WHERE id_productos = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, producto.getIdMarca());
            ps.setString(2, producto.getNombre());
            ps.setFloat(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setInt(5, producto.getIdProducto());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminar(Producto producto) {

        String sql = "DELETE FROM productos WHERE id_productoS = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, producto.getIdProducto());
            int filas = ps.executeUpdate();

            if (filas > 0) {
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }

        return false;
    }

    @Override
    public Producto buscarporId(int id) {

        String sql = "SELECT id_productos, id_marca, nombre, precio, stock FROM productos WHERE id_productos = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return mapearProducto(rs);
            }

        } catch (SQLException e) {

            System.out.println("Error al buscar el producto: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Producto> listaTodos() {

        List<Producto> productos = new ArrayList<>();

        String sql = "SELECT id_productos, id_marca, nombre, precio, stock FROM productos";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productos.add(mapearProducto(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los productos: " + e.getMessage());
        }

        return productos;
    }

    private Producto mapearProducto(ResultSet rs) {

        try {

            return new Producto(
                    rs.getInt("id_productos"),
                    rs.getInt("id_marca"),
                    rs.getString("nombre"),
                    rs.getFloat("precio"),
                    rs.getInt("stock")
            );

        } catch (SQLException e) {

            System.out.println("Error al momento de leer el producto: " + e.getMessage());
        }

        return null;
    }
}

