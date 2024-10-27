/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.repository;
import com.losAtuendos.models.Cliente;
import com.losAtuendos.models.Empleado;
import com.losAtuendos.models.Persona;

/**
 *
 * @author USER
 */
public interface PersonasRepository {
    boolean postPersona(Persona persona);
    boolean postEmpleado(Empleado empleado);
    boolean postCliente(Cliente cliente);
    
    boolean getEmpleadoById(String id);
    boolean getClienteById(String id);
}
