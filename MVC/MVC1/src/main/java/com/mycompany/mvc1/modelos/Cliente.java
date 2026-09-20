/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mvc1.modelos;

/**
 *
 * @author PC
 */
public class Cliente extends Persona {
  private int IdCliente;
    private String Nit;
    private String Email;
    
    public Cliente() {
        super();
    }


    public Cliente(int IdCliente, String NIT, String Nombre,String Apellidos, String Email) {

        super(Nombre, Apellidos);

        this.IdCliente = IdCliente;
        this.Nit = NIT;
        this.Email = Email;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getNit() {
        return Nit;
    }

    public void setNit(String Nit) {
        this.Nit = Nit;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public void mostrar() {

        System.out.println("ID Cliente: " + IdCliente);
        System.out.println("Nombre Completo: " + Nombre + " " + Apellidos);
        System.out.println("NIT: " + Nit);
        System.out.println("Email: " + Email);
    }   
}
