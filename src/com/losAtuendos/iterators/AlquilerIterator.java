/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.iterators;

import com.losAtuendos.models.Alquiler;
import java.util.List;

/**
 *
 * @author yanyg
 */
public class AlquilerIterator implements Iterator<Alquiler>{
    private List<Alquiler> alquileres;
    private int position = 0;
    
    public AlquilerIterator(List<Alquiler> alquileres){
        this.alquileres = alquileres;
    }
    @Override
    public boolean hasNext(){
        return position < alquileres.size();
    }

    @Override
    public Alquiler next() {
        if(!hasNext()){
            throw new IllegalStateException("No hay mas alquileres");
        }
        return alquileres.get(position++);
    }
    
    
    
    
}
