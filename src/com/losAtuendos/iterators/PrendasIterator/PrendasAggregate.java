/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.iterators.PrendasIterator;

import com.losAtuendos.iterators.Aggregate;
import com.losAtuendos.iterators.Iterator;
import com.losAtuendos.models.Prenda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author USER
 */
public class PrendasAggregate implements Aggregate<Prenda> {

    private List<Prenda> prendas = new ArrayList<>();

    public void add(Prenda prenda) {
        prendas.add(prenda);
    }

    public Prenda get(int index) {
        return prendas.get(index);
    }

    public int size() {
        return prendas.size();
    }

    public List<Prenda> getList() {
        return prendas;
    }

    public void printPrendasPorTipo() {
        Map<String, List<Prenda>> prendasPorTipo = new HashMap<>();
        Iterator<Prenda> iterator = createIterator();

        while (iterator.hasNext()) {
            Prenda prenda = iterator.next();
            String tipo = prenda.getTipo();
            prendasPorTipo.putIfAbsent(tipo, new ArrayList<>());
            prendasPorTipo.get(tipo).add(prenda);

        }

        for (Map.Entry<String, List<Prenda>> entry : prendasPorTipo.entrySet()) {
            String tipo = entry.getKey();
            List<Prenda> prendas = entry.getValue();

            System.out.println("Prendas de tipo '" + tipo + "':");
            for (Prenda prenda : prendas) {
                System.out.println(" - " + prenda);
            }
            System.out.println();
        }

    }

    @Override
    public Iterator<Prenda> createIterator() {
        return new PrendasIterator(prendas);
    }

}
