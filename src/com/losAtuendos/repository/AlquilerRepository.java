package com.losAtuendos.repository;

import com.losAtuendos.models.Alquiler;
import com.losAtuendos.models.DetalleAlquiler;
import java.time.LocalDate;
import java.util.List;

public interface AlquilerRepository {
    boolean postAlquiler(Alquiler alquiler);
    boolean postDetalleAlquiler(DetalleAlquiler detalleAlquiler);
    
    List getAlquilerPorNumeroAlquiler(int numeroServicio);
    List getAlquilerPorCliente(String refPrenda);
    List getAlquilerPorFecha(LocalDate fechaBusqueda);
}
