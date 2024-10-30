package com.losAtuendos.factory.AlquilerFactory;

import com.losAtuendos.models.Alquiler;
import com.losAtuendos.models.Cliente;
import com.losAtuendos.models.DetalleAlquiler;
import com.losAtuendos.models.Empleado;
import com.losAtuendos.service.AlquilerService;
import java.time.LocalDate;

public class AlquilerConcreteFactory extends AlquilerFactoryAbstract {

    private final AlquilerService alquilerService;

    public AlquilerConcreteFactory() {
        this.alquilerService = new AlquilerService();
    }

    @Override
    public Alquiler crearAlquiler(String tipoAlquiler,int numeroAlquiler, String clienteId, String empleadoId, LocalDate fechaSolicitud, 
                                  LocalDate fechaAlquiler, LocalDate fechaRegreso, String refPrenda) {
        Alquiler alquiler = null;
         switch (tipoAlquiler.toLowerCase()) {
             case "alquiler":
                alquiler = new Alquiler(000,clienteId, empleadoId, fechaSolicitud, fechaAlquiler, fechaRegreso);
                int numero = alquilerService.postAlquiler((Alquiler) alquiler);
                alquiler.setNumeroAlquiler(numero);
                System.out.println("create Alquiler " + numero);
            break;
            case "detalleAlquiler":
                alquiler = new DetalleAlquiler(numeroAlquiler, clienteId, empleadoId, fechaSolicitud, fechaAlquiler, fechaRegreso, refPrenda);
                boolean createDetalleAlquiler = alquilerService.postDetalleAlquiler((DetalleAlquiler) alquiler);
                System.out.println("create Detalle Alquiler " + createDetalleAlquiler);
                
                break;
            default:
                System.out.println("Tipo de alquiler no válido.");
                return null;
         }
        return alquiler;
    
    }

}