/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package catalan;

/**
 *
 * @author juan2ramos
 */
public class Catalan {

    int num;

    public Catalan(int num) {
        this.num = num;
        numeroCatalan(num);
    }

    private void numeroCatalan(int numero) {
        long Catalan = 0;
        Catalan = factorial(2 * numero) / (factorial(numero + 1));
        System.out.println("el numero catalan de " + num + " es: " + Catalan);

    }

    private long factorial(int n1) {

        long[] filas = new long[n1];
        filas[0] = 1;
        filas[1] = 2;
        for (int i = 2; i < n1; i++) {
            filas[i] = (i + 1) * filas[i - 1];
        }

        return filas[n1 - 1];
    }

    public static void main(String[] args) {
        new Catalan(4);
    }
}
