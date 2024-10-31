package com.losAtuendos.service;

import com.losAtuendos.models.Cliente;
import com.losAtuendos.models.Empleado;
import com.losAtuendos.models.Persona;
import com.losAtuendos.repository.PersonasRepository;
import com.losAtuendos.utils.dao.DBManager;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaService implements PersonasRepository {

    DBManager db = new DBManager();

    @Override
    public boolean postPersona(Persona persona) {
        String sqlInsert = "INSERT INTO persona (id, nombre, direccion, telefono) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert);
            pstmt.setString(1, persona.getId());
            pstmt.setString(2, persona.getNombre());
            pstmt.setString(3, persona.getDireccion());
            pstmt.setString(4, persona.getTelefono());


            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted < 0) {
                return false;
            }

            System.out.println("Nuevo registro persona insertado correctamente.");
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
    public boolean postEmpleado(Empleado empleado) {

        this.postPersona(empleado);

        String sqlInsert = "INSERT INTO Empleado (cargo , persona_id) VALUES (?, ? )";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert);
            pstmt.setString(1, empleado.getCargo());
            pstmt.setString(2, empleado.getId());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Nuevo registro insertado correctamente.");
                return true;
            }

            return false;

        } catch (SQLException ex) {
            Logger.getLogger(com.losAtuendos.models.Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
            return false;
        }
    }

    @Override
    public boolean postCliente(Cliente cliente) {
        this.postPersona(cliente);

        String sqlInsert = "INSERT INTO Cliente (email, persona_id) VALUES (?, ? )";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert);
            pstmt.setString(1, cliente.getMail());
            pstmt.setString(2, cliente.getId());


            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Nuevo registro insertado correctamente.");
                return true;
            }

            return false;

        } catch (SQLException ex) {
            Logger.getLogger(com.losAtuendos.models.Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
            return false;
        }
    }

    @Override
    public boolean getClienteById(String id) {
        String sqlQuery = "SELECT p.*, c.email FROM Persona p INNER JOIN Cliente c ON p.numero_identificacion = c.persona_id WHERE c.persona_id = ?";

        try {
            PreparedStatement stmt = db.createConnection().prepareStatement(sqlQuery);

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                System.out.println("Cliente encontrado");
                System.out.println(cliente.getId());
                System.out.println(cliente.getNombre());
                System.out.println(cliente.getDireccion());
                System.out.println(cliente.getTelefono());
                System.out.println(cliente.getMail());

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sqlQuery = "SELECT p.*, c.email FROM Persona p INNER JOIN Cliente c ON p.id = c.persona_id";
        System.out.println("Voy a hacer la consulta");
        try {
            PreparedStatement stmt = db.createConnection().prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("La lista de clientes es: ");
        for (Cliente cliente : clientes) {
            cliente.mostrarDetalles();
        }
        return clientes;
    }

    @Override
    public boolean getEmpleadoById(String id) {
        String sqlQuery = "SELECT p.*, e.cargo FROM Persona p INNER JOIN Empleado e ON p.numero_identificacion = e.persona_id WHERE e.persona_id = ?";

        try {
            PreparedStatement stmt = db.createConnection().prepareStatement(sqlQuery);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("cargo")
                );
                System.out.println("Empleado encontrado");
                System.out.println(empleado.getId());
                System.out.println(empleado.getNombre());
                System.out.println(empleado.getDireccion());
                System.out.println(empleado.getTelefono());
                System.out.println(empleado.getCargo());

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public List<Empleado> getAllEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        String sqlQuery = "SELECT p.*, em.cargo FROM Persona p INNER JOIN Empleado em ON p.id = em.persona_id;";

        try {
            PreparedStatement stmt = db.createConnection().prepareStatement(sqlQuery);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Empleado empleado = new Empleado(
                        rs.getString("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("cargo")
                );
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("La lista de clientes es: ");
        for (Empleado empleado : empleados) {
            empleado.mostrarDetalles();
        }
        return empleados;
    }
}
