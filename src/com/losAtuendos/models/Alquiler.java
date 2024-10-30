package com.losAtuendos.models;

import java.time.LocalDate;

public class Alquiler {

    protected int numeroAlquiler;
    protected String cliente;
    protected String empleado;
    protected LocalDate fechaSolicitud;
    protected LocalDate fechaAlquiler;
    protected LocalDate fechaRegreso;

    public Alquiler(int numeroAlquiler, String cliente, String empleado, LocalDate fechaSolicitud, LocalDate fechaAlquiler, LocalDate fechaRegreso) {
        this.numeroAlquiler = numeroAlquiler;
        this.cliente = cliente;
        this.empleado = empleado;
        this.fechaSolicitud = LocalDate.now();
        this.fechaAlquiler = fechaAlquiler;
        this.fechaAlquiler = fechaRegreso;
    }

    public int getNumeroAlquiler() {
        return numeroAlquiler;
    }

    public void setNumeroAlquiler(int numeroAlquiler) {
        this.numeroAlquiler = numeroAlquiler;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
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

    public void mostrarDetalles() {
        System.out.println("Se van a registrar los siguientes datos: ");
        System.out.println("Numero de orden: " + numeroAlquiler);
        System.out.println("Cliente: " + cliente);
        System.out.println("Empleado: " + empleado);
        System.out.println("Fecha de retiro: " + fechaAlquiler);
        System.out.println("Fecha de devolución: " + fechaRegreso);
    }

}
