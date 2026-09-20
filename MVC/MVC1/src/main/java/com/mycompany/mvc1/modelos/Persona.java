/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvc1.modelos;

/**
 *
 * @author PC
 */
public abstract class Persona {
    protected String Nombre;
    protected String Apellidos;
    
    public Persona() {
}

    public Persona(String nombre, String apellidos) {
        this.Nombre = nombre;
        this.Apellidos = apellidos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        this.Apellidos = apellidos;
    }

    public abstract void mostrar();
}
