/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryclient;

/**
 *
 * @author juan2ramos
 */
public class DogFactory {

    public static Dog createDog(String Criteria) {
        if (Criteria.equals("small")) {
            return new Poodle();
        } else if (Criteria.equals("working")) {
            return new Siberian();
        }
        return null;
    }
}
