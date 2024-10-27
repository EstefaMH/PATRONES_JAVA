/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.models;

import com.losAtuendos.utils.dao.DBManager;
import java.sql.Connection;

/**
 *
 * @author USER
 */
public abstract class Persona {

    protected String id;
    protected String nombre;
    protected String direccion;
    protected String telefono;

    public Persona(String id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public abstract boolean validarDatos();      
    
    
    public abstract void mostrarDetalles();
    
    public boolean validarDatosComunes() {
        boolean isValid = true;

        if (nombre == null || nombre.isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            isValid = false;
        }

        if (direccion == null || direccion.isEmpty()) {
            System.out.println("Error: La dirección no puede estar vacía.");
            isValid = false;
        }

        if (telefono == null || telefono.isEmpty()) {
            System.out.println("Error: El teléfono no puede estar vacío.");
            isValid = false;
        }
        return isValid; 
    }
    
}
