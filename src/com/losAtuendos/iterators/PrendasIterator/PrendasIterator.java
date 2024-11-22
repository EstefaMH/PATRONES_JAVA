/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.iterators.PrendasIterator;

import com.losAtuendos.iterators.Iterator;
import com.losAtuendos.models.Prenda;
import java.util.List;

/**
 *
 * @author USER
 */
public class PrendasIterator implements Iterator<Prenda> {

    private List<Prenda> prendas ;
    private int index = 0;

    PrendasIterator(List<Prenda> prendas) {
        this.prendas = prendas;
    }

    @Override
    public boolean hasNext() {
        return index< prendas.size();
    }

    @Override
    public Prenda next() {
         if(!hasNext()){
            throw new IllegalStateException("No hay mas prendas");
        }
        return prendas.get(index++);
    }

}
