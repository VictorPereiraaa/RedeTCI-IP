package networkEcho;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Cliente {
	
	private static boolean isRunning = true;
	
	public static void main(String[] args) throws ClassNotFoundException, InterruptedException, IOException {
		try {

			// cria conexao entre o cliente e servidor
			Socket socket = new Socket("localhost", Info.porta);

			// criação dos streams de entrada e saída
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

			//while (isRunning) {
				System.out.println("Enviando mensagem...");
				
				//gera a mensagem para o cliente
				Matriz matriz = Matriz.geraMatriz();
				
				for(int linha = 0; linha < Info.tamMax; linha++) {
					for(int coluna = 0; coluna < Info.tamMax ; coluna++) {
						System.out.print(matriz.matriz[linha][coluna] + " ");
					}
					System.out.println();
				}
				
				output.writeObject(matriz);
				output.flush(); // libera buffer para envio
			
				System.out.println("Mensagem " + matriz + " enviada");
			
				//recebe a transposta do Server
				//JSONArray transposta = (JSONArray) input.readObject();
				//System.out.println("Resposta: " + transposta);
				
				//Thread.sleep(Info.delay);	
			//}
			//encerra as streams
			input.close();
			output.close();
			socket.close();

		} catch (IOException e) {
			System.out.println("Erro no cliente");
		}

	}
}
