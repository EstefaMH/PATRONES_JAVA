package com.losAtuendos.factory.AlquilerFactory;

import com.losAtuendos.models.Alquiler;
import com.losAtuendos.models.Cliente;
import com.losAtuendos.models.Empleado;
import java.time.LocalDate;

public abstract class AlquilerFactoryAbstract {
     
    public abstract Alquiler crearAlquiler(int numeroAlquiler, Cliente cliente, Empleado empleado, 
            LocalDate fechaSolicitud, LocalDate fechaAlquiler, LocalDate fechaRegreso, String ref);

}
