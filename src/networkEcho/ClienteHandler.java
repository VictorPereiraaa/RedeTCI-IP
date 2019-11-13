package networkEcho;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteHandler extends Thread {
	private final Socket cliente;
	
	//Método construtor
	ClienteHandler(Socket clienteSocket){
		this.cliente = clienteSocket;
	}
	
	//Método que encerra a conexão do cliente
	private void encerraCliente() throws IOException {
		cliente.close();
	}
 
	//Método que trata conversação entre cliente e servidor (protocolo)
	private void trataClienteSocket() throws ClassNotFoundException, IOException {
		try {
			// streams de entrada e saída
			ObjectOutputStream output = new ObjectOutputStream(cliente.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());

			
			// recebe matriz de Cliente
			Matriz matrizRecebida = (Matriz) input.readObject();
			System.out.println("Mensagem recebida..." );
			Matriz.imprimeMatriz(matrizRecebida);
			
			//transpoem matriz
			Matriz transposta = new Matriz();
			transposta.geraMatriz();
			Matriz.transpoemMatriz(matrizRecebida, transposta);
			System.out.println("Transposta gerada: ");
			Matriz.imprimeMatriz(transposta);
			System.out.println();
			
			//devolve a matriz transposta para Cliente
			output.writeObject(transposta);
			output.flush(); 
			
			//encerra as streams
			input.close();
			output.close();
		
		}catch (IOException e){
			//erro na thread
			System.out.println("Erro na thread!");
			e.printStackTrace();
		}finally {
			encerraCliente();
		}
	}
	
	@Override
	public void run() {
		try {
			trataClienteSocket();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
}
