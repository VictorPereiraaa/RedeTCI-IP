package networkEcho;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.simple.JSONObject;

public class Server {

	private ServerSocket serverSocket;

	//inicia um novo servidor
	private void criarServerSocket(int porta) throws IOException {
		serverSocket = new ServerSocket(porta);
	}

	//aceita a conexão
	private Socket esperaConexao() throws IOException {
		Socket socket = serverSocket.accept();
		return socket;
	}

	//faz o tratamento do protocolo da aplicação
	private void trataConexao(Socket socket) throws IOException, ClassNotFoundException {
		
		try {

			// streams de entrada e saída
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			
			// tratando conversação entra cliente e servidor (protocolo)
			JSONObject msgRecebida = (JSONObject) input.readObject();
			System.out.println("Mensagem recebida..." + msgRecebida.toString());
			
			//fazer a transposta do obj recebido
			//
			//
			
			output.writeObject("HELLO WORLD!");
			output.flush(); // marcar o fim da msg

			//fecha as streams
			input.close();
			output.close();
		} catch (IOException e) {
			//problema na conexão com o cliente
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
