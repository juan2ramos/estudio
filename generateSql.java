/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snackfinal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author juan2ramos
 */
public class generateSql extends Conexion {

    private ResultSet rs = null;
    private PreparedStatement pstmt = null;

    public generateSql() {
        super();
    }

    private void preSql(String query) {
        try {

            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

        } catch (SQLException ex) {
            System.out.println("Error pstmt.executeQuery(): " + ex);
        }
    }

    public List<HashMap<String, Object>> selectSql(String query) throws SQLException {

        preSql(query);
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<HashMap<String, Object>> list = new ArrayList<>();


        try {
            while (rs.next()) {
                HashMap<String, Object> row = new HashMap<>(columns);
                for (int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnName(i), rs.getObject(i));
                }
                list.add(row);

            }
        } catch (SQLException ex) {
            System.out.println("Error rs.next(): " + ex);
        }
        
        return list;
    }

    boolean update(String query) {
        try {
            pstmt = conn.prepareStatement(query); 
            pstmt.executeUpdate();  
            return false;

        } catch (SQLException ex) {
            System.out.println("Error pstmt.executeQuery(): " + ex);
        }
        try {
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) { /*ignore Exception */

        }
        return false;
        

    }
}
