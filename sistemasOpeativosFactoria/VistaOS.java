package sisitemasopeativos;
import java.util.Vector;

public class VistaOS {

  private Ventana SOVentana;
  private Boton SOBoton;




  public VistaOS(OSFactory factory) {
      
     SOVentana = factory.CreateVentana();
     SOBoton = factory.CreateBoton();
  }

  public void ventana(){
      String mej = SOVentana.crearVentana();
      System.out.println(mej);
  }
  public void boton(){
      String mej = SOBoton.crearBoton();
      System.out.println(mej);
  }

}