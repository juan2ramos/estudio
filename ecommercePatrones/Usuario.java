/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

/**
 *
 * @author juan2ramos
 */
public class Usuario extends Thread {
    
     String numeroTarjeta;
     String cedula;
     public void run() {
       
        //trae los datos de la base de datos del usuario
         numeroTarjeta = "111";
         cedula = "80";
    }
}
