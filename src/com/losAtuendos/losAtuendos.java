package com.losAtuendos;

import com.losAtuendos.controllers.AlquilerController;
import com.losAtuendos.controllers.PersonaController;
import com.losAtuendos.controllers.PrendaController;
import com.losAtuendos.service.AlquilerService;
import com.losAtuendos.service.PrendaService;
import com.losAtuendos.service.PersonaService;
import com.losAtuendos.utils.dao.DBManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class losAtuendos {

    public static void main(String[] args) {

        DBManager db = new DBManager();
        db.createConnection();
        menuSystem();

    }

    private static void menuSystem() {

        Scanner sc = new Scanner(System.in);

        System.out.println("---------- Bienvenido al Sistema de Alquiler ---------");
        System.out.println("Escriba el número de la opción que desea:");
        System.out.println("1. Registros");
        System.out.println("2. Consultas");
        System.out.println("3. Salir");

        while (!sc.hasNextInt()) {
            System.out.println("Valor inválido. Por favor, ingrese un valor numerico.");
            sc.nextLine();
        }
        int opc = sc.nextInt();
        sc.nextLine();

        switch (opc) {

            case 1:
                System.out.println("Marque el numero que corresponde a la opcion que desea registrar: ");
                System.out.println("1. Personas");
                System.out.println("2. Prendas");
                System.out.println("3. Alquileres");
                System.out.println("4. Lavado");
                System.out.println("5. Envio a lavado");
                System.out.println("Marque cero para volver al menu principal");

                while (!sc.hasNextInt()) {
                    System.out.println("Valor inválido. Por favor, ingrese un valor numerico.");
                    sc.nextLine();
                }
                int opcOne = sc.nextInt();
                sc.nextLine();

                switch (opcOne) {
                    case 1:
                        //Registrar personas
                        PersonaController personaController = new PersonaController();
                        personaController.registros();
                        break;

                    case 2:
                        //Registrar prendas
                        PrendaController prendaController = new PrendaController();
                        prendaController.registros();
                        break;

                    case 3:
                        //Registrar alquileres
                        AlquilerController alquilerController = new AlquilerController();
                        alquilerController.registros();
                        break;

                    case 4:
                        //Registrar en lista de lavado
                        PrendaService prendaLavanderia = new PrendaService();
                        System.out.println("ingrese la referencia de la prenda");
                        String ref = sc.next();
                        System.out.println("Ingrese la prioridad ( 0 o 1 ) ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Valor inválido. Por favor, ingrese un valor numerico.");
                            sc.nextLine();
                        }
                        int prioridad = sc.nextInt();
                        prendaLavanderia.registroPrendasParaLavanderia(ref, prioridad);
                        break;

                    case 5:
                        //Enviar prendas a lavado según la lista
                        PrendaService prendasLavado = new PrendaService();
                        System.out.println("Ingrese la cantidad de prendas que desea enviar a lavado ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Valor inválido. Por favor, ingrese un valor numerico.");
                            sc.nextLine();
                        }
                        int lavado = sc.nextInt();
                        prendasLavado.envioPrendasParaLavanderia(lavado);
                        break;

                    case 0:
                        break;

                    default:
                        System.out.println("Opción no válida. Por favor marque un número válido");
                        break;
                }
                break;

            case 2:
                System.out.println("Marque el numero que corresponde a la opcion que desea consultar: ");
                System.out.println("1. Alquiler según el número");
                System.out.println("2. Alquiler según el cliente");
                System.out.println("3. Alquiler según la fecha");
                System.out.println("4. Prendas por talla");
                System.out.println("5. Prendas por referencia");
                System.out.println("6. Prendas disponibles");
                System.out.println("7. Lista de lavado");
                System.out.println("8. Clientes Registrados");
                System.out.println("9. Empleados Registrados");
                System.out.println("Marque cero para volver al menu principal");
                while (!sc.hasNextInt()) {
                    System.out.println("Valor inválido. Por favor, ingrese un valor numerico.");
                    sc.nextLine();
                }
                int opcTwo = sc.nextInt();
                sc.nextLine();

                PrendaService prenda = new PrendaService();
                PersonaService persona = new PersonaService();
                AlquilerService alquilerService = new AlquilerService();
                switch (opcTwo) {
                    case 1:
                        //Consultar alquiler por número                       
                        System.out.print("\nIngrese el número de alquiler: ");
                        int idIngresado2 = Integer.parseInt(sc.nextLine());
                        alquilerService.getAlquilerPorNumeroAlquiler(idIngresado2);
                        break;

                    case 2:
                        //Consultar alquiler por cliente
                        System.out.print("\nIngrese el id del cliente: ");
                        String idCliente = sc.nextLine();

                        alquilerService.getAlquilerPorCliente(idCliente);
                        break;

                    case 3:
                        //Consultar alquiler por fecha
                        LocalDate fecha = LocalDate.now();
                        System.out.print("\nIngrese la fecha de alquiler: ");
                        String fechaString = sc.nextLine();
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        try {
                            fecha = LocalDate.parse(fechaString, formatter);
                            alquilerService.getAlquilerPorFecha(fecha);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido. Debe ser AAAA-MM-DD");
                        }
                        break;

                    case 4:
                        //Consultar prendas por talla

                        System.out.println("Tngrese la talla que desea consultar (10, 12, 14, S, M, L, XL, XXL):");
                        String tallaConsulta = sc.nextLine();
                        prenda.getPrendasByTalla(tallaConsulta);

                        break;

                    case 5:
                        //Consultar prendas por referencia
                        System.out.println("Tngrese la referencia que desea consultar:");
                        String refConsulta = sc.nextLine();
                        prenda.getPrendasByReferencia(refConsulta);
                        break;

                    case 6:
                        //Consultar prendas disponibles
                        System.out.println("Por corregir");
                        System.out.println("Ingrese Y para disponibles y N para No disponibles: (Y / N):");
                        boolean disponiblesConsulta = "Y".equalsIgnoreCase(sc.next().toUpperCase());
                        prenda.getPrendasByDisponibilidad(disponiblesConsulta);
                        break;

                    case 7:
                        //Consultar lista de lavado
                        PrendaService prendaLavanderia = new PrendaService();
                        System.out.println("La lista de lavado actualmente tiene las siguientes entradas: ");
                        prendaLavanderia.getPrendasParaLavanderia();
                        break;

                    case 8:
                        //Consultar clientes registrados
                        System.out.println("La lista de clientes actualmente tiene las siguientes entradas: ");
                        persona.getAllClientes();
                        break;

                    case 9:
                        //Consultar empleados registrados
                        System.out.println("La lista de empleados actualmente tiene las siguientes entradas: ");
                        persona.getAllClientes();
                        break;
                    case 0:
                        menuSystem();
                        break;

                    default:
                        System.out.println("Opción no válida. Por favor marque un número válido");
                        break;

                }

            case 3:
                System.exit(0);
        }
    }
}
