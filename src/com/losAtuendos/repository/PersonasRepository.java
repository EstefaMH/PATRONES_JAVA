package com.losAtuendos.repository;
import com.losAtuendos.models.Cliente;
import com.losAtuendos.models.Empleado;
import com.losAtuendos.models.Persona;
import java.util.List;

public interface PersonasRepository {
    boolean postPersona(Persona persona);
    boolean postEmpleado(Empleado empleado);
    boolean postCliente(Cliente cliente);
    
    boolean getEmpleadoById(String id);
    List<Empleado> getAllEmpleados();
    boolean getClienteById(String id);
    List<Cliente> getAllClientes();
}
