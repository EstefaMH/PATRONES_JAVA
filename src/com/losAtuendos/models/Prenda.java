package com.losAtuendos.models;

public abstract class Prenda {

    protected String ref;
    protected String color;
    protected String marca;
    protected String talla;
    protected double valorAlquiler;
    protected String tipo;
    protected boolean disponible;
    
    public Prenda(String ref, String color, String marca, String talla, double valorAlquiler, String tipo, boolean disponible) {
        this.ref = ref;
        this.color = color;
        this.marca = marca;
        this.talla = talla;
        this.valorAlquiler = valorAlquiler;
        this.tipo = tipo;
        this.disponible = disponible;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public double getValorAlquiler() {
        return valorAlquiler;
    }

    public void setValorAlquiler(double valorAlquiler) {
        this.valorAlquiler = valorAlquiler;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    

    
    public abstract void mostrarDetalles();

    public abstract boolean validarDatos();

    public boolean validarDatosComunes() {
        String valorAlquilerStr = String.valueOf(valorAlquiler);
        boolean isValid = true;

        if (ref == null || ref.isEmpty()) {
            System.out.println("Error: La referencia no puede estar vacía.");
            isValid = false;
    }

        if (color == null || color.isEmpty()) {
            System.out.println("Error: El color no puede estar vacío.");
            isValid = false;
        }

        if (marca == null || marca.isEmpty()) {
            System.out.println("Error: La marca no puede estar vacía.");
            isValid = false;
        }

        if (talla == null || talla.isEmpty()) {
            System.out.println("Error: La talla no puede estar vacía.");
            isValid = false;
        }

        if (valorAlquiler < 0 || valorAlquilerStr == null) {
            System.out.println("Error: el valor de alquiler no puede estar vacío.");
            isValid = false;
        }

         if (tipo == null || tipo.isEmpty()) {
            System.out.println("Error: El tipo de prenda no puede estar vacío.");
            isValid = false;
        }
        return isValid;
    }

}
