package networkEcho;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.json.simple.JSONArray;


public class Cliente {
	
	private static boolean isRunning = true;
	
	public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
		try {

			// cria conexao entre o cliente e servidor
			Socket socket = new Socket("localhost", Info.porta);

			// cria��o dos streams de entrada e sa�da
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

			while (isRunning) {
				System.out.println("Enviando mensagem...");
				
				//gera a mensagem para o cliente
				JSONArray matriz = Mensagem.geraMatriz();
				output.writeObject(matriz);
				output.flush(); // libera buffer para envio
			
				System.out.println("Mensagem " + matriz + " enviada");
			
				//recebe a transposta do Server
				JSONArray transposta = (JSONArray) input.readObject();
				System.out.println("Resposta: " + transposta);
				
				Thread.sleep(Info.delay);	
			}
			//encerra as streams
			input.close();
			output.close();
			socket.close();

		} catch (IOException e) {
			System.out.println("Erro no cliente");
		}

	}
}
