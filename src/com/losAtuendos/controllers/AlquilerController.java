package com.losAtuendos.controllers;

import com.losAtuendos.factory.AlquilerFactory.AlquilerConcreteFactory;
import com.losAtuendos.factory.AlquilerFactory.AlquilerFactoryAbstract;
import java.util.Scanner;

public class AlquilerController {

    Scanner sc = new Scanner(System.in);

    public void registros() {
        AlquilerFactoryAbstract alquiler = new AlquilerConcreteFactory();

        System.out.print(" ---------- Bienvenido a registros de Personas los Atuendos ---------- \n\n");
        System.out.println("Seleccione el tipo de persona a crear:");
        System.out.println("1. Cliente");
        System.out.println("2. Empleado");

        /*    private List<Prenda> prendas;
    private List<Empleado> empleados;
    private Map<String, Cliente> clientes; 

   /* public AlquilerController() {
        prendas = new ArrayList<>();
        empleados = new ArrayList<>();
        clientes = new HashMap<>();
    }

    public void agregarPrenda(Prenda prenda) {
        prendas.add(prenda);
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public void agregarCliente(Cliente cliente) {
        clientes.put(cliente.getId(), cliente);
    }

    public List<Prenda> obtenerPrendas() {
        return prendas;
    }

    public Empleado obtenerEmpleado(String numIdentificacion) {
        for (Empleado empleado : empleados) {
            if (empleado.getId().equals(numIdentificacion)) {
                return empleado;
            }
        }
        return null;
    }

    public Cliente obtenerCliente(String numIdentificacion) {
        return clientes.get(numIdentificacion);
    }

    public boolean verificarDisponibilidad(Prenda prenda, Date fechaAlquiler) {
        // Aquí puedes implementar la lógica de verificación de disponibilidad
        // Podrías tener una lista de alquileres activos y comprobar si la prenda está en uso
        return true; // Placeholder
    }*/
    }
}
