package main.exameninterfaces;

import java.sql.Connection;
import java.sql.DriverManager;
public class BddConnection {

    private static final Connection con;
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "";

    static {
        String database = "exameninterfaces";
        try {
            con = DriverManager.getConnection(URL + database, USER, PASS);
            System.out.println("Conexion realizada con exito a '" + database + "'");
        } catch (Exception e) {
            System.out.println("Problema al conectar con la base de Datos: " + database);
            throw new RuntimeException(e);
        } finally {
            System.out.println("Proceso de conexion terminado");
        }
    }

    public static Connection getCon() {
        return con;
    }
}
