/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

/**
 *
 * @author juan2ramos
 */
public class CamisaHombre implements Camisa{

    
    public String comprar(String tipoPago) {
        return PagoFactory.crearPago(tipoPago).realizarTransaccion();
    }
    
}
