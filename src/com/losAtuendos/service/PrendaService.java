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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrendaService implements PrendaRepository {

    DBManager db = new DBManager();

    @Override
    public boolean postPrenda(Prenda prenda) {
        String sqlInsert = "INSERT INTO prenda ( ref, color, marca, talla, valorAlquiler, tipo, disponible) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert);
            pstmt.setString(1, prenda.getRef());
            pstmt.setString(2, prenda.getColor());
            pstmt.setString(3, prenda.getMarca());
            pstmt.setString(4, prenda.getTalla());
            pstmt.setDouble(5, prenda.getValorAlquiler());
            pstmt.setString(6, prenda.getTipo());
            pstmt.setBoolean(7, prenda.isDisponible());

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

        String sqlInsert = "INSERT INTO vestidodama (prenda_ref,pedreria,largo,cantPiezas) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert);
            pstmt.setString(1, vestido.getRef());
            pstmt.setBoolean(2, vestido.isPedreria());
            pstmt.setString(3, vestido.getLargo());
            pstmt.setInt(4, vestido.getCantPiezas());

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

        String sqlInsert = "INSERT INTO trajecaballero (prenda_ref, tipo, accesorio) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert);
            pstmt.setString(1, traje.getRef());
            pstmt.setString(2, traje.getTipoTraje());
            pstmt.setString(3, traje.getAccesorio());

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
            pstmt.setString(1, disfraz.getRef());
            pstmt.setString(2, disfraz.getNombreDisfraz());

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
    public List getPrendasByTalla(String tallaPrenda) {
        List listaPrendas = new ArrayList<>();
        PrendaFactoryAbstract prenda = new PrendaConcreteFactory();
        String sql = "SELECT p.ref, p.color, p.marca, p.talla, p.valorAlquiler, p.tipo, p.disponible, "
                + "d.pedreria, d.largo,d.cantPiezas, tc.tipo AS tipoTraje, tc.accesorio, di.nombre FROM Prenda p "
                + "LEFT JOIN VestidoDama d ON p.ref = d.prenda_ref LEFT JOIN TrajeCaballero tc ON p.ref = tc.prenda_ref "
                + "LEFT JOIN Disfraz di ON p.ref = di.prenda_ref  WHERE p.talla = ? ";

        try {
            PreparedStatement stmt = db.createConnection().prepareStatement(sql);
            stmt.setString(1, tallaPrenda);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String tipoPrenda = rs.getString("tipo");
                if (tipoPrenda.equals("vestidoDama")) {
                    List<VestidoDama> vestidos = new ArrayList<>();
                    vestidos.add(new VestidoDama(
                            rs.getString("ref"),
                            rs.getString("color"),
                            rs.getString("marca"),
                            rs.getString("talla"),
                            rs.getDouble("valorAlquiler"),
                            rs.getString("tipo"),
                            rs.getBoolean("disponible"),
                            rs.getBoolean("pedreria"),
                            rs.getString("largo"),
                            rs.getInt("cantPiezas")
                    ));
                    System.out.println(" ------- Vestidos Dama -------- ");
                    for (VestidoDama vestido : vestidos) {

                        System.out.println(" --------------- ");
                        vestido.mostrarDetalles();
                        System.out.println(" --------------- ");
                    }
                    System.out.println("\n\n");
                } else if (tipoPrenda.equals("trajeCaballero")) {
                    List<TrajeCaballero> trajes = new ArrayList<>();
                    trajes.add(new TrajeCaballero(
                            rs.getString("ref"),
                            rs.getString("color"),
                            rs.getString("marca"),
                            rs.getString("talla"),
                            rs.getDouble("valorAlquiler"),
                            rs.getString("tipo"),
                            rs.getBoolean("disponible"),
                            rs.getString("tipoTraje"),
                            rs.getString("accesorio")
                    ));
                    System.out.println(" ------- Traje Caballero -------- ");
                    for (TrajeCaballero traje : trajes) {
                        System.out.println(" --------------- ");
                        traje.mostrarDetalles();
                        System.out.println(" --------------- ");

                    }
                    System.out.println("\n\n");
                } else if (tipoPrenda.equals("disfraz")) {
                    List<Disfraz> disfraces = new ArrayList<>();
                    disfraces.add(new Disfraz(
                            rs.getString("ref"),
                            rs.getString("color"),
                            rs.getString("marca"),
                            rs.getString("talla"),
                            rs.getDouble("valorAlquiler"),
                            rs.getString("tipo"),
                            rs.getBoolean("disponible"),
                            rs.getString("nombre")
                    ));
                    System.out.println(" ------- Disfraces -------- ");
                    for (Disfraz disfraz : disfraces) {
                        System.out.println(" --------------- ");
                        disfraz.mostrarDetalles();
                        System.out.println(" --------------- ");

                    }
                    System.out.println("\n\n");
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPrendas;
    }

    @Override
    public List getPrendasByReferencia(String refPrenda) {
        List listaPrendas = new ArrayList<>();
        PrendaFactoryAbstract prenda = new PrendaConcreteFactory();
        String sql = "SELECT p.ref, p.color, p.marca, p.talla, p.valorAlquiler, p.tipo, p.disponible, "
                + "d.pedreria, d.largo,d.cantPiezas, tc.tipo AS tipoTraje, tc.accesorio, di.nombre FROM Prenda p "
                + "LEFT JOIN VestidoDama d ON p.ref = d.prenda_ref LEFT JOIN TrajeCaballero tc ON p.ref = tc.prenda_ref "
                + "LEFT JOIN Disfraz di ON p.ref = di.prenda_ref  WHERE p.ref = ? ";

        try {
            PreparedStatement stmt = db.createConnection().prepareStatement(sql);
            stmt.setString(1, refPrenda);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String tipoPrenda = rs.getString("tipo");
                if (tipoPrenda.equals("vestidoDama")) {
                    List<VestidoDama> vestidos = new ArrayList<>();
                    vestidos.add(new VestidoDama(
                            rs.getString("ref"),
                            rs.getString("color"),
                            rs.getString("marca"),
                            rs.getString("talla"),
                            rs.getDouble("valorAlquiler"),
                            rs.getString("tipo"),
                            rs.getBoolean("disponible"),
                            rs.getBoolean("pedreria"),
                            rs.getString("largo"),
                            rs.getInt("cantPiezas")
                    ));
                    System.out.println(" ------- Vestidos Dama -------- ");
                    for (VestidoDama vestido : vestidos) {

                        System.out.println(" --------------- ");
                        vestido.mostrarDetalles();
                        System.out.println(" --------------- ");
                    }
                    System.out.println("\n\n");
                } else if (tipoPrenda.equals("trajeCaballero")) {
                    List<TrajeCaballero> trajes = new ArrayList<>();
                    trajes.add(new TrajeCaballero(
                            rs.getString("ref"),
                            rs.getString("color"),
                            rs.getString("marca"),
                            rs.getString("talla"),
                            rs.getDouble("valorAlquiler"),
                            rs.getString("tipo"),
                            rs.getBoolean("disponible"),
                            rs.getString("tipoTraje"),
                            rs.getString("accesorio")
                    ));
                    System.out.println(" ------- Traje Caballero -------- ");
                    for (TrajeCaballero traje : trajes) {
                        System.out.println(" --------------- ");
                        traje.mostrarDetalles();
                        System.out.println(" --------------- ");

                    }
                    System.out.println("\n\n");
                } else if (tipoPrenda.equals("disfraz")) {
                    List<Disfraz> disfraces = new ArrayList<>();
                    disfraces.add(new Disfraz(
                            rs.getString("ref"),
                            rs.getString("color"),
                            rs.getString("marca"),
                            rs.getString("talla"),
                            rs.getDouble("valorAlquiler"),
                            rs.getString("tipo"),
                            rs.getBoolean("disponible"),
                            rs.getString("nombre")
                    ));
                    System.out.println(" ------- Disfraces -------- ");
                    for (Disfraz disfraz : disfraces) {
                        System.out.println(" --------------- ");
                        disfraz.mostrarDetalles();
                        System.out.println(" --------------- ");

                    }
                    System.out.println("\n\n");
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPrendas;
    }

    @Override
    public List getPrendasByDisponibilidad(boolean disponiblePrenda) {
        List listaPrendas = new ArrayList<>();
        PrendaFactoryAbstract prenda = new PrendaConcreteFactory();
        String sql = "SELECT p.ref, p.color, p.marca, p.talla, p.valorAlquiler, p.tipo, p.disponible, "
                + "d.pedreria, d.largo,d.cantPiezas, tc.tipo AS tipoTraje, tc.accesorio, di.nombre FROM Prenda p "
                + "LEFT JOIN VestidoDama d ON p.ref = d.prenda_ref LEFT JOIN TrajeCaballero tc ON p.ref = tc.prenda_ref "
                + "LEFT JOIN Disfraz di ON p.ref = di.prenda_ref  WHERE p.disponible = ? ";

        try {
            PreparedStatement stmt = db.createConnection().prepareStatement(sql);
            stmt.setString(1, disponiblePrenda ? "1" : "0");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String tipoPrenda = rs.getString("tipo");
                if (tipoPrenda.equals("vestidoDama")) {
                    List<VestidoDama> vestidos = new ArrayList<>();
                    vestidos.add(new VestidoDama(
                            rs.getString("ref"),
                            rs.getString("color"),
                            rs.getString("marca"),
                            rs.getString("talla"),
                            rs.getDouble("valorAlquiler"),
                            rs.getString("tipo"),
                            rs.getBoolean("disponible"),
                            rs.getBoolean("pedreria"),
                            rs.getString("largo"),
                            rs.getInt("cantPiezas")
                    ));
                    System.out.println(" ------- Vestidos Dama -------- ");
                    for (VestidoDama vestido : vestidos) {

                        System.out.println(" --------------- ");
                        vestido.mostrarDetalles();
                        System.out.println(" --------------- ");
                    }
                    System.out.println("\n\n");
                } else if (tipoPrenda.equals("trajeCaballero")) {
                    List<TrajeCaballero> trajes = new ArrayList<>();
                    trajes.add(new TrajeCaballero(
                            rs.getString("ref"),
                            rs.getString("color"),
                            rs.getString("marca"),
                            rs.getString("talla"),
                            rs.getDouble("valorAlquiler"),
                            rs.getString("tipo"),
                            rs.getBoolean("disponible"),
                            rs.getString("tipoTraje"),
                            rs.getString("accesorio")
                    ));
                    System.out.println(" ------- Traje Caballero -------- ");
                    for (TrajeCaballero traje : trajes) {
                        System.out.println(" --------------- ");
                        traje.mostrarDetalles();
                        System.out.println(" --------------- ");

                    }
                    System.out.println("\n\n");
                } else if (tipoPrenda.equals("disfraz")) {
                    List<Disfraz> disfraces = new ArrayList<>();
                    disfraces.add(new Disfraz(
                            rs.getString("ref"),
                            rs.getString("color"),
                            rs.getString("marca"),
                            rs.getString("talla"),
                            rs.getDouble("valorAlquiler"),
                            rs.getString("tipo"),
                            rs.getBoolean("disponible"),
                            rs.getString("nombre")
                    ));
                    System.out.println(" ------- Disfraces -------- ");
                    for (Disfraz disfraz : disfraces) {
                        System.out.println(" --------------- ");
                        disfraz.mostrarDetalles();
                        System.out.println(" --------------- ");

                    }
                    System.out.println("\n\n");
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPrendas;
    }

    @Override
    public void registroPrendasParaLavanderia(String ref, int prioridad) {
        String sqlInsert = "INSERT INTO lavanderia (prenda_ref, prioridad ) VALUES (?, ?)";
        try {
            PreparedStatement pstmt = db.createConnection().prepareStatement(sqlInsert);
            pstmt.setString(1, ref);
            pstmt.setInt(2, prioridad);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted < 0) {
                System.out.println("No se completo el registro, intentelo mas tarde");
            }

            System.out.println("Nuevo registro de prenda para lavanderia insertado correctamente.");

        } catch (MySQLIntegrityConstraintViolationException e) {
            System.out.println("Error el ID ya exise");
            throw new Error("El Id ya existe");
        } catch (SQLException ex) {
            // Check for specific error code indicating duplicate key violation
            if (ex.getErrorCode() == 1062) {
                System.out.println("La prenda con referencia '" + ref + "' ya existe.");
            } else {
                Logger.getLogger(com.losAtuendos.models.Cliente.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error inesperado: " + ex.getMessage());
            }
        }
    }

    @Override
    public Map<String, Boolean> getPrendasParaLavanderia() {
        Map<String, Boolean> prendas = new HashMap<>();
        String sql = "SELECT prenda_ref, prioridad FROM lavanderia";

        try {
            PreparedStatement stmt = db.createConnection().prepareStatement(sql);
            //stmt.setString(1, disponiblePrenda ? "1" : "0");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String prendaRef = rs.getString("prenda_ref");
                boolean prioridad = rs.getBoolean("prioridad");
                prendas.put(prendaRef, prioridad);
            }
            System.out.println("\n\n");
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Print the final Map content
        System.out.println("Prendas en lista para Lavanderia:");
        for (Map.Entry<String, Boolean> entry : prendas.entrySet()) {
            String prendaRef = entry.getKey();
            boolean prioridad = entry.getValue();
            System.out.println("  - Prenda: " + prendaRef + ", Prioridad: " + prioridad);
        }
        return prendas;
    }

    @Override
    public void envioPrendasParaLavanderia(int cantidadAEliminar
    ) {
        Map<String, Boolean> prendasEnListaDeLavado = getPrendasParaLavanderia();

        // Eliminar las primeras 'cantidadAEliminar' prendas del Map
        List<String> prendasAEliminar = new ArrayList<>(prendasEnListaDeLavado.keySet());
        prendasAEliminar = prendasAEliminar.subList(0, Math.min(cantidadAEliminar, prendasAEliminar.size()));
        prendasAEliminar.forEach(prendasEnListaDeLavado::remove);

        // Construir la consulta SQL para eliminar las prendas
        StringBuilder sql = new StringBuilder("DELETE FROM lavanderia WHERE prenda_ref IN (");
        for (int i = 0; i < prendasAEliminar.size(); i++) {
            sql.append("?,");
        }
        sql.deleteCharAt(sql.length() - 1); // Remove the trailing comma
        sql.append(")");

        try {
            PreparedStatement stmt = db.createConnection().prepareStatement(sql.toString());

            int i = 1;
            for (String prendaRef : prendasAEliminar) {
                stmt.setString(i++, prendaRef);
            }
            stmt.executeUpdate();
            // Execute the DELETE statement
            int prendasEnviadasALavado = stmt.executeUpdate();
            System.out.println("Numero de prendas enviadas a lavado: " + cantidadAEliminar);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Print the final Map content
        System.out.println("Prendas para Lavanderia:");
        for (Map.Entry<String, Boolean> entry : prendasEnListaDeLavado.entrySet()) {
            String prendaRef = entry.getKey();
            boolean prioridad = entry.getValue();
            System.out.println("  - Prenda: " + prendaRef + ", Prioridad: " + prioridad);
        }

    }
}
