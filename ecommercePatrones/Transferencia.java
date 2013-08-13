/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

/**
 *
 * @author juan2ramos
 */
public class Transferencia implements Pago{

    boolean exito = true;
    
    public String realizarTransaccion() {
        if(exito){
            return "Gracias por la transaferencia Pago Exitoso ";
        }
        return "Pago no Exitoso";
    }
    
}
