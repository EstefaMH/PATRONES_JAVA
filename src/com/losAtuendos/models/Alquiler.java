package com.losAtuendos.models;

import java.time.LocalDate;

public abstract class Alquiler {

    protected int numeroAlquiler;
    protected Cliente cliente;
    protected Empleado empleado;
    protected LocalDate fechaSolicitud;
    protected LocalDate fechaAlquiler;
    protected LocalDate fechaRegreso;

    public Alquiler(int numeroAlquiler, Cliente cliente, Empleado empleado, LocalDate fechaSolicitud, LocalDate fechaAlquiler, LocalDate fechaRegreso) {
        this.numeroAlquiler = numeroAlquiler;
        this.cliente = cliente;
        this.empleado = empleado;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaAlquiler = fechaAlquiler;
        this.fechaAlquiler = fechaRegreso;
    }

    public int getNumeroAlquiler() {
        return numeroAlquiler;
    }

    public void setNumeroAlquiler(int numeroAlquiler) {
        this.numeroAlquiler = numeroAlquiler;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDate getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(LocalDate fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public LocalDate getFechaRegreso() {
        return fechaRegreso;
    }

    public void setFechaRegreso(LocalDate fechaRegreso) {
        this.fechaRegreso = fechaRegreso;
    }

    public abstract void mostrarDetalles();

}
