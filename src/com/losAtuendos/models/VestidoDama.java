/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class VestidoDama extends Prenda {

    List<VestidoDama> listaPrendasDama = new ArrayList<VestidoDama>();
    private boolean pedreria;
    private String largo;
    private int cantPiezas;

    public VestidoDama(String referencia, String color, String marca, String talla, double valorAlquiler, String tipoPrenda, boolean pedreria, String largo, int cantidadPiezas) {
        super(referencia, color, marca, talla, valorAlquiler, tipoPrenda);
        this.pedreria = pedreria;
        this.largo = largo;
        this.cantPiezas = cantidadPiezas;
    }

    public boolean isPedreria() {
        return pedreria;
    }

    public void setPedreria(boolean pedreria) {
        this.pedreria = pedreria;
    }

    public String getLargo() {
        return largo;
    }

    public void setLargo(String largo) {
        this.largo = largo;
    }

    public int getCantPiezas() {
        return cantPiezas;
    }

    public void setCantPiezas(int cantPiezas) {
        this.cantPiezas = cantPiezas;
    }
    
    public String toString() {
        return "VestidoDama{" +
                "referencia='" + this.ref + '\'' +
                ", color='" + this.color + '\'' +
                ", marca='" + this.marca + '\'' +
                ", talla='" + this.talla + '\'' +
                ", valorAlquiler=" + this.valorAlquiler +
                ", pedreria=" + this.pedreria +
                ", largo=" + this.largo +
                ", cantPiezas=" + this.cantPiezas +
                ", tipo='" + this.tipo + '\'' +
                '}';
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Vestido:");
        System.out.println("Referencia: " + ref);
        System.out.println("Color: " + color);
        System.out.println("Marca: " + marca);
        System.out.println("Talla: " + talla);
        System.out.println("Valor Alquiler: " + valorAlquiler);
        System.out.println("¿Tiene pedrería?:" + pedreria);
        System.out.println("Altura" + largo);
        System.out.println("cantidad de piezas" + cantPiezas);
        System.out.println("Tipo de prenda: " + tipo);

    }

    @Override
    public boolean validarDatos() {
        boolean isValid = validarDatosComunes();
        String cantidadPiezasStr = String.valueOf(cantPiezas);

        if (largo.isEmpty()) {
            System.out.println("Error: La altura no puede estar vacia");
            isValid = false;
        }
        if (cantidadPiezasStr.isEmpty()) {
            System.out.println("Error: La cantidad de piezas no puede estar vacia.");
            isValid = false;
        }

        if (cantPiezas <= 0) {
            System.out.println("Error: La cantidad de piezas debe ser mayor que cero.");
            isValid = false;
        }

        return isValid;

    }

}
