/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.models;

import java.util.List;
import java.time.LocalDate;

/**
 *
 * @author USER
 */
public class Alquiler {
    private int numeroAlquiler;
    private Cliente cliente;
    private Empleado empleado;
    private List<Prenda> prendas;
    private LocalDate fechaAlquiler;
    private LocalDate fechaSolicitud;

    public Alquiler(int numeroAlquiler, Cliente cliente, Empleado empleado, List<Prenda> prendas, LocalDate fechaAlquiler, LocalDate fechaSolicitud) {
        this.numeroAlquiler = numeroAlquiler;
        this.cliente = cliente;
        this.empleado = empleado;
        this.prendas = prendas;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaSolicitud = fechaSolicitud;
    }

    public int getNumero() {
        return numeroAlquiler;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getFechaAlquiler() {
        return fechaAlquiler;
    }
}


