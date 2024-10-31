package com.losAtuendos.service;

import com.losAtuendos.factory.AlquilerFactory.AlquilerConcreteFactory;
import com.losAtuendos.factory.AlquilerFactory.AlquilerFactoryAbstract;
import com.losAtuendos.models.Alquiler;
import com.losAtuendos.models.DetalleAlquiler;
import com.losAtuendos.repository.AlquilerRepository;
import com.losAtuendos.service.facade.ServicioAlquilerI;
import com.losAtuendos.utils.dao.DBManager;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AlquilerService implements AlquilerRepository,ServicioAlquilerI {

    private DBManager db = new DBManager();

    @Override
    public int postAlquiler(Alquiler alquiler) {
    String sqlInsert = "INSERT INTO servicioAlquiler (fechaSolicitud, fechaAlquiler, fechaEntrega, cliente_id, empleado_id) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
    
            pstmt.setDate(1,  Date.valueOf(alquiler.getFechaSolicitud()));
            pstmt.setDate(2, Date.valueOf(alquiler.getFechaAlquiler()));
            pstmt.setDate(3, Date.valueOf(alquiler.getFechaRegreso()));
            pstmt.setString(4, alquiler.getCliente());
            pstmt.setString(5, alquiler.getEmpleado());

            System.out.println("The SQL statement is: " + sqlInsert + "\n");

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Retorna el ID generado
            }
            System.out.println("Nuevo registro alquiler insertado correctamente.");
            
        }
        throw new SQLException("Error al obtener el ID del nuevo alquiler");
        } catch (SQLException ex) {
            Logger.getLogger(AlquilerService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public boolean postDetalleAlquiler(DetalleAlquiler detalleAlquiler) {
        String sqlInsert = "INSERT INTO detalleServicioAlquiler (servicio_alquiler_id, prenda_ref) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert);
            
            pstmt.setInt(1, detalleAlquiler.getNumeroAlquiler());
            pstmt.setString(2, detalleAlquiler.getRef());

            System.out.println("The SQL statement is: " + sqlInsert + "\n");

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted < 0) {
                return false;
            }

            System.out.println("Nuevo registro detalle de alquiler insertado correctamente.");
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
    public List<Alquiler> getAlquilerPorNumeroAlquiler(int numeroServicio) {
    String sqlQuery = "SELECT * FROM servicioAlquiler WHERE numero = ?";
    List<Alquiler> alquileres = new ArrayList<>();

    try {
        PreparedStatement pstmt = db.createConnection().prepareStatement(sqlQuery);
        pstmt.setInt(1, numeroServicio); // Corregir el índice a 1

        ResultSet rs = pstmt.executeQuery();
        AlquilerFactoryAbstract alquiler = new AlquilerConcreteFactory();


        while (rs.next()) {
            // Asumiendo que tienes un constructor o método para crear un Alquiler a partir del ResultSet
            int numeroAlquiler = rs.getInt("numeroAlquiler");
            String cliente = rs.getString("idCliente");
            String empleado = rs.getString("idEmpleado");
            LocalDate fechaSolicitud = rs.getDate("fechaSolicitud").toLocalDate();
            LocalDate fechaAlquiler = rs.getDate("fechaAlquiler").toLocalDate();
            LocalDate fechaRegreso = rs.getDate("fechaRegreso").toLocalDate();

           Alquiler alquilerClass = new Alquiler(numeroAlquiler,cliente, empleado, fechaSolicitud, fechaAlquiler, fechaRegreso);
           alquileres.add(alquilerClass);
        }

        System.out.println("Consulta de alquiler por número de servicio completada correctamente.");
        return alquileres;

    } catch (SQLException ex) {
        Logger.getLogger(Alquiler.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Error al realizar la consulta de alquiler.");
        return alquileres; // Retornar la lista vacía en caso de error
    }
}

    @Override
    public List<Alquiler> getAlquilerPorCliente(String idCliente) {
        String sqlQuery = "SELECT * FROM servicioAlquiler WHERE cliente_id = ? ORDER BY fechaAlquiler ASC";
        List<Alquiler> alquileres = new ArrayList<>();

    try {
        PreparedStatement pstmt = db.createConnection().prepareStatement(sqlQuery);
        pstmt.setString(1, idCliente); // Corregir el índice a 1

        ResultSet rs = pstmt.executeQuery();
        AlquilerFactoryAbstract alquiler = new AlquilerConcreteFactory();


        while (rs.next()) {
            // Asumiendo que tienes un constructor o método para crear un Alquiler a partir del ResultSet
            int numeroAlquiler = rs.getInt("numeroAlquiler");
            String cliente = rs.getString("idCliente");
            String empleado = rs.getString("idEmpleado");
            LocalDate fechaSolicitud = rs.getDate("fechaSolicitud").toLocalDate();
            LocalDate fechaAlquiler = rs.getDate("fechaAlquiler").toLocalDate();
            LocalDate fechaRegreso = rs.getDate("fechaRegreso").toLocalDate();

           Alquiler alquilerClass = new Alquiler(numeroAlquiler,cliente, empleado, fechaSolicitud, fechaAlquiler, fechaRegreso);
           alquileres.add(alquilerClass);
        }

        System.out.println("Consulta de alquiler por número de servicio completada correctamente.");
        return alquileres;

    } catch (SQLException ex) {
        Logger.getLogger(Alquiler.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Error al realizar la consulta de alquiler.");
        return alquileres; // Retornar la lista vacía en caso de error
    }
    }

    @Override
    public List<Alquiler> getAlquilerPorFecha(LocalDate fechaBusqueda) {
        String sqlQuery = "SELECT * FROM Alquiler WHERE fechaAlquiler = ?";
        List<Alquiler> alquileres = new ArrayList<>();

    try {
        PreparedStatement pstmt = db.createConnection().prepareStatement(sqlQuery);
        pstmt.setDate(1,  Date.valueOf(fechaBusqueda)); // Corregir el índice a 1

        ResultSet rs = pstmt.executeQuery();
        AlquilerFactoryAbstract alquiler = new AlquilerConcreteFactory();


        while (rs.next()) {
            // Asumiendo que tienes un constructor o método para crear un Alquiler a partir del ResultSet
            int numeroAlquiler = rs.getInt("numeroAlquiler");
            String cliente = rs.getString("idCliente");
            String empleado = rs.getString("idEmpleado");
            LocalDate fechaSolicitud = rs.getDate("fechaSolicitud").toLocalDate();
            LocalDate fechaAlquiler = rs.getDate("fechaAlquiler").toLocalDate();
            LocalDate fechaRegreso = rs.getDate("fechaRegreso").toLocalDate();

           Alquiler alquilerClass = new Alquiler(numeroAlquiler,cliente, empleado, fechaSolicitud, fechaAlquiler, fechaRegreso);
           alquileres.add(alquilerClass);
        }

        System.out.println("Consulta de alquiler por número de servicio completada correctamente.");
        return alquileres;

    } catch (SQLException ex) {
        Logger.getLogger(Alquiler.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Error al realizar la consulta de alquiler.");
        return alquileres; // Retornar la lista vacía en caso de error
    }
    }

    public boolean validarIdCliente(String clienteIdIngresado) {

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

    public boolean validarIdEmpleado(String empleadoIdIngresado) {
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
   
    public boolean validarIdPrenda(String prendaIdPrenda) {
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

    

    @Override
    public List<Alquiler> consultarAlquilerPorCliente(String idCliente) {
        String sqlQuery = "SELECT * FROM servicioAlquiler WHERE cliente_id = ? ORDER BY fechaAlquiler ASC";
        List<Alquiler> alquileres = new ArrayList<>();

    try {
        PreparedStatement pstmt = db.createConnection().prepareStatement(sqlQuery);
        pstmt.setString(1, idCliente); // Corregir el índice a 1

        ResultSet rs = pstmt.executeQuery();
        AlquilerFactoryAbstract alquiler = new AlquilerConcreteFactory();


        while (rs.next()) {
            // Asumiendo que tienes un constructor o método para crear un Alquiler a partir del ResultSet
            int numeroAlquiler = rs.getInt("numeroAlquiler");
            String cliente = rs.getString("idCliente");
            String empleado = rs.getString("idEmpleado");
            LocalDate fechaSolicitud = rs.getDate("fechaSolicitud").toLocalDate();
            LocalDate fechaAlquiler = rs.getDate("fechaAlquiler").toLocalDate();
            LocalDate fechaRegreso = rs.getDate("fechaRegreso").toLocalDate();

           Alquiler alquilerClass = new Alquiler(numeroAlquiler,cliente, empleado, fechaSolicitud, fechaAlquiler, fechaRegreso);
           alquileres.add(alquilerClass);
        }

        System.out.println("Consulta de alquiler por número de servicio completada correctamente.");
        return alquileres;

    } catch (SQLException ex) {
        Logger.getLogger(Alquiler.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Error al realizar la consulta de alquiler.");
        return alquileres; // Retornar la lista vacía en caso de error
    }
    }

    @Override
    public List<Alquiler> consultarAlquilerPorFecha(LocalDate fechaBusqueda) {
         String sqlQuery = "SELECT * FROM Alquiler WHERE fechaAlquiler = ?";
        List<Alquiler> alquileres = new ArrayList<>();

    try {
        PreparedStatement pstmt = db.createConnection().prepareStatement(sqlQuery);
        pstmt.setDate(1,  Date.valueOf(fechaBusqueda)); // Corregir el índice a 1

        ResultSet rs = pstmt.executeQuery();
        AlquilerFactoryAbstract alquiler = new AlquilerConcreteFactory();


        while (rs.next()) {
            // Asumiendo que tienes un constructor o método para crear un Alquiler a partir del ResultSet
            int numeroAlquiler = rs.getInt("numeroAlquiler");
            String cliente = rs.getString("idCliente");
            String empleado = rs.getString("idEmpleado");
            LocalDate fechaSolicitud = rs.getDate("fechaSolicitud").toLocalDate();
            LocalDate fechaAlquiler = rs.getDate("fechaAlquiler").toLocalDate();
            LocalDate fechaRegreso = rs.getDate("fechaRegreso").toLocalDate();

           Alquiler alquilerClass = new Alquiler(numeroAlquiler,cliente, empleado, fechaSolicitud, fechaAlquiler, fechaRegreso);
           alquileres.add(alquilerClass);
        }

        System.out.println("Consulta de alquiler por fecha completada correctamente.");
        return alquileres;

    } catch (SQLException ex) {
        Logger.getLogger(Alquiler.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Error al realizar la consulta de alquiler.");
        return alquileres; // Retornar la lista vacía en caso de error
    }
    }

    @Override
    public boolean registrarAlquiler(int numeroAlquiler, String clienteId, String empleadoId, LocalDate fechaSolicitud, LocalDate fechaAlquiler, LocalDate fechaRegreso, List<String> refPrenda) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Alquiler> obtenerTodosLosAlquileres() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Alquiler consultarAlquilerPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean actualizarAlquiler(Alquiler alquiler) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminarAlquiler(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
