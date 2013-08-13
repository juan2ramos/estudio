/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenamiento;

import javax.swing.JOptionPane;

/**
 *
 * @author juan2ramos
 */
public class Ordenamiento {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] vector;
        int tamano, i, r;
        tamano = Integer.parseInt(JOptionPane.showInputDialog("Tamaño del vector"));
        vector = new int[tamano];

        for (i = 0; i < tamano; i++) {
            vector[i] = (int) (Math.random() * 1000);
        }


        QuickSort quickSort = new QuickSort();
        HeapSort heapSort = new HeapSort();
        
        long inicioH = System.currentTimeMillis();
        HeapSort.ordenacionMonticulos(vector);
        long finalH = System.currentTimeMillis();
        
        long inicioQ = System.currentTimeMillis();
        
        quickSort.quicksort(vector,0, vector.length -1);
        long finalQ = System.currentTimeMillis();

        System.out.println("QuickSort " + tamano + " elementos con tiempo " + (finalQ - inicioQ) + " milis");
        System.out.println("HeapSort " + tamano + " elementos con tiempo " + (finalH - inicioH) + " milis");

    }
}
