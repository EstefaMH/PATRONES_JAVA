/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.controllers;

import com.losAtuendos.models.Cliente;
import com.losAtuendos.models.Empleado;
import com.losAtuendos.models.Prenda;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AlquilerController {
    private List<Prenda> prendas;
    private List<Empleado> empleados;
    private Map<String, Cliente> clientes; 

    public AlquilerController() {
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
    }
}
