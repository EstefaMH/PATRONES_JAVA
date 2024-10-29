/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.repository;

import com.losAtuendos.models.Disfraz;
import com.losAtuendos.models.Prenda;
import com.losAtuendos.models.TrajeCaballero;
import com.losAtuendos.models.VestidoDama;
import java.util.List;

public interface PrendaRepository {

    boolean postPrenda(Prenda prenda);

    boolean postVestidoDama(VestidoDama vestido);

    boolean postTrajeCaballero(TrajeCaballero traje);

    boolean postDisfraz(Disfraz disfraz);

    List getPrendasByTalla();
    
    void registroPrendasParaLavanderia(String ref, int prioridad);
       

}
