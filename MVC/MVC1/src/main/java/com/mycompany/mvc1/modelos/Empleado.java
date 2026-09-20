/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvc1.modelos;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author PC
 */
public class Empleado extends Persona {

    private int IdEmpleado;
    private int IdPuesto;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate Contratacion;

    public Empleado() {
        super();
    }

    public Empleado(int IdEmpleado, int IdPuesto, String Nombre, String Apellidos, LocalDate Contratacion) {
        super(Nombre, Apellidos);
        this.IdEmpleado = IdEmpleado;
        this.IdPuesto = IdPuesto;
        this.Contratacion = Contratacion;
    }

    public int getIdEmpleado() {
        return IdEmpleado;
    }

    public void setIdEmpleado(int IdEmpleado) {
        this.IdEmpleado = IdEmpleado;
    }

    public int getIdPuesto() {
        return IdPuesto;
    }

    public void setIdPuesto(int IdPuesto) {
        this.IdPuesto = IdPuesto;
    }

    public LocalDate getContratacion() {
        return Contratacion;
    }

    public void setContratacion(LocalDate Contratacion) {
        this.Contratacion = Contratacion;
    }

    @Override
    public void mostrar() {

        System.out.println("ID Empleado: " + IdEmpleado);
        System.out.println("ID Puesto: " + IdPuesto);
        System.out.println("Nombre Completo: " + Nombre + " " + Apellidos);
        System.out.println("Fecha de contratacion: "+ Contratacion);
    }
}
