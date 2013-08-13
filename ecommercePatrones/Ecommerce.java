/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

/**
 *
 * @author juan2ramos
 */
public class Ecommerce {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SexoFactory camisa = new HombreFactory();
        TipoRopa producto = new TipoRopa(camisa);
        producto.camisa("tarjeta");
        
        SexoFactory pantalon = new MujerFactory();
        TipoRopa producto1 = new TipoRopa(pantalon);
        producto.pantalon("transferencia");
        
    }
}
