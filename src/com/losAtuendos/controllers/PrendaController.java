/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.controllers;

import com.losAtuendos.factory.PrendaFactory.PrendaConcreteFactory;
import com.losAtuendos.factory.PrendaFactory.PrendaFactoryAbstract;
import com.losAtuendos.models.Prenda;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class PrendaController {

    Scanner sc = new Scanner(System.in);

    public void registros() {
        boolean pedreria;
        boolean disponibleVestido;
        boolean disponibleTraje;
        boolean disponibleDisfraz;

        PrendaFactoryAbstract prenda = new PrendaConcreteFactory();

        System.out.print(" ---------- Bienvenido a registros de Prendas los Atuendos ---------- \n\n");
        System.out.println("Seleccione el tipo de persona a crear:");
        System.out.println("1. Vestido");
        System.out.println("2. Traje caballero");
        System.out.println("3. Disfraz");

        System.out.println("Ingrese una opción");
        while (!sc.hasNextInt()) {
            System.out.println("Valor inválido. Por favor, ingrese un valor numerico.");
            sc.nextLine();
        }
        int opc = sc.nextInt();
        sc.nextLine();

        switch (opc) {
            case 1:

                System.out.println("Ingrese referencia del vestido ");
                String referenciaVestido = sc.next();

                System.out.println("Ingrese color del vestido ");
                String colorVestido = sc.next();

                System.out.println("Ingrese marca del vestido ");
                String marcaVestido = sc.next();

                System.out.println("Ingrese talla del vestido ");
                String tallaVestido = sc.next();

                System.out.println("Ingrese valor alquiler del vestido");
                while (!sc.hasNextDouble()) {
                    System.out.println("Valor inválido. Por favor, ingrese un número decimal.");
                    sc.nextLine();
                }
                double valorAlquilerVestido = sc.nextDouble();
                sc.nextLine();

                System.out.println("Ingrese si está disponible: (Y / N):");
                disponibleVestido = "Y".equalsIgnoreCase(sc.next().toUpperCase());

                System.out.println("Tiene pedreria ? (Y / N)");
                pedreria = "Y".equalsIgnoreCase(sc.next().toUpperCase());

                System.out.println("Ingrese largo:");
                String largoVestido = sc.next();

                System.out.println("Ingresar la cantidad de piezas");
                while (!sc.hasNextInt()) {
                    System.out.println("Valor inválido. Por favor, ingrese un valor numerico.");
                    sc.nextLine();
                }
                int cantPiezas = sc.nextInt();
                sc.nextLine();

                Prenda vestido = prenda.crearPrenda("vestido", disponibleVestido, referenciaVestido, colorVestido, marcaVestido, tallaVestido, valorAlquilerVestido, pedreria, largoVestido, cantPiezas, null, null, null);

                if (vestido != null) {
                    vestido.mostrarDetalles();
                }

                break;

            case 2:
                System.out.println("Ingrese referencia del traje : ");
                String referenciaTraje = sc.next();
                System.out.print("Ingrese color del traje ");
                String colorTraje = sc.next();
                System.out.println("Ingrese marca del traje ");
                String marcaTraje = sc.next();
                System.out.println("Ingrese talla del traje ");
                String tallaTraje = sc.next();

                System.out.println("Ingrese valor alquiler del traje");
                while (!sc.hasNextDouble()) {
                    System.out.println("Valor inválido. Por favor, ingrese un número decimal.");
                    sc.nextLine();
                }
                double valorAlquilerTraje = sc.nextDouble();
                sc.nextLine();

                System.out.println("Ingrese el tipo de traje");
                String tipoTraje = sc.next();
                System.out.println("Ingrese el accesorio");
                String accesorioTraje = sc.next();
                System.out.println("Ingrese si está diponible: (Y / N):");
                disponibleTraje = "Y".equalsIgnoreCase(sc.next().toUpperCase());

                Prenda traje = prenda.crearPrenda("traje", disponibleTraje, referenciaTraje, colorTraje, marcaTraje, tallaTraje, valorAlquilerTraje, false, null, 0, tipoTraje, accesorioTraje, null);

                if (traje != null) {
                    traje.mostrarDetalles();
                }
                break;

            case 3:
                System.out.println("Ingrese referencia del disfraz : ");
                String referenciaDisfraz = sc.next();

                System.out.print("Ingrese color del traje ");
                String colorDisfraz = sc.next();

                System.out.println("Ingrese marca del disfraz ");
                String marcaDisfraz = sc.next();

                System.out.println("Ingrese talla del disfraz ");
                String tallaDisfraz = sc.next();

                System.out.println("Ingrese valor alquiler del disfraz");
                while (!sc.hasNextDouble()) {
                    System.out.println("Valor inválido. Por favor, ingrese un número decimal.");
                    sc.nextLine();
                }
                double valorAlquilerDisfraz = sc.nextDouble();
                sc.nextLine();

                System.out.println("Ingrese si está diponible: (Y / N):");
                disponibleDisfraz = "Y".equalsIgnoreCase(sc.next().toUpperCase());

                System.out.println("Ingrese nombre del disfraz");
                String nombreDisfraz = sc.next();

                Prenda disfraz = prenda.crearPrenda("disfraz", disponibleDisfraz, referenciaDisfraz, colorDisfraz, marcaDisfraz, tallaDisfraz, valorAlquilerDisfraz, false, null, 0, null, null, nombreDisfraz);

                if (disfraz != null) {
                    disfraz.mostrarDetalles();
                }
                break;

            default:
                System.out.println("Opción no válida. Por favor marque 1, 2 o 3.");
                break;
        }

        sc.close();

    }

}
