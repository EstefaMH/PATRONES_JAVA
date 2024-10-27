/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.controllers;

import com.losAtuendos.factory.PersonaFactory.PersonaConcreteFactory;
import com.losAtuendos.factory.PersonaFactory.PersonaFactoryAbstract;
import com.losAtuendos.models.Persona;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class PersonaController {

    Scanner sc = new Scanner(System.in);

    public void registros() {
        PersonaFactoryAbstract persona = new PersonaConcreteFactory();

        System.out.print(" ---------- Bienvenido a registros de Personas los Atuendos ---------- \n\n");
        System.out.println("Seleccione el tipo de persona a crear:");
        System.out.println("1. Cliente");
        System.out.println("2. Empleado");

        while (!sc.hasNextInt()) {
            System.out.println("Valor inválido. Por favor, ingrese un valor numerico.");
            sc.nextLine();
        }
        int opc = sc.nextInt();
        sc.nextLine();
        
        switch (opc) {
            case 1:
                System.out.println("Ingrese nombre del cliente: ");
                String nombreCliente = sc.next();
                System.out.print("Ingrese número de identificación: ");
                String numeroIdentificacionCliente = sc.next();
                System.out.println("Ingrese dirección: (escriba su direccin sin espacios) ");
                String direccionCliente = sc.next();
                System.out.println("Ingrese teléfono: ");
                String telefonoCliente = sc.next();
                System.out.println("Ingrese correo electrónico: ");
                String correoElectronicoCliente = sc.next();

                Persona cliente = persona.crearPersona("cliente", numeroIdentificacionCliente, nombreCliente, direccionCliente, telefonoCliente, null, correoElectronicoCliente);

                if (cliente != null) {
                    cliente.mostrarDetalles();
                }
                break;

            case 2:
                System.out.print("Ingrese nombre del empleado: ");
                String nombreEmpleado = sc.next();
                System.out.print("Ingrese número de identificación: ");
                String numeroIdentificacionEmpleado = sc.next();
                System.out.print("Ingrese dirección: ");
                String direccionEmpleado = sc.next();
                System.out.print("Ingrese teléfono: ");
                String telefonoEmpleado = sc.next();
                System.out.print("Ingrese cargo: ");
                String cargoEmpleado = sc.next();

                Persona empleado = persona.crearPersona("empleado", numeroIdentificacionEmpleado, nombreEmpleado, direccionEmpleado, telefonoEmpleado, cargoEmpleado, null);

                if (empleado != null) {
                    empleado.mostrarDetalles();
                }
                break;

            default:
                System.out.println("Opción no válida. Por favor elija 1 o 2.");
                break;
        }

        sc.close();

    }

    /*private void listarPrendas() {
        List<Prenda> prendas = prendaService.obtenerPrendas();
        for (Prenda prenda : prendas) {
            System.out.println(prenda);
        }
    }

    private void buscarPrenda() {
        System.out.print("Referencia de la prenda a buscar: ");
        String ref = scanner.nextLine();
        Prenda prenda = prendaService.buscarPrenda(ref);
        if (prenda != null) {
            System.out.println("Prenda encontrada: " + prenda);
        } else {
            System.out.println("Prenda no encontrada.");
        }
    }

    private void eliminarPrenda() {
        System.out.print("Referencia de la prenda a eliminar: ");
        String ref = scanner.nextLine();
        prendaService.eliminarPrenda(ref);
        System.out.println("Prenda eliminada si existía.");
    }*/
}
