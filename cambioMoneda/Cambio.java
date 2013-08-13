/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cambio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author juan2ramos
 */
public class Cambio {
    
    private HashMap caja;
    private List<Integer> list = new ArrayList<>();
    
    public Cambio() {
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(5);
        
        caja = new HashMap();
        int valor = 1;
        int cantidad = 2;
        
        int valor1 = 2;
        int cantidad1 = 2;
        
        int valor2 = 4;
        int cantidad2 = 2;
        
        int valor3 = 5;
        int cantidad3 = 2;
        
        caja.put(valor, cantidad);
        caja.put(valor1, cantidad1);
        caja.put(valor2, cantidad1);
        caja.put(valor3, cantidad1);
    }
    
    public void getCaja(int saldo) {
        MakeReturn(saldo);
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

            // UpdateCaja(mr);
            return true;
        }
        return false;
        
    }
    
    public HashMap MakeReturn(int saldo) {
        
        HashMap mr = MoneyReturn(saldo);
        boolean cr = CheckReurn(mr, saldo);
        if (cr == true) {
            System.out.println(mr);
            Iterator it = mr.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                int llave = (Integer) e.getKey();
                int valor = (Integer) e.getValue();
                int valorActual = (Integer)caja.get(llave);
                int nuevoValor = valorActual - valor;
                
                caja.put(llave, nuevoValor);
                //System.out.println(llave+" "+valor);
                //System.out.println(caja);
            }
            return mr;
        }
        System.out.println("lo siento");
        mr = null;
        return mr;
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int x = 100;
        Cambio cambio = new Cambio();
        
        x = Integer.parseInt(JOptionPane.showInputDialog("Digite un numero"));
        cambio.getCaja(x);
        
        JDialog.setDefaultLookAndFeelDecorated(true);
        int response = JOptionPane.showConfirmDialog(null, "Desea continuar?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        while (response == JOptionPane.YES_OPTION) {
            x = Integer.parseInt(JOptionPane.showInputDialog("Digite un numero"));
            cambio.getCaja(x);
        }



        //System.out.print(cambio.getCaja());
    }
}
