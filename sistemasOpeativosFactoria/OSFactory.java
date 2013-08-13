package sisitemasopeativos;
import java.util.Vector;

public abstract class OSFactory {

    public Vector myVistaOS;

    abstract Ventana CreateVentana();

    abstract Boton CreateBoton();
}