package com.losAtuendos.utils.dao;

import java.sql.*;


public class DBManager {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String URL = "jdbc:mysql://localhost:3306/pruebaatuendos";

    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Driver JDBC no encontrado" + e.getMessage());
        } 
    }

    private static void sendErrorMessage(Exception e) {
        //JOptionPane.showMessageDialog(null, "ERROR EN EL DRIVER");
         System.out.println("Error: Driver JDBC no encontrado" + e.getMessage());
    }

    public Connection createConnection() {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("conexion exitosa base de datos");
        }  catch (SQLException e) {
            System.out.println("fallo a conexion base de datos");
        }

        return conn;
       
    
    }
}
