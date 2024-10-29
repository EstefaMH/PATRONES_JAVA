package com.losAtuendos.models;

import java.time.LocalDate;

public class DetalleAlquiler extends Alquiler {

    private String ref;

    public DetalleAlquiler(int numeroAlquiler, Cliente cliente, Empleado empleado, LocalDate fechaSolicitud,
            LocalDate fechaAlquiler, LocalDate fechaRegreso, String ref) {
        super(numeroAlquiler, cliente, empleado, fechaSolicitud, fechaAlquiler, fechaRegreso);
        this.ref = ref;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("\nAlquiler Creado");
        System.out.println("Cliente: " + cliente);
        System.out.println("Empleado: " + empleado);
        System.out.println("Fecha de solicitud: " + fechaSolicitud);
        System.out.println("Fecha de alquiler: " + fechaAlquiler);
        System.out.println("Fecha de regreso: " + fechaRegreso);
        System.out.println("Referencia de la prenda: " + ref);
    }
;

}
