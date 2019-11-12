package networkEcho;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	private ServerSocket serverSocket; 

	//Método que inicia um novo Server
	private void criaServer(int porta) throws IOException {
		serverSocket = new ServerSocket(porta);
	}

	//Método que aceita a conexão
	private Socket esperaConexao() throws IOException {
		Socket socket = serverSocket.accept();
		return socket;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		try {
			Server server = new Server(); 
			System.out.println(Info.getNomeVersao());
			System.out.println("Aguardando conexão...");
			server.criaServer(Info.porta);
			while (true) {
				Socket socket = server.esperaConexao(); // protocolo
				System.out.println("Cliente conectado.");
				
				Thread clienteHandler = new ClienteHandler(socket);
				clienteHandler.start();
				
			}
		} catch (IOException e) {
			System.out.println("Erro no servidor: " + e.getMessage());
		}

	}

}
