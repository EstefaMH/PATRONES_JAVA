package com.losAtuendos.factory.PersonaFactory;

import com.losAtuendos.models.Cliente;
import com.losAtuendos.models.Empleado;
import com.losAtuendos.models.Persona;
import com.losAtuendos.service.PersonaService;


public class PersonaConcreteFactory extends PersonaFactoryAbstract {

    private final PersonaService personaService;

    public PersonaConcreteFactory() {
        this.personaService = new PersonaService();
    }

    @Override
    public Persona crearPersona(String tipoPersona, String id, String nombre, String direccion, String telefono, String cargo, String mail) {
        Persona persona = null;

        switch (tipoPersona.toLowerCase()) {
            case "empleado":
                persona = new Empleado(id, nombre, direccion, telefono, cargo);
                
                if (!persona.validarDatos()) {
                    System.out.println("Error al crear el empledo datos no válidos.");
                    return null;
                }

                boolean createEmpleado = personaService.postEmpleado((Empleado) persona);
                System.out.println("create persona " + createEmpleado);

                break;
            case "cliente":
                persona = new Cliente(id, nombre, direccion, telefono, mail);
                 if (!persona.validarDatos()) {
                    System.out.println("Error al crear el cliente datos no válidos.");
                    return null;
                }
                 
                boolean createCliente = personaService.postCliente((Cliente) persona);
                System.out.println("create persona " + createCliente);
                
                break;
            default:
                System.out.println("Tipo de persona no válido.");
                return null;
        }

        return persona;
    }
}
