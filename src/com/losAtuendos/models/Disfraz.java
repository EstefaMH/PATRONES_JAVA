/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.models;

/**
 *
 * @author USER
 */
public class Disfraz extends Prenda {

    private String nombreDisfraz;

    public Disfraz(String referencia, String color, String marca, String talla, double valorAlquiler, String tipoPrenda, String nombreDisfraz) {
        super(referencia, color, marca, talla, valorAlquiler, tipoPrenda);
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
