/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplo;

import javax.swing.JOptionPane;

/**
 *
 * @author juan2ramos
 */
public class Multiplo {

    private static int max(int m, int n, int num) {
        int val = -1;
        if (m > n) {
            if (100 % m == 0) {
                val = m;
            }
        } else {
            if (100 % n == 0) {
                val = n;
            }
        }

        return val;
    }

    public static int maxDyV(int[] v, int inf, int sup, int num) {
        // System.out.println(inf + " " + sup);
        if (inf >= sup) {
            return v[inf];
        } else {
            int mitad = (sup + inf) / 2;
            int max1 = maxDyV(v, inf, mitad, num);
            int max2 = maxDyV(v, mitad + 1, sup, num);
            return max(max1, max2, num);
        }
    }

    public static void main(String[] args) {
        int num = Integer.parseInt(JOptionPane.showInputDialog("Digite un numero"));

        int[] v = new int[num];
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 100);
            v[i] = rand;


        }
        if (maxDyV(v, 0, v.length - 1, num) == -1) {
            System.out.println("No se encontro multiplo para "+num);
        } else {
            System.out.println("El mayor multiplo de "+num+" econtrado es  "+maxDyV(v, 0, v.length - 1, num));
        }
    }
}
