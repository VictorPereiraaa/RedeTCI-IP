package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {
		try {
			
			//cria conexao entre o cliente e servidor
			Socket socket = new Socket("localhost", 5555);

			//criação dos streams de entrada e saída
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			
			System.out.println("Enviando mensagem...");
			String msg = "Hello";
			output.writeUTF(msg);
			output.flush();	//libera buffer para envio
			
			System.out.println("Mensagem " + msg + " enviada.");
		
			msg = input.readUTF();
			System.out.println("Resposta: " + msg);
			
			input.close();
			output.close();
			socket.close();
		
		} catch (IOException e) {
			System.out.println("Erro no cliente");
		}
		
		}
}
