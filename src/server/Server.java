package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket;

	private void criarServerSocket(int porta) throws IOException {
		serverSocket = new ServerSocket(porta);
	}

	private Socket esperaConexao() throws IOException {
		Socket socket = serverSocket.accept();
		return socket;
	}

	private void trataConexao(Socket socket) throws IOException {

		// protocolo da aplicação
		try {

			// streams de entrada e saída
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

			// tratando conversação entra cliente e servidor (protocolo)
			String msg = input.readUTF();
			System.out.println("Mensagem recebida..." + msg);
			output.writeUTF("HELLO WORLD!");
			output.flush(); // marcar o fim da msg

			input.close();
			output.close();

		} catch (IOException e) {
			System.out.println("Problema no tratamento da conexão com o cliente: " + socket.getInetAddress());
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// final do tratamento do protocolo
			socket.close();
		}

	}

	public static void main(String[] args) {

		try {
			
			Server server = new Server();
			System.out.println("Aguardando conexão...");
			server.criarServerSocket(5555);
			while (true) {
				Socket socket = server.esperaConexao(); //protocolo
				System.out.println("Cliente conectado.");
				server.trataConexao(socket);
				System.out.println("Cliente finalizado.");
			}
		} catch (IOException e) {
			System.out.println("Erro no servidor: " + e.getMessage());
		}

	}

}
