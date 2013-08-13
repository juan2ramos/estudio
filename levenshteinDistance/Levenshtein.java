/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package levenshtein;

/**
 *
 * @author juan2ramos
 */
public class Levenshtein {

    CharSequence str1; 
    CharSequence str2;
    public Levenshtein(CharSequence str1, CharSequence str2) {
        this.str1 = str1;
        this.str2 = str2;
        ejecutar();
    }

    private static int minimo(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    private  void ejecutar() {
        int[][] distancia = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++) {
            distancia[i][0] = i;
        }
        for (int j = 1; j <= str2.length(); j++) {
            distancia[0][j] = j;
        }

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                distancia[i][j] = minimo(
                        distancia[i - 1][j] + 1,
                        distancia[i][j - 1] + 1,
                        distancia[i - 1][j - 1]
                        + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0
                        : 1));
            }
        }
        System.out.println("la distancia es: " + distancia[str1.length()][str2.length()]);
        
    }

    public static void main(String[] args) {
        new Levenshtein("juan", "juaafasgvasdvnss");
        
    }
}
