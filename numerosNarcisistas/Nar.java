/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nar;

/**
 *
 * @author juan2ramos
 */
public class Nar {

    /**
     * @param args the command line arguments
     */
    public  void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            boolean narcissist = isNarcissist(i);
            if (narcissist) {
                System.out.print(" "+i);
            }
        }
    }

    public  boolean isNarcissist(int x) {
        int aux = x;    
        int n = 0;       
        while (aux != 0) {
            n++;
            aux /= 10;
        }

        aux = x;
        int sum = 0;

//eleva digito a digito à potencia n somando todos estes
        while (aux != 0) {
            int d = aux % 10;
            sum += Math.pow(d, n);

            aux /= 10;
        }


        return sum == x;
    }
}
