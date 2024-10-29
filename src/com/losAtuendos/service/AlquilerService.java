package com.losAtuendos.service;

import com.losAtuendos.models.Alquiler;
import com.losAtuendos.models.DetalleAlquiler;
import com.losAtuendos.repository.AlquilerRepository;
import com.losAtuendos.utils.dao.DBManager;
import java.time.LocalDate;
import java.util.List;


public class AlquilerService implements AlquilerRepository {
    
    DBManager db = new DBManager();

    @Override
    public boolean postAlquiler(Alquiler alquiler) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean postDetalleAlquiler(DetalleAlquiler detalleAlquiler) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getAlquilerPorNumeroAlquiler(int numeroServicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getAlquilerPorCliente(String refPrenda) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getAlquilerPorFecha(LocalDate fechaBusqueda) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
   
}