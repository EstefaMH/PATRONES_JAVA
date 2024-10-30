package com.losAtuendos.controllers;

import com.losAtuendos.factory.AlquilerFactory.AlquilerConcreteFactory;
import com.losAtuendos.factory.AlquilerFactory.AlquilerFactoryAbstract;
import com.losAtuendos.models.Alquiler;
import com.losAtuendos.models.DetalleAlquiler;
import com.losAtuendos.service.AlquilerService;
import com.losAtuendos.service.facade.ServicioAlquilerFacadeImpl;
import com.losAtuendos.service.facade.ServicioAlquilerI;
import com.losAtuendos.utils.dao.TemporalDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlquilerController {
    private ServicioAlquilerFacadeImpl servicioAlquilerFacade;
    
    Scanner sc = new Scanner(System.in);
    String clienteIdIngresado;
    String empleadoIdIngresado;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate fechaRetiro = null;
    LocalDate fechaDevolucion = null;
    int numeroPrendasParaRegistro;
    String referenciaPrendaFor;
    
     public AlquilerController() {
        // Crear una instancia de ServicioAlquilerFacadeImpl con un AlquilerService predeterminado
        this.servicioAlquilerFacade = new ServicioAlquilerFacadeImpl(new AlquilerService());
    }

    // Constructor con el parámetro de servicio para mayor flexibilidad
    public AlquilerController(ServicioAlquilerFacadeImpl servicioAlquilerFacade) {
        this.servicioAlquilerFacade = servicioAlquilerFacade;
    }
    //AlquilerFactoryAbstract alquiler = new AlquilerConcreteFactory();
    //   AlquilerService alquilerservice = new AlquilerService();
    // Alquiler detalleAlquiler = alquiler.crearAlquiler(numeroPrendasParaRegistro, cliente, empleado, LocalDate.EPOCH, LocalDate.MIN, LocalDate.MAX, clienteIdIngresado);
    // Prenda vestido = prenda.crearPrenda("vestido", disponibleVestido, referenciaVestido, colorVestido, marcaVestido, tallaVestido, valorAlquilerVestido, pedreria, largoVestido, cantPiezas, null, null, null);
    public void registros() {

        System.out.print(" ---------- Bienvenido al manejo de Alquileres ---------- \n\n");
        System.out.println("Escriba el número de la opción que desea:");
        System.out.println("1. Registros");
        System.out.println("2. Consultas");
        
        System.out.println("Ingrese una opción");
        while (!sc.hasNextInt()) {
            System.out.println("Valor inválido. Por favor, ingrese un valor numerico.");
            sc.nextLine();
        }
        int opc = sc.nextInt();
        sc.nextLine();

        switch (opc) {
            case 1:
        //Pedir cliente_id
        do {
            System.out.print("\nIngrese el número de identificación del cliente: ");
            clienteIdIngresado = sc.nextLine();
            //llamar método que busque y valide si existe Cliente DAO
            //} while (!AlquilerService.validarIdCliente(clienteIdIngresado));
        } while (!TemporalDAO.validarIdCliente(clienteIdIngresado));

        //Pedir empleado_id
        do {
            System.out.print("\nIngrese el número de identificación del empleado: ");
            empleadoIdIngresado = sc.nextLine();
            //llamar método que busque y valide si existe Empleado DAO
            //} while (!AlquilerService.validarIdEmpleado(empleadoIdIngresado));
        } while (!TemporalDAO.validarIdEmpleado(empleadoIdIngresado));

        //Pedir fecha de alquiler
        do {
            System.out.print("\nIngrese la fecha de retiro (AAAA-MM-DD): ");
            String fechaIngresadaRetiro = sc.nextLine();
            try {
                fechaRetiro = LocalDate.parse(fechaIngresadaRetiro, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Por favor, ingrese la fecha en el formato correcto.");
            }
        } while (fechaRetiro == null);

        //Pedir fecha de entrega
        do {
            System.out.print("\nIngrese la fecha de devolucion (AAAA-MM-DD): ");
            String fechaIngresadaDevolucion = sc.nextLine();
            try {
                fechaDevolucion = LocalDate.parse(fechaIngresadaDevolucion, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Por favor, ingrese la fecha en el formato correcto.");
            }
        } while (fechaDevolucion == null);
        System.out.println("\nIngrese el número de prendas a registrar");
        while (!sc.hasNextInt()) {
            System.out.println("Valor inválido. Por favor, ingrese un valor numerico.");
            sc.nextLine();
        }
        numeroPrendasParaRegistro = sc.nextInt();
        
        System.out.println("Se van a registrar " + numeroPrendasParaRegistro + " prendas");
       List<String> prendas= new ArrayList<>();
       for (int i = 0; i < numeroPrendasParaRegistro; i++) {
        String referenciaPrenda = sc.nextLine();
            do {
                System.out.println("Ingrese referencia de la prenda " + (i + 1));
                referenciaPrenda = sc.nextLine();
            } while (!TemporalDAO.validarIdPrenda(referenciaPrenda));

            prendas.add(referenciaPrenda);
        }

        System.out.println("Prendas registradas: "+prendas);
        //porfin llamo el facade y le paso todos los datos
        servicioAlquilerFacade.registrarAlquiler(0000, clienteIdIngresado, empleadoIdIngresado, LocalDate.now(), fechaRetiro, fechaDevolucion, prendas);
        
        
        System.out.println("Se van a registrar los siguientes datos: ");
        System.out.println("Cliente: " + clienteIdIngresado);
        System.out.println("Empleado: " + empleadoIdIngresado);
        System.out.println("Fecha de retiro: " + fechaRetiro);
        System.out.println("Fecha de devolución: " + fechaDevolucion);
        System.out.println("Número de prendas: " + numeroPrendasParaRegistro);

        System.out.println("Se ha creado con éxito la solicitud numero " + " con fecha de devolucion: " + fechaDevolucion);
    break;
            case 2:
                
        System.out.println("1. Consulta por numero de alquiler");
        System.out.println("2. Consulta por id de cliente");
        System.out.println("2. Consulta por fecha de alquiler");
        
        System.out.println("Ingrese una opción");
        while (!sc.hasNextInt()) {
            System.out.println("Valor inválido. Por favor, ingrese un valor numerico.");
            sc.nextLine();
        }
        int opc1 = sc.nextInt();
        sc.nextLine();

        switch (opc1) {
            case 1:
            System.out.print("\nIngrese el número de alquiler: ");
            clienteIdIngresado = sc.nextLine();
            servicioAlquilerFacade.registrarAlquiler(0000, clienteIdIngresado, empleadoIdIngresado, LocalDate.now(), fechaRetiro, fechaDevolucion, prendas);
               break;
        }
                
    }
    }
}