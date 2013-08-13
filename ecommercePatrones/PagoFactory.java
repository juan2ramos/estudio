/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

/**
 *
 * @author juan2ramos
 */
public class PagoFactory {
    
    public static Pago crearPago(String Criteria) {
        if (Criteria.equals("tarjeta")) {
            return new TarjetaCreditoFacade();
        } else if (Criteria.equals("transferencia")) {
            return new Transferencia();
        }
        return null;
    }
}
