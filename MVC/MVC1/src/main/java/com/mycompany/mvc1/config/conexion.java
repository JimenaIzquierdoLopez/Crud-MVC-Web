/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvc1.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author PC
 */
public class conexion {
     private static final String URL = "jdbc:mysql://localhost:3306/basededatos";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "jimena100";
    
    private static Connection conexion = null;
    
    private conexion(){
    }
    
    public static Connection IniciarConexion(){
        try{
            if(conexion == null || conexion.isClosed()){
               conexion = DriverManager.getConnection(URL,USUARIO,PASSWORD); 
                System.out.println("La conexion se realizo correctamente");
            }
        }catch(SQLException e){
            System.err.println("La conexion no se ha realizado");
             e.printStackTrace();
        }
        return conexion;
    }
    
    public static boolean ejecutarInstruccion(String sql){
        try{
            Connection conn = IniciarConexion();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Se ejecuto correctamente ");
            return true;
        }catch(SQLException e){
            System.err.println("No se ha ejecutado la instruccion");
            e.printStackTrace();
            return false;
        }
    }
    
    public static ResultSet ejecutarConsulta(String sql){
        ResultSet rs = null;
        try{
            Connection conn = IniciarConexion();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        }catch(SQLException e){
            System.err.println("No se ha ejecutado la instruccion");
            e.printStackTrace();
        }
        return rs;
    }
    
    public static void cerrarConexion(){
        try{
            if(conexion != null && !conexion.isClosed()){
                conexion.close();
                System.out.println("Se ha cerrado la conexion");
            }
        }catch(SQLException e){
            System.err.println("Error al cerrar la conexion");
            e.printStackTrace();
        }
    }
    
}
