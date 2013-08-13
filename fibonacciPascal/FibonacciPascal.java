/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fibonaccipascal;

import java.util.Arrays;

/**
 *
 * @author juan2ramos
 */
public class FibonacciPascal {

    private int[][] filas;

    public FibonacciPascal(int n) {
        filas = new int[n][];
        for (int i = 1; i <= n; i++) {
            filas[i - 1] = new int[i];
        }
        ejecutar(n);

    }

    private void ejecutar(int n) {
        for (int i = 0; i < filas.length; i++) {
            for (int j = 0; j < filas[i].length; j++) {
                if (j == 0 || j == filas[i].length - 1) {
                    filas[i][j] = 1;
                } else {
                    filas[i][j] = (filas[i - 1][j] + filas[i - 1][j - 1]);
                }
            }
        }

        String out = "";

        for (int fila = 0; fila < filas.length; fila++) {
            for (int espacios = filas.length - filas[fila].length; espacios >= 0; espacios--) {
                out += " ";
            }
            out += Arrays.toString(filas[fila]) + "\n";
        }
        System.out.println(out);
        int h = 0;
        int acum = 0;

        for (int i = n - 1; i >= 2; i--) {

            h = i / 2 + 1;

            int cont = 0;
            int dism = i;
            for (int j = 0; j < h; j++) {

                cont = cont + filas[dism][j];
                dism--;
            }
            acum = acum + cont;
        }
        int fibo = acum + 2;
        System.out.println("Fibonacci  de " + n + " es: " + fibo);
    }


    public static void main(String[] args) {
        new FibonacciPascal(10);
    }
}
