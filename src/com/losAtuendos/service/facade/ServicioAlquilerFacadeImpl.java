/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.service.facade;

import com.losAtuendos.factory.AlquilerFactory.AlquilerConcreteFactory;
import com.losAtuendos.factory.AlquilerFactory.AlquilerFactoryAbstract;
import com.losAtuendos.models.Alquiler;
import java.time.LocalDate;
import java.util.List;

public class ServicioAlquilerFacadeImpl implements ServicioAlquilerI {
    
    private final ServicioAlquilerI servicioAlquiler;

    public ServicioAlquilerFacadeImpl(ServicioAlquilerI servicioAlquiler) {
        this.servicioAlquiler = servicioAlquiler;
    }
    
    @Override
    public boolean registrarAlquiler(int numeroAlquiler,String clienteId, String empleadoId, LocalDate fechaSolicitud, 
                                     LocalDate fechaAlquiler, LocalDate fechaRegreso, List<String> refPrendas) {
        AlquilerFactoryAbstract alquiler = new AlquilerConcreteFactory();
        try {
            Alquiler alquilerPrincipal = alquiler.crearAlquiler("alquiler", numeroAlquiler, clienteId, empleadoId, fechaSolicitud, 
                                                          fechaAlquiler, fechaRegreso, null);
            if (alquilerPrincipal != null) {
            alquilerPrincipal.mostrarDetalles();
            int numero=alquilerPrincipal.getNumeroAlquiler();
            System.out.println("numero->"+numero);
            // Crear cada detalle de alquiler con la lista de referencias
            for (String refPrenda : refPrendas) {
                Alquiler detalleAlquiler = alquiler.crearAlquiler("detalle_alquiler", numero, clienteId, 
                                                                         empleadoId, fechaSolicitud, fechaAlquiler, fechaRegreso, refPrenda);
                if (detalleAlquiler == null) {
                    return false; // En caso de error, devolver false
                }
            }
            return true;
        } else {
            return false;
        }
        } catch (Exception e) {
            System.out.println("Error al registrar alquiler: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Alquiler> obtenerTodosLosAlquileres() {
        return servicioAlquiler.obtenerTodosLosAlquileres();
    }

    @Override
    public Alquiler consultarAlquilerPorId(int id) {
        return servicioAlquiler.consultarAlquilerPorId(id);
    }

    @Override
    public boolean actualizarAlquiler(Alquiler alquiler) {
        try {
            servicioAlquiler.actualizarAlquiler(alquiler);
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar alquiler: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarAlquiler(int id) {
        try {
            servicioAlquiler.eliminarAlquiler(id);
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar alquiler: " + e.getMessage());
            return false;
        }
    }
}

