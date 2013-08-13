/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mochilan;

/**
 *
 * @author juan2ramos
 */
public class MochilaN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        int[] pesos = {5, 3, 5, 1, 2};
        int[] beneficios = {3, 5, 2, 8, 3};
        int capacidad = 10;

        int[][] matriz_mochila = new int[pesos.length + 1][capacidad + 1];

       

        for (int i = 0; i <= pesos.length; i++) {
            for (int j = 1; j <= capacidad; j++) {

                if (i == 0) {
                    matriz_mochila[i][j] = 0;
                } else {
                    if (j== 0) {
                        matriz_mochila[i][j] = 0;
                    } else {

                        if (j < pesos[i - 1]) {
                            matriz_mochila[i][j] = matriz_mochila[i - 1][j];
                        } else {
                            if (matriz_mochila[i - 1][j] > matriz_mochila[i - 1][j - pesos[i - 1]] + beneficios[i - 1]) {
                                matriz_mochila[i][j] = matriz_mochila[i - 1][j];
                            } else {
                                matriz_mochila[i][j] = matriz_mochila[i - 1][j - pesos[i - 1]] + beneficios[i - 1];
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < pesos.length; i++) {
            for (int j = 0; j < capacidad; j++) {

                System.out.print(matriz_mochila[i][j] + " ");
            }
            System.out.println();
        }

    }
}
