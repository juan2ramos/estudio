/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snackfinal;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author juan2ramos
 */
public class Conexion {

    private String user = "root";
    private String password = "pass";
    private String url = "jdbc:mysql://localhost:3306/snack";
    protected Connection conn = null;
    
    Conexion() {

        loadDriver();
        conexionBD();
    }
    
    private void loadDriver() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            System.out.println("Error Exception loading Driver:" + ex);
        }
    }

    private void conexionBD() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            System.err.println("Error DriverManager.getConnection(): " + ex);
        }
    }    
}
