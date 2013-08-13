/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maxim;

/**
 *
 * @author juan2ramos
 */
import java.util.Random;

public class Maxim extends Thread {

    private int y;
    private int x;
    private int r;

    public Maxim(int vec,int y) {
        this.x = vec;
        this.y = y;
    }

    public void run() {
       // r = maximo(x, 0, x.length - 1);
        r = (int) potencia(y,x);
        
    }

    public static int maximo(int[] x, int a, int b) {

        int m1;
        int m2;

        if (a < b) {
            m1 = maximo(x, a, (a + b) / 2);
            m2 = maximo(x, ((a + b) / 2) + 1, b);
            if (m1 > m2) {
                return m1;
            } else {
                return m2;
            }
        } else {
            return x[a];
        }

    }

    public static float potencia(int x, int n) {
        
            if (n == 1) { 
                return x;
            } else { 
                float num = potencia(x, n / 2);
                if (n % 2 == 0) { 
                    return num * num;
                } else {
                    return num * num * x;
                }
            }
        
    }

    public static void main(String args[]) {

        int[] vec = new int[20];
        Random random = new Random();
        for (int i = 0; i < vec.length; i++) {
            vec[i] = random.nextInt(1000);
        }

        for (int i = 0; i < vec.length; i++) {
            //System.out.print(vec[i] + " ");
        }
        //System.out.println();

        int[] subvec1 = new int[vec.length / 2];
        int[] subvec2;
        if (vec.length % 2 == 0) {
            subvec2 = new int[vec.length / 2];
        } else {
            subvec2 = new int[(vec.length / 2) + 1];
        }

        for (int i = 0; i < vec.length; i++) {
            if (i < vec.length / 2) {
                subvec1[i] = vec[i];
            } else {
                subvec2[vec.length - 1 - i] = vec[i];
            }
        }
        
        int f=0;
        int p= 5;
        int b = 2;
        Maxim t1 = new Maxim(p/2,b);
        Maxim t2 = new Maxim(p/2,b);

        t1.start();
        t2.start();

        while (t1.isAlive());
        while (t2.isAlive());
        if (p % 2 == 0) {
            f = t1.r*t2.r;  
        }           
        else{
           f = t1.r*t2.r*b; 
        }
            
        
        System.out.println("Max value:" + f);

    }
}