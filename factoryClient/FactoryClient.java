    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryclient;

/**
 *
 * @author juan2ramos
 */
public class FactoryClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String Dog = DogFactory.createDog("small").pedigree();
        //String Dog = DogFactory.createDog("working").pedigree();
        System.out.print(Dog);
    }
}
            