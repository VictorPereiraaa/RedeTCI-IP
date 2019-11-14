package networkEcho;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;


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
			Scanner scanner = new Scanner(System.in);
			System.out.println("digite a altura da matriz");
		    String strAltura = scanner.nextLine();
			System.out.println("digite a largura da matriz");
		    String strLargura = scanner.nextLine();
		    scanner.close();

		    
			while (isRunning) {
				if (randGen.nextInt(20) == 0) {
					System.out.println("Encerrando cliente...");
					isRunning = false;
				} else {
					iniciaConexao();
					
					// gera a matriz para o cliente
					int altura = Integer.parseInt(strAltura);
					int largura = Integer.parseInt(strLargura);
					Matriz matriz = new Matriz(altura,largura);
					matriz.populaMatiz();

					// enviando matriz
					System.out.println("Enviando a matriz...");
					matriz.imprimeMatriz();
					output.writeObject(matriz);
					output.flush(); // libera buffer para envio
					System.out.println("Matriz enviada.");

					// recebe a matriz transposta do servidor
					System.out.println("Matriz recebida...");
					matriz = (Matriz) input.readObject();
					matriz.imprimeMatriz();
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
