package networkEcho;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONArray;


public class Server {

	private ServerSocket serverSocket;

	// inicia um novo servidor
	private void criarServerSocket(int porta) throws IOException {
		serverSocket = new ServerSocket(porta);
	}

	// aceita a conexão
	private Socket esperaConexao() throws IOException {
		Socket socket = serverSocket.accept();
		return socket;
	}

	// tratando conversação entre cliente e servidor (protocolo)
	private void trataConexao(Socket socket) throws IOException, ClassNotFoundException {
		try {
			// streams de entrada e saída
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

			// recebe objeto do Cliente
			JSONArray msgRecebida = (JSONArray) input.readObject();

			System.out.println("Mensagem recebida...");
			System.out.println(msgRecebida);

			// faz a transposta da matriz recebida
			JSONArray transposta = new JSONArray();
			transposta = Mensagem.geraTransposta(msgRecebida);
			
			System.out.println("Mensagem gerada...");
			System.out.println(transposta);
			
			//retorna a matriz transposta
			output.writeObject(transposta);
			output.flush(); // marcar o fim da msg

			// encerra as streams
			input.close();
			output.close();
		} catch (IOException e) {
			// problema na conexão com o cliente
			System.out.println("Problema no tratamento da conexão com o cliente: " + socket.getInetAddress());
			System.out.println("Erro: " + e.getMessage());
		} finally {
			// final do tratamento do protocolo
			socket.close();
		}

	}

	public static void main(String[] args) throws ClassNotFoundException {
		try {
			Server server = new Server();
			System.out.println(Info.getNomeVersao());
			System.out.println("Aguardando conexão...");
			server.criarServerSocket(Info.porta);
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
