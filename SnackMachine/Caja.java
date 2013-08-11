/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snackfinal;


import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan2ramos
 */
public class Caja {

    private HashMap caja;
    private HashMap saldo;
    private Money money = new Money();
    private List<Integer> list = new ArrayList<>();

    public Caja() {
        caja = new HashMap();
        saldo = new HashMap();
        try {
            list  =  money.getMoney();
            caja = money.getCaja();
        } catch (SQLException ex) {
            Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addMoney(int key, int times) {
        Integer num = (Integer) caja.get(key);
        int curr = 0;
        if (num != null) {
            curr = num.intValue();
        } else {
            curr = 0;
        }

        curr += times;
        money.update(key, new Integer(curr));
        caja.put(key, new Integer(curr));

    }

    public void addSaldo(int key, int times) {
        Integer num = (Integer) saldo.get(key);
        int curr = 0;
        if (num != null) {
            curr = num.intValue();
        } else {
            curr = 0;
        }

        curr += times;
        saldo.put(key, new Integer(curr));

    }

    public int getBalance(int key) {
        Integer cv = (Integer) caja.get(key);
        if (cv != null) {
            return cv.intValue();
        }
        return 0;
    }

    public HashMap getCaja() {

        return caja;
    }

    public HashMap getSaldo() {
        return saldo;
    }

    public void setSaldo() {
        saldo.clear();
    }

    private HashMap MoneyReturn(int saldo) {
        HashMap nw = new HashMap();
        Collections.sort(list);
        int sl = list.size() - 1;
        while (saldo > 0 && sl >= 0) {
            //verifica si hay existencias de la moneda solicitada 
            if ((caja.get(list.get(sl)) == null) || (caja.get(list.get(sl)) == 0)) {
                sl--;
                continue;

            }
            //verifica si el saldo es menor que la moneda 
            if (list.get(sl) > saldo) {
                sl--;
                continue;
            }

            //si la cantidad de saldo es nigual a la denominacion de la moneda
            if ((list.get(sl) == saldo)) {

                nw.put(list.get(sl), 1);//aumentar en uno la cantidad de moneda a devolver
                saldo -= (list.get(sl));//actualizar el saldo, dismuyendo el valor de la moneda 
                sl--;


            } else {

                int div = saldo / list.get(sl);//cantidad de moneda a devolver
                int numd = (Integer) caja.get(list.get(sl));
                int nec = numd - div;//lo que puedo devolver
                if (nec >= 0) {

                    saldo -= div * list.get(sl);//actualizar el saldo, dismuyendo el valor de la moneda 
                    nw.put(list.get(sl), div);//aumentar la cantidad de moneda a devolver

                } else {

                    saldo -= numd * list.get(sl);//actualizar el saldo, dismuyendo el valor de la moneda 
                    nw.put(list.get(sl), numd);//aumentar la cantidad de moneda a devolver


                }
                sl--;
            }


        }
        return nw;
    }

    private boolean CheckReurn(HashMap mr, int saldo) {
        int amount = 0;
        Collections.sort(list);
        int sl = list.size() - 1;


        Iterator it = mr.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            amount += (Integer) e.getKey() * (Integer) e.getValue();//Monto total
        }

        if (amount == saldo) {

            UpdateCaja(mr);
            return true;
        }
        return false;

    }

    public void UpdateCaja(HashMap mr) {

        Iterator it = mr.entrySet().iterator();
        while (it.hasNext()) {

            Map.Entry e = (Map.Entry) it.next();
            int dism = (Integer) caja.get(e.getKey()) - (Integer) e.getValue();
            int tempKey = (int) e.getKey();
            money.update(tempKey, dism);
            caja.put(e.getKey(), dism);//Disminuir en uno la cantidad de moneda existen en caja                
        }

    }

    public HashMap MakeReturn(int saldo) {

        HashMap mr = MoneyReturn(saldo);
        boolean cr = CheckReurn(mr, saldo);
        if (cr == true) {
            System.out.println(mr);
            return mr;
        }
        mr = null;
        return mr;

    }

    
        
        
    
}
