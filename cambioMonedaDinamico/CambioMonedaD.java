/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cambiomonedad;

/**
 *
 * @author juan2ramos
 */
public class CambioMonedaD {

    public static void main(String[] args) {

        int tabla[][] = new int[4][9];
        int VM[] = {1, 2, 4, 5};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {

                if (j == 0) {
                    tabla[i][j] = 0;
                } else {
                    if (i == 0) {
                        tabla[i][j] = j;
                    } else {
                        if (VM[i] > j) {
                            //System.out.println(VM[i] + ">" + j);
                            tabla[i][j] = tabla[i - 1][j];
                        } else {
                            
                            
                            if (tabla[i - 1][j]  < tabla[i][j - VM[i]] + 1) {
                                tabla[i][j] = tabla[i - 1][j] ;
                            } else {
                                tabla[i][j] = tabla[i][j - VM[i]] + 1;
                            }
                        }

                    }
                }

            }
        }




        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 9; j++) {

                System.out.print(tabla[i][j]);
            }
            System.out.println();
        }
    }
}
