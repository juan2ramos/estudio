
package Abstracfactory;

public class WindowsFactory extends OSFactory {
    
    Ventana CreateVentana(){
        VentanaWindows ventana = new VentanaWindows();
        return ventana;
    }
    
    Boton CreateBoton() {
        BotonWindows boton = new BotonWindows();
        return boton;
    }
    
}