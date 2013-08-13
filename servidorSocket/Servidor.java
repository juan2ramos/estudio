package servidor;

import java.io.*;
import java.net.*;

class Servidor {

    static final int PUERTO = 5000;

    public Servidor() {

        try {

            int a;

            ServerSocket skServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO);

            while (true) {

                Socket skCliente = skServidor.accept();

                InputStream stream_input = skCliente.getInputStream();
                DataInputStream data_input = new DataInputStream(stream_input);

                System.out.println("Sirvo al cliente: " + data_input.readUTF());

                a = data_input.readInt();

                OutputStream stream_output = skCliente.getOutputStream();
                DataOutputStream data_output = new DataOutputStream(stream_output);

                data_output.writeUTF(narcisistas(a));

                skCliente.close();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String narcisistas(int n) {
        
        
        int num = n/5;
        for(int i=0;i<5;i++){
            
           new Thread( new Write(num*i,num*(i+1))).start();
        }
        System.out.println(Write.getCadena());
 

        
        return Write.getCadena();

    }

    public static void main(String[] arg) {
        new Servidor();
    }
}