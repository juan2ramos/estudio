/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonaccidinamico;

import javax.swing.JOptionPane;

/**
 *
 * @author juan2ramos
 */
public class FibonacciDinamico {

    /**
     * @param args the command line arguments
     */
        public static void main(String[] args) {

            int x = Integer.parseInt(JOptionPane.showInputDialog("Digite un numero"));
            int[] fibo = new int[x];
            fibo[0] = 0;
            fibo[1] = 1;
            for (int i = 2; i < x; i++) {
                fibo[i] = fibo[i-1] + fibo[i-2]; 
            }
            for (int i = 0; i < x; i++) {
                System.out.print(fibo[i]+" ");
            }

        }
}
