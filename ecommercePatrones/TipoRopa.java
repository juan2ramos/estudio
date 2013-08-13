/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

/**
 *
 * @author juan2ramos
 */
public class TipoRopa {

    private Camisa sexoCamisa;
    private Pantalon sexoPantalon;

    public TipoRopa(SexoFactory factoria) {

        sexoCamisa = factoria.crearCamisa();
        sexoPantalon = factoria.crearPantalon();

    }

    public void pantalon(String tipoPago) {
        String mej = sexoPantalon.comprar(tipoPago);
        System.out.println(mej);
        
    }

    public void camisa(String tipoPago) {
        String mej = sexoCamisa.comprar(tipoPago);
        System.out.println(mej);
       
    }
}
