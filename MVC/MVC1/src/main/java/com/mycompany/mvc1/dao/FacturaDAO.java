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
import com.mycompany.mvc1.modelos.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO implements CrudDAO<Factura> {

    @Override
    public boolean insertar(Factura factura) {

        String sql = "INSERT INTO facturas (id_clientes, id_empleado, fecha, total) VALUES (?, ?, ?, ?)";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, factura.getIdCliente());
            ps.setInt(2, factura.getIdEmpleado());
            ps.setTimestamp(3,Timestamp.valueOf(factura.getFecha()));
            ps.setFloat(4, factura.getTotal());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al insertar la factura: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Factura factura) {

        String sql = "UPDATE facturas SET id_clientes = ?, id_empleado = ?, fecha = ?, total = ? WHERE id_facturas = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, factura.getIdCliente());
            ps.setInt(2, factura.getIdEmpleado());
            ps.setTimestamp(3,Timestamp.valueOf(factura.getFecha()));
            ps.setFloat(4, factura.getTotal());
            ps.setInt(5, factura.getIdFactura());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Error al actualizar la factura: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminar(Factura factura) {

        String sql = "DELETE FROM facturas WHERE id_facturas = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, factura.getIdFactura());
            int filas = ps.executeUpdate();

            if (filas > 0) {
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Error al eliminar la factura: " + e.getMessage());
        }

        return false;
    }

    @Override
    public Factura buscarporId(int id) {

        String sql = "SELECT id_facturas, id_clientes, id_empleado, fecha, total FROM facturas WHERE id_facturas = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return mapearFactura(rs);
            }

        } catch (SQLException e) {
                System.out.println("Error al buscar la factura: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Factura> listaTodos() {

        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT id_facturas, id_clientes, id_empleado, fecha, total FROM facturas";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                facturas.add(mapearFactura(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar las facturas: " + e.getMessage());
        }

        return facturas;
    }

    private Factura mapearFactura(ResultSet rs) {

        try {

            return new Factura(
                    rs.getInt("id_facturas"),
                    rs.getInt("id_clientes"),
                    rs.getInt("id_empleado"),
                    rs.getTimestamp("fecha").toLocalDateTime(),
                    rs.getFloat("total")
            );

        } catch (SQLException e) {

            System.out.println("Error al momento de leer la factura: " + e.getMessage()
            );
        }

        return null;
    }
}
