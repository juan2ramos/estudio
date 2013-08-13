/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pila;

/**
 *
 * @author juan2ramos
 */
public class Pila {
	
    class Nodo {
        int info;
        Nodo sig;
    }
	
    private Nodo raiz;
    
    public Pila () {
        raiz=null;
    }
    
    public void insertar(int x) {
    	Nodo nuevo;
        nuevo = new Nodo();
        nuevo.info = x;
        if (raiz==null)
        {
            nuevo.sig = null;
            raiz = nuevo;
        }
        else
        {
            nuevo.sig = raiz;
            raiz = nuevo;
        }
    }
    
    public int extraer ()
    {
        if (raiz!=null)
        {
            int informacion = raiz.info;
            raiz = raiz.sig;
            return informacion;
        }
        else
        {
            return Integer.MAX_VALUE;
        }
    }
    
    public void imprimir() {
        Nodo reco=raiz;
        System.out.println("Listado de todos los elementos de la pila.");
        while (reco!=null) {
            System.out.print(reco.info+"-");
            reco=reco.sig;
        }
        System.out.println();
    }
    
    public static void main(String[] ar) {
        Pila pila1=new Pila();
        pila1.insertar(10);
        pila1.insertar(40);
        pila1.insertar(3);
        pila1.imprimir();
        System.out.println("Extraemos de la pila:"+pila1.extraer());
        pila1.imprimir();        
    }
}
