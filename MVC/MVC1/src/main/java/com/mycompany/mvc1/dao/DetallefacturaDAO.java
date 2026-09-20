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
import com.mycompany.mvc1.modelos.Detallefactura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetallefacturaDAO implements CrudDAO<Detallefactura> {

    @Override
    public boolean insertar(Detallefactura detalle) {

        String sql = "INSERT INTO detalle_facturas (id_Factura, id_Producto, Cantidad, Precio_Unitario, Subtotal) VALUES (?, ?, ?, ?, ?)";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, detalle.getIdFactura());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setFloat(4, detalle.getPrecioUnitario());
            ps.setFloat(5, detalle.getSubtotal());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al insertar el Detalle de Factura: " + e.getMessage());

            return false;
        }
    }

    @Override
    public boolean actualizar(Detallefactura detalle) {

        String sql = "UPDATE detalle_facturas SET " + "id_factura = ?, " + "id_producto = ?, " + "cantidad = ?, " 
                + "precio_unitario = ?, " + "subtotal = ? " + "WHERE id_detalle = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, detalle.getIdFactura());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setFloat(4, detalle.getPrecioUnitario());
            ps.setFloat(5, detalle.getSubtotal());
            ps.setInt(6, detalle.getIdDetalle());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Error al actualizar el detalle de factura: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminar(Detallefactura detalle) {

        String sql = "DELETE FROM detalle_facturas WHERE id_detalle = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, detalle.getIdDetalle());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Error al eliminar el detalle de factura: " + e.getMessage());
        }

        return false;
    }

    @Override
    public Detallefactura buscarporId(int id) {

        String sql = "SELECT id_detalle, id_factura, id_producto, " + "cantidad, precio_unitario, subtotal " + "FROM detalle_facturas " + "WHERE id_detalle = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return mapearDetalleFactura(rs);
            }

        } catch (SQLException e) {

            System.out.println("Error al buscar el detalle de factura: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Detallefactura> listaTodos() {

        List<Detallefactura> detalles = new ArrayList<>();

        String sql = "SELECT id_detalle, id_factura, id_producto, cantidad, precio_unitario, subtotal FROM detalle_facturas";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                detalles.add(mapearDetalleFactura(rs));
            }

        } catch (SQLException e) {

            System.out.println("Error al listar los detalles de factura: " + e.getMessage());
        }

        return detalles;
    }

    private Detallefactura mapearDetalleFactura(ResultSet rs) {

        try {

            return new Detallefactura(
                    rs.getInt("id_detalle"),
                    rs.getInt("id_factura"),
                    rs.getInt("id_producto"),
                    rs.getInt("cantidad"),
                    rs.getFloat("precio_unitario"),
                    rs.getFloat("subtotal")
            );

        } catch (SQLException e) {

            System.out.println("Error al momento de leer el detalle de factura: " + e.getMessage());
        }

        return null;
    }
}
