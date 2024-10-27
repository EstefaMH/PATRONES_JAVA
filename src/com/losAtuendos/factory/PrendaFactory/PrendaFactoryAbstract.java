/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.factory.PrendaFactory;

import com.losAtuendos.models.Prenda;
import java.util.List;

/**
 *
 * @author USER
 */
public abstract class PrendaFactoryAbstract {

    public abstract Prenda crearPrenda(
            String tipo,
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

    public abstract List obtenerPrendasPorTalla(String tipo, 
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

}
