package Abstracfactory;

import java.util.Vector;

public class VistaOS {

  private Ventana SOVentana;
  private Boton SOBoton;




  public VistaOS(OSFactory factory) {
      
     SOVentana = factory.CreateVentana();
     String mej = SOVentana.crearVentana();
     System.out.print(mej);
  }

  public void ventana(){
      String mej = SOVentana.crearVentana();
      System.out.print(mej);
  }

}