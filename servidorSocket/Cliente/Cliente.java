package servidor.Cliente;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

class Cliente {

	static final String HOST = "localhost";
	static final int PUERTO = 5000;
	
	public Cliente () {
	
		try {
			
			int x;
		 
			x=Integer.parseInt(JOptionPane.showInputDialog("Digite el limite para calcular los numeros narcisisitas"));
			if (x>0) {
				
				Socket skCliente = new Socket (HOST,PUERTO);

				OutputStream stream_output = skCliente.getOutputStream();
				DataOutputStream data_output = new DataOutputStream(stream_output);
				
				data_output.writeUTF(String.valueOf(skCliente.getLocalAddress())+":"+String.valueOf(skCliente.getPort()));
				
				data_output.writeInt(x);
				 
				InputStream stream_input = skCliente.getInputStream();
				DataInputStream data_input = new DataInputStream(stream_input);
			 
				JOptionPane.showMessageDialog(null,"los numeros son "+data_input.readUTF());	
			
				skCliente.close();
				
			} else {
				
				JOptionPane.showMessageDialog(null,"El numero digitado no es mayor o igual a cero");
				
			}
			
		}	
		catch ( Exception e ) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String [] arg){
		new Cliente();
	}
}