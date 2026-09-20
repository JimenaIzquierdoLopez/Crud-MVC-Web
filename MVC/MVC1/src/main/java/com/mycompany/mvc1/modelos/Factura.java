/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvc1.modelos;
import java.time.LocalDateTime;
/**
 *
 * @author PC
 */
public class Factura {
    private int IdFactura;
    private int IdCliente;
    private int IdEmpleado;
    private LocalDateTime Fecha;
    private float Total;

    public Factura(int IdFactura, int IdCliente,int IdEmpleado, LocalDateTime Fecha, float Total) {

        this.IdFactura = IdFactura;
        this.IdCliente = IdCliente;
        this.IdEmpleado = IdEmpleado;
        this.Fecha = Fecha;
        this.Total = Total;
    }

    public int getIdFactura() {
        return IdFactura;
    }

    public void setIdFactura(int IdFactura) {
        this.IdFactura = IdFactura;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public LocalDateTime getFecha() {
        return Fecha;
    }

    public void setFecha(LocalDateTime Fecha) {
        this.Fecha = Fecha;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }
}
