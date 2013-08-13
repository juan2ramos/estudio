/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Abstracfactory;

/**
 *
 * @author juan2ramos
 */
public class Abstracfactory {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         System.out.print("gggg");
        OSFactory linux = new LinuxFactory();
        VistaOS vista = new VistaOS(linux);
        vista.ventana();

    }
}
