/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvc1.modelos;

/**
 *
 * @author PC
 */
public class Producto {
    private int IdProducto;
    private int IdMarca;
    private String Nombre;
    private float Precio;
    private int Stock;

    public Producto(int IdProducto, int IdMarca, String Nombre, float Precio, int Stock) {

        this.IdProducto = IdProducto;
        this.IdMarca = IdMarca;
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.Stock = Stock;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getIdMarca() {
        return IdMarca;
    }

    public void setIdMarca(int IdMarca) {
        this.IdMarca = IdMarca;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }
}