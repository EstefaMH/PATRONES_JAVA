package com.losAtuendos;

import com.losAtuendos.controllers.PersonaController;
import com.losAtuendos.controllers.PrendaController;
import com.losAtuendos.service.PrendaService;
import com.losAtuendos.utils.dao.DBManager;
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
                        PersonaController personaController = new PersonaController();
                        personaController.registros();
                        break;

                    case 2:
                        PrendaController prendaController = new PrendaController();
                        prendaController.registros();
                        break;

                    case 3:
                        //Alquiler controler método para registrar alquiler
                        PrendaService prenda = new PrendaService();
                        prenda.getPrendasByTalla();
                        break;

                    case 4:
                        //Lavado controler método para registrar en lista de lavado
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
                        //Lavado controler método para enviar prendas a lavado según la lista
                        break;

                    case 0:
                        menuSystem();
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

                switch (opcTwo) {
                    case 1:

                        break;

                    case 2:

                        break;

                    case 3:

                        break;

                    case 4:

                        break;

                    case 5:

                        break;

                    case 6:

                        break;

                    case 7:

                        break;

                    case 8:

                        break;

                    case 9:

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
