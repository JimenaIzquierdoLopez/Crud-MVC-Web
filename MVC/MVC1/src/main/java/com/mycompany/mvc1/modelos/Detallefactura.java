/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvc1.modelos;

/**
 *
 * @author PC
 */
public class Detallefactura {
    private int IdDetalle;
    private int IdFactura;
    private int IdProducto;
    private int Cantidad;
    private float PrecioUnitario;
    private float Subtotal;

    public Detallefactura(int IdDetalle, int IdFactura, int IdProducto, int Cantidad, float PrecioUnitario, float Subtotal) {

        this.IdDetalle = IdDetalle;
        this.IdFactura = IdFactura;
        this.IdProducto = IdProducto;
        this.Cantidad = Cantidad;
        this.PrecioUnitario = PrecioUnitario;
        this.Subtotal = Subtotal;
    }

    public int getIdDetalle() {
        return IdDetalle;
    }

    public void setIdDetalle(int IdDetalle) {
        this.IdDetalle = IdDetalle;
    }

    public int getIdFactura() {
        return IdFactura;
    }

    public void setIdFactura(int IdFactura) {
        this.IdFactura = IdFactura;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public float getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(float PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }

    public float getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(float Subtotal) {
        this.Subtotal = Subtotal;
    }
}
