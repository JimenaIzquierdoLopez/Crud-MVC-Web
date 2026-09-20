/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvc1.modelos;

/**
 *
 * @author PC
 */
public class Puesto {
    private int IdPuesto;
    private String Nombre;
    private float Salario;

    public Puesto(int IdPuesto, String Nombre, float Salario) {
        this.IdPuesto = IdPuesto;
        this.Nombre = Nombre;
        this.Salario = Salario;
    }

    public int getIdPuesto() {
        return IdPuesto;
    }

    public void setIdPuesto(int IdPuesto) {
        this.IdPuesto = IdPuesto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public float getSalario() {
        return Salario;
    }

    public void setSalarioBase(float Salario) {
        this.Salario = Salario;
    }
}
