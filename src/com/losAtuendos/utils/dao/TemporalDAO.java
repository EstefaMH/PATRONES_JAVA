package com.losAtuendos.utils.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TemporalDAO {

     public static boolean validarIdCliente(String clienteIdIngresado) {

        DBManager db = new DBManager();
        boolean flag = false;
        String sqlQuery = "SELECT cl.persona_id FROM cliente cl WHERE cl.persona_id = ?";

        try {
            PreparedStatement stmt = db.createConnection().prepareStatement(sqlQuery);

            stmt.setString(1, clienteIdIngresado);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String id_BaseDeDatos = rs.getString("persona_id");
                if (id_BaseDeDatos.equalsIgnoreCase(clienteIdIngresado)) {
                    System.out.println("Cliente registrado");
                    flag = true;
                } else {
                    System.out.println("Error: el cliente no está registrado");
                    flag = false;
                }
            } else {
                System.out.println("Error: La base de datos no arroja ningún resultado");
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean validarIdEmpleado(String empleadoIdIngresado) {
        DBManager db = new DBManager();
        boolean flag = false;
        String sqlQuery = "SELECT em.persona_id FROM empleado em WHERE em.persona_id = ?";

        try {
            PreparedStatement stmt = db.createConnection().prepareStatement(sqlQuery);

            stmt.setString(1, empleadoIdIngresado);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String id_BaseDeDatos = rs.getString("persona_id");
                if (id_BaseDeDatos.equalsIgnoreCase(empleadoIdIngresado)) {
                    System.out.println("Cliente registrado");
                    flag = true;
                } else {
                    System.out.println("Error: el cliente no está registrado");
                    flag = false;
                }
            } else {
                System.out.println("Error: La base de datos no arroja ningún resultado");
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    public static boolean validarIdPrenda(String prendaIdPrenda) {
        DBManager db = new DBManager();
        boolean flag = false;
        String sqlQuery = "SELECT ref FROM prenda WHERE ref = ?";

        try {
            PreparedStatement stmt = db.createConnection().prepareStatement(sqlQuery);

            stmt.setString(1, prendaIdPrenda);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String id_BaseDeDatos = rs.getString("persona_id");
                if (id_BaseDeDatos.equalsIgnoreCase(prendaIdPrenda)) {
                    System.out.println("Prenda registrada");
                    flag = true;
                } else {
                    System.out.println("Error:La prenda no está registrado");
                    flag = false;
                }
            } else {
                System.out.println("Error: La base de datos no arroja ningún resultado");
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}