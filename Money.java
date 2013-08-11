/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snackfinal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author juan2ramos
 */
public class Money {

    private generateSql pSql = new generateSql();
    private List<Integer> listaMonedas = new ArrayList<>();
    private HashMap caja = new HashMap();

    @SuppressWarnings("empty-statement")
    private void fillMoney() throws SQLException {


        List<HashMap<String, Object>> lista = pSql.selectSql("select * from monedas");
        for (int i = 0; i < lista.size(); i++) {

            int valor = (int) lista.get(i).get("valor");
            int cantidad = (int) lista.get(i).get("cantidad");
            listaMonedas.add(valor);

            caja.put(valor, new Integer(cantidad));

        }
    }

    HashMap getCaja() {
        return caja;
    }

    List<Integer> getMoney() throws SQLException {
        fillMoney();
        return listaMonedas;
    }

    void update(int key, int cantidad) {
        String query = "update monedas set cantidad = " + cantidad + " where valor = " +  key + ";" ;
        pSql.update(query);
    }
}
