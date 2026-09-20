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
import com.mycompany.mvc1.modelos.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO implements CrudDAO<Empleado> {

    @Override
    public boolean insertar(Empleado empleado) {

        String sql = "INSERT INTO empleados (id_puesto, nombre, apellidos, Fecha_Contratacion) VALUES (?, ?, ?, ?)";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, empleado.getIdPuesto());
        ps.setString(2, empleado.getNombre());
        ps.setString(3, empleado.getApellidos());
        ps.setDate(4, Date.valueOf(empleado.getContratacion()));

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al insertar el empleado: " + e.getMessage());

            return false;
        }
    }

    @Override
    public boolean actualizar(Empleado empleado) {

        String sql = "UPDATE empleados SET id_puesto = ?, nombre = ?, apellidos = ?, Fecha_Contratacion = ? WHERE id_empleado = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, empleado.getIdPuesto());
            ps.setString(2, empleado.getNombre());
            ps.setString(3, empleado.getApellidos());
            ps.setDate(4, java.sql.Date.valueOf(empleado.getContratacion()));
            ps.setInt(5, empleado.getIdEmpleado());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Error al actualizar el empleado: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminar(Empleado empleado) {

        String sql = "DELETE FROM empleados WHERE id_empleado = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, empleado.getIdEmpleado());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Error al eliminar el empleado: " + e.getMessage());
        }

        return false;
    }

    @Override
    public Empleado buscarporId(int id) {

        String sql = "SELECT id_empleado, id_puesto, nombre, apellidos, Fecha_Contratacion FROM empleados WHERE id_empleado = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return mapearEmpleado(rs);
            }

        } catch (SQLException e) {

            System.out.println("Error al buscar el empleado: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Empleado> listaTodos() {

        List<Empleado> empleados = new ArrayList<>();

        String sql = "SELECT id_empleado, id_puesto, nombre, apellidos, Fecha_Contratacion FROM empleados";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                empleados.add(mapearEmpleado(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar los empleados: " + e.getMessage());
        }

        return empleados;
    }

    private Empleado mapearEmpleado(ResultSet rs) {

    try {

        Date fechaSQL = rs.getDate("Fecha_Contratacion");

        java.time.LocalDate fecha = null;

        if (fechaSQL != null) {
            fecha = fechaSQL.toLocalDate();
        }

        return new Empleado(
                rs.getInt("id_Empleado"),
                rs.getInt("id_Puesto"),
                rs.getString("Nombre"),
                rs.getString("Apellidos"),
                fecha
        );

    } catch (SQLException e) {

        System.out.println("Error al momento de leer el empleado: " + e.getMessage());
    }

    return null;
}
}

