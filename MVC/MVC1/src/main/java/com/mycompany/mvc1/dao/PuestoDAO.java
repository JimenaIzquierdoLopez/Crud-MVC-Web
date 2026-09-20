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
import com.mycompany.mvc1.modelos.Puesto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PuestoDAO implements CrudDAO<Puesto> {

    @Override
    public boolean insertar(Puesto puesto) {

        String sql = "INSERT INTO puestos (Nombre, Salario_Base) VALUES (?, ?)";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, puesto.getNombre());
            ps.setFloat(2, puesto.getSalario());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al insertar el puesto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizar(Puesto puesto) {

        String sql = "UPDATE puestos SET Nombre = ?, Salario_Base = ? WHERE id_Puesto = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, puesto.getNombre());
            ps.setFloat(2, puesto.getSalario());
            ps.setInt(3, puesto.getIdPuesto());

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {

            System.out.println("Error al actualizar el puesto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Puesto puesto) {

        String sql = "DELETE FROM puestos WHERE id_Puesto = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, puesto.getIdPuesto());

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {

            System.out.println("Error al eliminar el puesto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Puesto buscarporId(int id) {

        String sql = "SELECT id_Puesto, Nombre, Salario_Base "
                   + "FROM puestos WHERE id_Puesto = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearPuesto(rs);
            }

        } catch (SQLException e) {

            System.out.println("Error al buscar el puesto: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Puesto> listaTodos() {

        List<Puesto> puestos = new ArrayList<>();

        String sql = "SELECT id_Puesto, Nombre, Salario_Base FROM puestos";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                puestos.add(mapearPuesto(rs));
            }

        } catch (SQLException e) {

            System.out.println("Error al listar los puestos: " + e.getMessage());
        }

        return puestos;
    }

    private Puesto mapearPuesto(ResultSet rs) {

        try {

            return new Puesto(
                    rs.getInt("id_Puesto"),
                    rs.getString("Nombre"),
                    rs.getFloat("Salario_Base")
            );

        } catch (SQLException e) {

            System.out.println("Error al momento de leer el puesto: " + e.getMessage());
        }

        return null;
    }
}
