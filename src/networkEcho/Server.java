package networkEcho;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	private ServerSocket serverSocket;

	//M�todo que inicia um novo Server
	private void criaServer(int porta) throws IOException {
		serverSocket = new ServerSocket(porta);
	}

	//M�todo que aceita a conex�o
	private Socket esperaConexao() throws IOException {
		Socket socket = serverSocket.accept();
		return socket;
	}
	
	//M�todo que encerra o Server
	private void encerraServer(Socket socket) throws IOException {
		socket.close();
	}

	//M�todo que trata conversa��o entre cliente e servidor (protocolo)
	private void trataConexao(Socket socket) throws IOException, ClassNotFoundException {
		try {
			// streams de entrada e sa�da
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
			// problema na conex�o com o cliente
			System.out.println("Problema no tratamento da conex�o com o cliente: " + socket.getInetAddress());
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
			System.out.println("Aguardando conex�o...");
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
