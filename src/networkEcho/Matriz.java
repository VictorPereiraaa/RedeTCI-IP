package networkEcho;

import java.io.Serializable;
import java.util.Random;

public class Matriz implements Serializable {

	private static final long serialVersionUID = 1L;
	private int[][] matriz;
	private static final Random randGen = new Random();

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

	public static void imprimeMatriz(Matriz mat){

		for (int linha = 0; linha < Info.tamMax; linha++) {
			for (int coluna = 0; coluna < Info.tamMax; coluna++) {
				System.out.print(mat.matriz[linha][coluna] + " ");
			}
			System.out.println();
		}
	}

	public static void transpoemMatriz(Matriz matriz, Matriz transposta) {

		for (int linha = 0; linha < Info.tamMax; linha++) {
			for (int coluna = 0; coluna < Info.tamMax; coluna++) {
				transposta.matriz[linha][coluna] = matriz.matriz[coluna][linha];
			}
		}
	}

}
