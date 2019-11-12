package networkEcho;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	
	//Método que encerra o Server
	private void encerraServer(Socket socket) throws IOException {
		socket.close();
	}

	//Método que trata conversação entre cliente e servidor (protocolo)
	private void trataConexao(Socket socket) throws IOException, ClassNotFoundException {
		try {
			// streams de entrada e saída
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

			// recebe matriz de Cliente
			Matriz matrizRecebida = (Matriz) input.readObject();
			System.out.println("Mensagem recebida...");
			Matriz.imprimeMatriz(matrizRecebida);
		
			//transpoem matriz
			Matriz transposta = new Matriz();
			transposta.geraMatriz();
			Matriz.transpoemMatriz(matrizRecebida, transposta);
			System.out.println("Transposta gerada: ");
			Matriz.imprimeMatriz(transposta);
			
			//devolve a matriz transposta para Cliente
			output.writeObject(transposta);
			output.flush();
			
			// encerra as streams
			input.close();
			output.close();
		} catch (IOException e) {
			// problema na conexão com o cliente
			System.out.println("Problema no tratamento da conexão com o cliente: " + socket.getInetAddress());
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// final do tratamento do protocolo
			encerraServer(socket);
		}

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
				server.trataConexao(socket);
				System.out.println("Cliente finalizado.");
			}
		} catch (IOException e) {
			System.out.println("Erro no servidor: " + e.getMessage());
		}

	}

}
