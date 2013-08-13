package Abstracfactory;
public class MacFactory extends OSFactory {
    
    Ventana CreateVentana(){
        VentanaMac ventana = new VentanaMac();
        return ventana;
    }
    
    Boton CreateBoton() {
        BotonMac boton = new BotonMac();
        return boton;
    }
}