package networkEcho;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Mensagem {

	private static final Random randGen = new Random();

	//gera uma matriz com valores aleatórios 
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
	
	//transpoem a matriz passada por parâmetro
	@SuppressWarnings("unchecked")
	public static JSONArray geraTransposta(JSONArray mensagem) {
		JSONArray array = new JSONArray();
		int valor[] = new int[20];
		int i = 0;
		
		//pega cada valor da mensagem passada como paramentro e joga num vetor de inteiros
		for(Object o: mensagem) {
			if(o instanceof JSONObject) {
				valor[i++] =  (int)((JSONObject) o).get("Valor");
			}
		}
		i=0;
		
		//transapoem a matriz recebida
		for(int coluna = 0; coluna < Info.tamMax; coluna++) {
			for(int linha = 0; linha < Info.tamMax; linha++) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("X", linha);
				params.put("Y", coluna);
				params.put("Valor", valor[i++]);
				
				JSONObject json = new JSONObject(params);
				array.add(json);
			}
		}
		return array;
	}

	// main só para testar os metodos
	public static void main(String[] args) {

		JSONArray json = Mensagem.geraMatriz();
		
		JSONArray transposta = Mensagem.geraTransposta(json);
		
		System.out.println(json.toString());
		System.out.println(transposta.toString());

	}
}