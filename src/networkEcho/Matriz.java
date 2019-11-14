package networkEcho;

import java.io.Serializable;
import java.util.Random;

public class Matriz implements Serializable {

	private static final long serialVersionUID = 1L;
	private int[][] matriz;
	private static final Random randGen = new Random();
	private int altura;
	private int largura;

	public Matriz(int altura, int largura) {
		this.altura = altura;
		this.largura = largura;
		this.matriz = new int[this.altura][this.largura];		
	}
	
	public void populaMatiz() {
		for (int linha = 0; linha < altura; linha++) {
			for (int coluna = 0; coluna < largura; coluna++) {
				this.matriz[linha][coluna] = randGen.nextInt(50);
			}
		}
	}
	
	public boolean verificaParametros() {
		if(altura < 0 ) {
			System.out.println("altura informada menor do que zero");
		} else if (largura < 0 ) {
			System.out.print("largura informada é menor do que zero");
		}
		
		return false;
	}
	
	public void imprimeMatriz(){
		
		for (int linha = 0; linha < altura; linha++) {
			for (int coluna = 0; coluna < largura; coluna++) {
				System.out.print(matriz[linha][coluna] + " ");
			}
			System.out.print("\n");
		}
	}

	public Matriz transpoemMatriz() {
		Matriz transposta = new Matriz(largura, altura);
		for (int linha = 0; linha < altura; linha++) {
			for (int coluna = 0; coluna < largura; coluna++) {
				transposta.matriz[linha][coluna] = matriz[coluna][linha];
			}
		}
		return transposta;
	}

}
