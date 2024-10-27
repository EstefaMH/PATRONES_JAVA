/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.factory.PersonaFactory;
import com.losAtuendos.models.Persona;

/**
 *
 * @author USER
 */
public abstract class PersonaFactoryAbstract {
    public abstract Persona crearPersona(String tipoPersona, String id , String nombre,String direccion, String telefono, String cargo, String mail);
      

}
