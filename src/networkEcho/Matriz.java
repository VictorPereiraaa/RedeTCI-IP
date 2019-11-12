package networkEcho;

import java.util.Random;

public class Matriz {

	private int[][] matriz;
	private static final Random randGen = new Random();

	public int[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(int[][] m) {
		this.matriz = m;
	}

	public void geraMatriz() {
		this.matriz = new int[Info.tamMax][Info.tamMax];

		for (int linha = 0; linha < Info.tamMax; linha++) {
			for (int coluna = 0; coluna < Info.tamMax; coluna++) {
				this.matriz[linha][coluna] = randGen.nextInt(50);
			}
		}
	}

	public void imprimeMatriz() {
		for (int linha = 0; linha < Info.tamMax; linha++) {
			for (int coluna = 0; coluna < Info.tamMax; coluna++) {
				System.out.print(this.matriz[linha][coluna] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public Matriz transpoemMatriz(Matriz matriz) {
		Matriz transposta = new Matriz();

		transposta.geraMatriz();
		for (int linha = 0; linha < Info.tamMax; linha++) {
			for (int coluna = 0; coluna < Info.tamMax; coluna++) {
				transposta.matriz[linha][coluna] = matriz.matriz[coluna][linha];
			}
		}
		transposta.imprimeMatriz();

		return transposta;
	}

	public static void main(String[] args) {

		Matriz m = new Matriz();
		Matriz transp = new Matriz();
		
		m.geraMatriz();
		m.imprimeMatriz();

		transp.transpoemMatriz(m);
		transp.imprimeMatriz();
	}

	
}
