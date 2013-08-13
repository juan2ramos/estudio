/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

/**
 *
 * @author juan2ramos
 */
public class TarjetaCreditoFacade implements Pago {
    private Usuario usuario;
    private EntidadBancaria banco;
    

    public TarjetaCreditoFacade() {
        usuario = new Usuario(); 
        banco = new EntidadBancaria();
        
    }    

    public String realizarTransaccion() {
        
        usuario.start();
        while (usuario.isAlive());  
        banco.setCedula(usuario.cedula);
        banco.setNumeroTarjeta(usuario.numeroTarjeta);
        banco.start();
        while (banco.isAlive());
        
        if (banco.msj) {
            return "Gracias por la transaccion Pago Exitoso";
        }
        return "Pago no Exitoso";
    }
    
    
}
