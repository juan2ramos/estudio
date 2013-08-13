/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mochila;

/**
 *
 * @author juan2ramos
 */
public class Mochila {

    int[] pesos;
    int[] beneficios;
    int capacidad;

    public Mochila(int[] pesos, int[] beneficios, int capacidad) {
        this.pesos = pesos;
        this.beneficios = beneficios;
        this.capacidad = capacidad;
        mo();
        
    }

    private void mo() {

        
        int[][] matriz_mochila = new int[pesos.length + 1][capacidad + 1];
        
        for (int i = 0; i <= capacidad; i++) {
            matriz_mochila[0][i] = 0;
        }        
        for (int i = 0; i <= pesos.length; i++) {
            matriz_mochila[i][0] = 0;
        }
        for (int j = 1; j <= pesos.length; j++) {
            for (int c = 1; c <= capacidad; c++) {
                if (c < pesos[j - 1]) {
                    matriz_mochila[j][c] = matriz_mochila[j - 1][c];
                } else {
                    if (matriz_mochila[j - 1][c] > matriz_mochila[j - 1][c - pesos[j - 1]] + beneficios[j - 1]) {
                        matriz_mochila[j][c] = matriz_mochila[j - 1][c];
                    } else {
                        matriz_mochila[j][c] = matriz_mochila[j - 1][c - pesos[j - 1]] + beneficios[j - 1];
                    }
                }
            }
        }       
        
        for (int i = 0; i < pesos.length; i++) {
            for (int j = 0; j < capacidad; j++) {

                System.out.print(matriz_mochila[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int[] pesos = {5,3,5,1,2};
        int[] beneficios = {3,5,2,8,3};
        int capacidad = 10;
        Mochila mochila = new Mochila(pesos, beneficios, capacidad);

    }
}
