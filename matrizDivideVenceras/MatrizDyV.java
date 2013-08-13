    /*
     * To change this template, choose Tools | Templates
     * and open the template in the editor.
     */
    package matrizdyv;

    /**
     *
     * @author juan2ramos
     */
    public class MatrizDyV {

        /**
         * @param args the command line arguments
         */
        private static int max(int max1, int max2) {
            return ((max1>max2)?max1:max2);
        }

        public static int maxDyV(int[][] v, int inf, int sup) {
            // System.out.println(inf + " " + sup);
            if (inf >= sup) {
                int sum = 0;
                int sum1 = 0;
                for (int i = 0; i < 5; i++) {
                    sum = v[i][sup] + sum;

                }

                return sum;
            } else {
                int mitad = (sup + inf) / 2;
                int max1 = maxDyV(v, inf, mitad);
                int max2 = maxDyV(v, mitad + 1, sup);


                return max(max1, max2);
            }
        }

        public static void main(String[] args) {
            int[][] m = new int[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    int rand = (int) (Math.random() * 100);
                    m[i][j] = rand;

                    System.out.print(m[i][j] + "     ");
                }
                System.out.println();
            }

            System.out.println("El numero mayor de la suma de las columnas es: "+maxDyV(m, 0, m.length - 1));
        }
    }
