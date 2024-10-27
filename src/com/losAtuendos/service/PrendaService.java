/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.losAtuendos.service;

import com.losAtuendos.factory.PrendaFactory.PrendaConcreteFactory;
import com.losAtuendos.factory.PrendaFactory.PrendaFactoryAbstract;
import com.losAtuendos.models.Disfraz;
import com.losAtuendos.models.Prenda;
import com.losAtuendos.models.TrajeCaballero;
import com.losAtuendos.models.VestidoDama;
import com.losAtuendos.repository.PrendaRepository;
import com.losAtuendos.utils.dao.DBManager;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class PrendaService implements PrendaRepository {

    DBManager db = new DBManager();

    @Override
    public boolean postPrenda(Prenda prenda) {
        String sqlInsert = "INSERT INTO prenda ( ref, color, marca, talla, valorAlquiler, tipo) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert);
            pstmt.setString(1, prenda.getReferencia());
            pstmt.setString(2, prenda.getColor());
            pstmt.setString(3, prenda.getMarca());
            pstmt.setString(4, prenda.getTalla());
            pstmt.setDouble(5, prenda.getValorAlquiler());
            pstmt.setString(6, prenda.getTipo());

            System.out.println("The SQL statement is: " + sqlInsert + "\n");

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted < 0) {
                return false;
            }

            System.out.println("Nuevo registro prenda insertado correctamente.");
            return true;

        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("Error el ID ya exise");
            throw new Error("El Id ya existe");

        } catch (SQLException ex) {
            Logger.getLogger(com.losAtuendos.models.Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
            return false;
        }
    }

    @Override
    public boolean postVestidoDama(VestidoDama vestido) {

        this.postPrenda(vestido);

        String sqlInsert = "INSERT INTO vestidodama (prenda_ref,pedreria,altura,cantPiezas) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert);
            pstmt.setString(1, vestido.getReferencia());
            pstmt.setBoolean(2, vestido.isPedreria());
            pstmt.setString(3, vestido.getLargo());
            pstmt.setInt(4, vestido.getCantPiezas());

            System.out.println("The SQL statement is: " + sqlInsert + "\n");

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted < 0) {
                return false;
            }

            System.out.println("Nuevo registro vestido de dama insertado correctamente.");
            return true;

        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("Error el ID ya exise");
            throw new Error("El Id ya existe");

        } catch (SQLException ex) {
            Logger.getLogger(com.losAtuendos.models.Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
            return false;
        }
    }

    @Override
    public boolean postTrajeCaballero(TrajeCaballero traje) {

        this.postPrenda(traje);

        String sqlInsert = "INSERT INTO prenda (prenda_ref, tipo, aderezo) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert);
            pstmt.setString(1, traje.getReferencia());
            pstmt.setString(2, traje.getTipoTraje());
            pstmt.setString(3, traje.getAccesorio());

            System.out.println("The SQL statement is: " + sqlInsert + "\n");

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted < 0) {
                return false;
            }

            System.out.println("Nuevo registro traje caballero insertado correctamente.");
            return true;

        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("Error el ID ya exise");
            throw new Error("El Id ya existe");

        } catch (SQLException ex) {
            Logger.getLogger(com.losAtuendos.models.Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
            return false;
        }
    }

    @Override
    public boolean postDisfraz(Disfraz disfraz) {

        this.postPrenda(disfraz);
        String sqlInsert = "INSERT INTO disfraz (prenda_ref, nombre) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert);
            pstmt.setString(1, disfraz.getReferencia());
            pstmt.setString(2, disfraz.getNombreDisfraz());

            System.out.println("The SQL statement is: " + sqlInsert + "\n");

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted < 0) {
                return false;
            }

            System.out.println("Nuevo registro disfraz insertado correctamente.");
            return true;

        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("Error el ID ya exise");
            throw new Error("El Id ya existe");

        } catch (SQLException ex) {
            Logger.getLogger(com.losAtuendos.models.Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
            return false;
        }
    }

    @Override
    public List getPrendasByTalla() {
        List listaPrendas = new ArrayList<>();
        PrendaFactoryAbstract prenda = new PrendaConcreteFactory();
        String sql = "SELECT  p.ref, p.color, p.marca, p.talla, p.valorAlquiler, p.tipo,d.pedreria, d.cantPiezas,tc.tipo AS tipoTraje, tc.aderezo, di.nombre FROM Prenda p LEFT JOIN VestidoDama d ON p.ref = d.prenda_ref LEFT JOIN TrajeCaballero tc ON p.ref = tc.prenda_ref LEFT JOIN Disfraz di ON p.ref = di.prenda_ref WHERE p.talla = 's'";

        try {
            PreparedStatement stmt = db.createConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                String ref = rs.getString("ref");
                String color = rs.getString("color");               
                String marca = rs.getString("marca");
                String talla = rs.getString("talla");
                double valorAlquiler = rs.getDouble("valorAlquiler");
                boolean pedreria = rs.getBoolean("pedreria");
                String largo = "";
                int cantPiezas = rs.getInt("cantPiezas");
                String tipoTraje = rs.getString("tipoTraje");
                String accesorios = "";
                String nombreDisfraz = rs.getString("nombre");
                
                System.out.println("Referencia: " + ref + ", Color: " + color + "tipo : " + tipo);
                listaPrendas = prenda.obtenerPrendasPorTalla(tipo, ref, color, marca, talla, valorAlquiler , pedreria, largo,cantPiezas, tipoTraje, accesorios, nombreDisfraz);
                System.out.println("lista de prendas" + listaPrendas);
                
                
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaPrendas;
    }
}
