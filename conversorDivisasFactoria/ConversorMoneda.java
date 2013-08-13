/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conversormoneda;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author juan2ramos
 */
public class ConversorMoneda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        boolean menu = true;
        int cont = 0;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);


        while (menu) {


            System.out.print("Digite el pais: ");
            String s1 = br.readLine();

            System.out.print("Digite  el dinero: ");
            double s2 = Double.parseDouble(br.readLine());

            MonedaFactory.crearMoneda(s1).calcularMoneda(s2);

            

            Moneda objeto = (Moneda) MonedaFactory.MonedaLista.get(cont);
            cont++;
            double r = objeto.getValor();
            System.out.println("El valor es de:" + r);
            
            
            System.out.print("Digite 1 para seguir calculando o 0 para salir ");
            Integer op = Integer.parseInt(br.readLine());
            if (op == 0) {
                menu = false;
            }


        }
        int tam = MonedaFactory.MonedaLista.size();
        System.out.println("las conversiones son:");
        for(int i=0;i<tam;i++){            
        Moneda objeto = (Moneda) MonedaFactory.MonedaLista.get(i);        
        double r = objeto.getValor();
        System.out.println("" + r);
            
        }

    }
}
