/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.mvc1.dao;

import java.util.List;

/**
 *
 * @author PC
 * @param <T>
 */
public interface CrudDAO<T> {
    boolean insertar (T objeto);
    
    boolean actualizar (T objeto);
    
    boolean eliminar (T objeto);

    T buscarporId (int id);
    
    List <T> listaTodos ();
}
