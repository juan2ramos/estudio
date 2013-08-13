
package sisitemasopeativos;
public class LinuxFactory extends OSFactory {
    
    Ventana CreateVentana(){
        VentanaLinux ventana = new VentanaLinux();
        return ventana;
    }
    
    Boton CreateBoton() {
        BotonLinux boton = new BotonLinux();
        return boton;
    }
}