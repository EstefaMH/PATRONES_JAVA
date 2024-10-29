package com.losAtuendos.factory.PersonaFactory;

import com.losAtuendos.models.Persona;

public abstract class PersonaFactoryAbstract {

    public abstract Persona crearPersona(String tipoPersona, String id,
            String nombre, String direccion, String telefono, String cargo, String mail);
}
