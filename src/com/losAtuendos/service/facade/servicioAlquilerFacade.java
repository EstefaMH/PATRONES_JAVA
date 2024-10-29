/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.service.facade;

import com.losAtuendos.controllers.AlquilerController;
import com.losAtuendos.models.Alquiler;
import com.losAtuendos.models.Cliente;
import com.losAtuendos.models.Empleado;
import com.losAtuendos.models.Prenda;
import java.util.Date;
import java.util.List;

/**
 *
 * @author USER
 */
public class servicioAlquilerFacade {
    private AlquilerController alquilercontroller;

    public servicioAlquilerFacade(AlquilerController alquilercontroller) {
        this.alquilercontroller = alquilercontroller;
    }
    
            
   /* public void RegistroServicioAlquiler(String idCliente , String idEmpleado, List<String> referenciasPrendas , Date fechaAlquiler ){
        Cliente cliente = alquilercontroller.obtenerCliente(idCliente);
        Empleado empleado = alquilercontroller.obtenerEmpleado(idEmpleado);
       // List<Prenda> prendas = alquilercontroller.obtenerPrendasPorReferencia(referenciasPrendas);
        
    }*/
    
    // consulta servicio alquiler (private)
    // consulta servicio por cliente (private)
    // Registro prenda para envío a lavandería ? o en lavanderia 
    
    
    // Visualización de listado de prendas para envío a lavandería ? o en lavanderia
    // Enviar prendas a lavandería
    
    public void ConsultarServicio(Alquiler alquiler){}
    
    
    
}
