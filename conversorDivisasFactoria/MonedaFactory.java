/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conversormoneda;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan2ramos
 */
public class MonedaFactory {
    public static  ArrayList  MonedaLista = new ArrayList();
    
    public static Moneda crearMoneda(String pais){
        
        if(pais.equals("usa") || pais.equals("canada") ){
            
           Dolar dolar = new Dolar(); 
           MonedaLista.add(dolar);
           return dolar;
           
        }
        if(pais.equals("espana") || pais.equals("francia") || pais.equals("alemania") ){
           Euro euro = new Euro(); 
           MonedaLista.add(euro);
           return euro; 
        }
        if(pais.equals("granbretana")  ){
           Libra libra = new Libra(); 
           MonedaLista.add(libra);
           return libra; 
        }
        if(pais.equals("brasil")  ){
           Real real = new Real(); 
           MonedaLista.add(real);
           return real; 
        }
        return null;
        
    }
    
}
