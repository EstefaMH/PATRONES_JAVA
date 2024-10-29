package com.losAtuendos.repository;
import com.losAtuendos.models.Cliente;
import com.losAtuendos.models.Empleado;
import com.losAtuendos.models.Persona;

public interface PersonasRepository {
    boolean postPersona(Persona persona);
    boolean postEmpleado(Empleado empleado);
    boolean postCliente(Cliente cliente);
    
    boolean getEmpleadoById(String id);
    boolean getClienteById(String id);
}
