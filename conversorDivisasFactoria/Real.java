/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conversormoneda;

/**
 *
 * @author juan2ramos
 */
public class Real implements Moneda{

    
   private double valorCalculado;
    
    public void calcularMoneda(double valor) {
        valorCalculado = valor * 1100;
    }
    
    public double getValor(){
        return valorCalculado;
        
    }
    
}
