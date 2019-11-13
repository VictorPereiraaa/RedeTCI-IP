package networkEcho;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class Cliente {

	private static boolean isRunning = true;
	private static Socket socket;
	private static ObjectOutputStream output;
	private static ObjectInputStream input;

	// Método que inicia a conexao cliente-servidor
	private static void iniciaConexao() throws IOException {
		socket = new Socket("localhost", Info.porta);
		System.out.println("Conectado a porta " + Info.porta + " com sucesso!");
		
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
	}
	
	// Método que encerra a conexao cliente-servidor
	private static void encerraConexao() throws IOException {
		input.close();
		output.close();
		socket.close();
	}

	public Cliente() throws ClassNotFoundException, InterruptedException {
		try {
			Random randGen = new Random();
			
			while (isRunning) {
				if (randGen.nextInt(20) == 0) {
					System.out.println("Encerrando cliente...");
					isRunning = false;
				} else {
					iniciaConexao();
					
					// gera a matriz para o cliente
					Matriz matriz = new Matriz();
					matriz.geraMatriz();

					// enviando matriz
					System.out.println("Enviando a matriz...");
					Matriz.imprimeMatriz(matriz);
					output.writeObject(matriz);
					output.flush(); // libera buffer para envio
					System.out.println("Matriz enviada.");

					// recebe a matriz transposta do servidor
					System.out.println("Matriz recebida...");
					matriz = (Matriz) input.readObject();
					Matriz.imprimeMatriz(matriz);
					System.out.println();

					Thread.sleep(Info.delay);
				}
			}
			encerraConexao();
		} catch (IOException e) {
			System.out.println("Erro no cliente");
		}
	}

}
