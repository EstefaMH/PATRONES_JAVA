package com.losAtuendos.models;

public class Empleado extends Persona {

    private String cargo; 

    public Empleado(String id, String nombre, String direccion, String telefono, String cargo) {
        super(id, nombre, direccion, telefono);
        this.cargo = cargo;
    }

    @Override
    public boolean validarDatos() {
        boolean isValid = validarDatosComunes(); 

        if (cargo == null || cargo.isEmpty()) {
            System.out.println("El cargo del empleado no puede estar vacío.");
            isValid = false;
        }
        
        return isValid; 
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("\nCreado internamente");
        System.out.println("Nombre: " + nombre);
        System.out.println("Número de Identificación: " + id);
        System.out.println("Dirección: " + direccion);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Cargo: " + cargo);
    }
}
