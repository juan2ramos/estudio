/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ecommerce;

import sun.font.TrueTypeFont;

/**
 *
 * @author juan2ramos
 */
public class EntidadBancaria extends Thread {

    boolean msj ;
    private String numeroTarjeta;
    private String Cedula;
    
    public EntidadBancaria() {
        
        
    }
    public void setNumeroTarjeta(String numeroTarjeta){
        this.numeroTarjeta = numeroTarjeta;
    }
    
    public void setCedula(String cedula){
        this.Cedula = cedula;
    }
    public void run (){
        this.numeroTarjeta = numeroTarjeta;
        this.Cedula = Cedula;
        msj = conexionEntidad();
    }
    private boolean conexionEntidad(){
        //se envia a la entidad numeroTarjeta y cedula
        return true;
    }
    
}
