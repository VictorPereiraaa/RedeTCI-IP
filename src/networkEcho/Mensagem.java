package networkEcho;

import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONObject;

public class Mensagem {

	private static final Random randGen = new Random();

	
	public static JSONObject geraMatriz() {
		HashMap<String, Object> params = new HashMap<String, Object>();	
		
		
		for (int linha = 0; linha < Info.tamMax; linha++) {
			for (int coluna = 0; coluna < Info.tamMax; coluna++) {
				params.put("X", linha);
				params.put("Y", coluna);
				params.put("Valor", randGen.nextInt(50));
			}
		}
		
		
		JSONObject json = new JSONObject(params);
		
		return json;
	}

	

	// main só para testar os metodos
	public static void main(String[] args) {

		JSONObject json = Mensagem.geraMatriz();

		System.out.println(json.toString());
	}
}