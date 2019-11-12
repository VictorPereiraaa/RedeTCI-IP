package networkEcho;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

	private static boolean isRunning = true; 
	private static Socket socket;
	private static ObjectOutputStream output;
	private static ObjectInputStream input;

	// Método que inicia a conexao cliente-servidor
	private static void iniciaConexao() throws IOException {
		socket = new Socket("localhost", Info.porta);
		System.out.println("Conectado a porta " + Info.porta + " com sucesso!");
	}

	// Método para criação das streams de entrada e saída
	private static void iniciaStreams() throws IOException {
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
	}

	// Método que encerra a conexao cliente-servidor
	private static void encerraConexao() throws IOException {
		input.close();
		output.close(); 
		socket.close();
	}

	public static void main(String[] args) throws ClassNotFoundException, InterruptedException, IOException {
		try {
			
			while (isRunning) {
				iniciaConexao();
				iniciaStreams();

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
			encerraConexao();

		} catch (IOException e) {
			System.out.println("Erro no cliente");
		}
	}

}
