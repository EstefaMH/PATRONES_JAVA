package com.losAtuendos.factory.PrendaFactory;

import com.losAtuendos.models.Disfraz;
import com.losAtuendos.models.Prenda;
import com.losAtuendos.models.TrajeCaballero;
import com.losAtuendos.models.VestidoDama;
import com.losAtuendos.service.PrendaService;
import java.util.ArrayList;
import java.util.List;

public class PrendaConcreteFactory extends PrendaFactoryAbstract {

    private final PrendaService prendaService;

    public PrendaConcreteFactory() {
        this.prendaService = new PrendaService();
    }

    @Override
    public Prenda crearPrenda(String tipo, boolean disponible, String ref, String color, String marca, String talla, double valorAlquiler, boolean pedreria, String largo, int cantPiezas, String tipoTraje, String accesorio, String nombreDisfraz) {
        Prenda prenda = null;

        switch (tipo.toLowerCase()) {
            case "vestido":

                prenda = new VestidoDama(ref, color, marca, talla, valorAlquiler, tipo, disponible, pedreria, largo, cantPiezas);

                if (!prenda.validarDatos()) {
                    System.out.println("Error al crear el vestido datos no válidos.");
                    return null;
                }

                boolean createVestido = prendaService.postVestidoDama((VestidoDama) prenda);
                System.out.println("create prenda " + createVestido);

                break;
            case "traje":
                prenda = new TrajeCaballero(ref, color, marca, talla, valorAlquiler, tipo, disponible, tipoTraje, accesorio);

                if (!prenda.validarDatos()) {
                    System.out.println("Error al crear el traje de caballero datos no válidos.");
                    return null;
                }

                boolean createTraje = prendaService.postPrenda((TrajeCaballero) prenda);
                System.out.println("create prenda " + createTraje);

                break;
            case "disfraz":
                prenda = new Disfraz(ref, color, marca, talla, valorAlquiler, tipo, disponible, nombreDisfraz);

                if (!prenda.validarDatos()) {
                    System.out.println("Error al crear el disfraz datos no válidos.");
                    return null;
                }

                boolean createDisfraz = prendaService.postDisfraz((Disfraz) prenda);
                System.out.println("create prenda " + createDisfraz);

                break;
            default:
                System.out.println("Tipo de prenda no válido.");
                return null;
        }

        return prenda;
    }

    @Override
    public List obtenerPrendasPorTalla(String tipo, String ref, boolean disponible, String color, String marca, String talla, double valorAlquiler, boolean pedreria, String largo, int cantPiezas, String tipoTraje, String accesorio, String nombreDisfraz) {
        Prenda prenda = null;
        List listaPrendas = new ArrayList<>();
        List vestidoDamalist = new ArrayList<VestidoDama>();

        switch (tipo.toLowerCase()) {
            case "vestido":

                prenda = new VestidoDama(ref, color, marca, talla, valorAlquiler, tipo, disponible, pedreria, largo, cantPiezas);
                System.out.println("obtener vestido dama" + prenda.toString());
                listaPrendas.add(prenda);
                vestidoDamalist.add(prenda);
                break;
            case "traje":
                prenda = new TrajeCaballero(ref, color, marca, talla, valorAlquiler, tipo, disponible, tipoTraje, accesorio);
                listaPrendas.add(prenda);
                System.out.println("obtener traje" + prenda.toString());
                break;
            case "disfraz":
                prenda = new Disfraz(ref, color, marca, talla, valorAlquiler, tipo, disponible, nombreDisfraz);
                listaPrendas.add(prenda);
                System.out.println("obtener vestido disfraz" + prenda.toString());
                break;
            default:
                System.out.println("Tipo de prenda no válido.");
                return null;
        }

        return listaPrendas;
    }

}
