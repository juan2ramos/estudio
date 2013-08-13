/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

/**
 *
 * @author juan2ramos
 */
public class Facade {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FacadePC facade = new FacadePC();
        facade.EncenderPc();
    }
}
