package com.losAtuendos.models;

public class TrajeCaballero extends Prenda {

    private String tipoTraje;
    private String accesorio;

    public TrajeCaballero(String referencia, String color, String marca, String talla, double valorAlquiler,
            String tipoPrenda, boolean disponible, String tipoTraje, String accesorio) {
        super(referencia, color, marca, talla, valorAlquiler, tipoPrenda, disponible);
        this.tipoTraje = tipoTraje;
        this.accesorio = accesorio;
    }

    public String getTipoTraje() {
        return tipoTraje;
    }

    public void setTipoTraje(String tipoTraje) {
        this.tipoTraje = tipoTraje;
    }

    public String getAccesorio() {
        return accesorio;
    }

    public void setAccesorio(String accesorio) {
        this.accesorio = accesorio;
    }

    @Override
    public String toString() {
        return "TrajeCaballero{" + 
                "Referencia: " + ref +
                ", Color: " + color + 
                ", Marca: " + marca+
                ", Talla: " + talla+
                ", Valor Alquiler: " + valorAlquiler+
                ", ¿Está disponible?: " + disponible+
                ", tipoTraje=" + tipoTraje + 
                ", accesorio=" + accesorio + '}';
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Traje Caballero:");
        System.out.println("Referencia: " + ref);
        System.out.println("Color: " + color);
        System.out.println("Marca: " + marca);
        System.out.println("Talla: " + talla);
        System.out.println("Valor Alquiler: " + valorAlquiler);
        System.out.println("Tipo de prenda: " + tipo);
        System.out.println("¿Está disponible?: " + disponible);
        System.out.println("Tipo de traje: " + tipoTraje);
        System.out.println("Accesorio: " + accesorio);
    }

    @Override
    public boolean validarDatos() {
        boolean isValid = validarDatosComunes();

        if (tipoTraje == null || tipoTraje.isEmpty()) {
            System.out.println("Error: El tipo de traje no puede estar vacío.");
            isValid = false;
        }

       return isValid;
    }
}
