package com.losAtuendos.service;

import com.losAtuendos.factory.AlquilerFactory.AlquilerConcreteFactory;
import com.losAtuendos.factory.AlquilerFactory.AlquilerFactoryAbstract;
import com.losAtuendos.iterators.AlquilerAggregate;
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
import java.util.Iterator;

public class AlquilerService implements AlquilerRepository,ServicioAlquilerI {

    private DBManager db = new DBManager();
    //private AlquilerAggregate alquilerAggregate;
    
    //public AlquilerService(){
      //  this.alquilerAggregate =  new AlquilerAggregate();
    //}

    @Override
    public int postAlquiler(Alquiler alquiler) {
    String sqlInsert = "INSERT INTO servicioalquiler (numero,cliente_id, empleado_id, fechaSolicitud, fechaAlquiler, fechaRegreso) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            pstmt.setDate(1, null);
            pstmt.setString(2, alquiler.getCliente());
            pstmt.setString(3, alquiler.getEmpleado());
            pstmt.setDate(4, Date.valueOf(alquiler.getFechaSolicitud()));
            pstmt.setDate(5, Date.valueOf(alquiler.getFechaAlquiler()));
            pstmt.setDate(6, Date.valueOf(alquiler.getFechaRegreso()));

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
                System.out.println("Nuevo registro alquiler insertado correctamente con el numero: " + generatedKeys);

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
   Alquiler alquilerClass = null;
        String sqlQuery = "SELECT * FROM servicioalquiler WHERE numero = ?";
        //List<Alquiler> alquileres = new ArrayList<>();
        AlquilerAggregate alquilerAggregate = new AlquilerAggregate();

        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlQuery);
            pstmt.setInt(1, numeroServicio);

            ResultSet rs = pstmt.executeQuery();
            AlquilerFactoryAbstract alquiler = new AlquilerConcreteFactory();

            while (rs.next()) {
                int numeroAlquiler = rs.getInt("numero");
                String cliente = rs.getString("cliente_id");
                String empleado = rs.getString("empleado_id");
                LocalDate fechaSolicitud = rs.getDate("fechaSolicitud").toLocalDate();
                LocalDate fechaAlquiler = rs.getDate("fechaAlquiler").toLocalDate();
                LocalDate fechaRegreso = rs.getDate("fechaRegreso").toLocalDate();

                alquilerClass = new Alquiler(numeroAlquiler, cliente, empleado, fechaSolicitud, fechaAlquiler, fechaRegreso);
                alquilerAggregate.add(alquilerClass);
            }

            System.out.println("Consulta de alquiler por número de servicio completada correctamente.");
            if (alquilerClass != null) {
                alquilerClass.mostrarDetalles();
            }

            return alquilerAggregate.getList();

        } catch (SQLException ex) {
            Logger.getLogger(Alquiler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al realizar la consulta de alquiler.");
            return alquilerAggregate.getList();
        }
}
    
    private void imprimirAlquileres(Iterator<Alquiler> iterator) {
    while (iterator.hasNext()) {
        Alquiler alquiler = iterator.next();
        alquiler.mostrarDetalles();
    }
}


    @Override
    public List<Alquiler> getAlquilerPorCliente(String idCliente) {
        Alquiler alquilerClass = null;
        String sqlQuery = "SELECT * FROM servicioalquiler WHERE cliente_id = ? ORDER BY fechaAlquiler ASC";
        //List<Alquiler> alquileres = new ArrayList<>();
        AlquilerAggregate alquilerAggregate = new AlquilerAggregate();

        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlQuery);
            pstmt.setString(1, idCliente);

            ResultSet rs = pstmt.executeQuery();
            AlquilerFactoryAbstract alquiler = new AlquilerConcreteFactory();

            while (rs.next()) {
                int numeroAlquiler = rs.getInt("numero");
                String cliente = rs.getString("cliente_id");
                String empleado = rs.getString("empleado_id");
                LocalDate fechaSolicitud = rs.getDate("fechaSolicitud").toLocalDate();
                LocalDate fechaAlquiler = rs.getDate("fechaAlquiler").toLocalDate();
                LocalDate fechaRegreso = rs.getDate("fechaRegreso").toLocalDate();

                alquilerClass = new Alquiler(numeroAlquiler, cliente, empleado, fechaSolicitud, fechaAlquiler, fechaRegreso);
                alquilerAggregate.add(alquilerClass);
            }

            System.out.println("Consulta de alquiler por número de servicio completada correctamente.");
            if (alquiler != null) {
                alquilerClass.mostrarDetalles();
            }

            return alquilerAggregate.getList();

        } catch (SQLException ex) {
            Logger.getLogger(Alquiler.class
                    .getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al realizar la consulta de alquiler.");
            return alquilerAggregate.getList();
        }
    }

    @Override
    public List<Alquiler> getAlquilerPorFecha(LocalDate fechaBusqueda) {
        Alquiler alquilerClass = null;
        String sqlQuery = "SELECT * FROM servicioalquiler WHERE fechaAlquiler = ?";
        //List<Alquiler> alquileres = new ArrayList<>();
        AlquilerAggregate alquilerAggregate = new AlquilerAggregate();

    try {
        PreparedStatement pstmt = db.createConnection().prepareStatement(sqlQuery);
        pstmt.setDate(1,  Date.valueOf(fechaBusqueda)); // Corregir el índice a 1

        ResultSet rs = pstmt.executeQuery();
        AlquilerFactoryAbstract alquiler = new AlquilerConcreteFactory();


        while (rs.next()) {
            // Asumiendo que tienes un constructor o método para crear un Alquiler a partir del ResultSet
            int numeroAlquiler = rs.getInt("numero");
            String cliente = rs.getString("cliente_id");
            String empleado = rs.getString("empleado_id");
            LocalDate fechaSolicitud = rs.getDate("fechaSolicitud").toLocalDate();
            LocalDate fechaAlquiler = rs.getDate("fechaAlquiler").toLocalDate();
            LocalDate fechaRegreso = rs.getDate("fechaRegreso").toLocalDate();

           alquilerClass = new Alquiler(numeroAlquiler,cliente, empleado, fechaSolicitud, fechaAlquiler, fechaRegreso);
           alquilerAggregate.add(alquilerClass);
        }

        System.out.println("Consulta de alquiler por número de servicio completada correctamente.");
        if (alquiler != null) {
                alquilerClass.mostrarDetalles();
            }
        return alquilerAggregate.getList();

    } catch (SQLException ex) {
        Logger.getLogger(Alquiler.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Error al realizar la consulta de alquiler.");
        return alquilerAggregate.getList(); // Retornar la lista vacía en caso de error
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

        if (rs == null) {
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
        } else {
            System.out.println("El usuario no tiene alquileres registrados");
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
