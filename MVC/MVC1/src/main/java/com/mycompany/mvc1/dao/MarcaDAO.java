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
import com.mycompany.mvc1.modelos.Marca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO implements CrudDAO<Marca> {

    @Override
    public boolean insertar(Marca marca) {

        String sql = "INSERT INTO marcas (Nombre) VALUES (?)";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, marca.getNombre());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Error al insertar la marca: " + e.getMessage());

            return false;
        }
    }

    @Override
    public boolean actualizar(Marca marca) {

        String sql = "UPDATE marcas SET Nombre = ? WHERE id_marcas = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, marca.getNombre());
            ps.setInt(2, marca.getIdMarca());

            int filas = ps.executeUpdate();

            if (filas > 0) {
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Error al actualizar la marca: " + e.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminar(Marca marca) {

        String sql = "DELETE FROM marcas WHERE id_marcas = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, marca.getIdMarca());
            int filas = ps.executeUpdate();

            if (filas > 0) {
                return true;
            }

        } catch (SQLException e) {

            System.out.println("Error al eliminar la marca: " + e.getMessage());
        }

        return false;
    }

    @Override
    public Marca buscarporId(int id) {

        String sql = "SELECT id_marcas, Nombre FROM marcas WHERE id_marcas = ?";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return mapearMarca(rs);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar la marca: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Marca> listaTodos() {

        List<Marca> marcas = new ArrayList<>();

        String sql = "SELECT id_marcas, nombre FROM marcas";

        try {

            Connection conn = conexion.IniciarConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                marcas.add(mapearMarca(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error al listar las marcas: " + e.getMessage());
        }
        return marcas;
    }

    private Marca mapearMarca(ResultSet rs) {

        try {

            return new Marca(
                    rs.getInt("id_marcas"),
                    rs.getString("Nombre")
            );

        } catch (SQLException e) {

            System.out.println("Error al momento de leer la marca: " + e.getMessage());
        }

        return null;
    }
}

