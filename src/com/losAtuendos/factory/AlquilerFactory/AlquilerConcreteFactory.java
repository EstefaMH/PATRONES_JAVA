package com.losAtuendos.factory.AlquilerFactory;

import com.losAtuendos.models.Alquiler;
import com.losAtuendos.models.Cliente;
import com.losAtuendos.models.Empleado;
import com.losAtuendos.service.AlquilerService;
import java.time.LocalDate;

public class AlquilerConcreteFactory extends AlquilerFactoryAbstract {

    private final AlquilerService alquilerService;

    public AlquilerConcreteFactory() {
        this.alquilerService = new AlquilerService();
    }

    @Override
    public Alquiler crearAlquiler(int numeroAlquiler, Cliente cliente, Empleado empleado, LocalDate fechaSolicitud, LocalDate fechaAlquiler, LocalDate fechaRegreso, String ref) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
