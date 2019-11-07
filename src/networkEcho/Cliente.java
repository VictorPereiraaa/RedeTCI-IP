package networkEcho;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.json.simple.JSONObject;

public class Cliente {
	

	private static boolean isRunning = true;
	
	public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
		try {

			// cria conexao entre o cliente e servidor
			Socket socket = new Socket("localhost", 5555);

			// criação dos streams de entrada e saída
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

			while (isRunning) {
				System.out.println("Enviando mensagem...");
				
				//gera a mensagem para o cliente
				JSONObject matriz = Mensagem.geraMatriz();
				output.writeObject(matriz);
				output.flush(); // libera buffer para envio
				System.out.println("Mensagem " + matriz + " enviada.");
				
				//fazer o tratamento da matriz transposta recebida
				//
				//
				
				String obj =  (String) input.readObject();
				System.out.println("Resposta: " + obj);
				
				Thread.sleep(Info.delay);	
			}
			input.close();
			output.close();
			socket.close();

		} catch (IOException e) {
			System.out.println("Erro no cliente");
		}

	}
}
