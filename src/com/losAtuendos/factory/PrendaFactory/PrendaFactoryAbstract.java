package com.losAtuendos.factory.PrendaFactory;

import com.losAtuendos.models.Prenda;

public abstract class PrendaFactoryAbstract {

    public abstract Prenda crearPrenda(
            String tipo,
            boolean disponible,
            String ref,
            String color,
            String marca,
            String talla,
            double valorAlquiler,
            boolean pedreria,
            String largo,
            int cantPiezas,
            String tipoTraje,
            String accesorio,
            String nombreDisfraz
    );

    /*
    public abstract List obtenerPrendasPorTalla(String tipo, 
            String ref, 
            boolean disponible,
            String color, 
            String marca, 
            String talla, 
            double valorAlquiler, 
            boolean pedreria, 
            String largo,
            int cantPiezas,
            String tipoTraje, 
            String accesorio, 
            String nombreDisfraz
    );*/

}
