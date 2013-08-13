/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author juan2ramos
 */
public class Write implements Runnable{

    private int tiempoInactividad;
    private int menor;
    private int mayor;
    private static String cadena;

    public Write( int menorW, int mayorW) {
        
        menor = menorW;
        mayor = mayorW;
        tiempoInactividad = (int) (Math.random() * 10000);
    }
    public static String getCadena(){
        return cadena;
    }

   public synchronized  void run() {
        
        
        for (int i = menor; i < mayor; i++) {
            boolean narcissist = isNarcissist(i);
            if (cadena == null){
                cadena = "";
            }
            if (narcissist) {
                cadena += " "+i;
                System.out.print(" " + i);
            }
        }
    }
     public  boolean isNarcissist(int x) {
        int aux = x;    
        int n = 0;       
        while (aux != 0) {
            n++;
            aux /= 10;
        }

        aux = x;
        int sum = 0;
    while (aux != 0) {
            int d = aux % 10;
            sum += Math.pow(d, n);

            aux /= 10;
        }


        return sum == x;
    }
}
