/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.iterators;

import com.losAtuendos.models.Alquiler;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yanyg
 */
public class AlquilerAggregate implements Aggregate<Alquiler>{
    private List<Alquiler> alquileres = new ArrayList<>();
    
    public void add(Alquiler alquiler){
        alquileres.add(alquiler);
    }
    public Alquiler get(int index){
        return alquileres.get(index);
    }
    public int size(){
        return alquileres.size();
    }

    @Override
    public Iterator<Alquiler> createIterator() {
        return new AlquilerIterator(alquileres);
    }

    public List<Alquiler> getList() {
        return new ArrayList<>(alquileres);
    }

      
}
