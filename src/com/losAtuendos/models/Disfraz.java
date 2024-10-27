package com.losAtuendos.models;

public class Disfraz extends Prenda {

    private String nombreDisfraz;

    // Constructor
    public Disfraz(String referencia, String color, String marca, String talla, double valorAlquiler, 
            String tipoPrenda, boolean disponible, String nombreDisfraz) {
        super(referencia, color, marca, talla, valorAlquiler, tipoPrenda, disponible);
        this.nombreDisfraz = nombreDisfraz;
    }

    public String getNombreDisfraz() {
        return nombreDisfraz;
    }

    public void setNombreDisfraz(String nombreDisfraz) {
        this.nombreDisfraz = nombreDisfraz;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Disfraz: " + nombreDisfraz);
        System.out.println("Referencia: " + ref);
        System.out.println("Color: " + color);
        System.out.println("Marca: " + marca);
        System.out.println("Talla: " + talla);
        System.out.println("Valor Alquiler: " + valorAlquiler); 
        System.out.println("Tipo de prenda: " + tipo);
        System.out.println("¿Está disponible?: " + disponible); 
        System.out.println("Nombre del disfraz: " + nombreDisfraz); 
    }

    @Override
    public boolean validarDatos() {
           boolean isValid = validarDatosComunes();

        if (nombreDisfraz == null || nombreDisfraz.isEmpty()) {
            System.out.println("Error: El nombre del disfraz no puede estar vacío.");
            isValid = false;
        }

        return isValid;
    }
}