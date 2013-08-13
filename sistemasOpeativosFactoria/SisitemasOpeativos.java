    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sisitemasopeativos;

/**
 *
 * @author juan2ramos
 */
public class SisitemasOpeativos {

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) {
        OSFactory linux = new LinuxFactory();
        VistaOS vistaLinux = new VistaOS(linux);
        
        OSFactory mac = new MacFactory();
        VistaOS vistaMac = new VistaOS(mac);
        
        vistaLinux.ventana();
        vistaLinux.boton();
        
        vistaMac.ventana();
        vistaMac.boton();
    }
}
