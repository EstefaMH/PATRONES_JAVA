package com.losAtuendos.repository;

import com.losAtuendos.models.Disfraz;
import com.losAtuendos.models.Prenda;
import com.losAtuendos.models.TrajeCaballero;
import com.losAtuendos.models.VestidoDama;
import java.util.List;
import java.util.Map;

public interface PrendaRepository {

    boolean postPrenda(Prenda prenda);

    boolean postVestidoDama(VestidoDama vestido);

    boolean postTrajeCaballero(TrajeCaballero traje);

    boolean postDisfraz(Disfraz disfraz);
    
    void registroPrendasParaLavanderia(String ref, int prioridad);
       

    List getPrendasByTalla(String tallaPrenda);

    List getPrendasByReferencia(String refPrenda);

    List getPrendasByDisponibilidad(boolean disponiblePrenda);
    
    Map<String, Boolean> getPrendasParaLavanderia(); 
    
    void envioPrendasParaLavanderia(int cantidadAEliminar);
        
 }
