package com.losAtuendos.service.facade;

import com.losAtuendos.models.Alquiler;
import java.time.LocalDate;
import java.util.List;

public interface ServicioAlquilerI {
    
  
    boolean registrarAlquiler(int numeroAlquiler, String clienteId, String empleadoId, LocalDate fechaSolicitud, 
                                     LocalDate fechaAlquiler, LocalDate fechaRegreso, List<String> refPrenda);
    
    
    List<Alquiler> obtenerTodosLosAlquileres();
    
    
    Alquiler consultarAlquilerPorId(int id);
    List<Alquiler> consultarAlquilerPorCliente(String idCliente);
    List<Alquiler> consultarAlquilerPorFecha(LocalDate idCliente);
    
    
    boolean actualizarAlquiler(Alquiler alquiler);
    
    
    boolean eliminarAlquiler(int id);
}
