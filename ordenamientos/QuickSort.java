/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenamiento;

/**
 *
 * @author juan2ramos
 */
public class QuickSort {

   

    public int[] quicksort(int numeros[], int izq, int der) {
        if (izq >= der) {
            return numeros;
        }
        int i = izq, d = der;
        if (izq != der) {
            int pivote;
            int aux;
            pivote = izq;
            while (izq != der) {
                //imprimeArreglo();

                while (numeros[der] >= numeros[pivote] && izq < der) {
                    der--;
                }
                while (numeros[izq] < numeros[pivote] && izq < der) {
                    izq++;
                }

                if (der != izq) {
                    aux = numeros[der];
                    numeros[der] = numeros[izq];
                    numeros[izq] = aux;
                }
            }
            if (izq == der) {
                quicksort(numeros, i, izq - 1);
                quicksort(numeros, izq + 1, d);
            }
        } else {
            return numeros;
        }
        return numeros;
    }

   
}
