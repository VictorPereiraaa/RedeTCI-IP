package networkEcho;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class Mensagem {

	private static final Random randGen = new Random();

	@SuppressWarnings("unchecked")
	public static JSONArray geraMatriz() {
		
		JSONArray array = new JSONArray();
		
		for (int linha = 0; linha < Info.tamMax; linha++) {
			for (int coluna = 0; coluna < Info.tamMax; coluna++) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("X", linha);
				params.put("Y", coluna);
				params.put("Valor", randGen.nextInt(50));
				
				JSONObject json = new JSONObject(params);
				
				array.add(json);
			}
		}

	
		return array;
	}

	// main só para testar os metodos
	public static void main(String[] args) {

		JSONArray json = Mensagem.geraMatriz();

		System.out.println(json.toString());
	}
}