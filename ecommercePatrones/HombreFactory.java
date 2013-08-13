/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

/**
 *
 * @author juan2ramos
 */
public class HombreFactory extends SexoFactory{
    
    Pantalon crearPantalon(){
        Pantalon pantalon = new PantalonHombre();
        return pantalon;
    }
    Camisa crearCamisa(){
        Camisa camisa = new CamisaMujer();
        return camisa;
    }
    
}
